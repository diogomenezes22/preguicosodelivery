package com.preguicoso.client.busca;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.RegistroErros;
import com.preguicoso.shared.entities.BairroBean;
import com.preguicoso.shared.entities.CidadeBean;

public class listaCategoria extends Composite {

	private static listaCategoriaUiBinder uiBinder = GWT
			.create(listaCategoriaUiBinder.class);

	interface listaCategoriaUiBinder extends UiBinder<Widget, listaCategoria> {
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	@UiField
	ListBox cidadeBox;
	@UiField
	ListBox bairroBox;
	@UiField
	InlineHyperlink pizza;
	@UiField
	InlineHyperlink chines;
	@UiField
	InlineHyperlink sushi;
	@UiField
	InlineHyperlink italiano;

	List<CidadeBean> cidadesList;
	List<BairroBean> bairrosList;
	private final BuscaServiceAsync buscaService = GWT
			.create(BuscaService.class);

	public listaCategoria() {
		initWidget(uiBinder.createAndBindUi(this));
		listarCidades();
		// TODO @Osman depois pegar a cidade atual da sessão
		cidadeBox.setSelectedIndex(0);
		listarBairros(cidadesList.get(cidadeBox.getSelectedIndex()));
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
		//
		//
		// }
		// });
	}

	@UiHandler("cidadeBox")
	void onCidadeBoxChange(ChangeEvent event) {
		listarBairros(cidadesList.get(cidadeBox.getSelectedIndex()));
	}

	@UiHandler("bairroBox")
	void onBairroBoxChange(ChangeEvent event) {
		RootPanel.get("busca").clear();
		RootPanel.get("busca").add(
				new ListaEstabelecimento(bairrosList.get(bairroBox
						.getSelectedIndex())));
	}

	private void listarCidades() {
		CidadeBean fortaleza = new CidadeBean();
		fortaleza.setId((long) 1);
		fortaleza.setNome("Fortaleza");
		cidadesList = new ArrayList<CidadeBean>();
		cidadesList.add(fortaleza);
		cidadeBox.addItem(fortaleza.getNome());
		cardapioService.getCidadesList(new AsyncCallback<List<CidadeBean>>() {

			@Override
			public void onSuccess(List<CidadeBean> result) {
				cidadeBox.clear();
				cidadesList = new ArrayList<CidadeBean>(result);
				for (CidadeBean cb : cidadesList) {
					cidadeBox.addItem(cb.getNome());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(RegistroErros.CONEXAO);
			}
		});
	}

	private void listarBairros(CidadeBean cb) {
		cardapioService.getBairros(cb.getId(),
				new AsyncCallback<List<BairroBean>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(RegistroErros.CONEXAO);
					}

					@Override
					public void onSuccess(List<BairroBean> result) {
						bairroBox.clear();
						bairrosList = new ArrayList<BairroBean>(result);
						for (BairroBean bb : result) {
							bairroBox.addItem(bb.getNome());
						}
					}
				});
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
