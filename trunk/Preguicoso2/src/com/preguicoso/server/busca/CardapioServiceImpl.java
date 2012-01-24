package com.preguicoso.server.busca;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.lavieri.modelutil.cep.WebServiceCep;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.server.carrinho.CarrinhoDeCompra;
import com.preguicoso.server.dao.BairroDAO;
import com.preguicoso.server.dao.CidadeDAO;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.dao.ItemCardapioDAO;
import com.preguicoso.server.dao.PedidoDAO;
import com.preguicoso.server.dbgenerator.BairroGenerator;
import com.preguicoso.server.entities.Bairro;
import com.preguicoso.server.entities.Cidade;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.ItemCardapio;
import com.preguicoso.server.entities.Pedido;
import com.preguicoso.shared.RegistroCategoriaEstabelecimento;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

/**
 * Author: Abraao Barros Lacerda The server side implementation of the RPC
 * service.
 */
public class CardapioServiceImpl extends RemoteServiceServlet implements
		CardapioService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6342485425372450637L;

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
			session.setAttribute("pedido", new ArrayList<ItemCardapioBean>());
		}
		carrinho.setPedido((ArrayList<ItemCardapioBean>) session
				.getAttribute("pedido"));
		ItemCardapioBean item = dao.retrieve(i.getId()).toBean();
		item.setObservacao(observacao);
		item.setQuantidade(quantidade);
		carrinho.addItem(item);
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
			session.setAttribute("pedido", new ArrayList<ItemCardapioBean>());
		}
		carrinho.setPedido((ArrayList<ItemCardapioBean>) session
				.getAttribute("pedido"));
		ArrayList<ItemCardapioBean> pedido = (ArrayList<ItemCardapioBean>) carrinho
				.getPedido();
		return pedido;
	}

	@Override
	public void carrinhoClean() {
		this.getThreadLocalRequest().getSession().setAttribute("pedido", null);

		// TODO @Osman gera cidades - temporário
		CidadeDAO cdao = new CidadeDAO();
		List<Cidade> listac = cdao.listAll();
		if (listac != null) {
			if (listac.isEmpty()) {
				Cidade c = new Cidade();
				c.setId((long) 1);
				c.setNome("Fortaleza");
				cdao.create(c);
			}
		}

		// TODO @Osman gera bairros - temporário
		BairroDAO bdao = new BairroDAO();
		List<Bairro> listab = bdao.listAll();
		if (listab != null) {
			if (listab.isEmpty()) {
				String[] bfort = BairroGenerator.bairrosFortaleza();
				Bairro b;
				for (int i = 0; i < bfort.length; i++) {
					b = new Bairro();
					b.setId((long) i + 1);
					b.setIdCidade((long) 1);
					b.setNome(bfort[i]);
					bdao.create(b);
				}
			}
		}

		// TODO @Osman gera restaurantes - temporário
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		List<Estabelecimento> lista = edao.listAll();
		if (lista != null) {
			if (lista.isEmpty()) {
				// Cria china in box
				Estabelecimento e = new Estabelecimento();
				e.setCnpj("123");
				e.setNome("China In Box");
				e.setCategoria(RegistroCategoriaEstabelecimento.Oriental);
				e.setId((long) 405);
				e.setLogoURL("http://www.guiabh.com.br/imgs_cadastradas/China%20in%20Box%20logo.jpg");
				e.setRazaoSocial("razao social");
				e.setStatus(RegistroStatusRestaurante.Aberto);
				List<Long> idBairroAtendimentoList = new ArrayList<Long>();
				idBairroAtendimentoList.add((long) 1);
				idBairroAtendimentoList.add((long) 5);
				idBairroAtendimentoList.add((long) 15);
				idBairroAtendimentoList.add((long) 20);
				idBairroAtendimentoList.add((long) 25);
				idBairroAtendimentoList.add((long) 30);
				e.setIdBairroAtendimentoList(idBairroAtendimentoList);
				e.setIdCidade((long) 1);
				edao.create(e);

				// Cria Real sucos
				e = new Estabelecimento();
				e.setCnpj("123456");
				e.setNome("Real Sucos");
				e.setCategoria(RegistroCategoriaEstabelecimento.Pizzaria);
				e.setId((long) 407);
				e.setLogoURL("http://fabricadamidia.com.br/imagens/minis/fotos_album_203.jpg");
				e.setRazaoSocial("razao social do real sucos");
				e.setStatus(RegistroStatusRestaurante.Aberto);
				idBairroAtendimentoList = new ArrayList<Long>();
				idBairroAtendimentoList.add((long) 35);
				idBairroAtendimentoList.add((long) 40);
				idBairroAtendimentoList.add((long) 45);
				idBairroAtendimentoList.add((long) 50);
				idBairroAtendimentoList.add((long) 55);
				idBairroAtendimentoList.add((long) 60);
				e.setIdBairroAtendimentoList(idBairroAtendimentoList);
				e.setIdCidade((long) 1);
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
			session.setAttribute("pedido", new ArrayList<ItemCardapioBean>());
		}
		carrinho.setPedido((ArrayList<ItemCardapioBean>) session
				.getAttribute("pedido"));
		carrinho.popItem(dao.retrieve(i.getId()).toBean());
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
			String complemento, String formaPagamento) {
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

	@Override
	public String[] getEnderecoByCep(String cep) {
		String[] endereco = new String[2];
		WebServiceCep ws = WebServiceCep.searchCep(cep);
		if (!ws.hasException()) {
			if (!ws.isCepNotFound()) {
				endereco[0] = ws.getLogradouroFull();
				endereco[1] = ws.getBairro();
				return endereco;
			}
		}
		return null;

	}

	@Override
	public List<String> getBairrosNome(Long idCidade) {
		BairroDAO bdao = new BairroDAO();
		List<String> lista = new ArrayList<String>();
		for (Bairro b : bdao.listByCidade(idCidade)) {
			lista.add(b.getNome());
		}
		return lista;
	}
}
