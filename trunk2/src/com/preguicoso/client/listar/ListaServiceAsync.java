package com.preguicoso.client.listar;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.EstabelecimentoBean;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ListaServiceAsync {
	void getListaEstabelecimento(AsyncCallback<ArrayList<EstabelecimentoBean>> callback);
	void getEstabelecimentoPorCep(String bairro, AsyncCallback<ArrayList<EstabelecimentoBean>> callback);
}
