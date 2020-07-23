package br.com.capiteweb.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="ligacoes")
public class Ligacoes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1147277025397798088L;
	@Id
	@GeneratedValue
	private Long id;
	@Transient
	@JsonProperty
	private Login login;
	private Long idAgenda;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataAgendamento;
	private String historico;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	public Date getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	

}
