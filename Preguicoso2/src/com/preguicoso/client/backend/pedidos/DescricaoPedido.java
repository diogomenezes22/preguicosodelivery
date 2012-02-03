package com.preguicoso.client.backend.pedidos;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.entities.ItemCardapioBean;
import com.preguicoso.shared.entities.PedidoBean;

public class DescricaoPedido extends Composite {

	private static DescricaoPedidoUiBinder uiBinder = GWT
			.create(DescricaoPedidoUiBinder.class);

	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);
	private final PedidoServiceAsync pedidoService = GWT
			.create(PedidoService.class);

	interface DescricaoPedidoUiBinder extends UiBinder<Widget, DescricaoPedido> {
	}

	@UiField
	Image enviar;
	@UiField
	Image remove;
	@UiField
	Image print;
	@UiField
	InlineLabel endereco;
	@UiField
	InlineLabel formaPagamento;
	@UiField
	InlineLabel numero;
	@UiField
	HTMLPanel listaPedidos;

	PedidoBean pb;
	PedidoUi pu;

	public DescricaoPedido(final PedidoBean pb, final PedidoUi pu) {
		initWidget(uiBinder.createAndBindUi(this));
		this.pb = pb;
		this.pu = pu;
		int cont = 1;
		endereco.setText(pb.getRua() + ", " + pb.getBairro());
		formaPagamento.setText(pb.getFormaPagamento());
		List<ItemCardapioBean> lista = pb.getListaItens();
		if (lista != null)
			for (ItemCardapioBean itemCardapioBean : lista) {
				ItemListaDescricaoPedido i = new ItemListaDescricaoPedido(
						itemCardapioBean);
				if (cont % 2 == 1)
					i.setOdd();
				listaPedidos.add(i);
				cont++;
			}
		enviar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cadastroService.setStatusPedido(pb.getId(),
						RegistroStatusPedido.enviado,
						new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Ocorreu um erro ao tentar enviar o pedido. "
										+ "Recarregue a página. Se o pedido não estiver presente "
										+ "na aba Ordem de Pedidos, significa que o pedido foi enviado. "
										+ "Caso ele continue na aba Ordem de Pedidos, "
										+ "tente enviá-lo novamente.");
							}

							@Override
							public void onSuccess(Void result) {
								removeUi(pu);
								Window.alert("Pedido enviado com sucesso.");
							}
						});

			}
		});
	}

	// TODO @Osman limitar o tamanho escrito no textarea e em outros campos de
	// texto digitado
	@UiHandler("remove")
	void onRemoveClick(ClickEvent event) {
		final DialogBox dialog = new DialogBox();
		dialog.setText("Recusar pedido");
		dialog.setPopupPosition(event.getClientX(), event.getClientY());
		VerticalPanel contentDialog = new VerticalPanel();
		final TextArea motivoArea = new TextArea();
		motivoArea.setPixelSize(50, 70);
		contentDialog.add(motivoArea);
		HorizontalPanel botoes = new HorizontalPanel();
		Button ok = new Button();
		ok.setText("ok");
		botoes.add(ok);
		Button fechar = new Button();
		fechar.setText("fechar");
		botoes.add(fechar);
		contentDialog.add(botoes);
		dialog.setWidget(contentDialog);
		dialog.show();
		ok.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!motivoArea.getText().isEmpty()) {
					setMotivoAndStatusRecusado(motivoArea.getText());
					dialog.hide();
				} else {
					Window.alert("Escreva o motivo.");
				}
			}
		});
		fechar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dialog.hide();
			}
		});
	}

	private void setMotivoAndStatusRecusado(String motivo) {
		pedidoService.setMotivo(motivo, pb, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					cadastroService.setStatusPedido(pb.getId(),
							RegistroStatusPedido.recusado,
							new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Não foi possível rejeitar o pedido do cliente "
											+ pb.getNomeCliente()
											+ ". Recarregue a página e tente novamente.");
								}

								@Override
								public void onSuccess(Void result) {
									removeUi(pu);
									Window.alert("O pedido do cliente "
											+ pb.getNomeCliente()
											+ " foi rejeitado corretamente.");
								}
							});
				} else {
					Window.alert("Digite o motivo.");
				}
			}
		});
	}

	private void removeUi(final PedidoUi pu) {
		DescricaoPedido.this.removeFromParent();
		pu.removeFromParent();
	}

	@UiHandler("print")
	void onPrintClick(ClickEvent event) {
		Window.print();
	}
}