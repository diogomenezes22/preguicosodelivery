package com.preguicoso.client.backend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class Login extends Composite{

	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	@UiField TextBox login;
	@UiField PasswordTextBox password;

	interface LoginUiBinder extends UiBinder<Widget, Login> {
	}

	public Login() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Login(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
