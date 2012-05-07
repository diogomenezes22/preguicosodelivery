package com.preguicoso.server.dao.cardapio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.cardapio.ItemCardapio;

public class ItemCardapioDAO extends DAOBase{

	static{
		ObjectifyService.register(ItemCardapio.class);
	}

	public void create(ItemCardapio itemCardapio) {
		//if (this.retrieveItemsByEstabelecimento(itemCardapio.getEstabelecimento()).contains(itemCardapio)) {
			//assert false;
		//}

		itemCardapio.setUltimaAtualizacao(Calendar.getInstance().getTime());
		itemCardapio.setDataRegistro(Calendar.getInstance().getTime());
		this.ofy().put(itemCardapio);
	}

	public ArrayList<ItemCardapio> retrieveItemsByEstabelecimento(Estabelecimento estabelecimento) {
		ArrayList<ItemCardapio> items;
		items = (ArrayList<ItemCardapio>) this.ofy().query(ItemCardapio.class).filter("estabelecimento", estabelecimento).list();
		if (items.isEmpty()) {
			items = new ArrayList<ItemCardapio>();
		}
		return items;
	}

	public ItemCardapio retrieve(Long id) {
		try {
			return this.ofy().get(ItemCardapio.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public void update(ItemCardapio item) {
	
	}

	public void delete(ItemCardapio item) {
		this.ofy().delete(item);
	}

	public List<ItemCardapio> listAll() {
		return this.ofy().query(ItemCardapio.class).list();
	}

	public List<ItemCardapio> listItensEstabelecimento(Long id) {
		Estabelecimento estabelecimento = (new EstabelecimentoDAO()).retrieve(id);
		List<ItemCardapio> itens = this.ofy().query(ItemCardapio.class).filter("estabelecimentoId", estabelecimento.getId()).order("categoria").list();
		return itens;
	}
}
