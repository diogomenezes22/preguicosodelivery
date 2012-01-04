package com.preguicoso.client.estabelecimento.cardapio;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

@RemoteServiceRelativePath("cardapio")
public interface CardapioService extends RemoteService {
	public ArrayList<ItemCardapioBean> getItensCardapio(Long id);

	void addItem(ItemCardapioBean i, int quantidade, String Observacao);

	void removeItem(ItemCardapioBean i);

	ArrayList<ItemCardapioBean> getCarrinho();

	ArrayList<CategoriaBean> getCategorias(Long Estabelecimento);

	void enviarPedido(String nomeCliente, String rua, String bairro,
			String formaPagamento);
}
