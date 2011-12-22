package com.preguicoso.client.cadastro;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.FieldVerifier;
import com.preguicoso.shared.entities.EstabelecimentoBean;

public class CadastroEstabelecimento extends Composite {

	private static CadastroEstabelecimentoUiBinder uiBinder = GWT
			.create(CadastroEstabelecimentoUiBinder.class);
	@UiField
	TextBox nome;
	@UiField
	TextBox razao;
	@UiField
	TextBox cnpj;
	@UiField
	PasswordTextBox pass;
	@UiField
	PasswordTextBox passConfirm;
	@UiField
	Button cadastrar;
	@UiField
	HTMLPanel error;
	@UiField
	TextBox logo;
	@UiField
	TextArea descricao;
	@UiField
	TextArea endereco;
	@UiField
	TextBox categoria;

	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);

	interface CadastroEstabelecimentoUiBinder extends
			UiBinder<Widget, CadastroEstabelecimento> {
	}

	public CadastroEstabelecimento() {
		this.initWidget(uiBinder.createAndBindUi(this));

		this.cadastrar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				CadastroEstabelecimento.this.error.clear();
				boolean flag = false;
				if (!FieldVerifier
						.isValidName(CadastroEstabelecimento.this.nome
								.getText())) {
					CadastroEstabelecimento.this.msgError("Nome Invalido");
					CadastroEstabelecimento.this.nome
							.setStyleName("errorLabel");
					flag = true;
				} else {
					CadastroEstabelecimento.this.nome
							.setStyleName("gwt-TextBox");
				}
				if (!FieldVerifier
						.isValidName(CadastroEstabelecimento.this.razao
								.getText())) {
					CadastroEstabelecimento.this
							.msgError("Razao social Incorreto");
					CadastroEstabelecimento.this.razao
							.setStyleName("errorLabel");
					flag = true;
				} else {
					CadastroEstabelecimento.this.razao
							.setStyleName("gwt-TextBox");
				}
				if (!FieldVerifier
						.isValidCNPJ(CadastroEstabelecimento.this.cnpj
								.getText())) {
					CadastroEstabelecimento.this.msgError("CNPJ Invalido");
					CadastroEstabelecimento.this.cnpj
							.setStyleName("errorLabel");
					flag = true;
				} else {
					CadastroEstabelecimento.this.cnpj
							.setStyleName("gwt-TextBox");
				}
				if (!FieldVerifier
						.isValidPassword(CadastroEstabelecimento.this.pass
								.getText())) {
					CadastroEstabelecimento.this
							.msgError("O Password deve conter no m�nimo 6 caracteres ");
					CadastroEstabelecimento.this.pass
							.setStyleName("errorLabel");
					flag = true;
					CadastroEstabelecimento.this.passConfirm
							.setStyleName("errorLabel");
				} else {
					CadastroEstabelecimento.this.pass
							.setStyleName("gwt-TextBox");
					CadastroEstabelecimento.this.passConfirm
							.setStyleName("gwt-TextBox");
				}
				if (!CadastroEstabelecimento.this.pass.getText().equals(
						CadastroEstabelecimento.this.passConfirm.getText())) {
					CadastroEstabelecimento.this
							.msgError("A confirmacao nao esta correta");
					CadastroEstabelecimento.this.pass
							.setStyleName("errorLabel");
					flag = true;
					CadastroEstabelecimento.this.passConfirm
							.setStyleName("errorLabel");
				} else {
					CadastroEstabelecimento.this.pass
							.setStyleName("gwt-TextBox");
					CadastroEstabelecimento.this.passConfirm
							.setStyleName("gwt-TextBox");
				}
				if (!flag) {
					CadastroEstabelecimento.this.salvar();
				}
			}

		});
	}

	protected void salvar() {
		this.cadastrar.setText("Cadastrando...");
		this.nome.setEnabled(false);
		this.razao.setEnabled(false);
		this.cnpj.setEnabled(false);
		this.pass.setEnabled(false);
		this.passConfirm.setEnabled(false);
		final EstabelecimentoBean a = new EstabelecimentoBean(
				this.nome.getText(), this.razao.getText(), this.cnpj.getText());
		a.setLogoURL(logo.getText());
		a.setCategoria(categoria.getText());
		this.cadastroService.salvarEstabelecimento(a,
				new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						CadastroEstabelecimento.this.cadastrar
								.setText("Cadastrado");
					}

					@Override
					public void onFailure(Throwable caught) {
						CadastroEstabelecimento.this.nome.setEnabled(true);
						CadastroEstabelecimento.this.razao.setEnabled(true);
						CadastroEstabelecimento.this.cnpj.setEnabled(true);
						CadastroEstabelecimento.this.pass.setEnabled(true);
						CadastroEstabelecimento.this.passConfirm
								.setEnabled(true);
						CadastroEstabelecimento.this.cadastrar
								.setText("Cadastrar");
						CadastroEstabelecimento.this
								.msgError("Problemas no servidor, tente novamente em instantes...");

					}
				});
	}

	private void msgError(String string) {
		this.error.add(new HTMLPanel(string));
	}

}
