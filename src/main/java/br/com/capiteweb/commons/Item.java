package br.com.capiteweb.commons;

public class Item {
	private String id;
	private String description;
	private String amount;
	private String quantity;
	private String weight;
	private String shippingCost;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getShippingCost() {
		return this.shippingCost;
	}

	public void setShippingCost(String shippingCost) {
		this.shippingCost = shippingCost;
	}
}