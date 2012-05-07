package com.preguicoso.server.carrinho;

import java.util.ArrayList;
import java.util.List;

import com.preguicoso.shared.entities.cardapio.ItemCardapioBean;

public class CarrinhoDeCompra {
	// private Usuario usuario;

	private List<ItemCardapioBean> listaPedido = new ArrayList<ItemCardapioBean>();

	public List<ItemCardapioBean> getPedido() {
		return listaPedido;
	}

	public void setPedido(List<ItemCardapioBean> listaPedido) {
		this.listaPedido = listaPedido;
	}

	private double frete;

	public CarrinhoDeCompra(String email) {
		// UsuarioDAO banco = new UsuarioDAO();
		// usuario = banco.retrieve(email);
	}

	public boolean isEmpty() {
		return listaPedido.isEmpty();
	}

	public void addItem(ItemCardapioBean item) {
		listaPedido.add(item);

		// if (pedido.get(item) == null)
		// pedido.put(item, item.getQuantidade());
		// else {
		// int quant = pedido.get(item) + 1;
		// popItem(item);
		// item.setQuantidade(quant);
		// pedido.put(item, quant);
		// }
	}

	// public void addItem(ItemCardapio retrieve, int quantidade, String
	// observacao) {
	// retrieve.setObservacao(observacao);
	// retrieve.setQuantidade(quantidade);
	// pedido.put(retrieve, quantidade);
	//
	// }

	public ItemCardapioBean popItem() {
		return listaPedido.remove(listaPedido.size() - 1);
		// Iterable<ItemCardapio> iter = pedido.keySet();
		// ItemCardapio item = null;
		// for (ItemCardapio itemCardapio : iter) {
		// item = itemCardapio;
		// }
		// if (pedido.get(item) == 1)
		// pedido.remove(item);
		// else
		// pedido.put(item, pedido.get(item) - 1);
	}

	public void limpar() {
		listaPedido = new ArrayList<ItemCardapioBean>();
		// pedido = new HashMap<ItemCardapio, Integer>();
	}

	/**
	 * Soma de todos os produtos no carrinho;
	 * 
	 * @author Abraao Barros
	 */
	public double soma() {
		double somaP = 0;
		for (ItemCardapioBean item : listaPedido) {
			somaP += item.getQuantidade() * item.getPreco();
		}
		return somaP;

		// Iterable<ItemCardapioBean> i = (Iterable<ItemCardapioBean>)
		// listaPedido
		// .iterator();
		// long somaP = 0;
		// for (ItemCardapio itemCardapio : i) {
		// somaP += itemCardapio.getPreco() * getQuantidade(itemCardapio);
		// }
		// return somaP;
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

	public void popItem(ItemCardapioBean item) {
		listaPedido.remove(item);

		// HashMap<ItemCardapio, Integer> pedidoaux = new HashMap<ItemCardapio,
		// Integer>();
		// for (ItemCardapio e : pedido.keySet()) {
		// if (!(e.getId() == item.getId()) && pedido.get(e) == 1) {
		// pedidoaux.put(e, pedido.get(e));
		// }
		// }
		// pedido = pedidoaux;
	}

	public int getSize() {
		return listaPedido.size();
		// return pedido.size();
	}

	// public void removeItem(ItemCardapioBean i) {
	// listaPedido.remove(i);
	// // pedido.remove(i);
	// }
}
