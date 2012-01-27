package com.preguicoso.server.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import com.preguicoso.server.entities.Pedido;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.entities.PedidoBean;

public class PedidoDAO extends DAOBase {
	static {
		ObjectifyService.register(Pedido.class);
	}

	public Key<Pedido> create(Pedido p) {
		return this.ofy().put(p);
	}

	public void update(Pedido p) {
		this.ofy().put(p);
	}

	public PedidoBean getByKey(Key<Pedido> key) {
		if (key != null) {
			try {
				Pedido p = this.ofy().get(key);
				return p.toBean();
			} catch (NotFoundException e) {
				return null;
			}
		}
		return null;
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
		RegistroStatusPedido[] listaStatus = { RegistroStatusPedido.esperando,
				RegistroStatusPedido.visto };
		List<Pedido> lista = this.ofy().query(Pedido.class)
				.filter("idEstabelecimento", idEstabelecimento)
				.filter("status in", listaStatus)
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
		RegistroStatusPedido[] listaStatus = { RegistroStatusPedido.esperando,
				RegistroStatusPedido.visto };
		List<Pedido> lista = this.ofy().query(Pedido.class)
				.filter("idEstabelecimento", idEstabelecimento)
				.filter("status in", listaStatus).list();

		// List<Pedido> lista = this.ofy().query(Pedido.class)
		// .filter("idEstabelecimento", idEstabelecimento).list();

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

	// TODO @Osman usar .order para otimizar no futuro
	public List<Pedido> listarHistorico(Long idEstabelecimento) {
		RegistroStatusPedido[] listaStatus = { RegistroStatusPedido.enviado,
				RegistroStatusPedido.recusado };
		List<Pedido> lista = this.ofy().query(Pedido.class)
				.filter("idEstabelecimento", idEstabelecimento)
				.filter("status in", listaStatus).list();

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
