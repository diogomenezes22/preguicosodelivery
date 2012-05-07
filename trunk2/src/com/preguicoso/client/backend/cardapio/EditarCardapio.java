package com.preguicoso.client.backend.cardapio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.entities.cardapio.CategoriaBean;
import com.preguicoso.shared.entities.cardapio.ItemCardapioBean;

public class EditarCardapio extends Composite {

	private static EditarCardapioUiBinder uiBinder = GWT
			.create(EditarCardapioUiBinder.class);
	@UiField
	HTMLPanel lista;
	@UiField
	HTMLPanel listaNovos;
	@UiField
	Button itemButton;
	@UiField
	Button itemCategoria;
	@UiField
	Button atualizar;

	final Long idEstabelecimento;

	interface EditarCardapioUiBinder extends UiBinder<Widget, EditarCardapio> {
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);

	ArrayList<CategoriaBean> categorias = new ArrayList<CategoriaBean>();
	ArrayList<ItemCategoria> listaCategorias = new ArrayList<ItemCategoria>();

	public EditarCardapio(Long idEstabelecimento) {
		initWidget(uiBinder.createAndBindUi(this));
		this.idEstabelecimento = idEstabelecimento;
		getItensCardapio();
		itemButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ItemCardapioBean novoItem = new ItemCardapioBean("Novo Item",
						"", false, "Descrição do item", (long) 0,
						new CategoriaBean("Sem Categoria"),
						EditarCardapio.this.idEstabelecimento);
				listaNovos.add(new ItemCardapioUI(novoItem, listaCategorias));
			}
		});
		itemCategoria.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CategoriaBean categoria = new CategoriaBean("Nova Categoria");
				ItemCategoria c = new ItemCategoria(categoria);
				listaNovos.add(c);
				listaCategorias.add(c);
			}
		});
	}

	private void getItensCardapio() {
		cardapioService.getItensCardapio(idEstabelecimento,
				new AsyncCallback<ArrayList<ItemCardapioBean>>() {

					@Override
					public void onSuccess(ArrayList<ItemCardapioBean> result) {
						String categoria = "";
						lista.clear();
						listaNovos.clear();
						listaCategorias = new ArrayList<ItemCategoria>();
						organizarLista(result);
						for (ItemCardapioBean itemCardapioBean : result) {

							if (!itemCardapioBean.getCategoriaBean().getNome()
									.equals(categoria)) {
								lista.add(new ItemCategoria(itemCardapioBean
										.getCategoriaBean().getNome(),
										idEstabelecimento));
								categoria = itemCardapioBean.getCategoriaBean()
										.getNome();
								categorias.add(itemCardapioBean
										.getCategoriaBean());
								ItemCategoria c = new ItemCategoria(
										itemCardapioBean.getCategoriaBean());
								listaCategorias.add(c);
							}
							lista.add(new ItemCardapioUI(itemCardapioBean,
									listaCategorias));
						}
					}

					private void organizarLista(
							ArrayList<ItemCardapioBean> result) {
						Collections.sort(result,
								new Comparator<ItemCardapioBean>() {

									@Override
									public int compare(ItemCardapioBean o1,
											ItemCardapioBean o2) {
										if (o1.getCategoriaBean()
												.getNome()
												.compareTo(
														o2.getCategoriaBean()
																.getNome()) > 0)
											return 1;
										return -1;
									}
								});
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Não foi possível recuperar os itens do cardápio. Recarregue a página.");

					}
				});

	}

	@UiHandler("atualizar")
	void onAtualizarClick(ClickEvent event) {
		getItensCardapio();
	}
}
