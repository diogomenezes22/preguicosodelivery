package com.preguicoso.client.listar;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.entities.EstabelecimentoBean;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("busca")
public interface ListaService extends RemoteService {
	ArrayList<EstabelecimentoBean> getListaEstabelecimento();
	ArrayList<EstabelecimentoBean> getEstabelecimentoPorCep(String bairro) throws IllegalArgumentException;
}
