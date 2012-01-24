package com.preguicoso.client.checkout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;

public class Checkout extends Composite {

	private static CheckoutUiBinder uiBinder = GWT
			.create(CheckoutUiBinder.class);
	@UiField
	RadioButton login;
	@UiField
	RadioButton endereco;
	@UiField
	RadioButton dinheiro;
	@UiField
	HTMLPanel enderecoBox;
	@UiField
	HTMLPanel loginBox;
	@UiField
	RadioButton pagseguro;

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	interface CheckoutUiBinder extends UiBinder<Widget, Checkout> {
	}

	public Checkout() {
		initWidget(uiBinder.createAndBindUi(this));
		// enderecoBox.setVisible(false);
		loginBox.setVisible(false);
		// endereco.addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// enderecoBox.add(new EnderecoBox());
		// }
		// });
	}

	public void testarEnvio() {
		// if ((pagseguro.getValue() || dinheiro.getValue())
		// && (login.getValue() || endereco.getValue()))
		// if (enderecoValido())
		// pedir.setEnabled(true);

	}

	private boolean enderecoValido() {
		return false;
	}

	@UiHandler("login")
	void onLoginClick(ClickEvent event) {
		enderecoBox.setVisible(false);
		loginBox.setVisible(true);
		testarEnvio();
	}

	@UiHandler("endereco")
	void onEnderecoClick(ClickEvent event) {
		enderecoBox.add(new EnderecoBox());
		loginBox.setVisible(false);
		// testarEnvio();
	}

	@UiHandler("dinheiro")
	void onDinheiroClick(ClickEvent event) {
		testarEnvio();
	}

}
