package com.preguicoso.client.backend.pedidos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.entities.PedidoBean;

public class PedidoUi extends Composite {

	private static PedidoUiUiBinder uiBinder = GWT
			.create(PedidoUiUiBinder.class);

	interface PedidoUiUiBinder extends UiBinder<Widget, PedidoUi> {
	}

	@UiField
	InlineLabel nomeCliente;
	@UiField
	InlineLabel rua;
	@UiField
	InlineLabel timestamp;

	public PedidoUi() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public PedidoUi(PedidoBean pb) {
		initWidget(uiBinder.createAndBindUi(this));
		this.nomeCliente.setText(pb.getNomeCliente());
		this.rua.setText(pb.getRua());

		DateTimeFormat dtf = DateTimeFormat.getFormat("h:mm a dd/MM/yyyy");
		this.timestamp.setText(dtf.format(pb.getTimeStamp()));
	}
}
