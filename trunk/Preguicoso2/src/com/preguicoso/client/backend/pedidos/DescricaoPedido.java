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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
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

	interface DescricaoPedidoUiBinder extends UiBinder<Widget, DescricaoPedido> {
	}

	@UiField
	Image enviar;
	@UiField
	Image remove;
	@UiField
	InlineLabel endereco;
	@UiField
	InlineLabel formaPagamento;
	@UiField
	InlineLabel numero;
	@UiField
	HTMLPanel listaPedidos;
	@UiField 
	Image print;

	public DescricaoPedido() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public DescricaoPedido(final PedidoBean pb, final PedidoUi pu) {
		initWidget(uiBinder.createAndBindUi(this));
		int cont = 1;
		endereco.setText(pb.getRua() + ", " + pb.getBairro());
		formaPagamento.setText(pb.getFormaPagamento());
		List<ItemCardapioBean> lista = pb.getListaItens();
		if (lista != null)
			for (ItemCardapioBean itemCardapioBean : lista) {
				ItemListaDescricaoPedido i = new ItemListaDescricaoPedido(itemCardapioBean);
				if(cont%2==1)
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
		// TODO @Osman recusar deve ter o motivo registrado
		remove.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cadastroService.setStatusPedido(pb.getId(),
						RegistroStatusPedido.recusado,
						new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable arg0) {
								Window.alert("Não foi possível rejeitar o pedido do cliente "
										+ pb.getNomeCliente()
										+ ". Recarregue a página e tente novamente.");
							}

							@Override
							public void onSuccess(Void arg0) {
								removeUi(pu);
								Window.alert("O pedido do cliente "
										+ pb.getNomeCliente()
										+ " foi rejeitado corretamente.");
							}
						});
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