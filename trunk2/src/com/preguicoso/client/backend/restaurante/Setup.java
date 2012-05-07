package com.preguicoso.client.backend.restaurante;

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
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.client.login.LoginServiceAsync;

public class Setup extends Composite {

	private static SetupUiBinder uiBinder = GWT.create(SetupUiBinder.class);

	interface SetupUiBinder extends UiBinder<Widget, Setup> {
	}

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	@UiField
	PasswordTextBox passwordOld;
	@UiField
	PasswordTextBox passwordNew;
	@UiField
	PasswordTextBox passwordNewCheck;
	@UiField
	Button mudar;

	public Setup() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("mudar")
	void onMudarClick(ClickEvent event) {
		loginService.changePasswordUsuarioEstabelecimento(
				passwordOld.getText(), passwordNew.getText(),
				passwordNewCheck.getText(), new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Não foi possível mudar sua senha.");
					}

					@Override
					public void onSuccess(String result) {
						Window.alert(result);
					}
				});
	}
}
