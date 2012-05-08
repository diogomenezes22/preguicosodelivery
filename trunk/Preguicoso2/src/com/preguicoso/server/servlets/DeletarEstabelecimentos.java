package com.preguicoso.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.dao.ItemCardapioDAO;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.ItemCardapio;
import com.preguicoso.shared.RegistroCategoriaEstabelecimento;
import com.preguicoso.shared.RegistroFormaPagamento;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

public class DeletarEstabelecimentos extends HttpServlet{

		  /**
	 * 
	 */
	private static final long serialVersionUID = 4601320986698884560L;
	@Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
	    
		try{
		
		EstabelecimentoDAO banco = new EstabelecimentoDAO();
		ArrayList<Estabelecimento> l = (ArrayList<Estabelecimento>)banco.listAll();
		for (Estabelecimento itemCardapio : l) {
			banco.delete(itemCardapio);
		}
		
		
		}catch (Exception e) {
			 out.println(" Ocorreu algum erro:" +e.getMessage());
		}
		
		
	  }
}
