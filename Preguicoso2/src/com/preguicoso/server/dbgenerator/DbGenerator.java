package com.preguicoso.server.dbgenerator;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.preguicoso.server.dao.BairroDAO;
import com.preguicoso.server.dao.CategoriaDAO;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.dao.ItemCardapioDAO;
import com.preguicoso.server.dao.UsuarioDAO;
import com.preguicoso.server.entities.Bairro;
import com.preguicoso.server.entities.Categoria;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.ItemCardapio;
import com.preguicoso.server.entities.Usuario;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.EstabelecimentoBean;

/**
 * The server side implementation of the RPC service.
 */
public class DbGenerator {
	Usuario usuario = new Usuario();
	// DAO
	EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();
	CategoriaDAO categoriaDAO = new CategoriaDAO();
	ItemCardapioDAO itensDAO = new ItemCardapioDAO();
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	BairroDAO bairroDAO = new BairroDAO();

	public static void gerar() {
		Bairro bairro = new Bairro();
		BairroDAO bairroDAO = new BairroDAO();
		bairroDAO.create(bairro);

		Estabelecimento estabelecimento2 = new Estabelecimento();
		estabelecimento2.setCnpj("1231231231");
		estabelecimento2.setNome("Guerra");
		estabelecimento2.setRazaoSocial("No Social Reason");

		DbGenerator gerar = new DbGenerator();
		gerar.generateDatabase();

		EstabelecimentoBean eBean;
		UserService userService = UserServiceFactory.getUserService();
		String ownerEmail = userService.getCurrentUser().getEmail();
	}

	private void presset() {
		this.usuario.setCadastrado(true);
		this.usuario.setCEP("12228-462");
		this.usuario.setEmail("igor.irb@gmail.com");
		this.usuarioDAO.create(this.usuario);
	}

	public void generateDatabase() {
		this.presset();
		this.generateEstabelecimentos();
	}

	public void generateEstabelecimentos() {
		Bairro bairro = new Bairro();
		this.bairroDAO.create(bairro);

		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setId((long) 405);
		estabelecimento.setCnpj("1231231231");
		estabelecimento.setNome("China in Box");
		estabelecimento
				.setLogoURL("http://www.guiabh.com.br/imgs_cadastradas/China%20in%20Box%20logo.jpg");
		estabelecimento.setRazaoSocial("No Social Reason");
		estabelecimento.setStatus(RegistroStatusRestaurante.Fechado);
		this.estabelecimentoDAO.create(estabelecimento);

		Estabelecimento estabelecimento2 = new Estabelecimento();
		estabelecimento.setNome("Pizza Hut");
		estabelecimento
				.setLogoURL("http://3.bp.blogspot.com/_AcBUSVxs82w/SsA0mmjO3oI/AAAAAAAAVKo/iaVgbZZp5gU/s400/Pizza_Hut_Logo.jpg");
		estabelecimento.setRazaoSocial("No Social Reason");
		estabelecimento.setStatus(RegistroStatusRestaurante.Fechado);
		this.estabelecimentoDAO.create(estabelecimento2);

		estabelecimento = this.estabelecimentoDAO.retrieve(estabelecimento
				.getId());

		Categoria categoria = new Categoria("nada");
		this.categoriaDAO.create(categoria);

		categoria = this.categoriaDAO.retrieve("nada");

		ItemCardapio item;
		item = new ItemCardapio(
				"Yakisoba Clássico",
				"massa",
				true,
				"Macarrão com pedaços de carne, frango, legumes e champignon. ",
				11.21, estabelecimento, categoria);
		this.itensDAO.create(item);
		item = new ItemCardapio("Hot Dog2", "sanduiche", true,
				"Pao com as paradas2", 11.21, estabelecimento, categoria);
		this.itensDAO.create(item);
		item = new ItemCardapio("Hot Dog3", "sanduiche", true,
				"Pao com as paradas3", 11.21, estabelecimento, categoria);
		this.itensDAO.create(item);
		item = new ItemCardapio("Hot Dog4", "sanduiche", true,
				"Pao com as paradas4", 11.21, estabelecimento, categoria);
		this.itensDAO.create(item);
		item = new ItemCardapio("Hot Dog5", "sanduiche", true,
				"Pao com as paradas5", 11.21, estabelecimento, categoria);
		this.itensDAO.create(item);

		item = new ItemCardapio(
				"Yakisoba Clássico",
				"massa",
				true,
				"Macarrão com pedaços de carne, frango, legumes e champignon. ",
				11.21, estabelecimento2, categoria);
		this.itensDAO.create(item);
	}

}