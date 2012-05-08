package com.preguicoso.client.busca;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.Entities;
import com.preguicoso.shared.entities.EstabelecimentoBean;

public class Busca extends Composite {

	private final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();

	private static BuscaUiBinder uiBinder = GWT.create(BuscaUiBinder.class);

	@UiField(provided = true)
	SuggestBox suggestBox;

	private final BuscaServiceAsync buscaService = GWT
			.create(BuscaService.class);

	interface BuscaUiBinder extends UiBinder<Widget, Busca> {
	}

	public Busca() {
		Busca.this.suggestBox = new SuggestBox(Busca.this.oracle);
		this.suggestBox.getElement().setId("searchField");
		RootPanel.get("cadastroEstabelecimento").add(new listaCategoria());
		this.initWidget(uiBinder.createAndBindUi(this));

	}

	public Busca(Entities e) {
		
		if (e.equals(Entities.Estabelecimento)) {
			this.buscaService
					.getListaEstabelecimento(new AsyncCallback<ArrayList<EstabelecimentoBean>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Não foi possível carregar a lista de estabelecimentos. Recarregue a página.");
						}

						@Override
						public void onSuccess(
								ArrayList<EstabelecimentoBean> result) {
							for (EstabelecimentoBean value : result) {
								Busca.this.oracle.add(value.getNome());
							}
						}
					});
		} else if (e.equals(Entities.Cidade)) {
		}
		Busca.this.suggestBox = new SuggestBox(Busca.this.oracle);
		this.suggestBox.getElement().setId("searchField");
		suggestBox.setAutoSelectEnabled(false);
		Busca.this.suggestBox.addStyleName("busca");
		this.initWidget(uiBinder.createAndBindUi(this));
		suggestBox.setText("Busque por Produtos ou Parceiros");
		suggestBox.addFocusListener(new FocusListener() {
			
			@Override
			public void onLostFocus(Widget sender) {
				suggestBox.setText("Busque por Produtos ou Parceiros");				
			}
			
			@Override
			public void onFocus(Widget sender) {
				suggestBox.setText("");				
			}
		});
	}

	@UiHandler("suggestBox")
	void onSuggestBoxKeyPress(KeyPressEvent event) {
		if (KeyCodes.KEY_ENTER == event.getNativeEvent().getKeyCode()) {
			if (!this.suggestBox.getText().equals("")) {
				RootPanel.get("busca").clear();
				ListaEstabelecimento lista = new ListaEstabelecimento(
						this.suggestBox.getText());
				ListarItens listaitens = new ListarItens(this.suggestBox.getText());
				RootPanel.get("busca").add(new HTMLPanel("<h3>Parceiros</h3>"));
				RootPanel.get("busca").add(lista);
				RootPanel.get("busca").add(new HTMLPanel("<h3>Produtos</h3>"));
				RootPanel.get("busca").add(listaitens);
			}
		}
	}

	public SuggestBox getSuggestBox() {
		return this.suggestBox;
	}
}