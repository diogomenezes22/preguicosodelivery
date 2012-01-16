package com.preguicoso.client.backend.pedidos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.entities.PedidoBean;

public class PedidoAntigoUi extends Composite {

	private static PedidoAntigoUiUiBinder uiBinder = GWT
			.create(PedidoAntigoUiUiBinder.class);

	interface PedidoAntigoUiUiBinder extends UiBinder<Widget, PedidoAntigoUi> {
	}

	public PedidoAntigoUi() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	InlineLabel nomeCliente;
	@UiField
	InlineLabel rua;
	@UiField
	InlineLabel timestamp;
	@UiField
	FocusPanel pedido;

	public PedidoAntigoUi(PedidoBean pb) {
		initWidget(uiBinder.createAndBindUi(this));
		this.nomeCliente.setText(pb.getNomeCliente());
		this.rua.setText(pb.getRua());

		DateTimeFormat dtf = DateTimeFormat.getFormat("HH:mm - dd/MM/yyyy");
		this.timestamp.setText(dtf.format(pb.getTimeStamp()));
	}
}
