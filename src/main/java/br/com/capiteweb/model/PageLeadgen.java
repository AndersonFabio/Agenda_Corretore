package br.com.capiteweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pageleadgen")
public class PageLeadgen implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2230674252664477731L;
	@Id
	@GeneratedValue
	private Long id;
	private Long idEmpresa;
	private String idPage;
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
	public String getIdPage() {
		return idPage;
	}
	public void setIdPage(String idPage) {
		this.idPage = idPage;
	}
	
	

}
