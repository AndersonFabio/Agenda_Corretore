package br.com.capiteweb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "a01usuario")
public class Corretor extends Usuario implements Serializable {
	private static final long serialVersionUID = 1374821418970038953L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "a01login")
	private String email;
	@Column(name = "a01passwd")
	private String senha;
	@Column(name = "a02passwd")
	private String senha2;
	private String nome;
	private String endereco;
	private String cep;
	private String cidade;
	private String estado;
	private String bairro;
	private String telefone;
	private String celular;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date vencimento;
	private String creci;
	private String cargo;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date vencimentoLeads;
	private String numero;
	private String complemento;
	private String cpf;
	private String indicacao;
	private String promocao;
	private Long idSupervisor;
	private Long idEmpresa;
	private Integer acesso;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha2() {
		return this.senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}

	public String getCreci() {
		return this.creci;
	}

	public void setCreci(String creci) {
		this.creci = creci;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Date getVencimento() {
		return this.vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Integer getAcesso() {
		return this.acesso;
	}

	public void setAcesso(Integer acesso) {
		this.acesso = acesso;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getVencimentoLeads() {
		return this.vencimentoLeads;
	}

	public void setVencimentoLeads(Date vencimentoLeads) {
		this.vencimentoLeads = vencimentoLeads;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getIndicacao() {
		return this.indicacao;
	}

	public void setIndicacao(String indicacao) {
		this.indicacao = indicacao;
	}

	public String getPromocao() {
		return this.promocao;
	}

	public void setPromocao(String promocao) {
		this.promocao = promocao;
	}

	public Long getIdSupervisor() {
		return this.idSupervisor;
	}

	public void setIdSupervisor(Long idSupervisor) {
		this.idSupervisor = idSupervisor;
	}

	public Long getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
}