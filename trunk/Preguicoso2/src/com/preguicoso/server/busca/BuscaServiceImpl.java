package com.preguicoso.server.busca;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.busca.BuscaService;
import com.preguicoso.server.dao.CidadeDAO;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.dao.ItemCardapioDAO;
import com.preguicoso.server.entities.Cidade;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.ItemCardapio;
import com.preguicoso.shared.AtributosSession;
import com.preguicoso.shared.entities.BairroBean;
import com.preguicoso.shared.entities.CidadeBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class BuscaServiceImpl extends RemoteServiceServlet implements
		BuscaService {

	@Override
	public ArrayList<EstabelecimentoBean> getListaEstabelecimento() {
		ArrayList<EstabelecimentoBean> lista = new ArrayList<EstabelecimentoBean>();

		// DbGenerator.gerar();

		for (Estabelecimento estabelecimento : (new EstabelecimentoDAO())
				.listAll()) {
			lista.add(estabelecimento.toBean());
		}

		return lista;
	}

	@Override
	public ArrayList<CidadeBean> getListaCidade()
			throws IllegalArgumentException {
		ArrayList<CidadeBean> lista = new ArrayList<CidadeBean>();

		for (Cidade c : (new CidadeDAO()).listAll()) {
			lista.add(c.toBean());
		}

		return lista;
	}

	@Override
	public Boolean editarEstabelecimento(EstabelecimentoBean bean) {
		Estabelecimento e = (new EstabelecimentoDAO()).retrieve(bean.getId());

		// if (e == null) {
		// return false;
		// }
		//
		// WebServiceCep wsc = WebServiceCep.searchCep(bean.getEnderecoBean()
		// .getCep());
		//
		// if (wsc.isCepNotFound()) {
		// throw new NoResultException("getEnderecoByCep");
		// }
		//
		// Bairro bairro = (new
		// BairroDAO()).retrieveByCep(bean.getEnderecoBean()
		// .getCep());
		//
		// Endereco end = e.getEndereco();
		//
		// if (end == null) {
		// end = new Endereco();
		// }
		//
		// if (!wsc.isCepNotFound()) {
		// end.setRua(wsc.getLogradouroFull());
		// end.setBairro(bairro);
		// }
		//
		// end.setNumero(bean.getEnderecoBean().getNumero());
		// end.setComplemento(bean.getEnderecoBean().getComplemento());
		//
		// e.setCnpj(bean.getCNPJ());
		// e.setNome(bean.getNome());
		// e.setRazaoSocial(bean.getRazaoSocial());
		// e.setEndereco(end);
		//
		// (new EstabelecimentoDAO()).update(e);
		//
		return true;
	}

	@Override
	public ArrayList<EstabelecimentoBean> getEstabelecimentoPorBairro(
			String bairro) throws IllegalArgumentException {
		List<Estabelecimento> estabelecimentos = (new EstabelecimentoDAO())
				.listAll();

		ArrayList<EstabelecimentoBean> list = new ArrayList<EstabelecimentoBean>();

		// for (Estabelecimento e : estabelecimentos) {
		// if (e.getEndereco() != null) {
		// if (e.getEndereco().getBairro() != null) {
		// if (e.getEndereco().getBairro().getNome().equals(bairro)) {
		// list.add(e.toBean());
		// }
		// }
		// }
		// }

		return list;
	}

	@Override
	public ArrayList<EstabelecimentoBean> getListaEstabelecimentoPorNome(
			String nome) {
		List<Estabelecimento> lista = (new EstabelecimentoDAO()).listAll();
		ArrayList<EstabelecimentoBean> listaRetorno = new ArrayList<EstabelecimentoBean>();
		for (Estabelecimento estabelecimento : lista) {
			if (estabelecimento.getNome().toLowerCase()
					.contains(nome.toLowerCase())) {
				listaRetorno.add(estabelecimento.toBean());
			}
		}
		return listaRetorno;
	}

	@Override
	public ArrayList<EstabelecimentoBean> getListaEstabelecimentoPorCategoria(
			String categoria) {
		List<Estabelecimento> lista = (new EstabelecimentoDAO()).listAll();
		ArrayList<EstabelecimentoBean> listaRetorno = new ArrayList<EstabelecimentoBean>();
		// for (Estabelecimento estabelecimento : lista) {
		// if (estabelecimento.getCategoria().toLowerCase()
		// .contains(categoria.toLowerCase())) {
		// listaRetorno.add(estabelecimento.toBean());
		// }
		// }
		return listaRetorno;
	}

	@Override
	public List<EstabelecimentoBean> getListaEstabelecimentoByBairro(
			BairroBean bb) {
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		return edao.getListByBairro(bb);
	}

	@Override
	public List<EstabelecimentoBean> getListaEstabelecimentoByCidade(
			Long idCidade) {
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		return edao.getListByCidade(idCidade);
	}

	@Override
	public List<EstabelecimentoBean> getListaEstabelecimentoBySession() {
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		HttpSession session = this.getThreadLocalRequest().getSession();
		CidadeBean cidadeBeanAtual = (CidadeBean) session
				.getAttribute(AtributosSession.cidadeBeanSession);
		BairroBean bairroBeanAtual = (BairroBean) session
				.getAttribute(AtributosSession.bairroBeanSession);
		if (cidadeBeanAtual == null) {
			return edao.getListByCidade((long) 1);
		}
		if (bairroBeanAtual == null)
			return edao.getListByCidade(cidadeBeanAtual.getId());
		return edao.getListByBairro(bairroBeanAtual);
	}

	@Override
	public List<EstabelecimentoBean> getListaEstabelecimentoBySession(
			String categoria) {
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		HttpSession session = this.getThreadLocalRequest().getSession();
		CidadeBean CidadeBeanAtual = (CidadeBean) session
				.getAttribute(AtributosSession.cidadeBeanSession);
		BairroBean bairroBeanAtual = (BairroBean) session
				.getAttribute(AtributosSession.bairroBeanSession);
		if (CidadeBeanAtual == null) {
			return edao.getListByCidade((long) 1, categoria);
		}
		if (bairroBeanAtual == null) {
			return edao.getListByCidade(CidadeBeanAtual.getId(), categoria);
		}
		return edao.getListByBairro(bairroBeanAtual, categoria);
	}

	@Override
	public CidadeBean getCidadeBeanSession() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		return (CidadeBean) session
				.getAttribute(AtributosSession.cidadeBeanSession);
	}

	@Override
	public BairroBean getBairroBeanSession() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		return (BairroBean) session
				.getAttribute(AtributosSession.bairroBeanSession);
	}

	@Override
	public void setCidadeBeanSession(CidadeBean cb) {
		HttpSession session = this.getThreadLocalRequest().getSession();
		session.setAttribute(AtributosSession.cidadeBeanSession, cb);
	}

	@Override
	public void setBairroBeanSession(BairroBean bb) {
		HttpSession session = this.getThreadLocalRequest().getSession();
		if (bb != null) {
			session.setAttribute(AtributosSession.cidadeBeanSession,
					(new CidadeDAO()).retrieveById(bb.getIdCidade()).toBean());
		}
		session.setAttribute(AtributosSession.bairroBeanSession, bb);
	}

	@Override
	public List<ItemCardapioBean> getListaItensByName(String nome) {
		//TODO Abraao: Utilizar Filter objectfy
		ItemCardapioDAO banco = new ItemCardapioDAO();
		ArrayList<ItemCardapio> lista = (ArrayList<ItemCardapio>) banco.listAll();
		ArrayList<ItemCardapioBean> retorno = new ArrayList<ItemCardapioBean>();
		for (ItemCardapio itemCardapio : lista) {
			if(itemCardapio!=null)
				if(itemCardapio.getNome().toLowerCase().contains(nome.toLowerCase()) || itemCardapio.getDescricao().toLowerCase().contains(nome.toLowerCase()))
					retorno.add(itemCardapio.toBean());
		}
		return retorno;
	}
}