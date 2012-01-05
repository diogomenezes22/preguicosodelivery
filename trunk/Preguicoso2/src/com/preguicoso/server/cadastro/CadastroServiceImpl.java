package com.preguicoso.server.cadastro;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.preguicoso.client.cadastro.CadastroService;
import com.preguicoso.server.dao.CategoriaDAO;
import com.preguicoso.server.dao.EstabelecimentoDAO;
import com.preguicoso.server.dao.ItemCardapioDAO;
import com.preguicoso.server.dao.PedidoDAO;
import com.preguicoso.server.dao.UsuarioDAO;
import com.preguicoso.server.dbgenerator.DbGenerator;
import com.preguicoso.server.entities.Categoria;
import com.preguicoso.server.entities.Estabelecimento;
import com.preguicoso.server.entities.ItemCardapio;
import com.preguicoso.server.entities.Pedido;
import com.preguicoso.server.entities.Usuario;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.ItemCardapioBean;
import com.preguicoso.shared.entities.PedidoBean;
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

	@Override
	public List<PedidoBean> getListaDePedidos(Long idEstabelecimento) {
		// TODO @Osman: Apagar depois. Gera pedidos.
		// GerenciadorDePedidos gp = new
		// GerenciadorDePedidos(idEstabelecimento);
		// gp.gerarPedidosDeExemplo();

		List<Pedido> lista = (new PedidoDAO())
				.listByBairroTimeStamp(idEstabelecimento);
		List<PedidoBean> listaBean = new ArrayList<PedidoBean>();
		if (lista != null) {
			for (Pedido p : lista) {
				listaBean.add(p.toBean());
			}
		}
		return listaBean;
	}

	@Override
	public ArrayList<CategoriaBean> getCategoria() {
		ArrayList<CategoriaBean> lista = new ArrayList<CategoriaBean>();
		for (Categoria categoria : (new CategoriaDAO()).listAll()) {
			lista.add(categoria.toBean());
		}
		return lista;
	}

	@Override
	public void salvarCategoria(Long idEstabelecimento, CategoriaBean i) {
		CategoriaDAO banco = new CategoriaDAO();
		Categoria e = new Categoria(i);
		e.setEstabelecimentoId(idEstabelecimento);
		banco.create(e);
	}

	@Override
	public void salvarItemCardapio(ItemCardapioBean i) {
		ItemCardapioDAO banco = new ItemCardapioDAO();
		ItemCardapio item = new ItemCardapio(i);
		banco.create(item);
	}

	@Override
	public void setPedidoVisualizado(Long idPedido) {
		PedidoDAO pdao = new PedidoDAO();
		Pedido p = pdao.retrieve(idPedido);
		p.setVisto(true);
		pdao.update(p);
		// TODO apagar
		System.out.println(p.getNomeCliente() + ": " + p.getVisto());
	}

}
