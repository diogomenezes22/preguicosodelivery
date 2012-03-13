package com.preguicoso.client.estabelecimento.carrinho;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.entities.cardapio.ItemCardapioBean;

public class CarrinhoPedidosUI extends Composite {

	private static CarrinhoPedidosUIUiBinder uiBinder = GWT
			.create(CarrinhoPedidosUIUiBinder.class);
	@UiField
	HTMLPanel pedidos;
	@UiField
	InlineLabel total;
	@UiField
	Image loading;
	@UiField
	Button pedir;
	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	final CarrinhoPedidosUI carrinho = this;

	interface CarrinhoPedidosUIUiBinder extends
			UiBinder<Widget, CarrinhoPedidosUI> {
	}

	public CarrinhoPedidosUI() {
		initWidget(uiBinder.createAndBindUi(this));
		atualizarCarrinhoPedidos();
		pedidos.add(new HTML("Carrinho vazio"));
	}

	public void atualizarCarrinhoPedidos() {
		loading.setVisible(true);
		pedir.setEnabled(false);
		cardapioService
				.getCarrinho(new AsyncCallback<ArrayList<ItemCardapioBean>>() {

					@Override
					public void onSuccess(ArrayList<ItemCardapioBean> result) {
						if (result.isEmpty()) {
							pedidos.clear();
							HTML item = new HTML("Carrinho vazio");
							item.setStyleName("item_pedido");
							pedidos.add(item);
							total.setText("0.00");
						} else {
							pedir.setEnabled(true);
							pedidos.clear();
							float soma = 0;
							for (ItemCardapioBean i : result) {
								pedidos.add(new ItemPedido(i, carrinho));
								soma += i.getQuantidade() * i.getPreco();
							}
							total.setText("" + soma);
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						pedidos.add(new HTML("Carrinho vazio"));

					}
				});
	}

	@UiHandler("pedir")
	void onPedirClick(ClickEvent event) {
		String token = History.getToken();
		if (!token.endsWith("/checkout")) {
			History.newItem(token + "/checkout");
		}
	}
}
