package com.preguicoso.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.preguicoso.client.backend.Backend;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.client.login.LoginServiceAsync;
import com.preguicoso.shared.entities.EstabelecimentoBean;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Preguicoso2 implements EntryPoint {
	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	public void onModuleLoad() {
		getEstabelecimento();
		// if (isLogado()) {
		// getEstabelecimento();
		// } else {
		// Login login = new Login();
		// login.setP(this);
		// RootPanel.get("loading").setVisible(false);
		// RootPanel.get("content").clear();
		// RootPanel.get("content").add(login);
		// }
	}

	private void getEstabelecimento() {
		loginService
				.getEstabelecimentoLogado(new AsyncCallback<EstabelecimentoBean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Ocorreu um problema com o login. Tente se logar novamente.");
					}

					@Override
					public void onSuccess(EstabelecimentoBean result) {
						Backend backend = new Backend(result);
						RootPanel.get("loading").setVisible(false);
						RootPanel.get("content").add(backend);
					}
				});
	}

	Boolean isLogado = false;

	private boolean isLogado() {

		loginService.isEstabelecimentoLogado(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				isLogado = result;
				if (result) {
					RootPanel.get("content").clear();
					RootPanel.get("loading").setVisible(true);
					onModuleLoad();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				isLogado = false;
			}
		});
		return isLogado;
	}
}
