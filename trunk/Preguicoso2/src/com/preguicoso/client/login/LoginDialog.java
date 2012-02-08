package com.preguicoso.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.RegistroErros;

public class LoginDialog extends DialogBox {

	public final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	public LoginDialog(Widget caller) {
		setText("Fazer login");
		setPopupPosition(caller.getAbsoluteLeft(), caller.getAbsoluteTop());
		VerticalPanel content = new VerticalPanel();
		InlineLabel loginLabel = new InlineLabel("E-mail");
		content.add(loginLabel);
		final TextBox loginTB = new TextBox();
		// loginTB.setText("E-mail");
		content.add(loginTB);
		InlineLabel passwordLabel = new InlineLabel("Senha");
		content.add(passwordLabel);
		final PasswordTextBox passwordTB = new PasswordTextBox();
		content.add(passwordTB);
		HorizontalPanel botoes = new HorizontalPanel();
		Button loginButton = new Button();
		loginButton.setText("Login");
		botoes.add(loginButton);
		Button fechar = new Button();
		fechar.setText("fechar");
		botoes.add(fechar);
		content.add(botoes);
		setWidget(content);
		loginButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loginService.logarUsuario(loginTB.getText(),
						passwordTB.getText(), new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
								if (result == null) {
									hide();
									LoginUI.getInstance().loadLoginField();
								} else {
									Window.alert(result);
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert(RegistroErros.LOGIN);
							}
						});
			}
		});
		fechar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		// loginTB.addFocusHandler(new FocusHandler() {
		//
		// @Override
		// public void onFocus(FocusEvent event) {
		// if (loginTB.getText().equals("E-mail"))
		// loginTB.setText("");
		// }
		// });
		// loginTB.addBlurHandler(new BlurHandler() {
		//
		// @Override
		// public void onBlur(BlurEvent event) {
		// if (loginTB.getText().equals(""))
		// loginTB.setText("E-mail");
		// }
		// });
	}
}
