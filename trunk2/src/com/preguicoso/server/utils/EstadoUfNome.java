package com.preguicoso.server.utils;

import java.util.HashMap;
import java.util.Map;

public class EstadoUfNome {
	private final static Map<String,String> ufEstado = new HashMap<String, String>();

	public static String getEstadoByUf(String uf) {
		if (ufEstado.isEmpty()) {
			ufEstado.put("AC", "Acre");
			ufEstado.put("AL", "Alagoas");
			ufEstado.put("AP", "Amap‡");
			ufEstado.put("AM", "Amazonas");
			ufEstado.put("BA", "Bahia");
			ufEstado.put("CE", "Cear‡");
			ufEstado.put("DF", "Distrito Federal");
			ufEstado.put("GO", "Goi‡s");
			ufEstado.put("MA", "Maranh‹o");
			ufEstado.put("MT", "Mato Grosso");
			ufEstado.put("MS", "Mato Grosso do Sul");
			ufEstado.put("MG", "Minas Gerais");
			ufEstado.put("PA", "Par‡");
			ufEstado.put("PB", "Para’ba");
			ufEstado.put("PR", "Paran‡");
			ufEstado.put("PE", "Pernambuco");
			ufEstado.put("PI", "Piau’");
			ufEstado.put("RJ", "Rio de Janeiro");
			ufEstado.put("RN", "Rio Grande do Norte");
			ufEstado.put("RS", "Rio Grande do Sul");
			ufEstado.put("RO", "Rond™nia");
			ufEstado.put("RR", "Roraima");
			ufEstado.put("SC", "Santa Catarina");
			ufEstado.put("SP", "S‹o Paulo");
			ufEstado.put("SE", "Sergipe");
			ufEstado.put("TO", "Tocantins");
		}

		return ufEstado.get(uf);
	}
}
