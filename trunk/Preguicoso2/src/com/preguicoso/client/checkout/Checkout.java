package com.preguicoso.client.checkout;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.entities.EstabelecimentoBean;

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
	RadioButton pagseguro;
	@UiField
	HTMLPanel enderecoBox;
	@UiField
	HTMLPanel loginBox;

	private final CheckoutServiceAsync checkoutService = GWT
			.create(CheckoutService.class);

	interface CheckoutUiBinder extends UiBinder<Widget, Checkout> {
	}

	EstabelecimentoBean eb;

	public Checkout(EstabelecimentoBean eb) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eb = eb;
		checkoutService.getBairrosAtendidos(eb,
				new AsyncCallback<List<String>>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(List<String> result) {
						loginAtivado(result);
						enderecoAtivado(result);
					}

				});
	}

	private void loginAtivado(final List<String> listaBairros) {
		login.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loginBox.clear();
				enderecoBox.clear();
				loginBox.setVisible(true);
				loginBox.add(new LoginCompra(listaBairros));
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
				enderecoBox.add(new EnderecoBox(eb, listaBairros));
				loginBox.setVisible(false);
			}
		});
	}

	@UiHandler("dinheiro")
	void onDinheiroClick(ClickEvent event) {
	}

}
