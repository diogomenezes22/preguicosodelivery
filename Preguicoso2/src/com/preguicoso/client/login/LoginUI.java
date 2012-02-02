package com.preguicoso.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;

public class LoginUI extends Composite {

	private static LoginUIUiBinder uiBinder = GWT.create(LoginUIUiBinder.class);
	@UiField
	HTMLPanel painel;
	// @UiField MenuBar menuBar;

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);
	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);

	interface LoginUIUiBinder extends UiBinder<Widget, LoginUI> {
	}

	public LoginUI() {
		this.initWidget(uiBinder.createAndBindUi(this));
		this.isLogado();

	}

	public void isLogado() {
		// this.loginService.isLogado(new AsyncCallback<UsuarioBean>() {
		// @Override
		// public void onSuccess(UsuarioBean result) {
		// LoginUI.this.usuario = result;
		//
		// Anchor linkLogin = new Anchor(); // botao de Login/Logout
		//
		// linkLogin.setHref(result.getUrl());
		// linkLogin.setStyleName("menuButton");
		//
		// if (!LoginUI.this.usuario.getEmail().equals("")) {
		// RootPanel.get("userDropdownMenu").removeStyleName(
		// "displayNone");
		// linkLogin.setHTML("Sair da conta");
		// } else {
		// linkLogin.setHTML("Login");
		// }
		//
		// painel.getParent().addStyleName("grid_2");
		// LoginUI.this.painel.add(linkLogin);
		//
		// final TextBox cep = LoginUI.this.criarInputCep();
		// cep.setStyleName("cepForm");
		// // cep.setValue("Digite seu CEP");
		// cep.getElement().setAttribute("placeholder",
		// "Digite aqui seu CEP");
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// }
		// });
	}
}
