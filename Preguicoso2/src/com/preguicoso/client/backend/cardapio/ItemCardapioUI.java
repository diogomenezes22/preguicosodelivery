package com.preguicoso.client.backend.cardapio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.entities.ItemCardapioBean;

public class ItemCardapioUI extends Composite {

	private static itemCardapioUiBinder uiBinder = GWT
			.create(itemCardapioUiBinder.class);
	@UiField
	InlineLabel numero;
	@UiField
	InlineLabel nome;
	@UiField
	InlineLabel preco;
	@UiField
	FocusPanel item;

	interface itemCardapioUiBinder extends UiBinder<Widget, ItemCardapioUI> {
	}

	public ItemCardapioUI() {
		initWidget(uiBinder.createAndBindUi(this));
		item.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("abraao");
			}
		});
	}

	public ItemCardapioUI(String numero, String nome, String preco) {
		initWidget(uiBinder.createAndBindUi(this));
		this.nome.setText(nome);
		this.numero.setText(numero);
		this.preco.setText(preco);
		item.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("editarItem").clear();
				RootPanel.get("editarItem").add(new EditarItem());

			}
		});
	}

	public ItemCardapioUI(final ItemCardapioBean i) {
		initWidget(uiBinder.createAndBindUi(this));
		this.nome.setText(i.getNome());
		this.numero.setText(i.getId().toString());
		this.preco.setText("R$ " + i.getPreco().toString());
		item.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("editarItem").clear();
				RootPanel.get("editarItem").add(new EditarItem(i));
			}
		});
	}

	@UiHandler("numero")
	void onNumeroClick(ClickEvent event) {
	}

	@UiHandler("nome")
	void onNomeClick(ClickEvent event) {
	}

	@UiHandler("preco")
	void onPrecoClick(ClickEvent event) {
	}
}
