package com.preguicoso.client.checkout;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.client.login.LoginServiceAsync;
import com.preguicoso.shared.RegistroFormaPagamento;
import com.preguicoso.shared.entities.EstabelecimentoBean;

public class Checkout extends Composite {

	private static CheckoutUiBinder uiBinder = GWT
			.create(CheckoutUiBinder.class);
	@UiField
	RadioButton login;
	@UiField
	RadioButton endereco;
	@UiField
	HTMLPanel formasPagamento;
	@UiField
	HTMLPanel enderecoBox;
	@UiField
	HTMLPanel loginBox;
	@UiField
	HTMLPanel enderecoEntrega;
	@UiField
	HTMLPanel trocoPanel;
	@UiField
	IntegerBox trocoBox;

	private final CheckoutServiceAsync checkoutService = GWT
			.create(CheckoutService.class);
	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	interface CheckoutUiBinder extends UiBinder<Widget, Checkout> {
	}

	EstabelecimentoBean eb;
	List<RadioButton> listaPagamento;

	public Checkout(EstabelecimentoBean eb) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eb = eb;
		trocoPanel.setVisible(false);
		enderecoEntrega.setVisible(false);
		carregarFormasDePagamento(eb);
		checkoutService.getBairrosAtendidos(eb,
				new AsyncCallback<List<String>>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(List<String> result) {
						verificarLogado(result);
						loginAtivado(result);
						enderecoAtivado(result);
					}

				});
	}

	private void verificarLogado(final List<String> listaBairros) {
		loginService.isUsuarioLogado(new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caght) {
			}

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					loginBox.add(new LoginCompra(listaBairros, Checkout.this,
							true));
				} else {
					enderecoEntrega.setVisible(true);
				}
			}
		});
	}

	private void carregarFormasDePagamento(EstabelecimentoBean eb) {
		listaPagamento = new ArrayList<RadioButton>();
		RadioButton radioForma;
		for (final RegistroFormaPagamento forma : eb.getFormasPagamento()) {
			radioForma = new RadioButton("forma_pagamento", forma.asHTML(),
					true);
			radioForma.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if (forma.equals(RegistroFormaPagamento.dinheiro)) {
						trocoPanel.setVisible(true);
					} else {
						trocoPanel.setVisible(false);
					}
				}
			});
			listaPagamento.add(radioForma);
			formasPagamento.add(radioForma);
		}
	}

	private void loginAtivado(final List<String> listaBairros) {
		login.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loginBox.clear();
				enderecoBox.clear();
				loginBox.setVisible(true);
				loginBox.add(new LoginCompra(listaBairros, Checkout.this, false));
				enderecoBox.setVisible(false);
			}
		});
	}

	private void enderecoAtivado(final List<String> listaBairros) {
		endereco.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				enderecoBox.clear();
				loginBox.clear();
				enderecoBox.setVisible(true);
				enderecoBox
						.add(new EnderecoBox(eb, listaBairros, Checkout.this));
				loginBox.setVisible(false);
			}
		});
	}

	public boolean isPagamentoChecked() {
		for (RadioButton radio : listaPagamento) {
			if (radio.getValue()) {
				return true;
			}
		}
		return false;
	}

	public String getPagamentoChecked() {
		for (RadioButton radio : listaPagamento) {
			if (radio.getValue()) {
				return radio.getText();
			}
		}
		return null;
	}

	public boolean isDinheiroChecked() {
		if (trocoPanel.isVisible()) {
			return true;
		}
		return false;
	}

	public boolean isTrocoFull() {
		if (trocoBox.getText().length() != 0)
			return true;
		return false;
	}
}
