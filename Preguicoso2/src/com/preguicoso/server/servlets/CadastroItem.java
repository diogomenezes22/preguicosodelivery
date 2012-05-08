package com.preguicoso.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

public class CadastroItem extends HttpServlet{

		  /**
	 * 
	 */
	private static final long serialVersionUID = 4601320986698884560L;
	@Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
	    
		try{
		ItemCardapioBean c = new ItemCardapioBean();
		
		c.setNome(req.getParameter("nome").trim());
		c.setImagem(req.getParameter("imagem").trim());
		c.setTipo(req.getParameter("tipo").trim());
		c.setNumero(new Integer(req.getParameter("numero").trim()));
		c.setDisponivel(new Boolean(req.getParameter("disponivel").trim()));
		c.setDescricao(req.getParameter("descricao").trim());
		c.setPreco((new Long(req.getParameter("preco").trim())));
		c.setCategoriaBean(new CategoriaBean(req.getParameter("categoria").trim()));
		c.setEstabelecimentobean(new Long(req.getParameter("idRest").trim()));
		c.setDataRegistro(new Date());
		
		
		ItemCardapioDAO banco = new ItemCardapioDAO();
		banco.create(new ItemCardapio(c));
		out.println(" Item Criado Com sucesso:");
		
		}catch (Exception e) {
			 out.println(" Ocorreu algum erro:" +e.getMessage());
		}
		
		
	  }
}
