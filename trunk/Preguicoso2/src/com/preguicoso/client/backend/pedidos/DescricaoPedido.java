package com.preguicoso.client.backend.pedidos;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.entities.ItemCardapioBean;
import com.preguicoso.shared.entities.PedidoBean;

public class DescricaoPedido extends Composite {

	private static DescricaoPedidoUiBinder uiBinder = GWT
			.create(DescricaoPedidoUiBinder.class);

	interface DescricaoPedidoUiBinder extends UiBinder<Widget, DescricaoPedido> {
	}
	@UiField Image salvar;
	@UiField InlineLabel endereco;
	@UiField InlineLabel formaPagamento;
	@UiField InlineLabel numero;
	@UiField HTMLPanel pedidos;

	public DescricaoPedido() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public DescricaoPedido(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public DescricaoPedido(PedidoBean pb) {
		initWidget(uiBinder.createAndBindUi(this));
		endereco.setText(pb.getRua()+", "+pb.getBairro());
		formaPagamento.setText(pb.getFormaPagamento());
		List<ItemCardapioBean> lista = pb.getListaItens();
		if(lista!=null)
			for (ItemCardapioBean itemCardapioBean : lista) {
				pedidos.add(new HTMLPanel(itemCardapioBean.getDescricao()));
			}
//		for (ItemCardapioBean item : pb.getListaItens()) {
//			Window.alert(item.getDescricao());
////			<tr>
////			<td>123
////			</td>
////			<td>
////				Item
////			</td>
////			<td>
////				Observação do item
////			</td>
////			<td>
////				3
////			</td>
////				</tr>
//		}
	}
}
