package com.preguicoso.client.backend.cardapio;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.client.cadastro.CadastroServiceAsync;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

public class EditarItem extends Composite {

	private static EditarItemUiBinder uiBinder = GWT
			.create(EditarItemUiBinder.class);

	@UiField
	HTMLPanel item;
	@UiField
	SimpleCheckBox disponivel;
	@UiField
	ListBox categoria;
	@UiField
	TextBox numero;
	@UiField
	TextBox nome;
	@UiField
	TextArea descricao;
	@UiField
	TextBox preco;
	@UiField
	Image salvar;

	boolean flag = false;

	private final CadastroServiceAsync cadastroService = GWT
			.create(CadastroService.class);

	interface EditarItemUiBinder extends UiBinder<Widget, EditarItem> {
	}

	public EditarItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	ItemCardapioBean i;

	public EditarItem(final ItemCardapioBean i,
			final ArrayList<ItemCategoria> lista) {
		initWidget(uiBinder.createAndBindUi(this));
		this.i = i;
		numero.setText("" + i.getId());
		nome.setText(i.getNome());
		numero.setText("0");
		descricao.setText(i.getDescricao());
		preco.setText("R$ " + i.getPreco());
		for (ItemCategoria cat : lista) {
			categoria.addItem(cat.getName());
		}
		categoria.setItemText(0, this.i.getCategoriaBean().getNome());
		salvar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (flag) {
					salvar.setStyleName("");
					flag = false;
					cadastroService.salvarItemCardapio(i,
							new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {

								}

								@Override
								public void onFailure(Throwable caught) {
									// TODO @Abraão Colocar um handler do erro
									Window.alert("Houve algum problema para salvar o item");

								}
							});
					Window.alert("Item Salvo com sucesso. Basta atualizar a lista");
				}
			}
		});
	}

	@UiHandler("numero")
	void onNumeroBlur(BlurEvent event) {
		foiAlterado();
	}

	@UiHandler("nome")
	void onNomeBlur(BlurEvent event) {
		foiAlterado();
		this.i.setNome(nome.getText());
	}

	@UiHandler("disponivel")
	void onDisponivelBlur(BlurEvent event) {
		foiAlterado();
		this.i.setDisponivel(disponivel.getValue());
	}

	@UiHandler("descricao")
	void onDescricaoBlur(BlurEvent event) {
		foiAlterado();
		this.i.setDescricao(descricao.getText());
	}

	@UiHandler("categoria")
	void onCategoriaBlur(BlurEvent event) {
		foiAlterado();
		this.i.setCategoriaBean(new CategoriaBean(categoria
				.getItemText(categoria.getSelectedIndex())));
	}

	@UiHandler("preco")
	void onPrecoBlur(BlurEvent event) {
		foiAlterado();
		String p = preco.getText().substring(3);
		this.i.setPreco(new Double(p));
	}

	private void foiAlterado() {
		salvar.addStyleName("active");
		flag = true;
	}
}
