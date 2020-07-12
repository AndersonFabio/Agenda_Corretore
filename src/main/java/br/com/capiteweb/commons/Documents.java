package br.com.capiteweb.commons;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Documents {
	@XmlElementWrapper(name = "documents")
	@XmlElement(name = "document")
	private List<Document> document;

	public List<Document> getDocument() {
		return this.document;
	}

	public void setDocument(List<Document> document) {
		this.document = document;
	}
}