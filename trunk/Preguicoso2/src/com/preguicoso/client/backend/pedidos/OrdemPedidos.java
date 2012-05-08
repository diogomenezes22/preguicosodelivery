package com.preguicoso.client.backend.pedidos;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.RegistroToken;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.entities.PedidoBean;

public class OrdemPedidos extends Composite {

	// TODO @Osman ||OrdemPedidos|| gambiarra do Singleton
	private static OrdemPedidos instance = null;
	private static Long idEstabelecimento = null;
	private static InlineLabel conexao = null;

	public static OrdemPedidos getInstance(final Long idEstabelecimento,
			final InlineLabel conexao) {
		if (instance == null) {
			if (!idEstabelecimento.equals(OrdemPedidos.idEstabelecimento)
					|| !conexao.equals(OrdemPedidos.conexao)) {
				OrdemPedidos.idEstabelecimento = idEstabelecimento;
				OrdemPedidos.conexao = conexao;
				instance = new OrdemPedidos(idEstabelecimento, conexao);
			}
		}
		return instance;
	}

	private static OrdemPedidosUiBinder uiBinder = GWT
			.create(OrdemPedidosUiBinder.class);

	@UiField
	HTMLPanel listaPanel;

	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);

	interface OrdemPedidosUiBinder extends UiBinder<Widget, OrdemPedidos> {
	}

	private OrdemPedidos(final Long idEstabelecimento, final InlineLabel conexao) {
		initWidget(uiBinder.createAndBindUi(this));

		carregaListaDePedidos(idEstabelecimento);
		// TODO @Osman existe a possibilidade de o pedido não ser atualizado
		// caso seja
		// enviado um pedido no instante em que o cara abriu a aba Ordem de
		// Pedidos
		final Date lastTimeStamp = new Date();

		Timer t = new Timer() {

			@Override
			public void run() {
				if (History.getToken().equals(RegistroToken.ordemPedidos)) {
					cadastroService.getPedidosNovos(idEstabelecimento,
							lastTimeStamp,
							new AsyncCallback<List<PedidoBean>>() {

								@Override
								public void onFailure(Throwable caught) {
									conexao.setText("Nível da Conexão "
											+ getStatusConexao(false));
									Window.alert("Servidor fora do ar: "+caught.getMessage());
								}

								@Override
								public void onSuccess(List<PedidoBean> result) {
									conexao.setText("Nível da Conexão "
											+ getStatusConexao(true));
									if (!result.isEmpty()) {
										lastTimeStamp.setTime(result.get(0)
												.getTimeStamp().getTime());
										if (result.size() == 1)
											Window.alert("Acaba de chegar um novo pedido!");
										else
											Window.alert("Acabam de chegar "
													+ result.size()
													+ " novos pedidos!");
										carregaListaDePedidos(idEstabelecimento);
									}
								}
							});
				}
			}
		};
		t.scheduleRepeating(10000);
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
							if (!result.isEmpty()) {
								PedidoUi pu;
								BairroUi bu;
								String bairroAtual = "";
								for (PedidoBean pb : result) {
									if (!pb.getBairro().equals(bairroAtual)) {
										bu = new BairroUi(pb.getBairro());
										listaPanel.add(bu);
										bairroAtual = pb.getBairro();
									}
									pu = new PedidoUi(pb);
									if (pb.getStatus() == RegistroStatusPedido.visto)
										pu.setStyleName("visualizada");
									listaPanel.add(pu);
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

	private int successSaldo = 0;

	private String getStatusConexao(boolean onSuccess) {
		if (onSuccess)
			successSaldo++;
		else
			successSaldo--;
		if (successSaldo <= -2)
			return "*....";
		if (successSaldo <= -1)
			return "**...";
		if (successSaldo <= 0)
			return "***..";
		if (successSaldo <= 1)
			return "****.";
		return "*****";
	}
}
