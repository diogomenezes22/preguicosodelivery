package com.preguicoso.client.estabelecimento.cardapio;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.preguicoso.shared.entities.BairroBean;
import com.preguicoso.shared.entities.CategoriaBean;
import com.preguicoso.shared.entities.CidadeBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;
import com.preguicoso.shared.entities.ItemCardapioBean;

@RemoteServiceRelativePath("cardapio")
public interface CardapioService extends RemoteService {
	public ArrayList<ItemCardapioBean> getItensCardapio(Long id);

	void removeItem(ItemCardapioBean i);

	ArrayList<ItemCardapioBean> getCarrinho();

	void carrinhoClean();

	ArrayList<CategoriaBean> getCategorias(Long Estabelecimento);

	void addItem(ItemCardapioBean i, int quantidade, String observacao);

	List<BairroBean> getBairros(Long idCidade);

	CidadeBean getCidade(Long idEstabelecimento);

	List<CidadeBean> getCidadesList();

	void setCidade(Long idEstabelecimento, CidadeBean cidadeBean);

	void salvarListaBairros(EstabelecimentoBean estabelecimentoBean,
			List<String> listaBairros);

	void updateEstabelecimento(EstabelecimentoBean eb,
			List<Long> idBairrosAtendidos);

}