package com.preguicoso.shared;

public enum RegistroFormaPagamento {
	dinheiro, cartaoDebito, cartaoCredito;

	public String asHTML() {
		if (this.equals(RegistroFormaPagamento.dinheiro)) {
			return "<img src=\""
					+ "http://www.controlenanet.com.br/portal/images/dinheiro.png"
					+ "\" />" + "Dinheiro<br />";
		}
		if (this.equals(RegistroFormaPagamento.cartaoDebito)) {
			return "<img src=\""
					+ "http://www.itau.com.br/seguranca/img/nav/nav_sb_03_00.gif"
					+ "\" />" + "Cartão/Débito<br />";
		}
		return "<img src=\""
				+ "http://www.itau.com.br/seguranca/img/nav/nav_sb_03_00.gif"
				+ "\" />" + "Cartão/Crédito<br />";
	}
}
