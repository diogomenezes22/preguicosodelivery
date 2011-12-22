package com.preguicoso.client.listar;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.entities.EstabelecimentoBean;

public class Lista extends Composite {

	private static ListaUiBinder uiBinder = GWT.create(ListaUiBinder.class);

	private final ListaServiceAsync listaService = GWT.create(ListaService.class);

	@UiField VerticalPanel listaEstabelecimento;
	@UiField VerticalPanel info;

	interface ListaUiBinder extends UiBinder<Widget, Lista> {
	}

	public Lista() {
		this.info = new VerticalPanel();
		this.listaEstabelecimento = new VerticalPanel();

		this.listaService.getListaEstabelecimento(new AsyncCallback<ArrayList<EstabelecimentoBean>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Couldn't get required data");
				Lista.this.info.add(new Label("Nao foram encontrados resultados."));
			}

			@Override
			public void onSuccess(ArrayList<EstabelecimentoBean> result) {
				Lista.this.info.add(new Label(result.size() + " resultado(s) encontrado(s)."));
				for (EstabelecimentoBean e : result) {
					Lista.this.listaEstabelecimento.add(new Hyperlink(e.getNome(),"estabelecimento/" + e.getId()));
				}
			}
		});

		this.initWidget(uiBinder.createAndBindUi(this));
	}

	public Lista(String cep) {
		this.info = new VerticalPanel();
		this.listaEstabelecimento = new VerticalPanel();

		this.info.add(new Label("Flag."));
		this.listaEstabelecimento.add(new Label("Flag."));
		/*;
		if (cep.length() == 0) {
			this.info.add(new Label("N‹o foram encontrados resultados para o seu cep."));
		} else {
			this.listaService.getEstabelecimentoPorCep(cep, new AsyncCallback<ArrayList<EstabelecimentoBean>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Couldn't get required data");
					Lista.this.info.add(new Label("N‹o foram encontrados resultados para o seu cep."));
				}

				@Override
				public void onSuccess(ArrayList<EstabelecimentoBean> result) {
					Lista.this.info.add(new Label("Foram encontrados " + result.size() + " para o seu cep."));
					for (EstabelecimentoBean e : result) {
						Lista.this.listaEstabelecimento.add(new Hyperlink(e.getNome(),"Estabelecimento/" + e.getId()));
					}
				}
			});
		}
		 */
		this.initWidget(uiBinder.createAndBindUi(this));
	}

}
