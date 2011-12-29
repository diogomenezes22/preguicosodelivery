package com.preguicoso.client.cadastro;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.UsuarioBean;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("cadastro")
public interface CadastroService extends RemoteService {
	public void salvarEstabelecimento(EstabelecimentoBean a);

	public EstabelecimentoBean getEstabelecimento(long id);

	public void salvarUsuario(UsuarioBean a);

	public void updateUsuario(UsuarioBean a);

	public UsuarioBean getUsuario(String email);
	
	public ArrayList<CategoriaBean> getCategoria(Long idEstabelecimento);

	public void salvarCategoria(Long idEstabelecimento,CategoriaBean i);

}
