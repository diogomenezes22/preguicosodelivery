package com.preguicoso.server.backend.pedidos;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.server.dao.PedidoDAO;
import com.preguicoso.server.entities.Pedido;
import com.preguicoso.shared.entities.ItemCardapioBean;

public class GerenciadorDePedidos extends RemoteServiceServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1384917405846331684L;

	private PedidoDAO dao;
	private Long idEstabelecimento;

	public GerenciadorDePedidos(Long idEstabelecimento) {
		dao = new PedidoDAO();
		this.idEstabelecimento = idEstabelecimento;
		gerarPedidosDeExemplo();
	}

	public List<Pedido> getListaDePedidos() {
		return dao.listByTimeStamp(idEstabelecimento);
	}

	// TODO @Osman: Apagar esse método depois. Utilizado para gerar o banco
	public void gerarPedidosDeExemplo() {
		PedidoDAO dao = new PedidoDAO();
		Pedido p = new Pedido();
		p.setIdEstabelecimento(idEstabelecimento);
		p.setNomeCliente("Cururu Verde");
		p.setRua("Rua Filgueiras Lima 189");
		p.setFormaPagamento("Cartão Crédito");

		List<ItemCardapioBean> listaItens = new ArrayList<ItemCardapioBean>();
		ItemCardapioBean item = new ItemCardapioBean();
		item.setNome("Maracujá");
		listaItens.add(item);
		item = new ItemCardapioBean();
		item.setNome("Caju");
		listaItens.add(item);
		item = new ItemCardapioBean();
		item.setNome("Goiaba");
		listaItens.add(item);
		p.setListaItensJSON(listaItens);
		dao.create(p);

		p = new Pedido();
		p.setIdEstabelecimento(idEstabelecimento);
		p.setNomeCliente("Chines Americano");
		p.setRua("Rua H8A 141");
		p.setFormaPagamento("Cartão Débito");
		// listaItens = new ArrayList<ItemCardapio>();
		// item = new ItemCardapio();
		// item.setNome("batatinha");
		// listaItens.add(item);
		// item = new ItemCardapio();
		// item.setNome("Batatonha");
		// listaItens.add(item);
		// item = new ItemCardapio();
		// item.setNome("Batata");
		// listaItens.add(item);
		// p.setListaItens(listaItens);
		dao.create(p);

		p = new Pedido();
		p.setIdEstabelecimento(idEstabelecimento);
		p.setNomeCliente("Cantor Jovem");
		p.setRua("Rua SA benevides 2355");
		p.setFormaPagamento("Dinheiro");
		// listaItens = new ArrayList<ItemCardapio>();
		// item = new ItemCardapio();
		// item.setNome("Laranja");
		// listaItens.add(item);
		// item = new ItemCardapio();
		// item.setNome("Tangerina");
		// listaItens.add(item);
		// item = new ItemCardapio();
		// item.setNome("Limão");
		// listaItens.add(item);
		// p.setListaItens(listaItens);
		dao.create(p);
	}
}
