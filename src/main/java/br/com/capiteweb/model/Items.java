package br.com.capiteweb.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Items {
	public Long id;
	public String description;
	@JsonFormat(pattern = "999999.99")
	public Float amount;
	@JsonFormat(pattern = "999999")
	public Float quantity;
	@JsonFormat(pattern = "999999")
	public Float weight;
	@JsonFormat(pattern = "999999.99")
	public Float shippingCost;
}