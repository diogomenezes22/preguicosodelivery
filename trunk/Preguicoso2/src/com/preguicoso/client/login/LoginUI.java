package com.preguicoso.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.RegistroToken;
import com.preguicoso.shared.RegistroErros;
import com.preguicoso.shared.entities.UsuarioBean;

public class LoginUI extends Composite {

	private static LoginUIUiBinder uiBinder = GWT.create(LoginUIUiBinder.class);

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	interface LoginUIUiBinder extends UiBinder<Widget, LoginUI> {
	}

	@UiField
	InlineLabel email;
	@UiField
	MenuBar menu;
	@UiField
	Button login;

	public static LoginUI lu;

	public static LoginUI getInstance() {
		return lu;
	}

	public LoginUI() {
		this.initWidget(uiBinder.createAndBindUi(this));
		lu = this;
		menu.setVisible(false);
		email.setVisible(false);
		login.setVisible(false);
		loadLoginField();
	}

	@UiHandler("login")
	void onLoginClick(ClickEvent event) {
		new LoginDialog(LoginUI.this).show();
	}

	public void loadLoginField() {
		loginService.isUsuarioLogado(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					setLogado();
				} else {
					setDeslogado();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	public void setLogado() {
		loginService.getUsuarioLogado(new AsyncCallback<UsuarioBean>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(UsuarioBean result) {
				if (result != null) {
					email.setText(result.getEmail());
					email.setVisible(true);
					login.setVisible(false);

					Command cmdPedido = new Command() {

						@Override
						public void execute() {
							History.newItem(RegistroToken.statusPedido);
						}
					};
					Command cmdSair = new Command() {

						@Override
						public void execute() {
							loginService
									.fazerLogoutUsuario(new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											Window.alert(RegistroErros.LOGOUT);
										}

										@Override
										public void onSuccess(Void result) {
											setDeslogado();
										}
									});
						}
					};
					MenuBar menuDrop = new MenuBar(true);
					menuDrop.addItem("Meu pedido", cmdPedido);
					menuDrop.addItem("Sair", cmdSair);
					menu.addItem("minha conta", menuDrop);
					menu.setVisible(true);
				}
			}
		});
	}

	public void setDeslogado() {
		email.setVisible(false);
		login.setVisible(true);
		menu.setVisible(false);
		menu.clearItems();
		History.newItem(RegistroToken.index);
	}

	// public void isLogado() {
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
	// }
}
