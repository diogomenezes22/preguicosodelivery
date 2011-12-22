package com.preguicoso.client.cadastro;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.UsuarioBean;

public interface CadastroServiceAsync {

	void salvarEstabelecimento(EstabelecimentoBean a,
			AsyncCallback<Void> callback);

	void getEstabelecimento(long id, AsyncCallback<EstabelecimentoBean> callback);

	void salvarUsuario(UsuarioBean a, AsyncCallback<Void> callback);

	void getUsuario(String email, AsyncCallback<UsuarioBean> callback);

	void updateUsuario(UsuarioBean a, AsyncCallback<Void> callback);

}
