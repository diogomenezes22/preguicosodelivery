package com.preguicoso.client.backend.cardapio.opcoes;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.backend.Backend;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.entities.cardapio.OpcoesBean;

public class EditarOpcoes extends Composite {

	private static EditarOpcoesUiBinder uiBinder = GWT
			.create(EditarOpcoesUiBinder.class);

	interface EditarOpcoesUiBinder extends UiBinder<Widget, EditarOpcoes> {
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	@UiField
	TextBox categoriaOpcoes;
	@UiField
	Button criar;
	@UiField
	HTMLPanel listaPanel;

	public EditarOpcoes() {
		initWidget(uiBinder.createAndBindUi(this));
		cardapioService.getListaOpcoes(
				Backend.getEstabalecimentoBean().getId(),
				new AsyncCallback<List<OpcoesBean>>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(List<OpcoesBean> result) {
						for (OpcoesBean ob : result) {
							listaPanel.add(new OpcoesUi(ob));
						}
					}
				});
	}

	@UiHandler("criar")
	void onCriarClick(ClickEvent event) {
		if (categoriaOpcoes.getText().length() == 0) {
			Window.alert("O campo \"Nome da categoria\" está vazio!");
		} else {
			cardapioService.criarCategoriaOpcao(categoriaOpcoes.getText(),
					Backend.getEstabalecimentoBean().getId(),
					new AsyncCallback<OpcoesBean>() {

						@Override
						public void onSuccess(OpcoesBean result) {
							listaPanel.add(new OpcoesUi(result));
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ocorreu um erro ao tentar criar nova categoria.");
						}
					});
		}
	}
}
