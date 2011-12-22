package com.preguicoso.server.carrinho;

import java.util.HashMap;

import com.preguicoso.server.dao.UsuarioDAO;
import com.preguicoso.server.entities.ItemCardapio;
import com.preguicoso.server.entities.Usuario;
import com.preguicoso.shared.entities.ItemCardapioBean;

public class CarrinhoDeCompra {
	private Usuario usuario;
	private HashMap<ItemCardapio, Integer> pedido = new HashMap<ItemCardapio, Integer>();

	public HashMap<ItemCardapio, Integer> getPedido() {
		return pedido;
	}

	public void setPedido(HashMap<ItemCardapio, Integer> pedido) {
		this.pedido = pedido;
	}

	private double frete;

	public CarrinhoDeCompra(String email) {
		UsuarioDAO banco = new UsuarioDAO();
		usuario = banco.retrieve(email);
	}

	public boolean isEmpty() {
		return pedido.isEmpty();
	}

	public void addItem(ItemCardapio item) {
		if (pedido.get(item) == null)
			pedido.put(item, item.getQuantidade());
		else {
			int quant = pedido.get(item) + 1;
			popItem(item);
			item.setQuantidade(quant);
			pedido.put(item, quant);
		}
	}

	public void addItem(ItemCardapio retrieve, int quantidade, String observacao) {
		retrieve.setObservacao(observacao);
		retrieve.setQuantidade(quantidade);
		pedido.put(retrieve, quantidade);

	}

	public ItemCardapio popItem() {
		Iterable<ItemCardapio> iter = pedido.keySet();
		ItemCardapio item = null;
		for (ItemCardapio itemCardapio : iter) {
			item = itemCardapio;
		}
		if (pedido.get(item) == 1)
			pedido.remove(item);
		else
			pedido.put(item, pedido.get(item) - 1);
		return item;
	}

	public int getQuantidade(ItemCardapio item) {
		return pedido.get(item);
	}

	public void limpar() {
		pedido = new HashMap<ItemCardapio, Integer>();
	}

	/**
	 * Soma de todos os produtos no carrinho;
	 * 
	 * @author Abraao Barros
	 */
	public double soma() {
		Iterable<ItemCardapio> i = pedido.keySet();
		long somaP = 0;
		for (ItemCardapio itemCardapio : i) {
			somaP += itemCardapio.getPreco() * getQuantidade(itemCardapio);
		}
		return somaP;
	}

	public void setFrete(double d) {
		frete = d;
	}

	public double getFrete() {
		return frete;
	}

	public double somaTotal() {
		return soma() + getFrete();
	}

	public void popItem(ItemCardapio item) {
		HashMap<ItemCardapio, Integer> pedidoaux = new HashMap<ItemCardapio, Integer>();
		for (ItemCardapio e : pedido.keySet()) {
			if (!(e.getId() == item.getId()) && pedido.get(e) == 1) {
				pedidoaux.put(e, pedido.get(e));
			}
		}
		pedido = pedidoaux;
	}

	public int getSize() {
		return pedido.size();
	}

	public void removeItem(ItemCardapioBean i) {
		pedido.remove(i);
	}
}
