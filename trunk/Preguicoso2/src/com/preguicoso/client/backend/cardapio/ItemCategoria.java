package com.preguicoso.client.backend.cardapio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.shared.entities.CategoriaBean;

public class ItemCategoria extends Composite {

	private static ItemCategoriaUiBinder uiBinder = GWT
			.create(ItemCategoriaUiBinder.class);
	@UiField
	InlineLabel categoria;
	@UiField
	HTMLPanel text;
	@UiField
	Button fechar;
	
	Long idEstabelecimento;

	interface ItemCategoriaUiBinder extends UiBinder<Widget, ItemCategoria> {
	}
	

	public ItemCategoria() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ItemCategoria(String categorial, Long idEstabelecimento) {
		initWidget(uiBinder.createAndBindUi(this));
		this.categoria.setText(categorial);
		fechar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				text.setVisible(false);
				// categoria.setVisible(false);
			}
		});
	}
	public ItemCategoria(CategoriaBean i) {
		initWidget(uiBinder.createAndBindUi(this));
		this.categoria.setText(i.getNome());
		fechar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				text.setVisible(false);
				// categoria.setVisible(false);
			}
		});
	}

	final TextBox campo = new TextBox();

	@UiHandler("categoria")
	void onCategoriaClick(ClickEvent event) {
		categoria.setVisible(false);
		campo.setVisible(true);
		campo.setText(categoria.getText());
		text.add(campo);
		if (campo.getText().equals("Nova Categoria")) {
			campo.setText("");
		}
		campo.setFocus(true);
		campo.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				if (!campo.getText().isEmpty()) {
					categoria.setText(campo.getText());
					categoria.setVisible(true);
					campo.setVisible(false);
					
				}
			}
			
		});
		campo.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				char keyCode = event.getCharCode();
				if (keyCode == KeyCodes.KEY_ENTER) {
					if (!campo.getText().isEmpty()) {
						categoria.setText(campo.getText());
						categoria.setVisible(true);
						campo.setVisible(false);
					
					}
				}
			}
		});

	}
	
	public String getName(){
		return categoria.getText();
	}
}
