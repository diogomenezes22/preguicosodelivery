package com.preguicoso.server.checkout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidatorServer {
	public static boolean isLogradouroTypeValid(String logradouro) {
		Matcher m = Pattern
				.compile(
						"^AEROPORTO |^AER |^ALAMEDA |^AL |^APARTAMENTO |^AP |^AVENIDA |^AV |^BECO |^BC "
								+ "|^BLOCO |^BL |^CAMINHO |^CAM |^ESCADINHA |^ESCD |^ESTAÇÃO |^EST |^ESTRADA "
								+ "|^ETR |^FAZENDA |^FAZ |^FORTALEZA |^FORT |^GALERIA |^GL |^LADEIRA |^LD "
								+ "|^LARGO |^LGO |^PRAÇA |^PÇA |^PARQUE |^PRQ |^PRAIA |^PR |^QUADRA |^QD "
								+ "|^QUILÔMETRO |^KM |^QUINTA |^QTA |^RODOVIA |^ROD |^RUA |^R |^SUPER "
								+ "|^QUADRA |^SQD |^TRAVESSA |^TRV |^VIADUTO |^VD |^VILA |^VL ",
						Pattern.CASE_INSENSITIVE).matcher(logradouro);
		if (m.find())
			return true;
		return false;
	}
}
