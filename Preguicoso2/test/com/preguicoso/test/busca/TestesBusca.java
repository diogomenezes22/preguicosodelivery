package com.preguicoso.test.busca;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.preguicoso.server.busca.BuscaServiceImpl;
import com.preguicoso.server.dao.CidadeDAO;
import com.preguicoso.server.dao.EstadoDAO;
import com.preguicoso.server.dao.PaisDAO;
import com.preguicoso.server.entities.Cidade;
import com.preguicoso.server.entities.Estado;
import com.preguicoso.server.entities.Pais;
import com.preguicoso.shared.entities.EnderecoBean;


public class TestesBusca {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		this.helper.setUp();
		Pais p = new Pais("Brasil", "BR");
		(new PaisDAO()).create(p);
		Estado e = new Estado("São Paulo", "SP", p);
		(new EstadoDAO()).create(e);
		Cidade c = new Cidade("São José dos Campos", e);
		(new CidadeDAO()).create(c);
	}

	@After
	public void tearDown() { this.helper.tearDown(); }

	@Test
	public void testCidadeDAO() {
		BuscaServiceImpl bS = new BuscaServiceImpl();
		EnderecoBean eB = bS.getEnderecoByCep("12228-462");
	}

}
