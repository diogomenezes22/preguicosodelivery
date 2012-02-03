package com.preguicoso.server.backend.pedidos;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.backend.pedidos.PedidoService;
import com.preguicoso.server.dao.PedidoDAO;
import com.preguicoso.server.entities.Pedido;
import com.preguicoso.shared.entities.PedidoBean;

public class PedidoServiceImpl extends RemoteServiceServlet implements
		PedidoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3590999714198865129L;

	@Override
	public boolean setMotivo(String motivo, PedidoBean pb) {
		PedidoDAO pdao = new PedidoDAO();
		if (motivo != null) {
			if (!motivo.isEmpty()) {
				Pedido p = new Pedido(pb);
				p.setMotivo(motivo);
				pdao.update(p);
				return true;
			}
		}
		return false;
	}

}
