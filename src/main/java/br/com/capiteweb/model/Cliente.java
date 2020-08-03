package br.com.capiteweb.model;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	private Long idSituacao;
	private Long idMidia;
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
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String cpf;
	private String rg;
	@ManyToOne(optional=true, fetch = FetchType.EAGER)
	@JoinColumn(name = "idSituacao", nullable = true, insertable = false, updatable = false)
	private Situacao situacao;
	@ManyToOne(optional=true , fetch = FetchType.EAGER)
	@JoinColumn(name = "idMidia", nullable = true, insertable = false, updatable = false)
	private Midia midia;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

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

	public Long getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(Long idSituacao) {
		this.idSituacao = idSituacao;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Long getIdMidia() {
		return idMidia;
	}

	public void setIdMidia(Long idMidia) {
		this.idMidia = idMidia;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}




}