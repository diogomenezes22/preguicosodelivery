package com.preguicoso.shared;

public class FormValidatorShared {
	// TODO @Osman usar trim
	public static boolean hasNumberOnly(String string) {
		char letra;
		for (int i = 0; i < string.length(); i++) {
			letra = string.charAt(i);
			if (letra < '0' || '9' < letra) {
				return false;
			}
		}
		return true;

		// O jeito mais rápido de verificar se é número
		// try {
		// Long.parseLong (s);
		// } catch (NumberFormatException ex) {
		// return false;
		// }
		// return true;
	}

	public static boolean hasLetterOnly(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isLetter(string.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static Character getFirstSpecialCharacter(String string) {
		for (int i = 0; i < string.length(); i++) {
			char letra = string.charAt(i);
			if (!Character.isLetterOrDigit(letra) && letra != ' ') {
				return letra;
			}
		}
		return null;
	}
}
