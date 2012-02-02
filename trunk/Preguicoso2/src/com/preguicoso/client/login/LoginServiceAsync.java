package com.preguicoso.client.login;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.PedidoBean;
import com.preguicoso.shared.entities.UsuarioBean;

/**
 * The async counterpart of <code>LoginService</code>.
 */
public interface LoginServiceAsync {

	void cadastrarUsuario(UsuarioBean ub, AsyncCallback<Void> callback);

	void hasAlreadyEmail(String email, AsyncCallback<Boolean> callback);

	void logarUsuario(String login, String pass, AsyncCallback<String> callback);

	void fazerLogoutUsuario(AsyncCallback<Void> callback);

	void isUsuarioLogado(AsyncCallback<Boolean> callback);

	void getUsuarioLogado(AsyncCallback<UsuarioBean> callback);

	void changePasswordUsuario(String passwordOld, String passwordNew,
			String passwordNewCheck, AsyncCallback<String> callback);

	void getURLLogout(AsyncCallback<String> callback);

	void getURLLogin(AsyncCallback<String> callback);

	void isEstabelecimentoLogado(AsyncCallback<Boolean> callback);

	void logarUsuarioEstabelecimento(String login, String pass,
			AsyncCallback<String> callback);

	void getEstabelecimentoLogado(AsyncCallback<EstabelecimentoBean> callback);

	void fazerLogoutUsuarioEstabelecimento(AsyncCallback<Void> callback);

	/**
	 * @param passwordOld
	 *            é o passowrd antigo
	 * @param passwordNew
	 *            é o password novo
	 * @param passwordNewCheck
	 *            é o password novo digitado novamente
	 * @return String com a menssagem da operação.
	 */
	void changePasswordUsuarioEstabelecimento(String passwordOld,
			String passwordNew, String passwordNewCheck,
			AsyncCallback<String> callback);

	void getPedidoAtualBySession(AsyncCallback<PedidoBean> callback);

}
