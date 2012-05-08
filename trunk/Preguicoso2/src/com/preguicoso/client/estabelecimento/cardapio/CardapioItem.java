package com.preguicoso.client.estabelecimento.cardapio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Image;
import com.preguicoso.client.estabelecimento.carrinho.CarrinhoPedidosUI;
import com.preguicoso.shared.entities.ItemCardapioBean;
import com.preguicoso.shared.utils.MoneyUtils;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.uibinder.client.UiHandler;

public class CardapioItem extends Composite {

	private static CardapioItemUiBinder uiBinder = GWT
			.create(CardapioItemUiBinder.class);
	@UiField
	Label prato;
	@UiField
	Label descricao;
	@UiField
	InlineLabel preco;
	@UiField
	HTMLPanel botaoAdd;
	@UiField Image imagem;
	@UiField FocusPanel focusPanel;

	interface CardapioItemUiBinder extends UiBinder<Widget, CardapioItem> {
	}

	public CardapioItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	ItemCardapioBean i;
	public CardapioItem(String prato, String descricao, String preco,
			HTMLPanel botao) {
		initWidget(uiBinder.createAndBindUi(this));
		this.prato.setText(prato);
		this.descricao.setText(descricao);
		this.preco.setText(preco);
		this.botaoAdd.add(botao);
	}
	public CardapioItem(ItemCardapioBean e) {
		initWidget(uiBinder.createAndBindUi(this));
		this.prato.setText(e.getNome());
		i=e;
		//this.descricao.setText(e.getDescricao());
		this.preco.setText(MoneyUtils.parseString(e.getPreco()));
		this.imagem.setUrl(e.getImagem());
		this.imagem.setWidth("60px");
	}
	
	
	CarrinhoPedidosUI carrinho;
	public void setCarrinho(CarrinhoPedidosUI c){
		carrinho = c;
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);
	
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
				cardapioService.addItem(i, quantidade.getValue(),
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

	@UiHandler("focusPanel")
	void onFocusPanelClick(ClickEvent event) {
		adicionarItemAoCarrinho(i);
	}
}
