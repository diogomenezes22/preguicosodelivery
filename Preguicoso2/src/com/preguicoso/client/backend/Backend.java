package com.preguicoso.client.backend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.Preguicoso2;
import com.preguicoso.client.backend.cardapio.EditarCardapio;
import com.preguicoso.client.backend.cardapio.EditarGrupos;
import com.preguicoso.client.backend.cardapio.ingredientes.EditarIngredientes;
import com.preguicoso.client.backend.cardapio.opcoes.EditarOpcoes;
import com.preguicoso.client.backend.pedidos.HistoricoPedidos;
import com.preguicoso.client.backend.pedidos.OrdemPedidos;
import com.preguicoso.client.backend.restaurante.EditarInformacao;
import com.preguicoso.client.backend.restaurante.Setup;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.client.login.LoginServiceAsync;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.EstabelecimentoBean;

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
	@UiField
	Button logout;
	@UiField
	InlineLabel conexao;

	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);
	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	interface backendUiBinder extends UiBinder<Widget, Backend> {
	}

	private static EstabelecimentoBean eb;
	Preguicoso2 p2;

	public static EstabelecimentoBean getEstabalecimentoBean() {
		return eb;
	}

	public Backend(EstabelecimentoBean eBean, Preguicoso2 p2) {
		initWidget(uiBinder.createAndBindUi(this));
		this.p2 = p2;
		eb = eBean;
		if (History.getToken().equals(""))
			History.newItem("pedidos/ordem");
		nomeEstabelecimento.setText(eb.getNome());
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
		containerMenu.add(new InlineHyperlink("Histórico de Pedidos",
				"pedidos/historico_de_pedidos"));

		for (RegistroStatusRestaurante rs : RegistroStatusRestaurante.values()) {
			status.addItem(rs.name());
		}

		cadastroService.getStatus(eb.getId(),
				new AsyncCallback<RegistroStatusRestaurante>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Ocorreu um erro ao tentar carregar o seu status. Recarregue a página e tente novamente.");
					}

					@Override
					public void onSuccess(RegistroStatusRestaurante result) {
						status.setSelectedIndex(result.ordinal());
					}
				});

		status.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				cadastroService.setStatus(eb.getId(),
						status.getSelectedIndex(), new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
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
			container.add(OrdemPedidos.getInstance(eb.getId(), conexao));
		} else if (token.startsWith("pedidos/historico_de_pedidos")) {
			container.clear();
			container.add(new HistoricoPedidos(eb.getId()));
		} else if (token.startsWith("cardapio/")) {
			if (token.endsWith("editar")) {
				container.clear();
				EditarCardapio editar = new EditarCardapio(eb.getId());
				container.add(editar);
			} else if (token.endsWith("opcoes")) {
				container.clear();
				container.add(new EditarOpcoes());
			} else if (token.endsWith("ingredientes")) {
				container.clear();
				container.add(new EditarIngredientes());
			} else if (token.endsWith("grupos")) {
				container.clear();
				container.add(new EditarGrupos());
			}
		} else if (token.startsWith("restaurante/editar")) {
			container.clear();
			container.add(new EditarInformacao(eb));
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

	@UiHandler("pedidos")
	void onPedidosClick(ClickEvent event) {
		pedidos.addStyleName("active");
		cardapio.setStyleName("menuItem");
		restaurante.setStyleName("menuItem");
		containerMenu.clear();
		containerMenu.add(new InlineHyperlink("Ordem de Pedidos",
				"pedidos/ordem"));
		containerMenu.add(new InlineHyperlink("Histórico de Pedidos",
				"pedidos/historico_de_pedidos"));
	}

	@UiHandler("cardapio")
	void onCardapioClick(ClickEvent event) {
		cardapio.addStyleName("active");
		pedidos.setStyleName("menuItem");
		restaurante.setStyleName("menuItem");
		containerMenu.clear();
		containerMenu.add(new InlineHyperlink("Editar", "cardapio/editar"));
		containerMenu.add(new InlineHyperlink("Opções", "cardapio/opcoes"));
		containerMenu.add(new InlineHyperlink("Ingredientes",
				"cardapio/ingredientes"));
		containerMenu.add(new InlineHyperlink("Grupos", "cardapio/grupos"));
	}

	@UiHandler("restaurante")
	void onRestauranteClick(ClickEvent event) {
		restaurante.addStyleName("active");
		cardapio.setStyleName("menuItem");
		pedidos.setStyleName("menuItem");
		containerMenu.clear();
		containerMenu.add(new InlineHyperlink("Editar Informações",
				"restaurante/editar"));
		containerMenu.add(new InlineHyperlink("Configurações",
				"restaurante/setup"));
	}

	@UiHandler("logout")
	void onLogoutClick(ClickEvent event) {
		loginService
				.fazerLogoutUsuarioEstabelecimento(new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Não foi possível fazer o logout. Recarregue a página e tente novamente.");
					}

					@Override
					public void onSuccess(Void result) {
						RootPanel.get("content").clear();
						p2.onModuleLoad();
					}
				});
	}
}
