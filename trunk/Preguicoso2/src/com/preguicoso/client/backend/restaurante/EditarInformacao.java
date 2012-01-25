package com.preguicoso.client.backend.restaurante;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.preguicoso.client.estabelecimento.cardapio.CardapioService;
import com.preguicoso.client.estabelecimento.cardapio.CardapioServiceAsync;
import com.preguicoso.shared.RegistroErros;
import com.preguicoso.shared.entities.BairroBean;
import com.preguicoso.shared.entities.CidadeBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;

public class EditarInformacao extends Composite {

	private static OrdemPedidosUiBinder uiBinder = GWT
			.create(OrdemPedidosUiBinder.class);

	interface OrdemPedidosUiBinder extends UiBinder<Widget, EditarInformacao> {
	}

	private final CardapioServiceAsync cardapioService = GWT
			.create(CardapioService.class);

	@UiField
	ListBox cidadeBox;
	@UiField
	VerticalPanel bairrosPanel;
	@UiField
	Button salvarBairros;

	List<CidadeBean> cidadesList;
	List<BairroBean> bairrosList;
	EstabelecimentoBean eb;

	public EditarInformacao(EstabelecimentoBean eb) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eb = eb;
		listarCidades();
		carregarCidadeAtual(eb.getId());
	}

	@UiHandler("cidadeBox")
	void onCidadeBoxChange(ChangeEvent event) {
		final CidadeBean cidadeBeanSelected = cidadesList.get(cidadeBox
				.getSelectedIndex());
		cardapioService.setCidade(eb.getId(), cidadeBeanSelected,
				new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(RegistroErros.CONEXAO);
					}

					@Override
					public void onSuccess(Void result) {
						listarBairros(cidadeBeanSelected);
					}
				});

	}

	@UiHandler("salvarBairros")
	void onSalvarBairrosClick(ClickEvent event) {
		List<Long> idBairrosAtendidos = new ArrayList<Long>();
		CheckBox bairroCheckBox;
		for (int i = 0; i < bairrosPanel.getWidgetCount(); i++) {
			bairroCheckBox = (CheckBox) bairrosPanel.getWidget(i);
			if (bairroCheckBox.getValue()) {
				idBairrosAtendidos.add(Long.parseLong(bairroCheckBox
						.getFormValue()));
			}
		}
		cardapioService.updateEstabelecimento(eb, idBairrosAtendidos,
				new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Não foi possível salvar a lista de bairros de atuação.");
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Dados salvos com sucesso.");
					}
				});
	}

	private void listarCidades() {
		CidadeBean fortaleza = new CidadeBean();
		fortaleza.setId((long) 1);
		fortaleza.setNome("Fortaleza");
		cidadesList = new ArrayList<CidadeBean>();
		cidadesList.add(fortaleza);
		cidadeBox.addItem(fortaleza.getNome());
		cardapioService.getCidadesList(new AsyncCallback<List<CidadeBean>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(RegistroErros.CONEXAO);
			}

			@Override
			public void onSuccess(List<CidadeBean> result) {
				cidadeBox.clear();
				cidadesList = new ArrayList<CidadeBean>(result);
				for (CidadeBean cb : cidadesList) {
					cidadeBox.addItem(cb.getNome());
				}
			}
		});
	}

	private void carregarCidadeAtual(final Long idEstabelecimento) {
		cardapioService.getCidade(idEstabelecimento,
				new AsyncCallback<CidadeBean>() {

					@Override
					public void onSuccess(CidadeBean result) {
						cidadeBox.setSelectedIndex(cidadesList.indexOf(result));
						listarBairros(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Problema de conexão. Recarregue a página.");
					}
				});
	}

	private void listarBairros(CidadeBean cb) {
		cardapioService.getBairros(cb.getId(),
				new AsyncCallback<List<BairroBean>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Problema de conexão. Recarregue a página.");
					}

					@Override
					public void onSuccess(List<BairroBean> result) {
						bairrosPanel.clear();
						List<Long> idBairrosAtendidos = eb
								.getIdBairroAtendimentoList();
						bairrosList = new ArrayList<BairroBean>(result);
						CheckBox bairroCheckBox;
						for (BairroBean bb : result) {
							bairroCheckBox = new CheckBox(bb.getNome());
							bairroCheckBox.setStyleName("inputFloat");
							bairroCheckBox.setFormValue(bb.getId().toString());
							if (idBairrosAtendidos.contains(bb.getId())) {
								bairroCheckBox.setValue(true);
							}
							bairrosPanel.add(bairroCheckBox);
						}
					}
				});
	}
}
