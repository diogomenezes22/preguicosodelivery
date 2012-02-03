package com.preguicoso.client.backend.pedidos;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.entities.PedidoBean;

@RemoteServiceRelativePath("pedido")
public interface PedidoService extends RemoteService {

	boolean setMotivo(String motivo, PedidoBean pb);
}
