package com.preguicoso.client.checkout;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.entities.BairroBean;

public class EnderecoBox extends Composite {

	private static EnderecoBoxUiBinder uiBinder = GWT
			.create(EnderecoBoxUiBinder.class);

	interface EnderecoBoxUiBinder extends UiBinder<Widget, EnderecoBox> {
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	@UiField
	HTMLPanel antesDoCep;
	@UiField
	HTMLPanel depoisDoCep;
	@UiField
	Button verificarCep;
	@UiField
	Button pularCep;
	@UiField
	TextBox enderecoCep;
	@UiField
	TextBox endereco_rua;
	@UiField
	TextBox endereco_numero;
	@UiField
	TextBox endereco_complemento;
	@UiField
	Button pedir;
	@UiField
	ListBox endereco_bairro;

	public EnderecoBox() {
		initWidget(uiBinder.createAndBindUi(this));
		depoisDoCep.setVisible(false);
		// TODO @Osman fazer pela cidade no futuro
		Long idCidade = (long) 1;
		cardapioService.getBairros(idCidade,
				new AsyncCallback<List<BairroBean>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Problemas de conexão, recarregue a página.");
					}

					@Override
					public void onSuccess(List<BairroBean> result) {
						for (BairroBean bb : result) {
							endereco_bairro.addItem(bb.getNome());
						}
					}
				});
		// cardapioService.getBairros(idCidade,
		// new AsyncCallback<List<String>>() {
		//
		// @Override
		// public void onSuccess(List<String> result) {
		// for (String nomeBairro : result) {
		// endereco_bairro.addItem(nomeBairro);
		// }
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("Problemas de conexão, recarregue a página.");
		// }
		// });
	}

	@UiHandler("pedir")
	void onPedirClick(ClickEvent event) {
		if (endereco_rua.getText().equals("Logradouro")) {
			Window.alert("Digite o logradouro.");
		} else if (endereco_numero.getText().equals("Número")) {
			Window.alert("Digite o número.");
		} else {
			String complemento = endereco_complemento.getText();
			if (complemento.equals("Complemento"))
				complemento = "";
			cardapioService.enviarPedido("Sem Nome", endereco_rua.getText()
					+ " " + endereco_numero.getText(), endereco_bairro
					.getValue(endereco_bairro.getSelectedIndex()), complemento,
					"Dinheiro", new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							Window.alert("Pedido enviado com sucesso!");
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Erro no Envio do pedido. Tente novamente.");
						}
					});
			History.newItem("index");
		}
	}

	@UiHandler("verificarCep")
	void onVerificarCepClick(ClickEvent event) {
		cardapioService.getEnderecoByCep(enderecoCep.getText(),
				new AsyncCallback<String[]>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Problema de conexão");
					}

					@Override
					public void onSuccess(String[] result) {
						if (enderecoCep.getText().equals("CEP")) {
							Window.alert("Digite seu cep.");
						} else {
							if (result != null) {
								endereco_rua.setText(result[0]);
								List<String> lista = new ArrayList<String>();
								for (int i = 0; i < endereco_bairro
										.getItemCount(); i++) {
									lista.add(endereco_bairro.getValue(i));
								}
								if (lista.contains(result[1])) {
									endereco_bairro.setSelectedIndex(lista
											.indexOf(result[1]));
									enderecoCep.setEnabled(false);
									verificarCep.setEnabled(false);
									endereco_rua.setEnabled(false);
									endereco_bairro.setEnabled(false);
								} else {
									Window.alert("Não foi possível encontrar seu cep. Digite seu endereço manualmente.");
									antesDoCep.setVisible(false);
								}
							} else {
								Window.alert("Problema no serviço de cep. Digite seu endereço manualmente.");
								antesDoCep.setVisible(false);
							}
							depoisDoCep.setVisible(true);
						}
					}
				});
	}

	@UiHandler("pularCep")
	void onPularCepClick(ClickEvent event) {
		depoisDoCep.setVisible(true);
		antesDoCep.setVisible(false);
		endereco_rua.setEnabled(true);
		endereco_bairro.setEnabled(true);
	}

	@UiHandler("enderecoCep")
	void onEnderecoCepFocus(FocusEvent event) {
		if (enderecoCep.getText().equals("CEP"))
			enderecoCep.setText("");
	}

	@UiHandler("enderecoCep")
	void onEnderecoCepBlur(BlurEvent event) {
		if (enderecoCep.getText().equals(""))
			enderecoCep.setText("CEP");
		else if (!hasNumberOnly(enderecoCep.getText())) {
			enderecoCep.setText("CEP");
			Window.alert("Esse campo só pode ter números.");
		}
	}

	@UiHandler("endereco_rua")
	void onEndereco_ruaFocus(FocusEvent event) {
		if (endereco_rua.getText().equals("Logradouro"))
			endereco_rua.setText("");
	}

	@UiHandler("endereco_rua")
	void onEndereco_ruaBlur(BlurEvent event) {
		if (endereco_rua.getText().equals(""))
			endereco_rua.setText("Logradouro");
		else if (!endereco_rua.getText().startsWith("Rua")
				&& !endereco_rua.getText().startsWith("Avenida")) {
			Window.alert("Logradouro deve começar com Rua ou Avenida.");
			endereco_rua.setText("Logradouro");
		}
	}

	@UiHandler("endereco_numero")
	void onEndereco_numeroFocus(FocusEvent event) {
		if (endereco_numero.getText().equals("Número"))
			endereco_numero.setText("");
	}

	@UiHandler("endereco_numero")
	void onEndereco_numeroBlur(BlurEvent event) {
		if (endereco_numero.getText().equals("")) {
			endereco_numero.setText("Número");
		} else if (!hasNumberOnly(endereco_numero.getText())) {
			endereco_numero.setText("Número");
			Window.alert("Esse campo só pode ter números.");
		}
	}

	@UiHandler("endereco_complemento")
	void onEndereco_complementoFocus(FocusEvent event) {
		if (endereco_complemento.getText().equals("Complemento"))
			endereco_complemento.setText("");
	}

	@UiHandler("endereco_complemento")
	void onEndereco_complementoBlur(BlurEvent event) {
		if (endereco_complemento.getText().equals(""))
			endereco_complemento.setText("Complemento");
	}

	private boolean hasNumberOnly(String string) {
		char letra;
		for (int i = 0; i < string.length(); i++) {
			letra = string.charAt(i);
			if (letra < '0' || '9' < letra) {
				return false;
			}
		}
		return true;
	}
}
