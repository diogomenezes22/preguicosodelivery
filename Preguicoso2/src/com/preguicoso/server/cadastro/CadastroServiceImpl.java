package com.preguicoso.server.cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.dao.PedidoDAO;
import com.preguicoso.server.dao.cardapio.CategoriaDAO;
import com.preguicoso.server.dao.cardapio.ItemCardapioDAO;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.Pedido;
import com.preguicoso.server.entities.cardapio.Categoria;
import com.preguicoso.server.entities.cardapio.ItemCardapio;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.PedidoBean;
import com.preguicoso.shared.entities.cardapio.CategoriaBean;
import com.preguicoso.shared.entities.cardapio.ItemCardapioBean;

/**
 * The server side implementation of the RPC service.
 */
public class CadastroServiceImpl extends RemoteServiceServlet implements
		CadastroService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6650028804777297689L;

	@Override
	public EstabelecimentoBean getEstabelecimento(long id) {
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		Estabelecimento e = edao.retrieve(id);
		return e.toBean();
	}

	@Override
	public List<PedidoBean> getListaDePedidos(Long idEstabelecimento) {
		List<Pedido> lista = (new PedidoDAO())
				.listByBairroTimeStamp(idEstabelecimento);
		List<PedidoBean> listaBean = new ArrayList<PedidoBean>();
		if (lista != null) {
			for (Pedido p : lista) {
				listaBean.add(p.toBean());
			}
		}
		return listaBean;
	}

	@Override
	public ArrayList<CategoriaBean> getCategoria() {
		ArrayList<CategoriaBean> lista = new ArrayList<CategoriaBean>();
		for (Categoria categoria : (new CategoriaDAO()).listAll()) {
			lista.add(categoria.toBean());
		}
		return lista;
	}

	@Override
	public void salvarCategoria(Long idEstabelecimento, CategoriaBean i) {
		CategoriaDAO banco = new CategoriaDAO();
		Categoria e = new Categoria(i);
		e.setEstabelecimentoId(idEstabelecimento);
		banco.create(e);
	}

	@Override
	public void salvarItemCardapio(ItemCardapioBean i) {
		ItemCardapioDAO banco = new ItemCardapioDAO();
		ItemCardapio item = new ItemCardapio(i);
		banco.create(item);
	}

	@Override
	public void setStatusPedido(Long idPedido, RegistroStatusPedido status) {
		PedidoDAO pdao = new PedidoDAO();
		Pedido p = pdao.retrieve(idPedido);
		p.setStatus(status);
		pdao.update(p);
	}

	@Override
	public void removePedido(Long idPedido) {
		PedidoDAO pdao = new PedidoDAO();
		Pedido p = pdao.retrieve(idPedido);
		pdao.delete(p);
	}

	@Override
	public RegistroStatusRestaurante getStatus(Long idEstabelecimento) {
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		Estabelecimento e = edao.retrieve(idEstabelecimento);
		if (e != null)
			return e.getStatus();
		else
			return RegistroStatusRestaurante.values()[0];
	}

	@Override
	public void setStatus(Long idEstabelecimento, int statusIndex) {
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		Estabelecimento e = edao.retrieve((long) 405);
		if (e != null) {
			e.setStatus(RegistroStatusRestaurante.values()[statusIndex]);
			edao.update(e);
		} else {

		}
	}

	@Override
	public List<PedidoBean> getPedidosNovos(Long idEstabelecimento,
			Date lastTime) {
		PedidoDAO pdao = new PedidoDAO();
		List<PedidoBean> lista = new ArrayList<PedidoBean>();
		for (Pedido p : pdao.retrieveAfter(idEstabelecimento, lastTime)) {
			lista.add(p.toBean());
		}
		return lista;
	}

	@Override
	public List<PedidoBean> getListaHistoricoDePedidos(Long idEstabelecimento) {
		List<Pedido> lista = (new PedidoDAO())
				.listarHistorico(idEstabelecimento);
		List<PedidoBean> listaBean = new ArrayList<PedidoBean>();
		if (lista != null) {
			for (Pedido p : lista) {
				listaBean.add(p.toBean());
			}
		}
		return listaBean;
	}
}
