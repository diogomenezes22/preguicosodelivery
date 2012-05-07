package com.preguicoso.client.checkout;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;
import com.preguicoso.shared.FormValidatorShared;

public class FormValidatorClient {

	CheckoutServiceAsync checkoutService;

	public FormValidatorClient(CheckoutServiceAsync checkoutService) {
		this.checkoutService = checkoutService;
	}

	public void verifyLogradouroValid(TextBox logradouro) {
		Character specialCharacter = FormValidatorShared
				.getFirstSpecialCharacter(logradouro.getText());
		if (specialCharacter != null) {
			Window.alert("Logradouro não pode ter \"" + specialCharacter + "\"");
		} else {
			checkoutService.isLogradouroTypeValid(logradouro.getText(),
					new AsyncCallback<Boolean>() {

						@Override
						public void onSuccess(Boolean result) {
							if (!result) {
								Window.alert("Logradouro inválido.");
							}
						}

						@Override
						public void onFailure(Throwable caught) {
						}
					});
		}
	}

	public static boolean isFormValid(String logradouro, String numero) {
		boolean isValid = false;
		if (logradouro.equals("Logradouro")) {
			Window.alert("Digite o logradouro.");
		} else if (numero.equals("Número")) {
			Window.alert("Digite o número.");
		} else if (!FormValidatorShared.hasNumberOnly(numero)) {
			Window.alert("O campo número deve conter apenas números.");
		} else {
			isValid = true;
		}
		return isValid;
	}

}
