package br.com.capiteweb.commons;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "checkout")
public class CheckoutRetorno {
	private String code;
	private String date;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}