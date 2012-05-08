package com.preguicoso.client.busca;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.entities.ItemCardapioBean;
import com.preguicoso.shared.utils.MoneyUtils;

public class ListarItensItem extends Composite {

	private static ListarItensItemUiBinder uiBinder = GWT
			.create(ListarItensItemUiBinder.class);

	interface ListarItensItemUiBinder extends UiBinder<Widget, ListarItensItem> {
	}

	public ListarItensItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	Label nome;
	@UiField
	InlineLabel descricao;
	@UiField
	Label endereco;
	@UiField
	InlineHyperlink link;
	@UiField
	Image logo;
	
	public ListarItensItem(ItemCardapioBean i) {
		initWidget(uiBinder.createAndBindUi(this));
		logo.setUrl(i.getImagem());
		logo.setWidth("118px");
		link.setTargetHistoryToken("Estabelecimento/"+i.getEstabelecimentobean());
		this.nome.setText(i.getNome());
		this.endereco.setText(i.getDescricao());
		this.descricao.setText(MoneyUtils.parseString(i.getPreco()));
	}


}
