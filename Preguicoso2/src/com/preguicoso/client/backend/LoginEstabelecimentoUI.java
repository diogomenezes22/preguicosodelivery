package com.preguicoso.client.backend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.Preguicoso2;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.client.login.LoginServiceAsync;

public class LoginEstabelecimentoUI extends Composite {

	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	@UiField
	TextBox login;
	@UiField
	PasswordTextBox password;
	@UiField
	Button botao;

	interface LoginUiBinder extends UiBinder<Widget, LoginEstabelecimentoUI> {
	}

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	public LoginEstabelecimentoUI(final Preguicoso2 preguicoso2) {
		initWidget(uiBinder.createAndBindUi(this));
		botao.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loginService.logarUsuarioEstabelecimento(login.getText(),
						password.getValue(), new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
								if (result == null) {
									LoginEstabelecimentoUI.this
											.removeFromParent();
									preguicoso2.openEstabelecimentoLogadoView();
								} else {
									Window.alert(result);
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Ocorreu um problema com login.");
							}
						});
			}
		});
	}
}
