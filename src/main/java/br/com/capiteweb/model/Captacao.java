package br.com.capiteweb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "captacao")
public class Captacao implements Serializable {
	private static final long serialVersionUID = -6979848385178163199L;
	@Id
	@GeneratedValue
	private Long id;
	@Transient
	@JsonProperty
	private Login login;
	private String situacao1;
	private String situacao2;
	private Long idEmpresa;
	private Long idCorretor;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data1;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data2;
	private String titulo1;
	private String titulo2;
	private String anuncio1;
	private String anuncio2;
	private String palavras1;
	private String palavras2;
	private Float investimento1;
	private Float investimento2;
	private Long idEmpreendimento;
	private String nomeCampanha;
	

	public Long getIdEmpreendimento() {
		return idEmpreendimento;
	}

	public void setIdEmpreendimento(Long idEmpreendimento) {
		this.idEmpreendimento = idEmpreendimento;
	}

	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getTitulo1() {
		return this.titulo1;
	}

	public void setTitulo1(String titulo1) {
		this.titulo1 = titulo1;
	}

	public String getTitulo2() {
		return this.titulo2;
	}

	public void setTitulo2(String titulo2) {
		this.titulo2 = titulo2;
	}

	public String getAnuncio1() {
		return this.anuncio1;
	}

	public void setAnuncio1(String anuncio1) {
		this.anuncio1 = anuncio1;
	}

	public String getAnuncio2() {
		return this.anuncio2;
	}

	public void setAnuncio2(String anuncio2) {
		this.anuncio2 = anuncio2;
	}

	public String getPalavras1() {
		return this.palavras1;
	}

	public void setPalavras1(String palavras1) {
		this.palavras1 = palavras1;
	}

	public String getPalavras2() {
		return this.palavras2;
	}

	public void setPalavras2(String palavras2) {
		this.palavras2 = palavras2;
	}

	public Float getInvestimento1() {
		return this.investimento1;
	}

	public void setInvestimento1(Float investimento1) {
		this.investimento1 = investimento1;
	}

	public Float getInvestimento2() {
		return this.investimento2;
	}

	public void setInvestimento2(Float investimento2) {
		this.investimento2 = investimento2;
	}

	public Date getData1() {
		return this.data1;
	}

	public void setData1(Date data1) {
		this.data1 = data1;
	}

	public Date getData2() {
		return this.data2;
	}

	public void setData2(Date data2) {
		this.data2 = data2;
	}

	public String getSituacao1() {
		return this.situacao1;
	}

	public void setSituacao1(String situacao1) {
		this.situacao1 = situacao1;
	}

	public String getSituacao2() {
		return this.situacao2;
	}

	public void setSituacao2(String situacao2) {
		this.situacao2 = situacao2;
	}

	public Long getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getIdCorretor() {
		return this.idCorretor;
	}

	public void setIdCorretor(Long idCorretor) {
		this.idCorretor = idCorretor;
	}
}