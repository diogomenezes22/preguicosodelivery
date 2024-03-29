package com.preguicoso.client.busca;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.entities.EstabelecimentoBean;

public class ListaEstabelecimentoItem extends Composite {

	private static ListaEstabelecimentoItemUiBinder uiBinder = GWT
			.create(ListaEstabelecimentoItemUiBinder.class);

	interface ListaEstabelecimentoItemUiBinder extends
			UiBinder<Widget, ListaEstabelecimentoItem> {
	}

	@UiField
	Label nome;
	@UiField
	Label descricao;
	@UiField
	Label endereco;
	@UiField
	InlineHyperlink link;
	@UiField
	Image logo;

	public ListaEstabelecimentoItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ListaEstabelecimentoItem(String nome, String descricao,
			String endereco) {
		initWidget(uiBinder.createAndBindUi(this));
		this.nome.setText(nome);
		this.endereco.setText(endereco);
		this.descricao.setText(descricao);
	}

	public ListaEstabelecimentoItem(EstabelecimentoBean e) {
		initWidget(uiBinder.createAndBindUi(this));
		this.nome.setText(e.getNome());
		this.descricao.setText(e.getDescricao());
		this.endereco.setText(e.getEndereco());
		this.link.setText("Delivery " + e.getStatus().name());
		if (!e.getStatus().name().equals("Aberto")) {
			// TODO @Osman mudar para botão e ajeitar essa gambiarra
			this.link.setTargetHistoryToken("index");
		} else {
			this.link.setTargetHistoryToken("Estabelecimento/" + e.getId());
		}

		//this.descricao.setText(e.getRazaoSocial());
		this.logo.setUrl(e.getLogoURL());
	}
}
