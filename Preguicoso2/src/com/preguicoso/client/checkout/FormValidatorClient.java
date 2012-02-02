package com.preguicoso.client.checkout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gwt.user.client.Window;

public class FormValidatorClient {

	public static boolean isLogradouroValid(String logradouro) {
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

	public static boolean isFormValid(String logradouro, String numero) {
		boolean isValid = false;
		if (logradouro.equals("Logradouro")) {
			Window.alert("Digite o logradouro.");
		} else if (numero.equals("Número")) {
			Window.alert("Digite o número.");
		} else if (!hasNumberOnly(numero)) {
			Window.alert("O campo número deve conter apenas números.");
		} else {
			isValid = true;
		}
		return isValid;
	}

	public static boolean hasNumberOnly(String string) {
		char letra;
		for (int i = 0; i < string.length(); i++) {
			letra = string.charAt(i);
			if (letra < '0' || '9' < letra) {
				return false;
			}
		}
		return true;
	}

	public static boolean hasLetterOnly(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isLetter(string.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
