package com.preguicoso.client.checkout;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.cardapio.ItemCardapioBean;

@RemoteServiceRelativePath("checkout")
public interface CheckoutService extends RemoteService {

	List<String> getBairrosAtendidos(EstabelecimentoBean eb);

	void enviarPedido(String nomeCliente, String rua, String bairro,
			String complemento, String formaPagamento, Long troco);

	// TODO @Osman deletar métodos inúteis em cardaprioService
	List<ItemCardapioBean> getCarrinho();

	String[] getEnderecoByCep(String cep);

	boolean isLogradouroTypeValid(String logradouro);

}
