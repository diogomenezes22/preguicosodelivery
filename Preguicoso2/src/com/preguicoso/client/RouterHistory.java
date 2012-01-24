package com.preguicoso.client;

import static com.google.gwt.query.client.GQuery.$;
import static com.google.gwt.query.client.plugins.Effects.Effects;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.query.client.Function;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.preguicoso.client.busca.Busca;
import com.preguicoso.client.busca.ListaEstabelecimento;
import com.preguicoso.client.estabelecimento.EstabelecimentoUI;

public class RouterHistory {

	public RouterHistory() {
		super();
	}

	public void routerHistory() {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				String token = History.getToken();
				clearContent();
				buildHomePage(token);
			}

		});
		$("#botao_toggle").live(Event.ONCLICK, new Function() {
			@Override
			public void f() {
				$("#explainConteiner").as(Effects).slideToggle(500);
			}
		});
		History.fireCurrentHistoryState();
	}

	public void buildHomePage(String token) {
		if (token.startsWith("index")) {
			RootPanel.get("cadastroEstabelecimento").setVisible(true);
			$(".middle_rodape").as(Effects).slideDown(1000);
			addSuggestBox();
			addListEstabelecimento();
		}
		// else if (token.equals("cadastroEstabelecimento")) {
		// CadastroEstabelecimento cadastro = new CadastroEstabelecimento();
		// RootPanel.get("busca").add(cadastro);}
		// else if (token.equals("cadastroUsuario")) {
		// CadastroEstabelecimento cadastro = new CadastroEstabelecimento();
		// RootPanel.get("busca").add(cadastro);}
		else if (token.equals("quem")) {
			animateBegin();
			gerarHtml("quem");
		} else if (token.equals("temos")) {
			animateBegin();
			gerarHtml("temos");
		} else if (token.equals("ajuda")) {
			animateBegin();
			gerarHtml("ajuda");
		} else if (token.equals("filie")) {
			animateBegin();
			gerarHtml("filie");
		} else if (token.contains("lista")) {
			ListaEstabelecimento listaEstabelecimento = new ListaEstabelecimento(
					"", token.substring(6));
			RootPanel.get("busca").add(listaEstabelecimento);
		} else if (token.startsWith("Estabelecimento/")) {
			RootPanel.get("cadastroEstabelecimento").setVisible(false);
			animateBegin();
			printEstabelicimento(token);
		}
	}

	public void animateBegin() {
		$("#explainConteiner").as(Effects).slideUp(200);
		$(".middle_rodape").as(Effects).slideUp(200);
		// RootPanel.get("cadastroEstabelecimento").clear();
	}

	public void printEstabelicimento(String token) {
		long id;
		String[] nomes = token.split("/");
		if (token.length() > 16) {
			id = Long.parseLong(nomes[1]);
		} else {
			id = 0;
		}

		EstabelecimentoUI page = new EstabelecimentoUI(id);
		// RootPanel.get("content").clear();
		RootPanel.get("content").add(page);
	}

	public void clearContent() {
		RootPanel.get("busca").clear();
		RootPanel.get("searchFieldDiv").clear();
		RootPanel.get("content").clear();
	}

	public void addListEstabelecimento() {
		ListaEstabelecimento listaEstabelecimento = new ListaEstabelecimento();
		RootPanel.get("busca").add(listaEstabelecimento);
	}

	public void addSuggestBox() {
		// SUGGEST BOX
		final Busca buscaEstabelecimento = new Busca(Entities.Estabelecimento);
		HTML lupaButton = new HTML(
				"<img style='cursor: pointer;'src='img/lupa.png'/>");
		lupaButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!buscaEstabelecimento.getSuggestBox().getText().equals("")) {
					RootPanel.get("busca").clear();
					ListaEstabelecimento lista = new ListaEstabelecimento(
							buscaEstabelecimento.getSuggestBox().getText());
					RootPanel.get("busca").add(lista);
				}
			}
		});
		RootPanel.get("searchFieldDiv").add(buscaEstabelecimento);
		lupaButton.getElement().setId("searchFieldImg");
		RootPanel.get("searchFieldDiv").add(lupaButton);
	}

	protected void gerarHtml(String string) {
		if (string.equals("quem")) {
			String html = "";
			html += "<h1>Quem Somos</h1>";
			HTMLPanel quem = new HTMLPanel(html);
			RootPanel.get("mainConteiner").clear();
			RootPanel.get("mainConteiner").add(quem);
		}
		if (string.equals("temos")) {
			String html = "";
			html += "<h1>Termos de Compromisso</h1>";
			HTMLPanel quem = new HTMLPanel(html);
			RootPanel.get("mainConteiner").clear();
			RootPanel.get("mainConteiner").add(quem);
		}
		if (string.equals("ajuda")) {
			String html = "";
			html += "<h1>FAC</h1>";
			HTMLPanel quem = new HTMLPanel(html);
			RootPanel.get("mainConteiner").clear();
			RootPanel.get("mainConteiner").add(quem);
		}
		if (string.equals("filie")) {
			String html = "";
			html += "<h1>Filie-se</h1>";
			HTMLPanel quem = new HTMLPanel(html);
			RootPanel.get("mainConteiner").clear();
			RootPanel.get("mainConteiner").add(quem);
		}

	}

}