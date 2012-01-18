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
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.entities.PedidoBean;

public class HistoricoPedidos extends Composite {

	private static HistoricoPedidosUiBinder uiBinder = GWT
			.create(HistoricoPedidosUiBinder.class);

	interface HistoricoPedidosUiBinder extends
			UiBinder<Widget, HistoricoPedidos> {
	}

	@UiField
	HTMLPanel listaPanel;

	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);

	public HistoricoPedidos() {
		initWidget(uiBinder.createAndBindUi(this));

		final Long idEstabelecimento = (long) 405;
		carregaListaDePedidos(idEstabelecimento);
	}

	private void carregaListaDePedidos(final Long idEstabelecimento) {

		cadastroService.getListaHistoricoDePedidos(idEstabelecimento,
				new AsyncCallback<List<PedidoBean>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Erro ao carregar histórico de pedidos. Recarregue a página.");
					}

					@Override
					public void onSuccess(List<PedidoBean> result) {
						if (result != null) {
							if (!result.isEmpty()) {
								PedidoAntigoUi pu;
								BairroUi bu;
								String bairroAtual = "";
								for (PedidoBean pb : result) {
									if (!pb.getBairro().equals(bairroAtual)) {
										bu = new BairroUi(pb.getBairro());
										listaPanel.add(bu);
										bairroAtual = pb.getBairro();
									}
									pu = new PedidoAntigoUi(pb);
									if (pb.getStatus() == RegistroStatusPedido.recusado)
										pu.setStyleName("recusada");
									listaPanel.add(pu);
								}
							} else {
								BairroUi bu = new BairroUi(
										"Não há nenhum pedido no histórico.");
								listaPanel.add(bu);
							}
						}

					}
				});
	}
}
