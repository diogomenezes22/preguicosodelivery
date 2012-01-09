package com.preguicoso.server.login;

import java.util.ArrayList;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.server.dao.UsuarioDAO;
import com.preguicoso.server.entities.Usuario;
import com.preguicoso.shared.entities.UsuarioBean;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
	@Override
	public String getEmailUser(String input) throws IllegalArgumentException {
		UserService userservice = UserServiceFactory.getUserService();
		String email = userservice.getCurrentUser().getEmail();
		return email;
	}

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
	public UsuarioBean isLogado() {
		UserService userservice = UserServiceFactory.getUserService();
		UsuarioBean a;
		a = new UsuarioBean();
		if (userservice.isUserLoggedIn()) {
			UsuarioDAO banco = new UsuarioDAO();
			Usuario usuario = banco.retrieve(userservice.getCurrentUser()
					.getEmail());
			if (usuario == null) {
				a.setEmail(userservice.getCurrentUser().getEmail());
				usuario = new Usuario(a);
				banco.create(usuario);
			} else {
				a = usuario.toBean();
			}
			a.setUrl(userservice.createLogoutURL(""));
			return a;
		}
		a.setUrl(userservice
				.createLoginURL("http://5.preguicosotest.appspot.com"));
		return a;
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
	public Boolean isAdmin() {
		UserService userservice = UserServiceFactory.getUserService();
		return userservice.isUserAdmin();
	}

	@Override
	public ArrayList<UsuarioBean> listAll() {
		UsuarioDAO usuarios = new UsuarioDAO();
		ArrayList<UsuarioBean> usuariosBeans = new ArrayList<UsuarioBean>();
		for (Usuario usuario : usuarios.listAll()) {
			usuariosBeans.add(usuario.toBean());
		}
		return usuariosBeans;
	}

	@Override
	public Boolean save(UsuarioBean Usuario) {
		return null;
	}

	@Override
	public Boolean logarEstabelecimento(String login, String password) {
		if(login.equals("admin") && password.equals("1234"))
			return true;
		return false;
	}

	@Override
	public Boolean isEstabelecimentoLogado() {
		// TODO Auto-generated method stub
		return true;
	}
}
