package com.preguicoso.client.cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.RegistroStatusPedido;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.ItemCardapioBean;
import com.preguicoso.shared.entities.PedidoBean;
import com.preguicoso.shared.entities.UsuarioBean;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("cadastro")
public interface CadastroService extends RemoteService {
	public void salvarEstabelecimento(EstabelecimentoBean a);

	public EstabelecimentoBean getEstabelecimento(long id);

	public void salvarUsuario(UsuarioBean a);

	void salvarItemCardapio(ItemCardapioBean i);

	public void updateUsuario(UsuarioBean a);

	public UsuarioBean getUsuario(String email);

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
