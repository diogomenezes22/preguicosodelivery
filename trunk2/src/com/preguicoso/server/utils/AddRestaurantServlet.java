package com.preguicoso.server.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.entities.Estabelecimento;

public class AddRestaurantServlet extends HttpServlet {

	private static final double MAX_PRICE = 100.0; // $100.00
	private static final double MAX_PRICE_CHANGE = 0.02; // +/- 2%

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Random rnd = new Random();

		PrintWriter out = resp.getWriter();

		String nome = req.getParameter("nome");
		out.println("nome: " + nome);
		String razaoSocial = req.getParameter("razao");
		out.println("razao: " + razaoSocial);
		String CNPJ = req.getParameter("cnpj");
		out.println("cnpj: " + CNPJ);
		String cep = req.getParameter("cep");
		out.println("cep: " + cep);
		String logo = req.getParameter("logo");
		out.println("logo: " + logo);
		String bairroget = req.getParameter("bairro");
		out.println("bairro: " + bairroget);
		String numero = req.getParameter("numero");
		out.println("numero: " + numero);
		String rua = req.getParameter("rua");
		out.println("rua: " + rua);
		String dataRegistro = req.getParameter("data");
		out.println("data: " + dataRegistro);

		try {
			Estabelecimento estabelecimento = new Estabelecimento();
			estabelecimento.setCnpj(CNPJ);
			estabelecimento.setNome(nome);
			estabelecimento.setRazaoSocial(razaoSocial);
			estabelecimento.setLogoURL(logo);
			EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();
			estabelecimentoDAO.create(estabelecimento);

			out.println("Funcionou");
		} catch (Exception e) {
			out.println();
			out.println("Nâo Funcionou : " + e.getStackTrace());
		}

		out.flush();
	}
}