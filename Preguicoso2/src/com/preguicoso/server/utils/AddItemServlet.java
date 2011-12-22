package com.preguicoso.server.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.preguicoso.server.dao.CategoriaDAO;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.dao.ItemCardapioDAO;
import com.preguicoso.server.entities.Categoria;
import com.preguicoso.server.entities.ItemCardapio;

public class AddItemServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		;

		PrintWriter out = resp.getWriter();

		String CNPJ = req.getParameter("cnpj");
		out.println("cnpj: " + CNPJ);
		String categoria = req.getParameter("categoria");
		out.println("categoria: " + categoria);
		String nome = req.getParameter("nome");
		out.println("nome: " + nome);
		String tipo = req.getParameter("tipo");
		out.println("tipo: " + tipo);
		String disponivel = req.getParameter("disponivel");
		out.println("disponivel: " + disponivel);
		String descricao = req.getParameter("descricao");
		out.println("descricao: " + descricao);
		String preco = req.getParameter("preco");
		out.println("preco: " + preco);
		String observacao = req.getParameter("observacao");
		out.println("observacao: " + observacao);
		String quantidade = req.getParameter("quantidade");
		out.println("quantidade: " + quantidade);

		try {
			Categoria cat = (new CategoriaDAO()).retrieve(categoria);
			if (cat == null) {
				cat = new Categoria();
				cat.setNome(categoria);
				cat.setDataRegistro(Calendar.getInstance().getTime());
				cat.setUltimaAtualizacao(cat.getDataRegistro());
				(new CategoriaDAO()).create(cat);
			}

			ItemCardapio item = new ItemCardapio();
			item.setCategoria(cat);
			item.setDataRegistro(Calendar.getInstance().getTime());
			item.setDescricao(descricao);
			item.setDisponivel(Boolean.parseBoolean(disponivel));
			item.setEstabelecimento((new EstabelecimentoDAO())
					.retrieveByCnpj(CNPJ));
			item.setNome(nome);
			item.setObservacao(observacao);
			item.setPreco(Double.parseDouble(preco));
			item.setTipo(tipo);
			item.setUltimaAtualizacao(item.getDataRegistro());
			(new ItemCardapioDAO()).create(item);

			out.println("Funcionou");
		} catch (Exception e) {
			out.println();
			out.println("Nâo Funcionou : " + e.getMessage());
		}

		out.flush();
	}
}