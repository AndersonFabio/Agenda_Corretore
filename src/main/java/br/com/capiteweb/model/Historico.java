package br.com.capiteweb.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "historico")
public class Historico implements Serializable {
	private static final long serialVersionUID = -7589537372616620926L;
	@Id
	@GeneratedValue
	private Long id;
	private Date data;
	private String historico;
	private Long idAgenda;

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

	public String getHistorico() {
		return this.historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Long getIdAgenda() {
		return this.idAgenda;
	}

	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
	}
}