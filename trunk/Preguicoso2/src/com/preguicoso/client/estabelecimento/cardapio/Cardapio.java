package com.preguicoso.client.estabelecimento.cardapio;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.carrinho.CarrinhoPedidosUI;
import com.preguicoso.shared.entities.ItemCardapioBean;

public class Cardapio extends Composite {
	private static CardapioUiBinder uiBinder = GWT
			.create(CardapioUiBinder.class);

	@UiField
	HTMLPanel cardapio;
	Label categoria;
	@UiField
	HTMLPanel listaItensCategoria;
	@UiField
	Image loading;
	@UiField
	HTMLPanel checkout;

	CarrinhoPedidosUI carrinho = new CarrinhoPedidosUI();

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	protected Button botaoPedir = new Button("Pagar");

	interface CardapioUiBinder extends UiBinder<Widget, Cardapio> {
	}

	public Cardapio() {
		this.initWidget(uiBinder.createAndBindUi(this));

	}

	public Cardapio(final long id, CarrinhoPedidosUI carrinho) {
		this.initWidget(uiBinder.createAndBindUi(this));
		this.carrinho = carrinho;
		this.loading.setUrl("img/ajax-loader.gif");
		this.cardapioService.getItensCardapio(id,
				new AsyncCallback<ArrayList<ItemCardapioBean>>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Nao foi possivel recuperar o cardapio do restaurante.");
					}

					@Override
					public void onSuccess(ArrayList<ItemCardapioBean> result) {
						if (result.isEmpty()) {
							Cardapio.this.cardapio.add(new HTML(
									"Cardapio vazio"));
						} else {
							this.mapearCategorias(result);
						}

					}

					private void mapearCategorias(
							ArrayList<ItemCardapioBean> result) {
						ArrayList<ItemCardapioBean> listaItens;
						HashMap<String, ArrayList<ItemCardapioBean>> mapaCategorias = new HashMap<String, ArrayList<ItemCardapioBean>>();
						for (ItemCardapioBean i : result) {
							if (!mapaCategorias.containsKey(i
									.getCategoriaBean().getNome())) {
								listaItens = new ArrayList<ItemCardapioBean>();
								listaItens.add(i);
								mapaCategorias.put(i.getCategoriaBean()
										.getNome(), listaItens);
							} else {
								listaItens = mapaCategorias.get(i
										.getCategoriaBean().getNome());
								listaItens.add(i);
								mapaCategorias.put(i.getCategoriaBean()
										.getNome(), listaItens);
							}
						}

						for (String nomeCategoria : mapaCategorias.keySet()) {
							listaItens = mapaCategorias.get(nomeCategoria);

							HTMLPanel parada = new HTMLPanel("<br/><h3>"
									+ nomeCategoria + "</h3><hr/>");
							int i = 0;
							for (ItemCardapioBean itemCardapio : listaItens) {
								if (itemCardapio.getEstabelecimentoBean()
										.equals(id)) {
									CardapioItem item = new CardapioItem(
											itemCardapio.getNome(),
											itemCardapio.getDescricao(),
											"R$ " + itemCardapio.getPreco(),
											Cardapio.this
													.templateBotaoItemCardapio(itemCardapio));
									if (i % 2 == 1) {
										item.addStyleName("restaurante_cardapio_item2");
									}
									parada.add(item);
									i++;
								}
							}
							if (i != 0) {
								Cardapio.this.listaItensCategoria.add(parada);
							}

						}

						Cardapio.this.loading.setVisible(false);
					}

				});
	}

	private HTMLPanel templateBotaoItemCardapio(final ItemCardapioBean i) {

		HTMLPanel divButton = new HTMLPanel("");

		final Image addButton = new Image("img/addButtonMouseOver.png");
		addButton.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				addButton.setUrl("img/addButtonMouseOut.png");
			}
		});
		addButton.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				addButton.setUrl("img/addButtonMouseOver.png");
			}
		});
		addButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Cardapio.this.adicionarItemAoCarrinho(i);
				addButton.setUrl("img/addButtonMouseOver.png");
			}
		});

		divButton.add(addButton);

		return divButton;
	}

	protected void adicionarItemAoCarrinho(final ItemCardapioBean i) {
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Adicionar item ao carrinho");
		dialogBox.setAnimationEnabled(false);
		dialogBox.setWidth("300px");
		// dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.setWidth("300px");
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Quantidade:</b>"));
		final IntegerBox quantidade = new IntegerBox();

		// TODO @Osman teste
		// <teste>
		final Button addItem = new Button("Add");
		final Button removeItem = new Button("Remove");
		quantidade.setEnabled(false);
		quantidade.setValue(1);

		addItem.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				quantidade.setValue(quantidade.getValue() + 1);
			}
		});

		removeItem.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				if (quantidade.getValue() > 1) {
					quantidade.setValue(quantidade.getValue() - 1);
				}
			}
		});
		// </teste>

		dialogVPanel.add(quantidade);
		dialogVPanel.add(new HTML("<br><b>Observacao:</b>"));
		final TextArea observacoes = new TextArea();
		dialogVPanel.add(observacoes);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		HorizontalPanel botoes = new HorizontalPanel();
		Button adicionarNoCarrinho = new Button("Adicionar ao carrinho");
		botoes.add(adicionarNoCarrinho);

		// TODO @Osman teste
		// <teste>
		botoes.add(addItem);
		botoes.add(removeItem);
		// </teste>

		botoes.add(closeButton);
		dialogVPanel.add(botoes);
		// Botão de adicionar pedido
		dialogVPanel.add(adicionarNoCarrinho);
		adicionarNoCarrinho.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Cardapio.this.cardapioService.addItem(i, quantidade.getValue(),
						observacoes.getText(), new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								carrinho.atualizarCarrinhoPedidos();
								dialogBox.hide();
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Não foi possível adicionar o item ao carrinho");
							}
						});
			}
		});
		dialogBox.setWidget(dialogVPanel);
		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}

		});
		dialogBox.setText("Adicionar " + i.getNome());
		serverResponseLabel.removeStyleName("serverResponseLabelError");
		dialogBox.center();
		closeButton.setFocus(true);
	}

	public void loadingImage(String nome) {
		Image carregando = new Image("img/ajax-loader.gif");
		RootPanel.get("pedido").add(carregando);
	}

}
