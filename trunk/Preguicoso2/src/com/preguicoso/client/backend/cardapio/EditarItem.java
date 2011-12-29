package com.preguicoso.client.backend.cardapio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.entities.ItemCardapioBean;

public class EditarItem extends Composite {

	private static EditarItemUiBinder uiBinder = GWT
			.create(EditarItemUiBinder.class);

	@UiField
	HTMLPanel item;
	@UiField
	SimpleCheckBox disponivel;
	@UiField
	ListBox categoria;
	@UiField
	TextBox numero;
	@UiField
	TextBox nome;
	@UiField
	TextArea descricao;
	@UiField
	TextBox preco;

	interface EditarItemUiBinder extends UiBinder<Widget, EditarItem> {
	}

	public EditarItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public EditarItem(ItemCardapioBean i) {
		initWidget(uiBinder.createAndBindUi(this));
		numero.setText("" + i.getId());
		nome.setText(i.getNome());
		descricao.setText(i.getDescricao());
		categoria.setTitle(i.getCategoriaBean().getNome());
		preco.setText("R$ " + i.getPreco());

	}

}
