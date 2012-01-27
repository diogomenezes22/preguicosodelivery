package com.preguicoso.client.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.client.login.LoginServiceAsync;
import com.preguicoso.shared.entities.PedidoBean;

public class StatusPedido extends Composite {

	private static StatusPedidoUiBinder uiBinder = GWT
			.create(StatusPedidoUiBinder.class);

	interface StatusPedidoUiBinder extends UiBinder<Widget, StatusPedido> {
	}

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	@UiField
	InlineLabel status;

	public StatusPedido() {
		initWidget(uiBinder.createAndBindUi(this));
		printStatus();
		Timer t = new Timer() {

			@Override
			public void run() {
				printStatus();
			}

		};
		t.scheduleRepeating(30000);
	}

	private void printStatus() {
		loginService.getPedidoAtualBySession(new AsyncCallback<PedidoBean>() {

			@Override
			public void onSuccess(PedidoBean result) {
				if (result != null) {
					status.setText(result.getStatus().name());
				} else {
					status.setText("Você não tem pedidos no momento");
				}
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});
	}

}
