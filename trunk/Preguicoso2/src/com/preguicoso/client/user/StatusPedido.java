package com.preguicoso.client.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.RegistroToken;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.client.login.LoginServiceAsync;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.entities.PedidoBean;

public class StatusPedido extends Composite {

	// TODO @Osman ||StatusPedido|| gambiarra do Singleton
	private static StatusPedido instance = null;

	public static StatusPedido getInstance() {
		if (instance == null) {
			instance = new StatusPedido();
		}
		return instance;
	}

	private static StatusPedidoUiBinder uiBinder = GWT
			.create(StatusPedidoUiBinder.class);

	interface StatusPedidoUiBinder extends UiBinder<Widget, StatusPedido> {
	}

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	@UiField
	Label mensagem;
	@UiField
	InlineLabel motivo;

	private StatusPedido() {
		initWidget(uiBinder.createAndBindUi(this));
		printStatus();
		Timer t = new Timer() {
			@Override
			public void run() {
				if (History.getToken().equals(RegistroToken.statusPedido))
					printStatus();
			}

		};
		t.scheduleRepeating(10000);
	}

	private void printStatus() {
		loginService.getPedidoAtualByUser(new AsyncCallback<PedidoBean>() {

			@Override
			public void onSuccess(PedidoBean result) {
				if (result != null) {
					if (result.getStatus().equals(
							RegistroStatusPedido.esperando)) {
						RootPanel.get("status_esperando")
								.addStyleName("active");
						mensagem.setText("Seu Pedido foi recebido pelo restaurante");
					} else if (result.getStatus().equals(
							RegistroStatusPedido.visto)) {
						RootPanel.get("status_esperando")
								.addStyleName("active");
						RootPanel.get("status_entrega").addStyleName("active");
						mensagem.setText("Seu Pedido foi enviado para cozinha");
					} else if (result.getStatus().equals(
							RegistroStatusPedido.enviado)) {
						RootPanel.get("status_esperando")
								.addStyleName("active");
						RootPanel.get("status_entrega").addStyleName("active");
						RootPanel.get("status_enviado").addStyleName("active");
						mensagem.setText("Seu pedido já está a caminho");
					} else if (result.getStatus().equals(
							RegistroStatusPedido.recusado)) {
						mensagem.setText("Pedido recusado.");
						motivo.setText("Motivo: " + result.getMotivo());
					}
				} else {
					History.newItem(RegistroToken.index);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

}
