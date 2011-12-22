package com.preguicoso.client.busca;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Widget;

public class listaCategoria extends Composite {

	private static listaCategoriaUiBinder uiBinder = GWT
			.create(listaCategoriaUiBinder.class);
	@UiField
	InlineHyperlink pizza;
	@UiField
	InlineHyperlink chines;
	@UiField
	InlineHyperlink sushi;
	@UiField
	InlineHyperlink italiano;

	private final BuscaServiceAsync buscaService = GWT
			.create(BuscaService.class);

	interface listaCategoriaUiBinder extends UiBinder<Widget, listaCategoria> {
	}

	public listaCategoria() {
		initWidget(uiBinder.createAndBindUi(this));
		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				sushi.setStyleName("categoria");
				italiano.setStyleName("categoria");
				pizza.setStyleName("categoria");
				chines.setStyleName("categoria");

			}
		});

		// buscaService.getListaCategoria(new
		// AsyncCallback<ArrayList<CategoriaBean>>() {
		//
		// @Override
		// public void onSuccess(ArrayList<String> result) {
		//
		//
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
	}

	@UiHandler("sushi")
	void onSushiClick(ClickEvent event) {
		sushi.addStyleName("categoriaAtiva");
		italiano.setStyleName("categoria");
		pizza.setStyleName("categoria");
		chines.setStyleName("categoria");

	}

	@UiHandler("italiano")
	void onItalianoClick(ClickEvent event) {
		italiano.addStyleName("categoriaAtiva");
		sushi.setStyleName("categoria");
		pizza.setStyleName("categoria");
		chines.setStyleName("categoria");
	}

	@UiHandler("pizza")
	void onPizzaClick(ClickEvent event) {
		pizza.addStyleName("categoriaAtiva");
		italiano.setStyleName("categoria");
		sushi.setStyleName("categoria");
		chines.setStyleName("categoria");
	}

	@UiHandler("chines")
	void onChinesClick(ClickEvent event) {
		chines.addStyleName("categoriaAtiva");
		italiano.setStyleName("categoria");
		pizza.setStyleName("categoria");
		sushi.setStyleName("categoria");
	}
}
