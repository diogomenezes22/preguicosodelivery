package com.preguicoso.client.checkout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
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
	Button pedir;
	@UiField
	TextBox endereco_rua;
	@UiField
	TextBox endereco_numero;
	@UiField
	TextBox endereco_complemento;
	@UiField
	TextBox endereco_bairro;
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
		enderecoBox.setVisible(false);
		loginBox.setVisible(false);
		pedir.setEnabled(true);
		pedir.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cardapioService.enviarPedido("Sem Nome", endereco_rua.getText()
						+ " " + endereco_numero.getText(),
						endereco_bairro.getText(), dinheiro.getFormValue(),
						new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								Window.alert("Pedido enviado com sucesso!");
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Erro no Envio do pedido.");
							}
						});
			}
		});
	}

	public void testarEnvio() {
		if ((pagseguro.getValue() || dinheiro.getValue())
				&& (login.getValue() || endereco.getValue()))
			if (enderecoValido())
				pedir.setEnabled(true);

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
		enderecoBox.setVisible(true);
		loginBox.setVisible(false);
		testarEnvio();
	}

	@UiHandler("dinheiro")
	void onDinheiroClick(ClickEvent event) {
		testarEnvio();
	}

	@UiHandler("endereco_rua")
	void onEndereco_ruaFocus(FocusEvent event) {
		endereco_rua.setText("");
	}

	@UiHandler("endereco_rua")
	void onEndereco_ruaBlur(BlurEvent event) {
		if (endereco_rua.getText().equals(""))
			endereco_rua.setText("Logradouro");
	}

	@UiHandler("endereco_numero")
	void onEndereco_numeroFocus(FocusEvent event) {
		endereco_numero.setText("");
	}

	@UiHandler("endereco_numero")
	void onEndereco_numeroBlur(BlurEvent event) {
		if (endereco_numero.getText().equals(""))
			endereco_numero.setText("Número");
	}

	@UiHandler("endereco_bairro")
	void onEndereco_bairroFocus(FocusEvent event) {
		endereco_bairro.setText("");
	}

	@UiHandler("endereco_complemento")
	void onEndereco_complementoFocus(FocusEvent event) {
		endereco_complemento.setText("");
	}

	@UiHandler("endereco_bairro")
	void onEndereco_bairroBlur(BlurEvent event) {
		if (endereco_bairro.getText().equals(""))
			endereco_bairro.setText("Bairro");
	}

	@UiHandler("endereco_complemento")
	void onEndereco_complementoBlur(BlurEvent event) {
		if (endereco_complemento.getText().equals(""))
			endereco_complemento.setText("Complemento");
	}

}
