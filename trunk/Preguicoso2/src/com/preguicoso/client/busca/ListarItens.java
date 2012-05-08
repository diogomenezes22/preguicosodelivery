package com.preguicoso.client.busca;

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
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.entities.ItemCardapioBean;

public class ListarItens extends Composite{

	private static ListarItensUiBinder uiBinder = GWT
			.create(ListarItensUiBinder.class);
	
	private final BuscaServiceAsync buscaService = GWT
			.create(BuscaService.class);
	
	interface ListarItensUiBinder extends UiBinder<Widget, ListarItens> {
	}
	@UiField
	HTMLPanel listaEstabelecimento;
	@UiField
	Image loading;

	public ListarItens() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public ListarItens(String nome) {
		initWidget(uiBinder.createAndBindUi(this));
		
		buscaService.getListaItensByName(nome, new AsyncCallback<List<ItemCardapioBean>>() {
			
			@Override
			public void onSuccess(List<ItemCardapioBean> result) {
				if(result!=null){
					for (ItemCardapioBean itemCardapioBean : result) {
						listaEstabelecimento.add(new ListarItensItem(itemCardapioBean));
					}
				}else{
					listaEstabelecimento.add(new HTMLPanel("Não foi encontrado nenhum item"));	
				}
					loading.setVisible(false);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});	
	}


}
