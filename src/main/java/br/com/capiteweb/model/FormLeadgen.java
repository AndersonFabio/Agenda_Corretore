package br.com.capiteweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="formleadgen")
public class FormLeadgen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4795419836793689530L;
	@Id
	@GeneratedValue
	private Long id;
	private Long idEmpresa;
	private String idForm;
	private String nomeForm;
	private Long idEmpreendimento;
	private Long idCorretor;
	private Long idSituacao;
	private Long idMidia;
	@Transient
	@JsonProperty
	private Login login;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getIdForm() {
		return idForm;
	}
	public void setIdForm(String idForm) {
		this.idForm = idForm;
	}
	public String getNomeForm() {
		return nomeForm;
	}
	public void setNomeForm(String nomeForm) {
		this.nomeForm = nomeForm;
	}
	public Long getIdEmpreendimento() {
		return idEmpreendimento;
	}
	public void setIdEmpreendimento(Long idEmpreendimento) {
		this.idEmpreendimento = idEmpreendimento;
	}
	public Long getIdCorretor() {
		return idCorretor;
	}
	public void setIdCorretor(Long idCorretor) {
		this.idCorretor = idCorretor;
	}
	public Long getIdSituacao() {
		return idSituacao;
	}
	public void setIdSituacao(Long idSituacao) {
		this.idSituacao = idSituacao;
	}
	public Long getIdMidia() {
		return idMidia;
	}
	public void setIdMidia(Long idMidia) {
		this.idMidia = idMidia;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	
}
