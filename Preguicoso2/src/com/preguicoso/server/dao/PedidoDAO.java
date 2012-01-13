package com.preguicoso.server.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Pedido;

public class PedidoDAO extends DAOBase {
	static {
		ObjectifyService.register(Pedido.class);
	}

	public void create(Pedido p) {
		this.ofy().put(p);
	}

	public void update(Pedido p) {
		this.ofy().put(p);
	}

	public Pedido retrieve(Long idPedido) {
		try {
			Pedido p = this.ofy().get(Pedido.class, idPedido);
			return p;
		} catch (Exception e) {
			return null;
		}
	}

	public Pedido retrieveByNomeCliente(String nomeCliente) {
		try {
			return this.ofy().get(Pedido.class, nomeCliente);
		} catch (Exception e) {
			return null;
		}
	}

	public void delete(Pedido p) {
		this.ofy().delete(p);
	}

	public List<Pedido> retrieveAfter(Long idEstabelecimento, Date lastTime) {
		List<Pedido> lista = this.ofy().query(Pedido.class)
				.filter("idEstabelecimento", idEstabelecimento)
				.filter("timeStamp >", lastTime).list();
		Collections.sort(lista, new Comparator<Pedido>() {

			@Override
			public int compare(Pedido o1, Pedido o2) {
				if (o1.getTimeStamp().compareTo(o2.getTimeStamp()) > 0)
					return -1;
				return 1;
			}
		});
		return lista;
	}

	public List<Pedido> listByBairroTimeStamp(Long idEstabelecimento) {
		List<Pedido> lista = this.ofy().query(Pedido.class)
				.filter("idEstabelecimento", idEstabelecimento).list();

		Collections.sort(lista, new Comparator<Pedido>() {

			@Override
			public int compare(Pedido o1, Pedido o2) {
				if (o1.getBairro().compareTo(o2.getBairro()) > 0) {
					return 1;
				} else if (o1.getBairro().equals(o2.getBairro())) {
					if (o1.getTimeStamp().compareTo(o2.getTimeStamp()) > 0)
						return -1;
					return 1;
				}
				return -1;
			}

		});

		return lista;
	}

}