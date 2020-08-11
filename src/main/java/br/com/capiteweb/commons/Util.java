package br.com.capiteweb.commons;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.internet.ParseException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.mail.SimpleEmail;



public class Util {
	@SuppressWarnings("deprecation")
	public static void EnviarEmail(String mensagem, String destino, String assunto) throws ParseException {
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.capiteweb.com.br");
		email.setAuthentication("contato@capiteweb.com.br", "P@c01701");
		email.setDebug(true);
		email.setStartTLSEnabled(true);
		email.setHostName("smtp.capiteweb.com.br");
		email.setSmtpPort(587);
		email.setSSL(false);
		email.setTLS(false);

		try {
			email.addTo(destino);
			email.setFrom("contato@capiteweb.com.br", "CapiteWeb");
			email.setSubject(assunto);
			email.setMsg(mensagem);
			email.send();
		} catch (Exception var5) {
			;
		}

	}
	
	public static Connection getConexao()throws ClassNotFoundException, SQLException{
		final String url = "jdbc:mysql://mysql.capiteweb.com.br/capiteweb";
		final String username = "capiteweb";
		final String password = "Paco1701";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
		}
	
	public static String encode(String key, String data) {
	    try {

	        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
	        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
	        sha256_HMAC.init(secret_key);

	        return new String(Hex.encodeHex(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch (InvalidKeyException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
}