package com.preguicoso.client.backend.pedidos;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.PedidoBean;

public interface PedidoServiceAsync {

	void setMotivo(String motivo, PedidoBean pb, AsyncCallback<Boolean> callback);

}
