package com.preguicoso.test.shared.utils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.preguicoso.shared.utils.MoneyUtils;

public class MoneyUtilsTest {

	@Test
	public void converterCentavos() {
		assertEquals(new BigDecimal(1), MoneyUtils.parseNumber((long) 100));
		assertEquals(new BigDecimal(2.35), MoneyUtils.parseNumber((long) 235));
		assertEquals(new BigDecimal(14.33), MoneyUtils.parseNumber((long) 1433));
	}

	@Test
	public void parseToString() {
		assertEquals("0,00", MoneyUtils.parseString((long) 0));
		assertEquals("0,05", MoneyUtils.parseString((long) 5));
		assertEquals("0,20", MoneyUtils.parseString((long) 20));
		assertEquals("1,00", MoneyUtils.parseString((long) 100));
		assertEquals("2,35", MoneyUtils.parseString((long) 235));
		assertEquals("14,33", MoneyUtils.parseString((long) 1433));
	}

	@Test
	public void parseNegativeToString() {
		assertEquals("-0,05", MoneyUtils.parseString((long) -5));
		assertEquals("-0,20", MoneyUtils.parseString((long) -20));
		assertEquals("-1,00", MoneyUtils.parseString((long) -100));
		assertEquals("-14,33", MoneyUtils.parseString((long) -1433));
	}
}
