package com.preguicoso.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.preguicoso.client.busca.listaCategoria;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.client.login.LoginServiceAsync;
import com.preguicoso.client.login.LoginUI;

/**
 * 
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Preguicoso extends RouterHistory implements EntryPoint {

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	@Override
	public void onModuleLoad() {
		RootPanel.get("login").clear();
		RootPanel.get("userDropdownMenu").clear();
		RootPanel.get("login").add(new LoginUI());

		RootPanel.get("cadastroEstabelecimento").add(new listaCategoria());

		final String Token = History.getToken();
		if (Token.length() == 0) {
			History.newItem("index");
		}
		super.routerHistory();
	}

}
