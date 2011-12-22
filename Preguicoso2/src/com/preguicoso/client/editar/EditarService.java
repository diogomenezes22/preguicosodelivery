package com.preguicoso.client.editar;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.entities.EstabelecimentoBean;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("busca")
public interface EditarService extends RemoteService {
	Boolean editarEstabelecimento(EstabelecimentoBean bean);
}
