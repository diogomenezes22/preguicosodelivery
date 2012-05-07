package com.preguicoso.client.estabelecimento;

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
import com.preguicoso.client.busca.BuscaService;
import com.preguicoso.client.busca.BuscaServiceAsync;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;
import com.preguicoso.client.checkout.Checkout;
import com.preguicoso.client.editar.EditarService;
import com.preguicoso.client.editar.EditarServiceAsync;
import com.preguicoso.client.estabelecimento.cardapio.Cardapio;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.client.estabelecimento.carrinho.CarrinhoPedidosUI;
import com.preguicoso.client.estabelecimento.filtro.Filtro;
import com.preguicoso.shared.RegistroErros;
import com.preguicoso.shared.entities.EstabelecimentoBean;

public class EstabelecimentoUI extends Composite {

	private static EstabelecimentoUIUiBinder uiBinder = GWT
			.create(EstabelecimentoUIUiBinder.class);
	@UiField
	HTMLPanel coluna1;
	@UiField
	HTMLPanel coluna2;
	@UiField
	HTMLPanel coluna3;
	@UiField
	HTMLPanel filtro;
	@UiField
	Label nome;
	@UiField
	Label endereco;
	@UiField
	Image logo;

	private Long estabelecimentoId;
	private Object currentObject;

	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);
	private final BuscaServiceAsync buscaService = GWT
			.create(BuscaService.class);
	private final EditarServiceAsync editarService = GWT
			.create(EditarService.class);
	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	interface EstabelecimentoUIUiBinder extends
			UiBinder<Widget, EstabelecimentoUI> {
	}

	public EstabelecimentoUI(final Long id) {
		this.estabelecimentoId = id;
		this.initWidget(uiBinder.createAndBindUi(this));
		this.cadastroService.getEstabelecimento(id,
				new AsyncCallback<EstabelecimentoBean>() {
					@Override
					public void onSuccess(EstabelecimentoBean result) {
						EstabelecimentoUI.this.currentObject = result;
						EstabelecimentoUI.this.nome.setText(result.getNome());
						EstabelecimentoUI.this.logo.setUrl(result.getLogoURL());

						logo.setWidth("218px");
						CarrinhoPedidosUI carrinho = new CarrinhoPedidosUI();
						String token = History.getToken();
						if (!token.endsWith("/checkout")) {
							Cardapio cardapio = new Cardapio(id, carrinho);
							EstabelecimentoUI.this.coluna2.add(cardapio);
							filtro.add(new Filtro(id));
						} else {
							coluna1.add(new Checkout(result));
						}
						EstabelecimentoUI.this.coluna3.add(carrinho);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(RegistroErros.CONEXAO);
					}
				});
	}
}