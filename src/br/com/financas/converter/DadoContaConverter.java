package br.com.financas.converter;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.codec.binary.Hex;

@Converter
public class DadoContaConverter implements AttributeConverter<String, String> {

	private final SecretKey key = new SecretKeySpec("1234567890123456".getBytes(), "AES");

	@Override
	public String convertToDatabaseColumn(String texto) {

		if(texto == null || texto.isEmpty()) {
			return "";
		}
		
		String ret = "";

		try {

			if (texto != null && !texto.isEmpty()) {
				Cipher cipher = Cipher.getInstance("AES");

				cipher.init(Cipher.ENCRYPT_MODE, key);

				byte[] bs = cipher.doFinal(texto.getBytes());
				ret = Hex.encodeHexString(bs);
			} else {

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public String convertToEntityAttribute(String texto) {


		if(texto == null || texto.isEmpty()) {
			return "";
		}
		
		
		String ret = "";

		try {
			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.DECRYPT_MODE, key);

			byte[] b = Hex.decodeHex(texto.toCharArray());

			b = cipher.doFinal(b);
			ret = new String(b);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

}
