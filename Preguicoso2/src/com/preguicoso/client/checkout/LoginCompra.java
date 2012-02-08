package com.preguicoso.client.checkout;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.preguicoso.shared.RegistroErros;
import com.preguicoso.shared.entities.UsuarioBean;

public class LoginCompra extends Composite {

	private static LoginCompraUiBinder uiBinder = GWT
			.create(LoginCompraUiBinder.class);

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);
	private final CheckoutServiceAsync checkoutService = GWT
			.create(CheckoutService.class);

	interface LoginCompraUiBinder extends UiBinder<Widget, LoginCompra> {
	}

	@UiField
	TextBox email;
	@UiField
	PasswordTextBox senha;
	@UiField
	Button loginButton;
	@UiField
	HTMLPanel antesLogin;
	@UiField
	HTMLPanel depoisLogin;
	@UiField
	TextBox logradouro;
	@UiField
	TextBox numero;
	@UiField
	TextBox complemento;
	@UiField
	ListBox bairro;
	@UiField
	Button pedir;

	List<String> listaBairros;
	Checkout ch;

	public LoginCompra(List<String> listaBairros, Checkout ch, boolean isLogado) {
		initWidget(uiBinder.createAndBindUi(this));
		if (isLogado) {
			antesLogin.setVisible(false);
			setFormByUser();
		} else {
			depoisLogin.setVisible(false);
		}
		this.listaBairros = listaBairros;
		this.ch = ch;
		for (String s : listaBairros) {
			bairro.addItem(s);
		}
	}

	@UiHandler("loginButton")
	void onLoginButtonClick(ClickEvent event) {
		loginService.logarUsuario(email.getText(), senha.getText(),
				new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						if (result == null) {
							LoginUI.getInstance().setLogado();
							depoisLogin.setVisible(true);
							antesLogin.setVisible(false);
							setFormByUser();
						} else {
							Window.alert(result);
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(RegistroErros.LOGIN);
					}
				});
	}

	private void setFormByUser() {
		loginService.getUsuarioLogado(new AsyncCallback<UsuarioBean>() {

			@Override
			public void onSuccess(UsuarioBean result) {
				if (listaBairros.contains(result.getBairro())) {
					logradouro.setText(result.getLogradouro());
					numero.setText(result.getNumero());
					if (!result.getComplemento().equals(""))
						complemento.setText(result.getComplemento());
					bairro.setSelectedIndex(listaBairros.indexOf(result
							.getBairro()));
				} else {
					Window.alert("O estabelecimento não atende no bairro "
							+ result.getBairro()
							+ ", mas você pode escolher outro bairro agora. ");
				}
				addPedirClick(result);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	private boolean jaPediu = false;

	private void addPedirClick(final UsuarioBean ub) {
		pedir.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (ch.isPagamentoChecked()) {
					if (FormValidatorClient.isFormValid(logradouro.getText(),
							numero.getText())) {
						if (!jaPediu) {
							jaPediu = true;
							String complementoText = complemento.getText();
							if (complementoText.equals("Complemento"))
								complementoText = "";
							checkoutService.enviarPedido(
									ub.getNome(),
									logradouro.getText() + " "
											+ numero.getText(),
									bairro.getValue(bairro.getSelectedIndex()),
									complementoText, ch.getPagamentoChecked(),
									new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											Window.alert("Erro no Envio do pedido. Tente comprar novamente.");
											History.newItem("index");
										}

										@Override
										public void onSuccess(Void result) {
											Window.alert("Pedido enviado com sucesso!");
											History.newItem("pedido");
										}
									});
						}
					}
				} else {
					Window.alert("Escolha a forma de pagamento.");
				}
			}
		});
	}

	@UiHandler("email")
	void onEmailFocus(FocusEvent event) {
		if (email.getText().equals("E-mail"))
			email.setText("");
	}

	@UiHandler("email")
	void onEmailBlur(BlurEvent event) {
		if (email.getText().equals(""))
			email.setText("E-mail");
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

	@UiHandler("logradouro")
	void onEndereco_ruaFocus(FocusEvent event) {
		if (logradouro.getText().equals("Logradouro"))
			logradouro.setText("");
	}

	@UiHandler("logradouro")
	void onEndereco_ruaBlur(BlurEvent event) {
		if (logradouro.getText().equals(""))
			logradouro.setText("Logradouro");
		else {
			FormValidatorClient validator = new FormValidatorClient(
					checkoutService);
			validator.verifyLogradouroValid(logradouro);
		}
	}

	@UiHandler("numero")
	void onEndereco_numeroFocus(FocusEvent event) {
		if (numero.getText().equals("Número"))
			numero.setText("");
	}

	@UiHandler("numero")
	void onEndereco_numeroBlur(BlurEvent event) {
		if (numero.getText().equals("")) {
			numero.setText("Número");
		} else if (!FormValidatorShared.hasNumberOnly(numero.getText())) {
			numero.setText("Número");
			Window.alert("Esse campo só pode ter números.");
		}
	}

	@UiHandler("complemento")
	void onEndereco_complementoFocus(FocusEvent event) {
		if (complemento.getText().equals("Complemento"))
			complemento.setText("");
	}

	@UiHandler("complemento")
	void onEndereco_complementoBlur(BlurEvent event) {
		if (complemento.getText().equals(""))
			complemento.setText("Complemento");
	}

}
