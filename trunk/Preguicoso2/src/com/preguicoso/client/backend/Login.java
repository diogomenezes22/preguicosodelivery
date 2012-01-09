package com.preguicoso.client.backend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.client.login.LoginServiceAsync;

public class Login extends Composite{

	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	@UiField TextBox login;
	@UiField PasswordTextBox password;
	@UiField Button botao;

	interface LoginUiBinder extends UiBinder<Widget, Login> {
	}

	public Login() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Login(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	private final LoginServiceAsync loginService = GWT
	.create(LoginService.class);
	@UiHandler("botao")
	void onBotaoClick(ClickEvent event) {
		loginService.logarEstabelecimento(login.getText(),password.getValue(),new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				if(result){
					Window.alert("Você está logado");
				}
				else{
					Window.alert("Você não está logado");
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Pronto2");				
			}
		});
	}
}
