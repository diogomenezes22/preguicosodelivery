package com.preguicoso.client.busca;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.RegistroErros;
import com.preguicoso.shared.entities.EstabelecimentoBean;

public class ListaEstabelecimento extends Composite {

	private static ListaUiBinder uiBinder = GWT.create(ListaUiBinder.class);

	private final BuscaServiceAsync buscaService = GWT
			.create(BuscaService.class);
	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);
	@UiField
	Image loading;
	@UiField
	HTMLPanel listaEstabelecimento;

	interface ListaUiBinder extends UiBinder<Widget, ListaEstabelecimento> {
	}

	/**
	 * Cria a lista baseado na cidade presente na session
	 */
	public ListaEstabelecimento() {
		this.initWidget(uiBinder.createAndBindUi(this));

		// TODO @Osman gambiarra temporária para solucionar o problema da
		// session do
		// carrinho
		cardapioService.carrinhoClean(new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Não foi possível zerar o carrinho.");
			}
		});

		String[] token = History.getToken().split("/");
		if (token.length == 2) {
			buscaService.getListaEstabelecimentoBySession(token[1],
					new AsyncCallback<List<EstabelecimentoBean>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Não foi possível carregar a lista de restaurantes.");
						}

						@Override
						public void onSuccess(List<EstabelecimentoBean> result) {
							carregarListaEstabelecimento(result);
						}
					});
		} else {
			buscaService
					.getListaEstabelecimentoBySession(new AsyncCallback<List<EstabelecimentoBean>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Não foi possível carregar a lista de restaurantes.");
						}

						@Override
						public void onSuccess(List<EstabelecimentoBean> result) {
							carregarListaEstabelecimento(result);
						}
					});
		}
	}

	public ListaEstabelecimento(String... nome) {
		if (nome.length == 1) {
			this.buscaService.getListaEstabelecimentoPorNome(nome[0],
					new AsyncCallback<ArrayList<EstabelecimentoBean>>() {

						@Override
						public void onSuccess(
								ArrayList<EstabelecimentoBean> result) {
							if (!result.isEmpty()) {
								for (EstabelecimentoBean est : result) {
									ListaEstabelecimento.this.listaEstabelecimento
											.add(new ListaEstabelecimentoItem(
													est));
								}
							} else {
								ListaEstabelecimento.this.listaEstabelecimento
										.add(new Label(
												"Nenhum resultado foi encontrado."));
							}
							loading.setVisible(false);

						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Não foi possível carregar a lista de restaurantes.");
						}
					});
		} else if (nome.length == 2) {
			this.buscaService.getEstabelecimentoPorBairro(nome[1],
					new AsyncCallback<ArrayList<EstabelecimentoBean>>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Não foi possível carregar a lista de restaurantes.");
						}

						@Override
						public void onSuccess(
								ArrayList<EstabelecimentoBean> result) {
							if (!result.isEmpty()) {
								for (EstabelecimentoBean est : result) {
									ListaEstabelecimento.this.listaEstabelecimento
											.add(new ListaEstabelecimentoItem(
													est));
								}
							} else {
								ListaEstabelecimento.this.listaEstabelecimento
										.add(new Label(
												"Nenhum resultado foi encontrado."));
							}
							loading.setVisible(false);
						}
					});
		}
		this.initWidget(uiBinder.createAndBindUi(this));
		loading.setVisible(true);

	}

	public ListaEstabelecimento(Long idCidade) {
		this.initWidget(uiBinder.createAndBindUi(this));
		buscaService.getListaEstabelecimentoByCidade(idCidade,
				new AsyncCallback<List<EstabelecimentoBean>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(RegistroErros.ESTABELECIMENTO_NOT_FOUND);
					}

					@Override
					public void onSuccess(List<EstabelecimentoBean> result) {
						carregarListaEstabelecimento(result);
					}

				});
	}

	private void carregarListaEstabelecimento(List<EstabelecimentoBean> lista) {
		if (!lista.isEmpty()) {
			for (EstabelecimentoBean eb : lista) {
				ListaEstabelecimento.this.listaEstabelecimento
						.add(new ListaEstabelecimentoItem(eb));
			}
		} else {
			ListaEstabelecimento.this.listaEstabelecimento.add(new Label(
					"Nenhum resultado foi encontrado."));
		}
		loading.setVisible(false);
	}
}