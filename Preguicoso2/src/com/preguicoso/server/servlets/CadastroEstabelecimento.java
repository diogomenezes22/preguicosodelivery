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
import com.preguicoso.server.dao.UsuarioEstabelecimentoDAO;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.UsuarioEstabelecimento;
import com.preguicoso.shared.RegistroCategoriaEstabelecimento;
import com.preguicoso.shared.RegistroFormaPagamento;
import com.preguicoso.shared.RegistroStatusRestaurante;

public class CadastroEstabelecimento extends HttpServlet{

		  /**
	 * 
	 */
	private static final long serialVersionUID = 4601320986698884560L;
	@Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
	    
		try{
			
			// Cria china in box
			Estabelecimento e = new Estabelecimento();
			e.setNome(req.getParameter("nome"));
			e.setRazaoSocial(req.getParameter("razao"));
			e.setCnpj(req.getParameter("cnpj"));
			e.setLogoURL(req.getParameter("logo"));
			e.setDescricao(req.getParameter("descricao"));
			e.setId(new Long(req.getParameter("id")));
			e.setCategoria(RegistroCategoriaEstabelecimento.Oriental);
			e.setStatus(RegistroStatusRestaurante.Aberto);
			e.putBairroFrete((long) 1, (long) 120);
			e.putBairroFrete((long) 15, (long) 125);
			e.putBairroFrete((long) 20, (long) 110);
			e.putBairroFrete((long) 25, (long) 100);
			e.putBairroFrete((long) 30, (long) 150);
			e.setIdCidade((long) 1);
			e.setTelefone(req.getParameter("telefone"));
			e.setEndereco(req.getParameter("endereco"));
			e.setFormasPagamento(RegistroFormaPagamento.values());
			String[] horariosFuncionamento = { "08:00 às 00:00",
					"08:00 às 23:00", "08:00 às 23:00", "08:00 às 23:00",
					"08:00 às 23:00", "09:00 às 23:00", "08:00 às 23:00" };
			e.setHorariosFuncionamento(horariosFuncionamento);
			EstabelecimentoDAO banco = new EstabelecimentoDAO();
			banco.create(e);
			
			UsuarioEstabelecimentoDAO udao = new UsuarioEstabelecimentoDAO();
			UsuarioEstabelecimento u = new UsuarioEstabelecimento();
			u.setEmail(req.getParameter("email"));
			u.setLogin(req.getParameter("email"));
			u.setPassword(req.getParameter("senha"));
			u.setNome(req.getParameter("nome"));
			List<Long> listaId = new ArrayList<Long>();
			listaId.add(new Long(req.getParameter("id")));
			u.setIdEstabelecimentoList(listaId);
			udao.create(u);
			
		
		
		
		}catch (Exception e) {
			 out.println(" Ocorreu algum erro:" +e.getMessage());
		}
		
		out.println("Estabelecimento Criado com sucesso" );
	    out.flush();
	  }
}
