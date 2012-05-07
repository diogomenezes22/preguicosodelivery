package com.preguicoso.client.cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.PedidoBean;
import com.preguicoso.shared.entities.cardapio.CategoriaBean;
import com.preguicoso.shared.entities.cardapio.ItemCardapioBean;

public interface CadastroServiceAsync {

	void getEstabelecimento(long id, AsyncCallback<EstabelecimentoBean> callback);

	void getListaDePedidos(Long idEstabelecimento,
			AsyncCallback<List<PedidoBean>> callback);

	void getCategoria(AsyncCallback<ArrayList<CategoriaBean>> callback);

	void salvarCategoria(Long idEstabelecimento, CategoriaBean i,
			AsyncCallback<Void> callback);

	void salvarItemCardapio(ItemCardapioBean i, AsyncCallback<Void> callback);

	void getStatus(Long idEstabelecimento,
			AsyncCallback<RegistroStatusRestaurante> callback);

	void setStatus(Long idEstabelecimento, int statusIndex,
			AsyncCallback<Void> callback);

	void getPedidosNovos(Long idEstabelecimento, Date lastTime,
			AsyncCallback<List<PedidoBean>> callback);

	void removePedido(Long idPedido, AsyncCallback<Void> callback);

	void setStatusPedido(Long idPedido, RegistroStatusPedido status,
			AsyncCallback<Void> callback);

	void getListaHistoricoDePedidos(Long idEstabelecimento,
			AsyncCallback<List<PedidoBean>> callback);

}
