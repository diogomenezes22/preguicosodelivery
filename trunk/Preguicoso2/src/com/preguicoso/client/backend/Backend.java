package com.preguicoso.client.backend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.RegistroStatus;
import com.preguicoso.client.backend.cardapio.EditarCardapio;
import com.preguicoso.client.backend.pedidos.FecharBalanco;
import com.preguicoso.client.backend.pedidos.OrdemPedidos;
import com.preguicoso.client.backend.restaurante.EditarInformacao;
import com.preguicoso.client.backend.restaurante.Setup;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.client.login.LoginServiceAsync;

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
	@UiField
	ListBox status;

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);

	interface backendUiBinder extends UiBinder<Widget, Backend> {
	}

	public Backend() {
		initWidget(uiBinder.createAndBindUi(this));
		if (History.getToken().equals(""))
			History.newItem("pedidos/ordem");
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

		for (RegistroStatus rs : RegistroStatus.values()) {
			status.addItem(rs.name());
		}
		// TODO @Osman pegar id do restaurante logado
		final Long idRestauranteLogado = (long) 405;
		cadastroService.getStatus(idRestauranteLogado,
				new AsyncCallback<Integer>() {

					@Override
					public void onFailure(Throwable arg0) {
						Window.alert("Ocorreu um erro ao tentar carregar o seu status. Recarregue a página e tente novamente.");
					}

					@Override
					public void onSuccess(Integer result) {
						status.setSelectedIndex(result);
					}
				});

		status.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				cadastroService.setStatus(idRestauranteLogado,
						status.getSelectedIndex(), new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable arg0) {
								Window.alert("Ocorreu um erro ao tentar mudar o seu status. Recarregue a página e tente novamente.");
							}

							@Override
							public void onSuccess(Void result) {
							}
						});
			}
		});

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
			EditarCardapio editar = new EditarCardapio();
			container.add(editar);

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
