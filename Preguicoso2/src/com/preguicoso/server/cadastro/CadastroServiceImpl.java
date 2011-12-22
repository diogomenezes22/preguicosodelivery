package com.preguicoso.server.cadastro;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.dao.UsuarioDAO;
import com.preguicoso.server.dbgenerator.DbGenerator;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.Usuario;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.UsuarioBean;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CadastroServiceImpl extends RemoteServiceServlet implements
		CadastroService {

	@Override
	public void salvarEstabelecimento(EstabelecimentoBean a) {
		EstabelecimentoDAO banco = new EstabelecimentoDAO();
		Estabelecimento estabelecimento = new Estabelecimento(a);
		UserService userservice = UserServiceFactory.getUserService();
		UsuarioDAO bancoU = new UsuarioDAO();
		DbGenerator gerar = new DbGenerator();
		gerar.generateDatabase();
		Usuario user = bancoU.retrieve(userservice.getCurrentUser().getEmail());
		estabelecimento.setDono(user);
		// banco.create(estabelecimento);
	}

	@Override
	public EstabelecimentoBean getEstabelecimento(long id) {
		EstabelecimentoDAO banco = new EstabelecimentoDAO();
		Estabelecimento a = banco.retrieve(id);
		return a.toBean();
	}

	@Override
	public void salvarUsuario(UsuarioBean a) {
		UsuarioDAO banco = new UsuarioDAO();
		Usuario e = new Usuario(a);
		banco.create(e);
	}

	@Override
	public UsuarioBean getUsuario(String email) {
		UsuarioDAO banco = new UsuarioDAO();
		Usuario a = banco.retrieve(email);
		return a.toBean();
	}

	@Override
	public void updateUsuario(UsuarioBean a) {
		UsuarioDAO banco = new UsuarioDAO();
		Usuario e = new Usuario(a);
		banco.update(e);
	}
}
