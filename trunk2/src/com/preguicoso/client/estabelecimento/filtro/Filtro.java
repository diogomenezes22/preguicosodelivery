package com.preguicoso.client.estabelecimento.filtro;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.entities.cardapio.CategoriaBean;

public class Filtro extends Composite {

	private static FiltroUiBinder uiBinder = GWT.create(FiltroUiBinder.class);
	@UiField
	TextBox busca;
	@UiField
	HTMLPanel filtro;
	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	interface FiltroUiBinder extends UiBinder<Widget, Filtro> {
	}

	public Filtro(Long estabelecimentoId) {
		initWidget(uiBinder.createAndBindUi(this));
		cardapioService.getCategorias(estabelecimentoId,
				new AsyncCallback<ArrayList<CategoriaBean>>() {

					@Override
					public void onSuccess(ArrayList<CategoriaBean> result) {
						HashMap<CategoriaBean, Integer> campos = new HashMap<CategoriaBean, Integer>();
						for (CategoriaBean categoriaBean : result) {
							campos.put(categoriaBean, 1);
						}
						ArrayList<CategoriaBean> lista = new ArrayList<CategoriaBean>(
								campos.keySet());
						for (CategoriaBean categoriaBean2 : lista) {
							filtro.add(new CheckFiltro(categoriaBean2.getNome()));
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Problemas nas categorias");

					}
				});
	}
}
