package br.com.capiteweb.commons;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Checkout {
	private Sender sender;
	private String currency;
	@XmlElementWrapper(name = "items")
	@XmlElement(name = "item")
	private List<Item> items;
	private String redirectURL;
	private String extraAmount;
	private String reference;
	private Shipping shipping;
	private String timeout;
	private String maxAge;
	private String maxUses;
	private Receiver receiver;
	private String enableRecover;

	public Sender getSender() {
		return this.sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRedirectURL() {
		return this.redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public String getExtraAmount() {
		return this.extraAmount;
	}

	public void setExtraAmount(String extraAmount) {
		this.extraAmount = extraAmount;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Shipping getShipping() {
		return this.shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public String getTimeout() {
		return this.timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getMaxAge() {
		return this.maxAge;
	}

	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}

	public String getMaxUses() {
		return this.maxUses;
	}

	public void setMaxUses(String maxUses) {
		this.maxUses = maxUses;
	}

	public Receiver getReceiver() {
		return this.receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	public String getEnableRecover() {
		return this.enableRecover;
	}

	public void setEnableRecover(String enableRecover) {
		this.enableRecover = enableRecover;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}