package com.preguicoso.client.cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.PedidoBean;
import com.preguicoso.shared.entities.cardapio.CategoriaBean;
import com.preguicoso.shared.entities.cardapio.ItemCardapioBean;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("cadastro")
public interface CadastroService extends RemoteService {
	public EstabelecimentoBean getEstabelecimento(long id);

	void salvarItemCardapio(ItemCardapioBean i);

	public ArrayList<CategoriaBean> getCategoria();

	public void salvarCategoria(Long idEstabelecimento, CategoriaBean i);

	public List<PedidoBean> getListaDePedidos(Long idEstabelecimento);

	List<PedidoBean> getListaHistoricoDePedidos(Long idEstabelecimento);

	void setStatusPedido(Long idPedido, RegistroStatusPedido status);

	void removePedido(Long idPedido);

	public RegistroStatusRestaurante getStatus(Long idEstabelecimento);

	void setStatus(Long idEstabelecimento, int statusIndex);

	List<PedidoBean> getPedidosNovos(Long idEstabelecimento, Date lastTime);
}
