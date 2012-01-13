package com.preguicoso.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;
import com.preguicoso.shared.FieldVerifier;
import com.preguicoso.shared.entities.UsuarioBean;

public class LoginUI extends Composite {

	private static LoginUIUiBinder uiBinder = GWT.create(LoginUIUiBinder.class);
	@UiField
	HTMLPanel painel;
	UsuarioBean usuario;

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
		this.loginService.isLogado(new AsyncCallback<UsuarioBean>() {
			@Override
			public void onSuccess(UsuarioBean result) {
				LoginUI.this.usuario = result;

				Anchor linkLogin = new Anchor(); // botao de Login/Logout

				linkLogin.setHref(result.getUrl());
				linkLogin.setStyleName("menuButton");

				if (!LoginUI.this.usuario.getEmail().equals("")) {
					RootPanel.get("userDropdownMenu").removeStyleName(
							"displayNone");
					linkLogin.setHTML("Sair da conta");
				} else {
					linkLogin.setHTML("Login");
				}

				painel.getParent().addStyleName("grid_2");
				LoginUI.this.painel.add(linkLogin);

				final TextBox cep = LoginUI.this.criarInputCep();
				cep.setStyleName("cepForm");
				// cep.setValue("Digite seu CEP");
				cep.getElement().setAttribute("placeholder",
						"Digite aqui seu CEP");
				// cep.addFocusHandler(new FocusHandler() {
				// @Override
				// public void onFocus(FocusEvent event) {
				// cep.setValue("");
				// }
				// });
				// RootPanel.get("cepForm").add(cep);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	private void updateUsuario(UsuarioBean usuarioBean) {
		this.cadastroService.updateUsuario(usuarioBean,
				new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
	}

	private TextBox criarInputCep() {
		final TextBox cep = new TextBox();

		cep.setText("digite o seu CEP");

		if (this.usuario != null) {
			cep.setText(this.usuario.getCEP());
		}

		cep.addFocusHandler(new FocusHandler() {
			@Override
			public void onFocus(FocusEvent event) {
				// cep.setText("Digite seu CEP");

			}
		});

		cep.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {

				if (KeyCodes.KEY_ENTER == event.getNativeEvent().getKeyCode()) {
					if (FieldVerifier.isValidCEP(cep.getText())) {
						LoginUI.this.usuario.setCEP(cep.getText());
						LoginUI.this.usuario.setCadastrado(true);
						LoginUI.this.updateUsuario(LoginUI.this.usuario);

						History.newItem("lista/"
								+ cep.getText().replace("-", ""));
						History.fireCurrentHistoryState();
					} else {
						Window.alert("Cep inválido");
					}
				}
			}
		});

		return cep;
	}

}
