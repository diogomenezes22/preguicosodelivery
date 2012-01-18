package com.preguicoso.server.login;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.dao.UsuarioDAO;
import com.preguicoso.server.dao.UsuarioEstabelecimentoDAO;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.Usuario;
import com.preguicoso.server.entities.UsuarioEstabelecimento;
import com.preguicoso.shared.CriptoUtils;
import com.preguicoso.shared.entities.EstabelecimentoBean;
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
	public String logarUsuarioEstabelecimento(String login, String password) {
		UsuarioEstabelecimentoDAO udao = new UsuarioEstabelecimentoDAO();

		// TODO @Osman <temporario>
		List<UsuarioEstabelecimento> lista = udao.getAll();
		if (lista != null) {
			if (lista.isEmpty()) {
				// Criando o dono do id 405
				UsuarioEstabelecimento u = new UsuarioEstabelecimento();
				u.setEmail("admin@preguicoso.com.br");
				u.setLogin("admin");
				try {
					String senhaCript = CriptoUtils
							.byteArrayToHexString(CriptoUtils.digest(
									"arrastao".getBytes(), "MD5"));
					u.setPassword(senhaCript);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				u.setNome("Administrador");
				List<Long> listaId = new ArrayList<Long>();
				listaId.add((long) 405);
				u.setIdEstabelecimentoList(listaId);
				udao.create(u);

				// Criando o dono do id 407
				UsuarioEstabelecimento u2 = new UsuarioEstabelecimento();
				u2.setEmail("admin2@preguicoso.com.br");
				u2.setLogin("admin2");
				try {
					String senhaCript = CriptoUtils
							.byteArrayToHexString(CriptoUtils.digest(
									"arrastao2".getBytes(), "MD5"));
					u2.setPassword(senhaCript);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				u2.setNome("Administrador2");
				List<Long> listaId2 = new ArrayList<Long>();
				listaId2.add((long) 407);
				u2.setIdEstabelecimentoList(listaId2);
				udao.create(u2);
			}
		}
		// </temporario>
		try {
			String senhaCriptografada = CriptoUtils
					.byteArrayToHexString(CriptoUtils.digest(
							password.getBytes(), "MD5"));
			UsuarioEstabelecimento user = udao.retrieve(login);
			if (user != null) {
				if (user.getPassword().equals(senhaCriptografada)) {
					HttpSession session = this.getThreadLocalRequest()
							.getSession();
					session.setAttribute("usuarioEstabelecimento", user);
					session.setAttribute("isLogado", true);
					return null;
				}
				return "A senha está errada.";
			}
			return "O login não existe.";
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "Ocorreu um erro. Tente se logar novamente.";
		}
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
}
