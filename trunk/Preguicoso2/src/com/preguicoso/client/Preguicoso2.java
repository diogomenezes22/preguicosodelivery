package com.preguicoso.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.preguicoso.client.backend.Backend;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.client.login.LoginServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Preguicoso2 implements EntryPoint {
	private final LoginServiceAsync loginService = GWT
	.create(LoginService.class);
	public void onModuleLoad() {
		
			Backend backend = new Backend();
			RootPanel.get("loading").setVisible(false);
			RootPanel.get("content").add(backend);
		
	}
	Boolean isLogado = false;
	private boolean isLogado() {
		
		loginService.isEstabelecimentoLogado(new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				isLogado = result;
				Window.alert("asdads");
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				isLogado = false;
				
			}
		});
		return isLogado;
	}
}
