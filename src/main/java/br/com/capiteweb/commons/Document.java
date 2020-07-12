package br.com.capiteweb.commons;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Document {
	private String type;
	private String value;

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}