package com.preguicoso.server.busca;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.server.carrinho.CarrinhoDeCompra;
import com.preguicoso.server.dao.BairroDAO;
import com.preguicoso.server.dao.CidadeDAO;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.dao.cardapio.IngredienteDAO;
import com.preguicoso.server.dao.cardapio.ItemCardapioDAO;
import com.preguicoso.server.dao.cardapio.OpcoesDAO;
import com.preguicoso.server.dbgenerator.BairroGenerator;
import com.preguicoso.server.entities.Bairro;
import com.preguicoso.server.entities.Cidade;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.cardapio.Ingrediente;
import com.preguicoso.server.entities.cardapio.ItemCardapio;
import com.preguicoso.server.entities.cardapio.Opcoes;
import com.preguicoso.shared.RegistroCategoriaEstabelecimento;
import com.preguicoso.shared.RegistroFormaPagamento;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.BairroBean;
import com.preguicoso.shared.entities.CidadeBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.cardapio.CategoriaBean;
import com.preguicoso.shared.entities.cardapio.IngredienteBean;
import com.preguicoso.shared.entities.cardapio.ItemCardapioBean;
import com.preguicoso.shared.entities.cardapio.OpcoesBean;

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
				e.putBairroFrete((long) 1, (long) 120);
				e.putBairroFrete((long) 15, (long) 125);
				e.putBairroFrete((long) 20, (long) 110);
				e.putBairroFrete((long) 25, (long) 100);
				e.putBairroFrete((long) 30, (long) 150);
				e.setIdCidade((long) 1);
				e.setTelefone("32221313");
				e.setEndereco("Rua x 123, Aldeota");
				e.setFormasPagamento(RegistroFormaPagamento.values());
				String[] horariosFuncionamento = { "08:00 às 00:00",
						"08:00 às 23:00", "08:00 às 23:00", "08:00 às 23:00",
						"08:00 às 23:00", "09:00 às 23:00", "08:00 às 23:00" };
				e.setHorariosFuncionamento(horariosFuncionamento);
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
				e.putBairroFrete((long) 0, (long) 120);
				e.putBairroFrete((long) 15, (long) 125);
				e.putBairroFrete((long) 20, (long) 110);
				e.putBairroFrete((long) 25, (long) 100);
				e.putBairroFrete((long) 30, (long) 150);
				e.setFormasPagamento(RegistroFormaPagamento.values());
				e.setIdCidade((long) 1);
				e.setTelefone("31154444");
				e.setEndereco("Rua y 1234, Mucuripe");
				e.setFormasPagamento(RegistroFormaPagamento.values());
				String[] horariosFuncionamento2 = { "10:00 às 00:00",
						"08:00 às 23:00", "08:00 às 23:00", "08:00 às 23:00",
						"10:00 às 23:00", "10:00 às 23:00", "10:00 às 23:00" };
				e.setHorariosFuncionamento(horariosFuncionamento2);
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
	public List<BairroBean> getBairros(Long idCidade) {
		BairroDAO bdao = new BairroDAO();
		List<BairroBean> lista = new ArrayList<BairroBean>();
		for (Bairro b : bdao.listByCidade(idCidade)) {
			lista.add(b.toBean());
		}
		return lista;
	}

	@Override
	public CidadeBean getCidade(Long idEstabelecimento) {
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		CidadeDAO cdao = new CidadeDAO();
		return cdao
				.retrieveById(edao.retrieve(idEstabelecimento).getIdCidade())
				.toBean();
	}

	@Override
	public List<CidadeBean> getCidadesList() {
		CidadeDAO cdao = new CidadeDAO();
		List<CidadeBean> lista = new ArrayList<CidadeBean>();
		for (Cidade c : cdao.listAll()) {
			lista.add(c.toBean());
		}
		return lista;
	}

	@Override
	public void setCidade(Long idEstabelecimento, CidadeBean cidadeBean) {
		CidadeDAO cdao = new CidadeDAO();
		cdao.update(new Cidade(cidadeBean));
	}

	@Override
	public void salvarListaBairros(EstabelecimentoBean estabelecimentoBean,
			List<String> listaBairros) {
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		Estabelecimento e = new Estabelecimento(estabelecimentoBean);
		BairroDAO bdao = new BairroDAO();
		for (Bairro b : bdao.getBairrosByName(
				estabelecimentoBean.getIdCidade(), listaBairros)) {
			e.putBairroFrete(b.getId(), (long) 100);
		}
		edao.update(e);
	}

	@Override
	public void updateEstabelecimento(EstabelecimentoBean eb,
			List<Long> idBairrosAtendidos) {
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		Estabelecimento e = new Estabelecimento(eb);
		e.setListaIdBairrosAtendidos(new ArrayList<Long>());
		e.setListaFretes(new ArrayList<Long>());
		for (Long bId : idBairrosAtendidos) {
			e.putBairroFrete(bId, (long) 100);
		}
		edao.update(e);
	}

	@Override
	public OpcoesBean criarCategoriaOpcao(String categoria,
			Long idEstabelecimento) {
		OpcoesDAO odao = new OpcoesDAO();
		Opcoes o = new Opcoes();
		o.setNome(categoria);
		o.setListaOpcoes(new ArrayList<String>());
		o.setIdEstabelecimento(idEstabelecimento);
		odao.create(o);
		return o.toBean();
	}

	@Override
	public List<OpcoesBean> getListaOpcoes(Long idEstabelecimento) {
		OpcoesDAO odao = new OpcoesDAO();
		return odao.listByIdEstabelecimento(idEstabelecimento);
	}

	@Override
	public void updateOpcoes(OpcoesBean ob) {
		OpcoesDAO odao = new OpcoesDAO();
		odao.update(new Opcoes(ob));
	}

	@Override
	public void removeOpcoes(OpcoesBean ob) {
		OpcoesDAO odao = new OpcoesDAO();
		odao.delete(new Opcoes(ob));
	}

	@Override
	public IngredienteBean criarIngrediente(String nome, Long preco,
			Long idEstabelecimento) {
		IngredienteDAO idao = new IngredienteDAO();
		Ingrediente i = new Ingrediente();
		i.setIdEstabelecimento(idEstabelecimento);
		i.setNome(nome);
		i.setPreco(preco);
		idao.create(i);
		return i.toBean();
	}

	@Override
	public List<IngredienteBean> getListaIngredientes(Long idEstabelecimento) {
		return new IngredienteDAO().listByIdEstabelecimento(idEstabelecimento);
	}

	@Override
	public void removeIngrediente(IngredienteBean ib) {
		new IngredienteDAO().delete(new Ingrediente(ib));
	}
}
