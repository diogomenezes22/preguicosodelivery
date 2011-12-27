package com.preguicoso.client.backend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.backend.cardapio.EditarCardapio;
import com.preguicoso.client.backend.pedidos.FecharBalanco;
import com.preguicoso.client.backend.pedidos.OrdemPedidos;
import com.preguicoso.client.backend.restaurante.EditarInformacao;
import com.preguicoso.client.backend.restaurante.Setup;

public class Backend extends Composite {

	private static backendUiBinder uiBinder = GWT.create(backendUiBinder.class);
	@UiField
	InlineLabel nomeEstabelecimento;
	@UiField
	InlineLabel pedidos;
	@UiField
	InlineLabel cardapio;
	@UiField
	InlineLabel restaurante;
	@UiField
	HTMLPanel containerMenu;
	@UiField
	HTMLPanel container;

	interface backendUiBinder extends UiBinder<Widget, Backend> {
	}

	public Backend() {
		initWidget(uiBinder.createAndBindUi(this));
		createMenu();
		inicio();
		routerHistory();
	}

	private void inicio() {
		pedidos.addStyleName("active");
		cardapio.setStyleName("menuItem");
		restaurante.setStyleName("menuItem");
		containerMenu.clear();
		containerMenu.add(new InlineHyperlink("Ordem de Pedidos",
				"pedidos/ordem"));
		containerMenu.add(new InlineHyperlink("Fechar balanço",
				"pedidos/balanco"));
	}

	public void routerHistory() {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				String token = History.getToken();
				clearContent();
				buildHomePage(token);
			}

		});
		History.fireCurrentHistoryState();
	}

	public void buildHomePage(String token) {
		if (token.startsWith("pedidos/ordem")) {
			container.clear();
			container.add(new OrdemPedidos());
		} else if (token.startsWith("pedidos/balanco")) {
			container.clear();
			container.add(new FecharBalanco());
		} else if (token.startsWith("cardapio/editar")) {
			container.clear();
			container.add(new EditarCardapio());
		} else if (token.startsWith("restaurante/editar")) {
			container.clear();
			container.add(new EditarInformacao());
		} else if (token.startsWith("restaurante/setup")) {
			container.clear();
			container.add(new Setup());
		}

	}

	public void clearContent() {
		// RootPanel.get("busca").clear();
		// RootPanel.get("searchFieldDiv").clear();
		// RootPanel.get("content").clear();
	}

	private void createMenu() {
		pedidos.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				pedidos.addStyleName("active");
				cardapio.setStyleName("menuItem");
				restaurante.setStyleName("menuItem");
				containerMenu.clear();
				containerMenu.add(new InlineHyperlink("Ordem de Pedidos",
						"pedidos/ordem"));
				containerMenu.add(new InlineHyperlink("Fechar balanço",
						"pedidos/balanco"));
			}
		});
		cardapio.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				cardapio.addStyleName("active");
				pedidos.setStyleName("menuItem");
				restaurante.setStyleName("menuItem");
				containerMenu.clear();
				containerMenu.add(new InlineHyperlink("Editar",
						"cardapio/editar"));
			}
		});
		restaurante.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				restaurante.addStyleName("active");
				cardapio.setStyleName("menuItem");
				pedidos.setStyleName("menuItem");
				containerMenu.clear();
				containerMenu.add(new InlineHyperlink("Editar Informações",
						"restaurante/editar"));
				containerMenu.add(new InlineHyperlink("Configurações",
						"restaurante/setup"));

			}
		});
	}
}
