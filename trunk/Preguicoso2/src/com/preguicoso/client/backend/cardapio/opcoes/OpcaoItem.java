package com.preguicoso.client.backend.cardapio.opcoes;

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
import com.preguicoso.shared.entities.cardapio.OpcoesBean;

public class OpcaoItem extends Composite {

	private static OpcaoItemUiBinder uiBinder = GWT
			.create(OpcaoItemUiBinder.class);

	interface OpcaoItemUiBinder extends UiBinder<Widget, OpcaoItem> {
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	@UiField
	InlineLabel valueOpcao;
	@UiField
	Image remove;
	@UiField
	FocusPanel focus;

	String opcao;
	OpcoesBean ob;

	public OpcaoItem(String opcao, OpcoesBean ob) {
		initWidget(uiBinder.createAndBindUi(this));
		this.opcao = opcao;
		this.ob = ob;
		valueOpcao.setText(opcao);
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
		ob.getListaOpcoes().remove(opcao);
		cardapioService.updateOpcoes(ob, new AsyncCallback<Void>() {

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
