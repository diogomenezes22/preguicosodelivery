package com.preguicoso.client.backend.cardapio.ingredientes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.entities.cardapio.IngredienteBean;
import com.preguicoso.shared.utils.MoneyUtils;

public class IngredienteUi extends Composite {

	private static IngredienteUiUiBinder uiBinder = GWT
			.create(IngredienteUiUiBinder.class);

	interface IngredienteUiUiBinder extends UiBinder<Widget, IngredienteUi> {
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	@UiField
	InlineLabel nome;
	@UiField
	InlineLabel preco;
	@UiField
	Image remove;
	@UiField
	FocusPanel focus;

	IngredienteBean ib;

	public IngredienteUi(IngredienteBean ib) {
		initWidget(uiBinder.createAndBindUi(this));
		this.ib = ib;
		nome.setText(ib.getNome());
		preco.setText(MoneyUtils.parseString(ib.getPreco()));
		remove.setVisible(false);
	}

	@UiHandler("focus")
	void onFocusMouseOver(MouseOverEvent event) {
		remove.setVisible(true);
	}

	@UiHandler("focus")
	void onFocusMouseOut(MouseOutEvent event) {
		remove.setVisible(false);
	}

	@UiHandler("remove")
	void onRemoveClick(ClickEvent event) {
		cardapioService.removeIngrediente(ib, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				removeFromParent();
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

}
