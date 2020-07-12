package br.com.capiteweb.commons;

import java.util.List;

public class Sender {
	private String name;
	private String email;
	private Phone phone;
	private List<Document> documents;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Phone getPhone() {
		return this.phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public List<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
}