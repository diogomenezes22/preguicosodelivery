package com.preguicoso.client.login;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.PedidoBean;
import com.preguicoso.shared.entities.UsuarioBean;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

	void cadastrarUsuario(UsuarioBean ub);

	Boolean hasAlreadyEmail(String email);

	String logarUsuario(String login, String pass);

	void fazerLogoutUsuario();

	Boolean isUsuarioLogado();

	UsuarioBean getUsuarioLogado();

	String changePasswordUsuario(String passwordOld, String passwordNew,
			String passwordNewCheck);

	String getURLLogout();

	String getURLLogin();

	String logarUsuarioEstabelecimento(String login, String pass);

	void fazerLogoutUsuarioEstabelecimento();

	Boolean isEstabelecimentoLogado();

	EstabelecimentoBean getEstabelecimentoLogado();

	String changePasswordUsuarioEstabelecimento(String passwordOld,
			String passwordNew, String passwordNewCheck);

	PedidoBean getPedidoAtualByUser();

}
