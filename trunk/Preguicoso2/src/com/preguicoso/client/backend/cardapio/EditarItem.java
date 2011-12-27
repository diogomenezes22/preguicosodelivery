package com.preguicoso.client.backend.cardapio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class EditarItem extends Composite {

	private static EditarItemUiBinder uiBinder = GWT
			.create(EditarItemUiBinder.class);

	interface EditarItemUiBinder extends UiBinder<Widget, EditarItem> {
	}

	public EditarItem() {
		initWidget(uiBinder.createAndBindUi(this));

	}

}
