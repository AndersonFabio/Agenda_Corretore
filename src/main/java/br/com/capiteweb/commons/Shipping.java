package br.com.capiteweb.commons;

public class Shipping {
	private Address address;
	private String type;
	private String cost;
	private String addressRequired;

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCost() {
		return this.cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getAddressRequired() {
		return this.addressRequired;
	}

	public void setAddressRequired(String addressRequired) {
		this.addressRequired = addressRequired;
	}
}