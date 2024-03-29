package com.preguicoso.client.backend.pedidos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.entities.PedidoBean;

public class PedidoUi extends Composite {

	private static PedidoUiUiBinder uiBinder = GWT
			.create(PedidoUiUiBinder.class);

	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);

	interface PedidoUiUiBinder extends UiBinder<Widget, PedidoUi> {
	}

	@UiField
	InlineLabel nomeCliente;
	@UiField
	InlineLabel rua;
	@UiField
	InlineLabel timestamp;
	@UiField
	FocusPanel pedido;

	public PedidoUi() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	private PedidoBean pb;

	public PedidoUi(PedidoBean pb) {
		initWidget(uiBinder.createAndBindUi(this));
		this.pb = pb;
		this.nomeCliente.setText(pb.getNomeCliente());
		this.rua.setText(pb.getRua());

		// TODO @Osman e o horário de verão? Como fica?
		DateTimeFormat dtf = DateTimeFormat.getFormat("HH:mm - dd/MM/yyyy");
		this.timestamp.setText(dtf.format(pb.getTimeStamp()));
	}

	@UiHandler("pedido")
	void onPedidoClick(ClickEvent event) {
		RootPanel.get("editarItem").clear();
		RootPanel.get("editarItem").add(new DescricaoPedido(pb, this));
		this.setStyleName("visualizada");
		cadastroService.setStatusPedido(pb.getId(), RegistroStatusPedido.visto,
				new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Void result) {
					}
				});
	}
}
