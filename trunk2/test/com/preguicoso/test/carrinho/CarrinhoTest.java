package com.preguicoso.test.carrinho;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.preguicoso.server.carrinho.CarrinhoDeCompra;
import com.preguicoso.server.dao.cardapio.CategoriaDAO;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.cardapio.Categoria;
import com.preguicoso.server.entities.cardapio.ItemCardapio;
import com.preguicoso.shared.entities.cardapio.ItemCardapioBean;

public class CarrinhoTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	CarrinhoDeCompra carrinho;
	Estabelecimento estabelecimento;

	@Before
	public void setUp() {
		this.helper.setUp();
		String email = "abraaobarros3@gmail.com";
		this.carrinho = new CarrinhoDeCompra(email);

		this.estabelecimento = new Estabelecimento();
		this.estabelecimento.setId(146L);

		Categoria categoria = new Categoria();
		categoria.setNome("nada");
		(new CategoriaDAO()).create(categoria);
	}

	@After
	public void tearDown() {
		this.helper.tearDown();
	}

	@Test
	public void testInicializaCarrinho() {
	}

	@Test
	public void testCarrinhoVazio() {
		assertTrue(this.carrinho.isEmpty());
	}

	@Test
	public void testAdicionaItemNoCarrinho() {
		CategoriaDAO categoria = new CategoriaDAO();
		ItemCardapioBean item = new ItemCardapio("Hot Dog", "sanduiche", true,
				"Pao com as paradas", 11.21, this.estabelecimento,
				categoria.retrieve("nada")).toBean();
		this.carrinho.addItem(item);
		ItemCardapioBean itemIgual = this.carrinho.popItem();
		assertEquals(item, itemIgual);
	}

	// @Test
	// public void testAdicionarDoisItensIguaisNoCarrinho() {
	// CategoriaDAO categoria = new CategoriaDAO();
	// ItemCardapioBean item = new ItemCardapio("Hot Dog", "sanduiche", true,
	// "Pao com as paradas", 11.21, this.estabelecimento,
	// categoria.retrieve("nada")).toBean();
	// this.carrinho.addItem(item);
	// this.carrinho.addItem(item);
	// assertEquals(this.carrinho.getQuantidade(item), 2);
	// }

	@Test
	public void testlistarProdutosCarrinho() {
		CategoriaDAO categoria = new CategoriaDAO();
		ItemCardapio item = new ItemCardapio("Hot Dog", "sanduiche", true,
				"Pao com as paradas", 11.21, this.estabelecimento,
				categoria.retrieve("nada"));
		ItemCardapio item2 = new ItemCardapio("Yakisoba", "Chineza", true,
				"Pao com as paradas", 30.21, this.estabelecimento,
				categoria.retrieve("nada"));
		this.carrinho.addItem(item.toBean());
		this.carrinho.addItem(item2.toBean());
	}

	@Test
	public void testLimpaCarrinho() {
		CategoriaDAO categoria = new CategoriaDAO();
		ItemCardapio item = new ItemCardapio("Hot Dog", "sanduiche", true,
				"Pao com as paradas", 11.21, this.estabelecimento,
				categoria.retrieve("nada"));
		ItemCardapio item2 = new ItemCardapio("Yakisoba", "Chineza", true,
				"Pao com as paradas", 30.21, this.estabelecimento,
				categoria.retrieve("nada"));
		this.carrinho.addItem(item.toBean());
		this.carrinho.addItem(item2.toBean());
		this.carrinho.limpar();
		assertTrue(this.carrinho.isEmpty());
	}

	@Test
	public void testSomarItensDoCarrinho() {
		CategoriaDAO categoria = new CategoriaDAO();
		ItemCardapioBean item = new ItemCardapio("Hot Dog", "sanduiche", true,
				"Pao com as paradas", 11.21, this.estabelecimento,
				categoria.retrieve("nada")).toBean();
		ItemCardapioBean item2 = new ItemCardapio("Yakisoba", "Chineza", true,
				"Pao com as paradas", 30.21, this.estabelecimento,
				categoria.retrieve("nada")).toBean();
		this.carrinho.addItem(item);
		this.carrinho.addItem(item);
		this.carrinho.addItem(item2);
		assertEquals(this.carrinho.soma(), 50.32, 2.0);
	}

	@Test
	public void testSomarFreteAoTotal() {
		CategoriaDAO categoria = new CategoriaDAO();
		ItemCardapio item = new ItemCardapio("Hot Dog", "sanduiche", true,
				"Pao com as paradas", 11.21, this.estabelecimento,
				categoria.retrieve("nada"));
		ItemCardapio item2 = new ItemCardapio("Yakisoba", "Chineza", true,
				"Pao com as paradas", 30.21, this.estabelecimento,
				categoria.retrieve("nada"));
		this.carrinho.addItem(item.toBean());
		this.carrinho.addItem(item.toBean());
		this.carrinho.addItem(item2.toBean());
		this.carrinho.setFrete(10.00);
		assertEquals(this.carrinho.somaTotal(), 60.32, 2.0);
	}

	@Test
	public void testRetiraItemDoCarrinho() {
		CategoriaDAO categoria = new CategoriaDAO();
		ItemCardapioBean item = new ItemCardapio("Hot Dog", "sanduiche", true,
				"Pao com as paradas", 11.21, this.estabelecimento,
				categoria.retrieve("nada")).toBean();
		ItemCardapioBean item2 = new ItemCardapio("Yakisoba", "Chineza", true,
				"Pao com as paradas", 30.21, this.estabelecimento,
				categoria.retrieve("nada")).toBean();
		this.carrinho.addItem(item);
		this.carrinho.addItem(item2);
		this.carrinho.popItem(item);
		assertEquals(1, this.carrinho.getSize());
	}
}
