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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.entities.cardapio.OpcoesBean;

public class OpcoesUi extends Composite {

	private static OpcoesUiUiBinder uiBinder = GWT
			.create(OpcoesUiUiBinder.class);

	interface OpcoesUiUiBinder extends UiBinder<Widget, OpcoesUi> {
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	@UiField
	FocusPanel opcoes;
	@UiField
	InlineLabel nomeCategoria;
	@UiField
	Image remove;

	OpcoesBean ob;

	public OpcoesUi(OpcoesBean ob) {
		initWidget(uiBinder.createAndBindUi(this));
		this.ob = ob;
		nomeCategoria.setText(ob.getNome());
		remove.setVisible(false);
	}

	@UiHandler("opcoes")
	void onOpcoesClick(ClickEvent event) {
		// TODO @Osman ERRO não sei resolver essa porra
		RootPanel.get("editarItem").clear();
		RootPanel.get("editarItem").add(new DescricaoOpcao(ob));
	}

	@UiHandler("opcoes")
	void onOpcoesMouseOver(MouseOverEvent event) {
		remove.setVisible(true);
	}

	@UiHandler("opcoes")
	void onOpcoesMouseOut(MouseOutEvent event) {
		remove.setVisible(false);
	}

	@UiHandler("remove")
	void onRemoveClick(ClickEvent event) {
		cardapioService.removeOpcoes(ob, new AsyncCallback<Void>() {

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
