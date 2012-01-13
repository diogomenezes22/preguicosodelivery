package com.preguicoso.client.backend.pedidos;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
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

		final Long idEstabelecimento = (long) 405;
		carregaListaDePedidos(idEstabelecimento);
		// TODO @Osman existe a possibilidade de o pedido não ser atualizado
		// caso seja
		// enviado um pedido no instante em que o cara abriu a aba Ordem de
		// Pedidos
		final Date lastTimeStamp = new Date();

		Timer t = new Timer() {

			@Override
			public void run() {
				cadastroService.getPedidosNovos(idEstabelecimento,
						lastTimeStamp, new AsyncCallback<List<PedidoBean>>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Ocorreu um erro no processo de busca de pedidos remota. "
										+ "Por favor, recarregue a página.");
							}

							@Override
							public void onSuccess(List<PedidoBean> result) {
								if (!result.isEmpty()) {
									lastTimeStamp.setTime(result.get(0)
											.getTimeStamp().getTime());
									Window.alert("Acaba de chegar um novo pedido!");
									carregaListaDePedidos(idEstabelecimento);
								}
							}
						});
			}
		};
		t.scheduleRepeating(30000);
	}

	private void carregaListaDePedidos(final Long idEstabelecimento) {

		// TODO @Osman fazer de um jeito otimizado no futuro
		if (this.listaPanel != null) {
			this.listaPanel.clear();
		}
		cadastroService.getListaDePedidos(idEstabelecimento,
				new AsyncCallback<List<PedidoBean>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Erro ao carregar lista de pedidos. Recarregue a página.");
					}

					@Override
					public void onSuccess(List<PedidoBean> result) {
						if (result != null) {
							boolean todosEnviados = true;
							if (!result.isEmpty()) {
								PedidoUi pu;
								BairroUi bu;
								String bairroAtual = "";
								for (PedidoBean pb : result) {
									// TODO @Osman talvez seja melhor criar
									// entidades
									// separadas para Pedidos atuais e enviados
									if (!pb.getEnviado()) {
										todosEnviados = false;
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
								if (todosEnviados) {
									printPedidosEmpty();
								}
							} else {
								printPedidosEmpty();
							}
						}

					}

					private void printPedidosEmpty() {
						BairroUi bu = new BairroUi("Sem pedidos no momento");
						listaPanel.add(bu);
					}

				});
	}
}
