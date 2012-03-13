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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.login.LoginService;
import com.preguicoso.client.login.LoginServiceAsync;
import com.preguicoso.client.login.LoginUI;
import com.preguicoso.shared.FormValidatorShared;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.UsuarioBean;

public class EnderecoBox extends Composite {

	private static EnderecoBoxUiBinder uiBinder = GWT
			.create(EnderecoBoxUiBinder.class);

	interface EnderecoBoxUiBinder extends UiBinder<Widget, EnderecoBox> {
	}

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);
	private final CheckoutServiceAsync checkoutService = GWT
			.create(CheckoutService.class);

	@UiField
	TextBox nome;
	@UiField
	TextBox email;
	@UiField
	PasswordTextBox senha;
	@UiField
	PasswordTextBox senhaCheck;
	@UiField
	TextBox telefone;
	@UiField
	TextBox celular;
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

	EstabelecimentoBean eb;
	Checkout ch;

	// TODO @Osman Usuario pode desativar o javascript, fazer validação no
	// servidor também
	public EnderecoBox(EstabelecimentoBean eb, List<String> listaBairros,
			Checkout ch) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eb = eb;
		this.ch = ch;
		depoisDoCep.setVisible(false);
		for (String s : listaBairros) {
			endereco_bairro.addItem(s);
		}
	}

	private boolean jaPediu = false;

	@UiHandler("pedir")
	void onPedirClick(ClickEvent event) {
		if (ch.isPagamentoChecked()) {
			if (ch.isDinheiroChecked()) {
				if (ch.isTrocoFull()) {
					analisaEnvio();
				} else {
					Window.alert("Campo \"Troco para\" deve ser preenchido.");
				}
			} else {
				analisaEnvio();
			}
		} else {
			Window.alert("Escolha a forma de pagamento.");
		}
	}

	private void analisaEnvio() {
		if (FormValidatorClient.isFormValid(endereco_rua.getText(),
				endereco_numero.getText())) {
			if (!jaPediu) {
				jaPediu = true;
				// Cadastrar usuario
				final UsuarioBean ub = new UsuarioBean();
				ub.setNome(nome.getText());
				ub.setEmail(email.getText());
				ub.setPassword(senha.getText());
				ub.setTelefoneResidencial(telefone.getText());
				ub.setTelefoneCelular(celular.getText());
				ub.setIdCidade(eb.getIdCidade());
				ub.setLogradouro(endereco_rua.getText());
				ub.setNumero(endereco_numero.getText());
				ub.setComplemento(endereco_complemento.getText());
				ub.setBairro(endereco_bairro.getItemText(endereco_bairro
						.getSelectedIndex()));
				loginService.cadastrarUsuario(ub, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						String complementoText = endereco_complemento.getText();
						if (complementoText.equals("Complemento"))
							complementoText = "";
						logarUsuario(complementoText, ub);
					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
			}
		}
	}

	private void logarUsuario(final String complementoText, final UsuarioBean ub) {
		loginService.logarUsuario(ub.getEmail(), ub.getPassword(),
				new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(String result) {
						LoginUI.getInstance().setLogado();
						enviarPedido(complementoText);
					}

				});
	}

	private void enviarPedido(String complementoText) {
		Long troco = null;
		if (ch.isDinheiroChecked() && ch.isTrocoFull()) {
			if (FormValidatorShared.hasNumberOnly(ch.trocoBox.getText())) {
				troco = ch.trocoBox.getValue().longValue();
				checkoutService.enviarPedido(
						nome.getText(),
						endereco_rua.getText() + " "
								+ endereco_numero.getText(), endereco_bairro
								.getValue(endereco_bairro.getSelectedIndex()),
						complementoText, ch.getPagamentoChecked(), troco,
						new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Erro no Envio do pedido. Tente novamente.");
								History.newItem("index");
							}

							@Override
							public void onSuccess(Void result) {
								Window.alert("Pedido enviado com sucesso!");
								History.newItem("pedido");
							}
						});
			} else {
				Window.alert("O campo \"Troco para\" só pode ter números.");
			}
		}
	}

	@UiHandler("verificarCep")
	void onVerificarCepClick(ClickEvent event) {
		checkoutService.getEnderecoByCep(enderecoCep.getText(),
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
								List<String> lista = new ArrayList<String>();
								for (int i = 0; i < endereco_bairro
										.getItemCount(); i++) {
									lista.add(endereco_bairro.getValue(i));
								}
								if (lista.contains(result[1])) {
									endereco_rua.setText(result[0]);
									endereco_bairro.setSelectedIndex(lista
											.indexOf(result[1]));
									enderecoCep.setEnabled(false);
									verificarCep.setEnabled(false);
									endereco_rua.setEnabled(false);
									endereco_bairro.setEnabled(false);
								} else {
									Window.alert("O restaurante não atua nessa área. Digite seu endereço manualmente.");
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

	// TODO @Osman validar novos campos no futuro
	@UiHandler("nome")
	void onNomeFocus(FocusEvent event) {
		if (nome.getText().equals("Nome Completo"))
			nome.setText("");
	}

	@UiHandler("nome")
	void onNomeBlur(BlurEvent event) {
		if (nome.getText().equals(""))
			nome.setText("Nome Completo");
	}

	@UiHandler("email")
	void onEmailFocus(FocusEvent event) {
		if (email.getText().equals("E-mail"))
			email.setText("");
	}

	@UiHandler("email")
	void onEmailBlur(BlurEvent event) {
		if (email.getText().equals("")) {
			email.setText("E-mail");
		} else {
			loginService.hasAlreadyEmail(email.getText(),
					new AsyncCallback<Boolean>() {

						@Override
						public void onFailure(Throwable caught) {
						}

						@Override
						public void onSuccess(Boolean result) {
							if (result) {
								Window.alert("O email já existe.");
								email.setText("E-mail");
								email.setStyleName("falseField");
							} else {
								email.setStyleName("trueField");
							}
						}
					});
		}
	}

	@UiHandler("senha")
	void onSenhaFocus(FocusEvent event) {
		if (senha.getText().equals("Senha"))
			senha.setText("");
	}

	@UiHandler("senha")
	void onSenhaBlur(BlurEvent event) {
		if (senha.getText().equals(""))
			senha.setText("Senha");
	}

	@UiHandler("senhaCheck")
	void onSenhaCheckFocus(FocusEvent event) {
		if (senhaCheck.getText().equals("Senha"))
			senhaCheck.setText("");
	}

	@UiHandler("senhaCheck")
	void onSenhaCheckBlur(BlurEvent event) {
		if (senhaCheck.getText().equals(""))
			senhaCheck.setText("Senha");
	}

	// TODO @Osman fazer telefone no autocomplete
	@UiHandler("telefone")
	void onTelefoneFocus(FocusEvent event) {
		if (telefone.getText().equals("Telefone Residencial"))
			telefone.setText("");
	}

	@UiHandler("telefone")
	void onTelefoneBlur(BlurEvent event) {
		if (telefone.getText().equals(""))
			telefone.setText("Telefone Residencial");
	}

	@UiHandler("celular")
	void onCelularFocus(FocusEvent event) {
		if (celular.getText().equals("Celular"))
			celular.setText("");
	}

	@UiHandler("celular")
	void onCelularBlur(BlurEvent event) {
		if (celular.getText().equals(""))
			celular.setText("Celular");
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
		else if (!FormValidatorShared.hasNumberOnly(enderecoCep.getText())) {
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
		else {
			FormValidatorClient validator = new FormValidatorClient(
					checkoutService);
			validator.verifyLogradouroValid(endereco_rua);
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
		} else if (!FormValidatorShared
				.hasNumberOnly(endereco_numero.getText())) {
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

}
