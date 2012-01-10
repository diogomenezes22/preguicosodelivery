package com.preguicoso.client.backend.pedidos;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;
import com.preguicoso.shared.entities.ItemCardapioBean;
import com.preguicoso.shared.entities.PedidoBean;

public class DescricaoPedido extends Composite {

	private static DescricaoPedidoUiBinder uiBinder = GWT
			.create(DescricaoPedidoUiBinder.class);

	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);

	interface DescricaoPedidoUiBinder extends UiBinder<Widget, DescricaoPedido> {
	}

	@UiField
	Image enviar;
	@UiField
	InlineLabel endereco;
	@UiField
	InlineLabel formaPagamento;
	@UiField
	InlineLabel numero;
	@UiField
	HTMLPanel pedidos;

	public DescricaoPedido() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public DescricaoPedido(final PedidoBean pb, final PedidoUi pu) {
		initWidget(uiBinder.createAndBindUi(this));
		endereco.setText(pb.getRua() + ", " + pb.getBairro());
		formaPagamento.setText(pb.getFormaPagamento());
		List<ItemCardapioBean> lista = pb.getListaItens();
		if (lista != null)
			for (ItemCardapioBean itemCardapioBean : lista) {
				pedidos.add(new HTMLPanel(itemCardapioBean.getDescricao()));
			}
		enviar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cadastroService.setPedidoEnviado(pb.getId(),
						new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								DescricaoPedido.this.removeFromParent();
								pu.removeFromParent();
								RootPanel.get("Ordem");
								Window.alert("Pedido enviado com sucesso.");
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Ocorreu um erro ao tentar enviar o pedido. "
										+ "Recarregue a página. Se o pedido não estiver presente "
										+ "na aba Ordem de Pedidos, significa que o pedido foi enviado. "
										+ "Caso ele continue na aba Ordem de Pedidos, "
										+ "tente enviá-lo novamente.");
							}
						});
			}
		});
	}
}