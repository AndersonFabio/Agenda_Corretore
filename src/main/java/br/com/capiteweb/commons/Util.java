package br.com.capiteweb.commons;

import javax.mail.internet.ParseException;
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
}