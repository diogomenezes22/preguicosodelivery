package com.preguicoso.shared.utils;

import java.math.BigDecimal;

public class MoneyUtils {

	public static BigDecimal parseNumber(Long centavos) {
		return new BigDecimal((double) centavos / 100);
	}

	public static String parseString(Long centavos) {
		BigDecimal number = parseNumber(centavos);
		String s = Double.toString(number.doubleValue());
		if (s.substring(s.indexOf(".")).length() == 2) {
			s = s.concat("0");
		}
		s = s.replace('.', ',');
		return s;
	}

}
