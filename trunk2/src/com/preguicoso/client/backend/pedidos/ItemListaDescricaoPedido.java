package com.preguicoso.client.backend.pedidos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.entities.cardapio.ItemCardapioBean;

public class ItemListaDescricaoPedido extends Composite  {

	private static itemListaDescricaoPedidoUiBinder uiBinder = GWT
			.create(itemListaDescricaoPedidoUiBinder.class);
	@UiField InlineLabel no;
	@UiField InlineLabel observacao;
	@UiField InlineLabel item;
	@UiField InlineLabel qtd;
	@UiField HTMLPanel campo;

	interface itemListaDescricaoPedidoUiBinder extends
			UiBinder<Widget, ItemListaDescricaoPedido> {
	}
	public void setOdd(){
		campo.addStyleName("odd");
	}
	public ItemListaDescricaoPedido() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public ItemListaDescricaoPedido(ItemCardapioBean item) {
		initWidget(uiBinder.createAndBindUi(this));
		no.setText(item.getNumero()+"");
		observacao.setText(item.getObservacao());
		this.item.setText((item.getNome()));
		this.qtd.setText(item.getQuantidade()+"");
	}


}
