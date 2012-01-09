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
		cadastroService.getListaDePedidos((long) 405,
				new AsyncCallback<List<PedidoBean>>() {

					@Override
					public void onFailure(Throwable arg0) {
						Window.alert("Erro ao carregar lista de pedidos. Recarregue a página.");
					}

					@Override
					public void onSuccess(List<PedidoBean> result) {
						if (result != null) {
							if (!result.isEmpty()) {
								PedidoUi pu;
								BairroUi bu;
								String bairroAtual = "";
								for (PedidoBean pb : result) {
									// TODO @Osman talvez seja melhor criar
									// entidades
									// separadas para Pedidos atuais e enviados
									if (!pb.getEnviado()) {
										if (!pb.getBairro().equals(bairroAtual)) {
											bu = new BairroUi(pb.getBairro());
											listaPanel.add(bu);
											bairroAtual = pb.getBairro();
										}
										pu = new PedidoUi(pb);
										if (pb.getVisto())
											pu.setStyleName("visualizada");
										listaPanel.add(pu);
									}
								}
							} else {
								BairroUi bu = new BairroUi(
										"Sem pedidos no momento");
								listaPanel.add(bu);
							}
						}

					}

				});

	}
}