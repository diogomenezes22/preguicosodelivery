package com.preguicoso.server.login;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.dao.PedidoDAO;
import com.preguicoso.server.dao.UsuarioDAO;
import com.preguicoso.server.dao.UsuarioEstabelecimentoDAO;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.Pedido;
import com.preguicoso.server.entities.Usuario;
import com.preguicoso.server.entities.UsuarioEstabelecimento;
import com.preguicoso.shared.AtributosSession;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.PedidoBean;
import com.preguicoso.shared.entities.UsuarioBean;
import com.preguicoso.shared.utils.CriptoUtils;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	@SuppressWarnings("unused")
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public String getURLLogin() {
		UserService userservice = UserServiceFactory.getUserService();
		return userservice
				.createLoginURL("http://5.preguicosotest.appspot.com");
	}

	@Override
	public String getURLLogout() {
		UserService userservice = UserServiceFactory.getUserService();
		return userservice
				.createLogoutURL("http://5.preguicosotest.appspot.com");
	}

	@Override
	public String logarUsuarioEstabelecimento(String login, String password) {
		UsuarioEstabelecimentoDAO udao = new UsuarioEstabelecimentoDAO();
		

		List<UsuarioEstabelecimento> lista = udao.getAll();
		if (lista != null) {
			if (lista.isEmpty()) {
				// Criando o dono do id 405
				UsuarioEstabelecimento u = new UsuarioEstabelecimento();
				u.setEmail("admin@preguicoso.com.br");
				u.setLogin("admin");
				u.setPassword("arrastao");
				u.setNome("Administrador");
				List<Long> listaId = new ArrayList<Long>();
				listaId.add((long) 405);
				u.setIdEstabelecimentoList(listaId);
				udao.create(u);

				// Criando o dono do id 407
				UsuarioEstabelecimento u2 = new UsuarioEstabelecimento();
				u2.setEmail("admin2@preguicoso.com.br");
				u2.setLogin("admin2");
				u2.setPassword("arrastao2");
				u2.setNome("Administrador2");
				List<Long> listaId2 = new ArrayList<Long>();
				listaId2.add((long) 407);
				u2.setIdEstabelecimentoList(listaId2);
				udao.create(u2);
			}
		}
		// </temporario>

		UsuarioEstabelecimento user = udao.retrieve(login);
		if (user != null) {
			if (CriptoUtils.equalsMD5(password, user.getPassword())) {
				HttpSession session = this.getThreadLocalRequest().getSession();
				session.setAttribute("usuarioEstabelecimento", user);
				// TODO @Osman isLogado não tem necessidade, mudar em breve
				session.setAttribute("isLogado", true);
				return null;
			}
			return "A senha está errada.";
		}
		return "O login não existe.";
	}

	@Override
	public Boolean isEstabelecimentoLogado() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		Boolean isLogado = (Boolean) session.getAttribute("isLogado");
		if (isLogado != null)
			if (isLogado)
				return true;
		return false;
	}

	@Override
	public EstabelecimentoBean getEstabelecimentoLogado() {
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		// TODO @Osman update para mais de um restaurante associado ao usuario
		HttpSession session = this.getThreadLocalRequest().getSession();
		UsuarioEstabelecimento user = (UsuarioEstabelecimento) session
				.getAttribute("usuarioEstabelecimento");
		Estabelecimento e = edao.retrieve(user.getIdEstabelecimentoList()
				.get(0));
		if (e != null) {
			return e.toBean();
		}
		return null;
	}

	@Override
	public void fazerLogoutUsuarioEstabelecimento() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		session.setAttribute("isLogado", null);
		session.setAttribute("usuarioEstabelecimento", null);
	}

	@Override
	public String changePasswordUsuarioEstabelecimento(String passwordOld,
			String passwordNew, String passwordNewCheck) {
		HttpSession session = this.getThreadLocalRequest().getSession();
		UsuarioEstabelecimento user = (UsuarioEstabelecimento) session
				.getAttribute("usuarioEstabelecimento");
		UsuarioEstabelecimentoDAO udao = new UsuarioEstabelecimentoDAO();
		if (CriptoUtils.equalsMD5(passwordOld, user.getPassword())) {
			if (passwordNew.equals(passwordNewCheck)) {
				user.setPassword(passwordNew);
				udao.update(user);
				return "Senha modificada com sucesso.";
			}
			return "Os campos com a nova senha devem ser iguais.";
		}
		return "Você não digitou sua senha corretamente.";
	}

	@Override
	public PedidoBean getPedidoAtualByUser() {
		PedidoDAO pdao = new PedidoDAO();
		Usuario user = (Usuario) this.getThreadLocalRequest().getSession()
				.getAttribute(AtributosSession.usuarioLogado);
		if (user != null) {
			Pedido pLast = pdao.retrieveLastPedidoByUser(user.getEmail());
			if (pLast != null) {
				return pLast.toBean();
			}
		}
		return null;
	}

	@Override
	public void cadastrarUsuario(UsuarioBean ub) {
		UsuarioDAO udao = new UsuarioDAO();
		ub.setDataRegistro(new Date());
		udao.create(new Usuario(ub));
	}

	@Override
	public Boolean hasAlreadyEmail(String email) {
		UsuarioDAO udao = new UsuarioDAO();
		if (udao.retrieve(email) == null)
			return false;
		return true;
	}

	@Override
	public String logarUsuario(String login, String password) {
		UsuarioDAO udao = new UsuarioDAO();

		Usuario user = udao.retrieve(login);
		if (user != null) {
			if (CriptoUtils.equalsMD5(password, user.getPassword())) {
				HttpSession session = this.getThreadLocalRequest().getSession();
				session.setAttribute(AtributosSession.usuarioLogado, user);
				return null;
			}
			return "A senha está errada.";
		}
		return "O login não existe.";
	}

	@Override
	public void fazerLogoutUsuario() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		session.setAttribute(AtributosSession.usuarioLogado, null);
	}

	@Override
	public Boolean isUsuarioLogado() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		Usuario userLogado = (Usuario) session
				.getAttribute(AtributosSession.usuarioLogado);
		if (userLogado != null)
			return true;
		return false;
	}

	@Override
	public UsuarioBean getUsuarioLogado() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		return ((Usuario) session.getAttribute(AtributosSession.usuarioLogado))
				.toBean();
	}

	@Override
	public String changePasswordUsuario(String passwordOld, String passwordNew,
			String passwordNewCheck) {
		HttpSession session = this.getThreadLocalRequest().getSession();
		Usuario user = (Usuario) session
				.getAttribute(AtributosSession.usuarioLogado);
		UsuarioDAO udao = new UsuarioDAO();
		if (CriptoUtils.equalsMD5(passwordOld, user.getPassword())) {
			if (passwordNew.equals(passwordNewCheck)) {
				user.setPassword(passwordNew);
				udao.update(user);
				return "Senha modificada com sucesso.";
			}
			return "Os campos com a nova senha devem ser iguais.";
		}
		return "Você não digitou sua senha corretamente.";
	}
}
