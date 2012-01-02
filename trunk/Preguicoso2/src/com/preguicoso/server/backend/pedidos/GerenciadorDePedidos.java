package com.preguicoso.server.backend.pedidos;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.server.dao.PedidoDAO;
import com.preguicoso.server.entities.Pedido;

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
		p.setNomeCliente("Abraham Kebratodas Lacerda");
		p.setRua("Rua Filgueiras Lima 189");
		p.setFormaPagamento("Cartão Crédito");
		// List<ItemCardapio> listaItens = new ArrayList<ItemCardapio>();
		// ItemCardapio item = new ItemCardapio();
		// item.setNome("Maracujá");
		// listaItens.add(item);
		// item = new ItemCardapio();
		// item.setNome("Caju");
		// listaItens.add(item);
		// item = new ItemCardapio();
		// item.setNome("Goiaba");
		// listaItens.add(item);
		// p.setListaItens(listaItens);
		dao.create(p);

		p = new Pedido();
		p.setIdEstabelecimento(idEstabelecimento);
		p.setNomeCliente("Battata das Quantas");
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
		p.setNomeCliente("Carlos Lemos");
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
