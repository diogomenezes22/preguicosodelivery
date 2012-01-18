package com.preguicoso.client.login;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.UsuarioBean;

/**
 * The async counterpart of <code>LoginService</code>.
 */
public interface LoginServiceAsync {
	void getEmailUser(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void save(UsuarioBean Usuario, AsyncCallback<Boolean> callback);

	void isLogado(AsyncCallback<UsuarioBean> callback);

	void listAll(AsyncCallback<ArrayList<UsuarioBean>> callback);

	void isAdmin(AsyncCallback<Boolean> callback);

	void getURLLogout(AsyncCallback<String> callback);

	void getURLLogin(AsyncCallback<String> callback);

	void isEstabelecimentoLogado(AsyncCallback<Boolean> callback);

	void logarUsuarioEstabelecimento(String login, String pass,
			AsyncCallback<String> callback);

	void getEstabelecimentoLogado(AsyncCallback<EstabelecimentoBean> callback);

	void fazerLogoutUsuarioEstabelecimento(AsyncCallback<Void> callback);
}
