package com.preguicoso.client.backend.pedidos;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;
import com.preguicoso.shared.entities.PedidoBean;

public class OrdemPedidos extends Composite {

	private static OrdemPedidosUiBinder uiBinder = GWT
			.create(OrdemPedidosUiBinder.class);

	@UiField
	HTMLPanel listaPanel;

	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);

	interface OrdemPedidosUiBinder extends UiBinder<Widget, OrdemPedidos> {
	}

	public OrdemPedidos() {
		initWidget(uiBinder.createAndBindUi(this));
		BairroUi bu = new BairroUi("Jardim América");
		listaPanel.add(bu);
		cadastroService.getListaDePedidos((long) 385,
				new AsyncCallback<List<PedidoBean>>() {

					@Override
					public void onFailure(Throwable arg0) {
						Window.alert("Erro");
					}

					@Override
					public void onSuccess(List<PedidoBean> result) {
						PedidoUi pu;
						for (PedidoBean pb : result) {
							pu = new PedidoUi(pb);
							listaPanel.add(pu);
						}

					}

				});

	}
}
