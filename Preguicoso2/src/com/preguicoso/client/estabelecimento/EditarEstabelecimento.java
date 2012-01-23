package com.preguicoso.client.estabelecimento;

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
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.busca.BuscaService;
import com.preguicoso.client.busca.BuscaServiceAsync;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.shared.FieldVerifier;
import com.preguicoso.shared.entities.EnderecoBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;

public class EditarEstabelecimento extends Composite {

	private static EditarEstabelecimentoUiBinder uiBinder = GWT
			.create(EditarEstabelecimentoUiBinder.class);

	interface EditarEstabelecimentoUiBinder extends
	UiBinder<Widget, EditarEstabelecimento> {
	}

	public EditarEstabelecimento() {
		this.initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField Label nomeLabel;
	@UiField Label razaoSocialLabel;
	@UiField Label cnpjLabel;
	@UiField Label cepLabel;
	@UiField Label ruaLabel;
	@UiField Label numeroLabel;
	@UiField Label complementoLabel;
	@UiField Label bairroLabel;
	@UiField Label cidadeLabel;
	@UiField Label estadoLabel;

	@UiField TextBox nomeTextbox;
	@UiField TextBox razaoSocialTextbox;
	@UiField TextBox cnpjTextbox;
	@UiField TextBox cepTextbox;
	@UiField TextBox ruaTextbox;
	@UiField TextBox numeroTextbox;
	@UiField TextBox complementoTextbox;
	@UiField TextBox bairroTextbox;
	@UiField TextBox cidadeTextbox;
	@UiField TextBox estadoTextbox;

	@UiField HorizontalPanel nomeHPanel;
	@UiField HorizontalPanel razaoSocialHPanel;
	@UiField HorizontalPanel cnpjHPanel;
	@UiField HorizontalPanel cepHPanel;
	@UiField HorizontalPanel ruaHPanel;
	@UiField HorizontalPanel numeroHPanel;
	@UiField HorizontalPanel complementoHPanel;
	@UiField HorizontalPanel bairroHPanel;
	@UiField HorizontalPanel cidadeHPanel;
	@UiField HorizontalPanel estadoHPanel;

	@UiField Button saveButton;
	@UiField HTMLPanel errorArea;
	@UiField Button useCepButton;
	@UiField Hidden idHidden;

	private Long estabelecimentoId;
	private Object currentObject;
	private final CadastroServiceAsync cadastroService = GWT.create(CadastroService.class);
	private final BuscaServiceAsync buscaService = GWT.create(BuscaService.class);

	public EditarEstabelecimento(Long id) {
		this.estabelecimentoId = id;

		this.initWidget(uiBinder.createAndBindUi(this));
		this.cadastroService.getEstabelecimento(id, new AsyncCallback<EstabelecimentoBean>() {
			@Override
			public void onSuccess(EstabelecimentoBean result) {
				EditarEstabelecimento.this.currentObject = result;
				EditarEstabelecimento.this.idHidden.setValue(result.getId().toString());
				EditarEstabelecimento.this.nomeTextbox.setText(result.getNome());
				//				EditarEstabelecimento.this.logo.setUrl("img/estabelecimentos/chinainbox.jpg");
				EditarEstabelecimento.this.razaoSocialTextbox.setText(result.getRazaoSocial());
				EditarEstabelecimento.this.cnpjTextbox.setText(result.getCNPJ());

				EnderecoBean e = result.getEnderecoBean();
				EditarEstabelecimento.this.ruaTextbox.setText(e.getRua());
				EditarEstabelecimento.this.numeroTextbox.setText(e.getNumero()==null?"":e.getNumero().toString());
				EditarEstabelecimento.this.complementoTextbox.setText(e.getComplemento());
				EditarEstabelecimento.this.bairroTextbox.setText(e.getBairroBean().getNome());
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Erro - CadastroService.getEstabelecimento");
			}
		});
	}

	protected void salvar() {
		this.saveButton.setText("Salvando...");

		this.nomeTextbox.setEnabled(false);
		this.razaoSocialTextbox.setEnabled(false);
		this.cnpjTextbox.setEnabled(false);

		EstabelecimentoBean estabelecimentoBean = ((new EstabelecimentoDAO()).retrieve(this.estabelecimentoId)).toBean();

		EnderecoBean enderecoBean = estabelecimentoBean.getEnderecoBean();
		enderecoBean.setCep(this.cepTextbox.getText());
		enderecoBean.setComplemento(this.complementoTextbox.getText());
		enderecoBean.setNumero(Integer.parseInt(this.numeroTextbox.getText()));
		estabelecimentoBean.setEnderecoBean(enderecoBean);

		this.cadastroService.salvarEstabelecimento(estabelecimentoBean, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				EditarEstabelecimento.this.saveButton.setText("Okay!");
			}

			@Override
			public void onFailure(Throwable caught) {
				EditarEstabelecimento.this.nomeTextbox.setEnabled(true);
				EditarEstabelecimento.this.razaoSocialTextbox.setEnabled(true);
				EditarEstabelecimento.this.cnpjTextbox.setEnabled(true);
				EditarEstabelecimento.this.saveButton.setText("Cadastrar");
				EditarEstabelecimento.this.msgError("Problemas no servidor, tente novamente em instantes...");
			}
		});
	}

	@UiHandler("saveButton")
	void onSaveButtonClick(ClickEvent event) {
		EditarEstabelecimento.this.errorArea.clear();
		boolean flag = false;
		if (!FieldVerifier.isValidName(EditarEstabelecimento.this.nomeTextbox.getText())) {
			EditarEstabelecimento.this.msgError("Nome Invalido");
			EditarEstabelecimento.this.nomeTextbox.setStyleName("errorLabel");
			flag = true;
		} else {
			EditarEstabelecimento.this.nomeTextbox.setStyleName("gwt-TextBox");
		}
		if (!FieldVerifier.isValidName(EditarEstabelecimento.this.razaoSocialTextbox.getText())) {
			EditarEstabelecimento.this.msgError("Razao social Incorreto");
			EditarEstabelecimento.this.razaoSocialTextbox.setStyleName("errorLabel");
			flag = true;
		} else {
			EditarEstabelecimento.this.razaoSocialTextbox.setStyleName("gwt-TextBox");
		}
		if (!FieldVerifier.isValidCNPJ(EditarEstabelecimento.this.cnpjTextbox.getText())) {
			EditarEstabelecimento.this.msgError("CNPJ Invalido");
			EditarEstabelecimento.this.cnpjTextbox.setStyleName("errorLabel");
			flag = true;
		} else {
			EditarEstabelecimento.this.cnpjTextbox.setStyleName("gwt-TextBox");
		}

		if (!flag) {
			EditarEstabelecimento.this.salvar();
		}
	}

	private void msgError(String string) {
		this.errorArea.add(new HTMLPanel(string));
	}

	@UiHandler("useCepButton")
	void onUseCepButtonClick(ClickEvent event) {
		this.buscaService.getEnderecoByCep(this.cepTextbox.getText(), new AsyncCallback<EnderecoBean>() {

			@Override
			public void onSuccess(EnderecoBean result) {
				EditarEstabelecimento.this.ruaTextbox.setText(result.getRua());
				EditarEstabelecimento.this.bairroTextbox.setText(result.getBairroBean().getNome());
				EditarEstabelecimento.this.complementoTextbox.setText("");
				EditarEstabelecimento.this.numeroTextbox.setText("");
			}

			@Override
			public void onFailure(Throwable caught) {
				EditarEstabelecimento.this.errorArea.clear();
				EditarEstabelecimento.this.msgError("O CEP digitado eh invalido.");
			}
		});
	}
}
