package com.preguicoso.client.backend.cardapio.ingredientes;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.backend.Backend;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.FormValidatorShared;
import com.preguicoso.shared.entities.cardapio.IngredienteBean;

public class EditarIngredientes extends Composite {

	private static EditarIngredientesUiBinder uiBinder = GWT
			.create(EditarIngredientesUiBinder.class);

	interface EditarIngredientesUiBinder extends
			UiBinder<Widget, EditarIngredientes> {
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	@UiField
	TextBox nomeIngrediente;
	@UiField
	TextBox realIngrediente;
	@UiField
	TextBox centavoIngrediente;
	@UiField
	Button criar;
	@UiField
	HTMLPanel listaPanel;

	public EditarIngredientes() {
		initWidget(uiBinder.createAndBindUi(this));
		cardapioService.getListaIngredientes(Backend.getEstabalecimentoBean()
				.getId(), new AsyncCallback<List<IngredienteBean>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(List<IngredienteBean> result) {
				for (IngredienteBean ib : result) {
					listaPanel.add(new IngredienteUi(ib));
				}
			}
		});
	}

	@UiHandler("criar")
	void onCriarClick(ClickEvent event) {
		String real = realIngrediente.getText();
		String centavo = centavoIngrediente.getText();
		if (nomeIngrediente.getText().length() == 0) {
			Window.alert("O campo \"Nome\" deve ser preenchido.");
		} else if (real.length() == 0) {
			Window.alert("Há campos vazios no preço.");
		} else if (centavo.length() == 0) {
			Window.alert("Há campos vazios no preço.");
		} else if (!FormValidatorShared.hasNumberOnly(real)) {
			Window.alert("O preço pode apenas ter números.");
		} else if (!FormValidatorShared.hasNumberOnly(centavo)) {
			Window.alert("O preço pode apenas ter números.");
		} else {
			cardapioService.criarIngrediente(nomeIngrediente.getText(),
					Long.parseLong(real) * 100 + Long.parseLong(centavo),
					Backend.getEstabalecimentoBean().getId(),
					new AsyncCallback<IngredienteBean>() {

						@Override
						public void onFailure(Throwable caught) {
						}

						@Override
						public void onSuccess(IngredienteBean result) {
							listaPanel.add(new IngredienteUi(result));
						}
					});

		}

	}
}
