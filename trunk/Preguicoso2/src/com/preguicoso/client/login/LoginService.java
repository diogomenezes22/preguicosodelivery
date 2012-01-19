package com.preguicoso.client.login;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.UsuarioBean;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	String getEmailUser(String name) throws IllegalArgumentException;

	Boolean save(UsuarioBean Usuario);

	UsuarioBean isLogado();

	ArrayList<UsuarioBean> listAll();

	Boolean isAdmin();

	String getURLLogout();

	String getURLLogin();

	String logarUsuarioEstabelecimento(String login, String pass);

	void fazerLogoutUsuarioEstabelecimento();

	Boolean isEstabelecimentoLogado();

	EstabelecimentoBean getEstabelecimentoLogado();

	String changePasswordUsuarioEstabelecimento(String passwordOld,
			String passwordNew, String passwordNewCheck);
}
