package com.preguicoso.client.estabelecimento.carrinho;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.entities.ItemCardapioBean;

public class ItemPedido extends Composite {

	private static ItemPedidoUiBinder uiBinder = GWT
			.create(ItemPedidoUiBinder.class);

	interface ItemPedidoUiBinder extends UiBinder<Widget, ItemPedido> {
	}

	public ItemPedido() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	@UiField
	InlineLabel categoria;
	@UiField
	InlineLabel nome;
	@UiField
	InlineLabel obs;
	@UiField
	InlineLabel qtde;
	@UiField
	InlineLabel preco;
	@UiField
	Image close;

	public ItemPedido(final ItemCardapioBean i, final CarrinhoPedidosUI carrinho) {
		initWidget(uiBinder.createAndBindUi(this));
		nome.setText(i.getNome());
		categoria.setText(i.getCategoriaBean().getNome());
		obs.setText(i.getObservacao());
		qtde.setText(i.getQuantidade() + "");
		preco.setText(i.getPreco() + "");
		close.setUrl("img/close.png");
		close.setWidth("30px");
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cardapioService.removeItem(i, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						carrinho.atualizarCarrinhoPedidos();
					}

					@Override
					public void onFailure(Throwable caught) {

					}
				});
			}
		});
	}
}
