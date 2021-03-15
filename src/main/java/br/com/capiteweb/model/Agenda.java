package br.com.capiteweb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "agenda")
public class Agenda implements Serializable {
	private static final long serialVersionUID = -6323827157600326302L;
	@Id
	@GeneratedValue
	private Long id;
	private Long idCliente;
	@Transient
	@JsonProperty
	private Login login;
	private Long idEmpreendimento;
	private Long idCorretor;
	private String historico;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data;
	private boolean agendado;
	private Long idEmpresa;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCliente", nullable = false, insertable = false, updatable = false)
	private Cliente cliente;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idEmpreendimento", nullable = false, insertable = false, updatable = false)
	private Empreendimento empreendimento;
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isAgendado() {
		return this.agendado;
	}

	public void setAgendado(boolean agendado) {
		this.agendado = agendado;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getHistorico() {
		return this.historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdEmpreendimento() {
		return this.idEmpreendimento;
	}

	public void setIdEmpreendimento(Long idEmpreendimento) {
		this.idEmpreendimento = idEmpreendimento;
	}

	public Long getIdCorretor() {
		return this.idCorretor;
	}

	public void setIdCorretor(Long idCorretor) {
		this.idCorretor = idCorretor;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}
}