package com.preguicoso.client.editar;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.EstabelecimentoBean;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface EditarServiceAsync {
	void editarEstabelecimento(EstabelecimentoBean bean, AsyncCallback<Boolean> callback);
}
