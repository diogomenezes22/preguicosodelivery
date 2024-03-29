package com.preguicoso.client.busca;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.RegistroCategoriaEstabelecimento;
import com.preguicoso.shared.RegistroErros;
import com.preguicoso.shared.entities.BairroBean;
import com.preguicoso.shared.entities.CidadeBean;
import com.google.gwt.user.client.ui.InlineLabel;

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
	HTMLPanel categoriasPanel;
	@UiField
	HTMLPanel localPanel;
	@UiField
	InlineHyperlink todas;

	List<CidadeBean> cidadesList;
	List<BairroBean> bairrosList;
	private final BuscaServiceAsync buscaService = GWT
			.create(BuscaService.class);

	public listaCategoria() {
		initWidget(uiBinder.createAndBindUi(this));
		listarCidades();
		//gerarCategorias();
		categoriasPanel.setVisible(true);
		localPanel.setVisible(true);
		todas.setVisible(false);
	}

	@UiHandler("todas")
	void onTodasClick(ClickEvent event) {
		localPanel.setVisible(true);
		todas.setVisible(false);
		for (int i = 0; i < categoriasPanel.getWidgetCount(); i++) {
			((InlineHyperlink) categoriasPanel.getWidget(i))
					.setStyleName("categoria");
		}
		todas.addStyleName("categoriaAtiva");
	}

	private void gerarCategorias() {
		for (RegistroCategoriaEstabelecimento cat : RegistroCategoriaEstabelecimento
				.values()) {
			final InlineHyperlink linkCat = new InlineHyperlink(cat.name(),
					"index/" + cat.name());
			linkCat.setStyleName("categoria");
			linkCat.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					todas.setStyleName("categoria");
					for (int i = 0; i < categoriasPanel.getWidgetCount(); i++) {
						((InlineHyperlink) categoriasPanel.getWidget(i))
								.setStyleName("categoria");
					}
					linkCat.addStyleName("categoriaAtiva");
				}
			});
			categoriasPanel.add(linkCat);
		}
	}

	@UiHandler("cidadeBox")
	void onCidadeBoxChange(ChangeEvent event) {
		CidadeBean cbSelected = cidadesList.get(cidadeBox.getSelectedIndex());
		listarBairros(cbSelected);
		setCidadeBeanSelected(cbSelected);
	
	
	}

	private void setCidadeBeanSelected(CidadeBean cbSelected) {
		buscaService.setCidadeBeanSession(cbSelected,
				new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Void result) {
						RootPanel.get("busca").clear();
						RootPanel.get("busca").add(new ListaEstabelecimento());
					}
				});
	}

	@UiHandler("bairroBox")
	void onBairroBoxChange(ChangeEvent event) {
		todas.setVisible(true);
		todas.setText("Bairro: "+ bairrosList.get(bairroBox
					.getSelectedIndex() - 1).getNome());
		todas.setTitle("Trocar o Bairro de entrega");
		localPanel.setVisible(false);
		if (bairroBox.getSelectedIndex() != 0) {
			BairroBean bbSelected = bairrosList.get(bairroBox
					.getSelectedIndex() - 1);
			setBairroBeanSelected(bbSelected);
		} else {
			setBairroBeanSelected(null);
		}
	}

	private void setBairroBeanSelected(final BairroBean bbSelected) {
		buscaService.setBairroBeanSession(bbSelected,
				new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Void result) {
						RootPanel.get("busca").clear();
						RootPanel.get("busca").add(new ListaEstabelecimento());
					}
				});
	}

	private void listarCidades() {
		cardapioService.getCidadesList(new AsyncCallback<List<CidadeBean>>() {

			@Override
			public void onSuccess(List<CidadeBean> result) {
				cidadeBox.clear();
				cidadesList = new ArrayList<CidadeBean>(result);
				for (CidadeBean cb : cidadesList) {
					cidadeBox.addItem(cb.getNome());
				}
				setCidadeBoxSelectedIndex();
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(RegistroErros.CONEXAO);
			}
		});
	}

	private void setCidadeBoxSelectedIndex() {
		buscaService.getCidadeBeanSession(new AsyncCallback<CidadeBean>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(RegistroErros.CONEXAO);
			}

			@Override
			public void onSuccess(CidadeBean result) {
				if (result == null) {
					cidadeBox.setSelectedIndex(0);
				} else {
					cidadeBox.setSelectedIndex(cidadesList.indexOf(result));
				}
				listarBairros(cidadesList.get(cidadeBox.getSelectedIndex()));
			}
		});
	}

	private void setBairroBoxSelectedIndex() {
		buscaService.getBairroBeanSession(new AsyncCallback<BairroBean>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(RegistroErros.CONEXAO);
			}

			@Override
			public void onSuccess(BairroBean result) {
				if (result == null) {
					bairroBox.setSelectedIndex(0);
				} else {
					bairroBox.setSelectedIndex(bairrosList.indexOf(result) + 1);
				}
			}
		});
	}

	private void listarBairros(CidadeBean cb) {
		bairroBox.clear();
		bairroBox.addItem("Escolha o bairro");
		cardapioService.getBairros(cb.getId(),
				new AsyncCallback<List<BairroBean>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(RegistroErros.CONEXAO);
					}

					@Override
					public void onSuccess(List<BairroBean> result) {
						bairrosList = new ArrayList<BairroBean>(result);
						for (BairroBean bb : result) {
							bairroBox.addItem(bb.getNome());
						}
						setBairroBoxSelectedIndex();
					}
				});
	}
}
