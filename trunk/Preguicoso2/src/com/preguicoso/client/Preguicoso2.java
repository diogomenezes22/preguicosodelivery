package com.preguicoso.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.preguicoso.client.backend.Backend;
import com.preguicoso.client.backend.LoginEstabelecimentoUI;
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
		loginService.isEstabelecimentoLogado(new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Erro no login. Recarregue a página e tente se logar novamente.");
			}

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					openEstabelecimentoLogadoView();
				} else {
					LoginEstabelecimentoUI lu = new LoginEstabelecimentoUI(
							Preguicoso2.this);
					RootPanel.get("loading").setVisible(false);
					RootPanel.get("content").add(lu);
				}
			}
		});
	}

	public void openEstabelecimentoLogadoView() {
		loginService
				.getEstabelecimentoLogado(new AsyncCallback<EstabelecimentoBean>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(EstabelecimentoBean result) {
						if (result != null) {
							Backend backend = new Backend(result,
									Preguicoso2.this);
							RootPanel.get("loading").setVisible(false);
							RootPanel.get("content").add(backend);
						} else {
							Window.alert("Você não está associado a nenhum estabelecimento.");
						}
					}
				});
	}
}
