package com.preguicoso.client.backend.cardapio.opcoes;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.entities.cardapio.OpcoesBean;

public class DescricaoOpcao extends Composite {

	private static DescricaoOpcaoUiBinder uiBinder = GWT
			.create(DescricaoOpcaoUiBinder.class);

	interface DescricaoOpcaoUiBinder extends UiBinder<Widget, DescricaoOpcao> {
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	@UiField
	InlineLabel nomeOpcao;
	@UiField
	InlineLabel nomeOpcao2;
	@UiField
	HTMLPanel listaPanel;
	@UiField
	TextBox novaOpcao;
	@UiField
	Button criar;

	OpcoesBean ob;

	public DescricaoOpcao(final OpcoesBean ob) {
		initWidget(uiBinder.createAndBindUi(this));
		this.ob = ob;
		nomeOpcao.setText(ob.getNome());
		nomeOpcao2.setText(ob.getNome());
		if (ob.getListaOpcoes() != null) {
			if (!ob.getListaOpcoes().isEmpty()) {
				for (String s : ob.getListaOpcoes()) {
					listaPanel.add(new OpcaoItem(s, this.ob));
				}
			}
		} else {
			ob.setListaOpcoes(new ArrayList<String>());
		}
	}

	@UiHandler("criar")
	void onCriarClick(ClickEvent event) {
		ob.getListaOpcoes().add(novaOpcao.getText());
		cardapioService.updateOpcoes(ob, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				listaPanel.clear();
				for (String s : ob.getListaOpcoes()) {
					listaPanel.add(new OpcaoItem(s, ob));
				}
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

}
