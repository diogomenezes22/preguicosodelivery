package com.preguicoso.server.editar;

import javax.persistence.NoResultException;

import org.lavieri.modelutil.cep.WebServiceCep;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.editar.EditarService;
import com.preguicoso.server.dao.BairroDAO;
import com.preguicoso.server.dao.CidadeDAO;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.entities.Bairro;
import com.preguicoso.server.entities.Cidade;
import com.preguicoso.server.entities.Endereco;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.shared.entities.EstabelecimentoBean;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EditarServiceImpl extends RemoteServiceServlet implements
		EditarService {

	@SuppressWarnings("null")
	@Override
	public Boolean editarEstabelecimento(EstabelecimentoBean bean) {
		Estabelecimento e = (new EstabelecimentoDAO()).retrieve(bean.getId());

		if (e == null) {
			return false;
		}

		WebServiceCep wsc = WebServiceCep.searchCep(bean.getEnderecoBean()
				.getCep());

		if (wsc.isCepNotFound()) {
			throw new NoResultException();
		}

		Cidade cidade = (new CidadeDAO()).retrieve(wsc.getCidade());

		Endereco end = e.getEndereco();

		if (end == null) {
			end = new Endereco();
		}

		if (!wsc.isCepNotFound()) {
			Bairro bairro = new Bairro();

			bairro = (new BairroDAO()).retrieveByCep(wsc.getCep());
			if (bairro == null) {
				bairro.setNome(wsc.getBairro());
				(new BairroDAO()).create(bairro);
			}

			end.setRua(wsc.getLogradouroFull());
		}

		end.setNumero(bean.getEnderecoBean().getNumero());
		end.setComplemento(bean.getEnderecoBean().getComplemento());

		e.setCnpj(bean.getCNPJ());
		e.setNome(bean.getNome());
		e.setRazaoSocial(bean.getRazaoSocial());
		e.setEndereco(end);

		(new EstabelecimentoDAO()).update(e);

		return true;
	}

}