package com.preguicoso.client.estabelecimento.cardapio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CardapioItem extends Composite {

	private static CardapioItemUiBinder uiBinder = GWT
			.create(CardapioItemUiBinder.class);
	@UiField
	Label prato;
	@UiField
	Label descricao;
	@UiField
	Label preco;
	@UiField
	HTMLPanel botaoAdd;

	interface CardapioItemUiBinder extends UiBinder<Widget, CardapioItem> {
	}

	public CardapioItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public CardapioItem(String prato, String descricao, String preco,
			HTMLPanel botao) {
		initWidget(uiBinder.createAndBindUi(this));
		this.prato.setText(prato);
		this.descricao.setText(descricao);
		this.preco.setText(preco);
		this.botaoAdd.add(botao);
	}
}
