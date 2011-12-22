package com.preguicoso.client.busca;

import java.util.ArrayList;

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
import com.preguicoso.shared.entities.EstabelecimentoBean;

public class ListaEstabelecimento extends Composite {

	private static ListaUiBinder uiBinder = GWT.create(ListaUiBinder.class);

	private final BuscaServiceAsync buscaService = GWT
			.create(BuscaService.class);
	@UiField
	Image loading;
	@UiField
	HTMLPanel listaEstabelecimento;

	interface ListaUiBinder extends UiBinder<Widget, ListaEstabelecimento> {
	}

	public ListaEstabelecimento() {
		this.initWidget(uiBinder.createAndBindUi(this));
		String[] token = History.getToken().split("/");
		if (token.length > 1) {
			this.buscaService.getListaEstabelecimentoPorCategoria(token[1],
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
							Window.alert("Couldn't get required data");

						}
					});
		} else {
			this.buscaService
					.getListaEstabelecimento(new AsyncCallback<ArrayList<EstabelecimentoBean>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Couldn't get required data");
						}

						@Override
						public void onSuccess(
								ArrayList<EstabelecimentoBean> result) {
							for (EstabelecimentoBean e : result) {
								ListaEstabelecimento.this.listaEstabelecimento
										.add(new ListaEstabelecimentoItem(e));
							}
							loading.setVisible(false);
						}
					});
		}
		loading.setVisible(true);
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
							Window.alert("Couldn't get required data");

						}
					});
		} else if (nome.length == 2) {
			this.buscaService.getEstabelecimentoPorBairro(nome[1],
					new AsyncCallback<ArrayList<EstabelecimentoBean>>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Couldn't get required data");
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

}