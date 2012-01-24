package com.preguicoso.server.checkout;

import java.math.BigDecimal;
import java.net.URL;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.domain.Currency;
import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;

import com.preguicoso.server.entities.ItemCardapio;

public class PagSeguroCheckout {
	PaymentRequest paymentRequest = new PaymentRequest();

	public PagSeguroCheckout() {
		paymentRequest.setCurrency(Currency.BRL);
	}

	public void addItem(ItemCardapio i) {
		paymentRequest.addItem("" + i.getId(), i.getDescricao(),
				new Integer(i.getQuantidade()), new BigDecimal(i.getPreco()),
				new Long(0), new BigDecimal(0));
	}

	// public void setShippingAddress(EnderecoBean e) {
	// paymentRequest.setShippingAddress("BRA", e.getBairroBean()
	// .getCidadeBean().getEstadoBean().getNome(), e.getBairroBean()
	// .getCidadeBean().getNome(), e.getBairroBean().getNome(),
	// e.getCep(), e.getRua(), "" + e.getNumero(), e.getComplemento());
	// }

	public URL finalizarCompra() {
		try {
			return paymentRequest.register(new AccountCredentials(
					"bob_sponja_5@gmail.com",
					"92E0D915AC1E41269C926CC3719AC7EF"));
		} catch (PagSeguroServiceException e) {
			e.printStackTrace();
		}
		return null;

	}

}
