package br.com.capiteweb.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "checkoutpagamento")
public class CheckoutPagamento implements Serializable {
	private static final long serialVersionUID = 1239791522203833225L;
	@Id
	@GeneratedValue
	private Long id;
	private String cargo;
	private Long idCorretor;
	private Long idEmpresa;
	private String situacao;
	private String notificationCode;
	private Date data;
	private String idProduto;
	private String produto;
	private String preco;
	private Long idCaptacao;

	public Long getIdCaptacao() {
		return idCaptacao;
	}

	public void setIdCaptacao(Long idCaptacao) {
		this.idCaptacao = idCaptacao;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Long getIdCorretor() {
		return this.idCorretor;
	}

	public void setIdCorretor(Long idCorretor) {
		this.idCorretor = idCorretor;
	}

	public Long getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getNotificationCode() {
		return this.notificationCode;
	}

	public void setNotificationCode(String notificationCode) {
		this.notificationCode = notificationCode;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getProduto() {
		return this.produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getPreco() {
		return this.preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getIdProduto() {
		return this.idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
}