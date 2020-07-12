package br.com.capiteweb.commons;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {
	private String date;
	private String code;
	private String reference;
	private String type;
	private String status;
	private String lastEventDate;
	private Paymentmethod paymentmethod;
	private String grossamount;
	private String discountamount;
	private String feeamount;
	private String netamount;
	private String extraamount;
	private String installmentcount;
	private String itemcount;
	@XmlElementWrapper(name = "items")
	@XmlElement(name = "item")
	private List<Item> items;
	private Sender sender;
	private Shipping shipping;

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastEventDate() {
		return this.lastEventDate;
	}

	public void setLastEventDate(String lastEventDate) {
		this.lastEventDate = lastEventDate;
	}

	public Paymentmethod getPaymentmethod() {
		return this.paymentmethod;
	}

	public void setPaymentmethod(Paymentmethod paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public String getGrossamount() {
		return this.grossamount;
	}

	public void setGrossamount(String grossamount) {
		this.grossamount = grossamount;
	}

	public String getDiscountamount() {
		return this.discountamount;
	}

	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
	}

	public String getFeeamount() {
		return this.feeamount;
	}

	public void setFeeamount(String feeamount) {
		this.feeamount = feeamount;
	}

	public String getNetamount() {
		return this.netamount;
	}

	public void setNetamount(String netamount) {
		this.netamount = netamount;
	}

	public String getExtraamount() {
		return this.extraamount;
	}

	public void setExtraamount(String extraamount) {
		this.extraamount = extraamount;
	}

	public String getInstallmentcount() {
		return this.installmentcount;
	}

	public void setInstallmentcount(String installmentcount) {
		this.installmentcount = installmentcount;
	}

	public String getItemcount() {
		return this.itemcount;
	}

	public void setItemcount(String itemcount) {
		this.itemcount = itemcount;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Sender getSender() {
		return this.sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Shipping getShipping() {
		return this.shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
}