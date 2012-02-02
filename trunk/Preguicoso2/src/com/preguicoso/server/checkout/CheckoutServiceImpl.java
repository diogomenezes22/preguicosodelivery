package com.preguicoso.server.checkout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.checkout.CheckoutService;
import com.preguicoso.server.carrinho.CarrinhoDeCompra;
import com.preguicoso.server.dao.BairroDAO;
import com.preguicoso.server.dao.PedidoDAO;
import com.preguicoso.server.entities.Bairro;
import com.preguicoso.server.entities.Pedido;
import com.preguicoso.shared.AtributosSession;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

public class CheckoutServiceImpl extends RemoteServiceServlet implements
		CheckoutService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4474555612149042111L;

	@Override
	public List<String> getBairrosAtendidos(EstabelecimentoBean eb) {
		List<String> lista = new ArrayList<String>();
		for (Bairro b : (new BairroDAO()).listByEstabelecimento(eb)) {
			lista.add(b.getNome());
		}
		return lista;
	}

	@Override
	public void enviarPedido(String nomeCliente, String rua, String bairro,
			String complemento, String formaPagamento) {
		Pedido p = new Pedido();
		p.setFormaPagamento(formaPagamento);
		List<ItemCardapioBean> listaItens = getCarrinho();
		// TODO @Osman melhor não ficar dependente do carrinho para determinar o
		// id
		p.setIdEstabelecimento(listaItens.get(0).getEstabelecimentoBean());
		p.setListaItensJSON(listaItens);
		p.setNomeCliente(nomeCliente);
		p.setRua(rua);
		p.setBairro(bairro);
		p.setTimeStamp(new Date());
		p.setStatus(RegistroStatusPedido.esperando);

		PedidoDAO pdao = new PedidoDAO();
		// Guarda a key<Pedido> do pedido na sessão e cria o pedido
		this.getThreadLocalRequest().getSession()
				.setAttribute(AtributosSession.keyPedido, pdao.create(p));
	}

	@Override
	public List<ItemCardapioBean> getCarrinho() {
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
			session.setAttribute("pedido", new ArrayList<ItemCardapioBean>());
		}
		carrinho.setPedido((ArrayList<ItemCardapioBean>) session
				.getAttribute("pedido"));
		ArrayList<ItemCardapioBean> pedido = (ArrayList<ItemCardapioBean>) carrinho
				.getPedido();
		return pedido;
	}

}
