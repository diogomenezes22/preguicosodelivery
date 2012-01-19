package com.preguicoso.shared;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class CriptoUtils {
	private static final String hexDigits = "0123456789abcdef";

	/**
	 * Realiza um digest em um array de bytes através do algoritmo especificado
	 * 
	 * @param input
	 *            - O array de bytes a ser criptografado
	 * @param algoritmo
	 *            - O algoritmo a ser utilizado
	 * @return byte[] - O resultado da criptografia
	 * @throws NoSuchAlgorithmException
	 *             - Caso o algoritmo fornecido não seja válido
	 */
	private static byte[] digest(byte[] input, String algoritmo)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(algoritmo);
		md.reset();
		return md.digest(input);
	}

	/**
	 * Converte o array de bytes em uma representação hexadecimal.
	 * 
	 * @param input
	 *            - O array de bytes a ser convertido.
	 * @return Uma String com a representação hexa do array
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < b.length; i++) {
			int j = ((int) b[i]) & 0xFF;
			buf.append(hexDigits.charAt(j / 16));
			buf.append(hexDigits.charAt(j % 16));
		}

		return buf.toString();
	}

	/**
	 * Converte uma String hexa no array de bytes correspondente.
	 * 
	 * @param hexa
	 *            - A String hexa
	 * @return O vetor de bytes
	 * @throws IllegalArgumentException
	 *             - Caso a String não sej auma representação haxadecimal válida
	 */
	private static byte[] hexStringToByteArray(String hexa)
			throws IllegalArgumentException {

		// verifica se a String possui uma quantidade par de elementos
		if (hexa.length() % 2 != 0) {
			throw new IllegalArgumentException("String hexa inválida");
		}

		byte[] b = new byte[hexa.length() / 2];

		for (int i = 0; i < hexa.length(); i += 2) {
			b[i / 2] = (byte) ((hexDigits.indexOf(hexa.charAt(i)) << 4) | (hexDigits
					.indexOf(hexa.charAt(i + 1))));
		}
		return b;
	}

	/**
	 * 
	 * @param password
	 *            é a senha que queremos converter para MD5
	 * @return String MD5 da senha solicitada
	 */
	public static String parseMD5(String password) {
		try {
			return byteArrayToHexString(digest(password.getBytes(), "MD5"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param password
	 *            é a senha não criptografada
	 * @param md5
	 *            é o formato md5 esperado do password
	 * @return true se password no formato md5 for correspondente ao parametro
	 *         md5
	 */
	public static boolean equalsMD5(String password, String md5) {
		String passwordMD5 = parseMD5(password);
		if (passwordMD5.equals(md5))
			return true;
		return false;
	}
}