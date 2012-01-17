package com.preguicoso.server.busca;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.server.carrinho.CarrinhoDeCompra;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.dao.ItemCardapioDAO;
import com.preguicoso.server.dao.PedidoDAO;
import com.preguicoso.server.entities.Bairro;
import com.preguicoso.server.entities.Endereco;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.ItemCardapio;
import com.preguicoso.server.entities.Pedido;
import com.preguicoso.server.entities.Usuario;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

/**
 * Author: Abraao Barros Lacerda The server side implementation of the RPC
 * service.
 */
@SuppressWarnings("serial")
public class CardapioServiceImpl extends RemoteServiceServlet implements
		CardapioService {

	@Override
	public ArrayList<ItemCardapioBean> getItensCardapio(Long id) {
		ArrayList<ItemCardapioBean> itensBeans = new ArrayList<ItemCardapioBean>();

		ItemCardapioDAO itemDAO = new ItemCardapioDAO();
		List<ItemCardapio> itens = itemDAO.listAll();
		for (ItemCardapio i : itens) {
			if (i != null) {
				if (i.getEstabelecimentoId().equals(id))
					itensBeans.add(i.toBean());
			}
		}
		return itensBeans;
	}

	@Override
	public void addItem(ItemCardapioBean i, int quantidade, String observacao)
			throws IllegalArgumentException {
		CarrinhoDeCompra carrinho;
		UserService userservice = UserServiceFactory.getUserService();
		if (userservice.isUserLoggedIn()) {
			carrinho = new CarrinhoDeCompra(userservice.getCurrentUser()
					.getEmail());
		} else {
			carrinho = new CarrinhoDeCompra("anonimo");
		}
		ItemCardapioDAO dao = new ItemCardapioDAO();
		HttpSession session = this.getThreadLocalRequest().getSession();
		if (session.getAttribute("pedido") == null) {
			session.setAttribute("pedido", new HashMap());
		}
		carrinho.setPedido((HashMap) session.getAttribute("pedido"));
		carrinho.addItem(dao.retrieve(i.getId()), quantidade, observacao);
		session.setAttribute("pedido", carrinho.getPedido());
	}

	@Override
	public ArrayList<ItemCardapioBean> getCarrinho() {

		CarrinhoDeCompra carrinho;
		UserService userservice = UserServiceFactory.getUserService();
		if (userservice.isUserLoggedIn()) {
			carrinho = new CarrinhoDeCompra(userservice.getCurrentUser()
					.getEmail());
		} else {
			carrinho = new CarrinhoDeCompra("anonimo");
		}
		HttpSession session = this.getThreadLocalRequest().getSession();
		// TODO @Osman aqui tem erro na session
		if (session.getAttribute("pedido") == null) {
			session.setAttribute("pedido", new HashMap());
		}
		carrinho.setPedido((HashMap) session.getAttribute("pedido"));
		HashMap<ItemCardapioBean, Integer> pedido = carrinho.getPedido();

		ArrayList<ItemCardapioBean> pedidoBean = new ArrayList<ItemCardapioBean>();

		for (ItemCardapioBean e : pedido.keySet()) {
			pedidoBean.add(e);
		}

		return pedidoBean;

	}

	@Override
	public void carrinhoClean() {
		this.getThreadLocalRequest().getSession().setAttribute("pedido", null);

		// TODO @Osman gera restaurante teste temporário
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		List<Estabelecimento> lista = edao.listAll();
		if (lista != null) {
			if (lista.isEmpty()) {
				Estabelecimento e = new Estabelecimento();
				e.setCnpj("123");
				e.setNome("China In Box");
				e.setCategoria("Chinesa");
				e.setAreaAtendimento(new ArrayList<Bairro>());
				e.setDono(new Usuario());
				e.setEmailDono("teste@teste.com");
				e.setEndereco(new Endereco());
				e.setId((long) 405);
				e.setLogoURL("http://www.guiabh.com.br/imgs_cadastradas/China%20in%20Box%20logo.jpg");
				e.setRazaoSocial("razao social");
				e.setStatus(RegistroStatusRestaurante.Aberto);
				edao.create(e);
			}
		}
	}

	@Override
	public void removeItem(ItemCardapioBean i) {
		CarrinhoDeCompra carrinho;
		UserService userservice = UserServiceFactory.getUserService();
		if (userservice.isUserLoggedIn()) {
			carrinho = new CarrinhoDeCompra(userservice.getCurrentUser()
					.getEmail());
		} else {
			carrinho = new CarrinhoDeCompra("anonimo");
		}
		ItemCardapioDAO dao = new ItemCardapioDAO();
		HttpSession session = this.getThreadLocalRequest().getSession();
		if (session.getAttribute("pedido") == null) {
			session.setAttribute("pedido", new HashMap());
		}
		carrinho.setPedido((HashMap) session.getAttribute("pedido"));
		carrinho.popItem(dao.retrieve(i.getId()));
		session.setAttribute("pedido", carrinho.getPedido());

	}

	@Override
	public ArrayList<CategoriaBean> getCategorias(Long Estabelecimento) {
		ArrayList<CategoriaBean> categorias = new ArrayList<CategoriaBean>();
		ItemCardapioDAO itemDAO = new ItemCardapioDAO();
		List<ItemCardapio> itens = itemDAO.listAll();
		for (ItemCardapio i : itens) {
			if (i != null) {
				if (i.getEstabelecimento() != null)
					if (i.getEstabelecimento().getId().equals(Estabelecimento)) {
						categorias.add(new CategoriaBean(i.getCategoria()));
					}
			}
		}
		return categorias;

	}

	@Override
	public void enviarPedido(String nomeCliente, String rua, String bairro,
			String formaPagamento) {
		Pedido p = new Pedido();
		p.setFormaPagamento(formaPagamento);
		ArrayList<ItemCardapioBean> listaItens = getCarrinho();
		p.setIdEstabelecimento(listaItens.get(0).getEstabelecimentoBean());
		p.setListaItensJSON(listaItens);
		p.setNomeCliente(nomeCliente);
		p.setRua(rua);
		p.setBairro(bairro);
		p.setTimeStamp(new Date());
		p.setStatus(RegistroStatusPedido.esperando);

		PedidoDAO pdao = new PedidoDAO();
		pdao.create(p);
	}

}
