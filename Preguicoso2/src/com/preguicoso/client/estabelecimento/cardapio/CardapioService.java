package com.preguicoso.client.estabelecimento.cardapio;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

@RemoteServiceRelativePath("cardapio")
public interface CardapioService extends RemoteService {
	public ArrayList<ItemCardapioBean> getItensCardapio(Long id);

	void removeItem(ItemCardapioBean i);

	ArrayList<ItemCardapioBean> getCarrinho();

	void carrinhoClean();

	ArrayList<CategoriaBean> getCategorias(Long Estabelecimento);

	void enviarPedido(String nomeCliente, String rua, String bairro,
			String complemento, String formaPagamento);

	void addItem(ItemCardapioBean i, int quantidade, String observacao);

	String[] getEnderecoByCep(String cep);

	List<String> getBairrosNome(Long idCidade);
}
