package com.preguicoso.client.backend.cardapio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditarCardapio extends Composite {

	private static EditarCardapioUiBinder uiBinder = GWT
			.create(EditarCardapioUiBinder.class);
	@UiField
	HTMLPanel lista;
	@UiField
	Button itemButton;
	@UiField
	Button itemCategoria;

	interface EditarCardapioUiBinder extends UiBinder<Widget, EditarCardapio> {
	}

	public EditarCardapio() {
		initWidget(uiBinder.createAndBindUi(this));
		lista.add(new ItemCategoria("Abraao"));
		ItemCardapioUI item1 = new ItemCardapioUI("110", "Cerveja", "R$ 12,00");
		lista.add(item1);
		itemButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				lista.add(new ItemCardapioUI("123", "nao sei", "R$ 25,00"));
			}
		});
		itemCategoria.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				lista.add(new ItemCategoria("Nova Categoria"));
			}
		});
	}
}
