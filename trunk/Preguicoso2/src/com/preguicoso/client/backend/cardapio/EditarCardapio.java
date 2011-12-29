package com.preguicoso.client.backend.cardapio;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

public class EditarCardapio extends Composite {

	private static EditarCardapioUiBinder uiBinder = GWT
			.create(EditarCardapioUiBinder.class);
	@UiField
	HTMLPanel lista;
	@UiField
	Button itemButton;
	@UiField
	Button itemCategoria;

	interface EditarCardapioUiBinder extends UiBinder<Widget, EditarCardapio> {
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);
	
	ArrayList<CategoriaBean> categorias = new ArrayList<CategoriaBean>();
	
	public EditarCardapio() {
		initWidget(uiBinder.createAndBindUi(this));
		cardapioService.getItensCardapio((long) 387,
				new AsyncCallback<ArrayList<ItemCardapioBean>>() {

					@Override
					public void onSuccess(ArrayList<ItemCardapioBean> result) {
						String categoria = "";
						for (ItemCardapioBean itemCardapioBean : result) {
							if (!itemCardapioBean.getCategoriaBean().getNome().equals(categoria)) {
								lista.add(new ItemCategoria(itemCardapioBean.getCategoriaBean().getNome()));
								categoria = itemCardapioBean.getCategoriaBean().getNome();
								categorias.add(itemCardapioBean.getCategoriaBean());
							}
							lista.add(new ItemCardapioUI(itemCardapioBean));
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Houve algum problema");

					}
				});

		itemButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				lista.add(new ItemCardapioUI("123", "nao sei", "R$ 25,00"));
			}
		});
		itemCategoria.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CategoriaBean categoria = new CategoriaBean("Nova Categoria");
				lista.add(new ItemCategoria(categoria));
			}
		});
	}
}
