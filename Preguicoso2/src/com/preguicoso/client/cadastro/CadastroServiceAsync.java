package com.preguicoso.client.cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.ItemCardapioBean;
import com.preguicoso.shared.entities.PedidoBean;
import com.preguicoso.shared.entities.UsuarioBean;

public interface CadastroServiceAsync {

	void salvarEstabelecimento(EstabelecimentoBean a,
			AsyncCallback<Void> callback);

	void getEstabelecimento(long id, AsyncCallback<EstabelecimentoBean> callback);

	void salvarUsuario(UsuarioBean a, AsyncCallback<Void> callback);

	void getUsuario(String email, AsyncCallback<UsuarioBean> callback);

	void updateUsuario(UsuarioBean a, AsyncCallback<Void> callback);

	void getListaDePedidos(Long idEstabelecimento,
			AsyncCallback<List<PedidoBean>> callback);

	void getCategoria(AsyncCallback<ArrayList<CategoriaBean>> callback);

	void salvarCategoria(Long idEstabelecimento, CategoriaBean i,
			AsyncCallback<Void> callback);

	void salvarItemCardapio(ItemCardapioBean i, AsyncCallback<Void> callback);

	void setPedidoVisualizado(Long idPedido, AsyncCallback<Void> callback);

	void getStatus(Long idEstabelecimento, AsyncCallback<Integer> callback);

	void setStatus(Long idEstabelecimento, Integer status,
			AsyncCallback<Void> callback);

	void setPedidoEnviado(Long idPedido, AsyncCallback<Void> callback);

	void getPedidosNovos(Long idEstabelecimento, Date lastTime,
			AsyncCallback<List<PedidoBean>> callback);

	void removePedido(Long idPedido, AsyncCallback<Void> callback);

}
