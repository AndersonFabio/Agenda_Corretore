package br.com.capiteweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = -4071572900327758037L;
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@Transient
	@JsonProperty
	private Login login;
	private String situacao;
	private Long idEmpresa;
	private Long idCorretor;
	private Long idCaptador;
	private String captacao;
	private String profissao;
	private Float renda;
	private Float entrada;
	private Float fgts;
	private String fone1;
	private String fone2;
	private String fone3;
	private String contato1;
	private String contato2;
	private String contato3;
	private boolean whatsapp1;
	private boolean whatsapp2;
	private boolean whatsapp3;
	private String email;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getCaptacao() {
		return this.captacao;
	}

	public void setCaptacao(String captacao) {
		this.captacao = captacao;
	}

	public String getProfissao() {
		return this.profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Float getRenda() {
		return this.renda;
	}

	public void setRenda(Float renda) {
		this.renda = renda;
	}

	public Float getEntrada() {
		return this.entrada;
	}

	public void setEntrada(Float entrada) {
		this.entrada = entrada;
	}

	public Float getFgts() {
		return this.fgts;
	}

	public void setFgts(Float fgts) {
		this.fgts = fgts;
	}

	public String getFone1() {
		return this.fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	public String getFone2() {
		return this.fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}

	public String getFone3() {
		return this.fone3;
	}

	public void setFone3(String fone3) {
		this.fone3 = fone3;
	}

	public String getContato1() {
		return this.contato1;
	}

	public void setContato1(String contato1) {
		this.contato1 = contato1;
	}

	public String getContato2() {
		return this.contato2;
	}

	public void setContato2(String contato2) {
		this.contato2 = contato2;
	}

	public String getContato3() {
		return this.contato3;
	}

	public void setContato3(String contato3) {
		this.contato3 = contato3;
	}

	public boolean isWhatsapp1() {
		return this.whatsapp1;
	}

	public void setWhatsapp1(boolean whatsapp1) {
		this.whatsapp1 = whatsapp1;
	}

	public boolean isWhatsapp2() {
		return this.whatsapp2;
	}

	public void setWhatsapp2(boolean whatsapp2) {
		this.whatsapp2 = whatsapp2;
	}

	public boolean isWhatsapp3() {
		return this.whatsapp3;
	}

	public void setWhatsapp3(boolean whatsapp3) {
		this.whatsapp3 = whatsapp3;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Long getIdCaptador() {
		return this.idCaptador;
	}

	public void setIdCaptador(Long idCaptador) {
		this.idCaptador = idCaptador;
	}
}