package br.com.capiteweb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa extends Usuario implements Serializable {
	private static final long serialVersionUID = 5036881573792794444L;
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String endereco;
	private String cep;
	private String cidade;
	private String estado;
	private String bairro;
	private String telefone;
	private String celular;
	private String creci;
	private String email;
	private String senha;
	private String senha2;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date vencimento;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date vencimentoLed;
	private String numero;
	private String complemento;
	private String cnpj;
	private String promocao;
	private String indicacao;

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

	public String getCreci() {
		return this.creci;
	}

	public void setCreci(String creci) {
		this.creci = creci;
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

	public String getSenha2() {
		return this.senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}

	public Date getVencimento() {
		return this.vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Date getVencimentoLed() {
		return this.vencimentoLed;
	}

	public void setVencimentoLed(Date vencimentoLed) {
		this.vencimentoLed = vencimentoLed;
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

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getPromocao() {
		return this.promocao;
	}

	public void setPromocao(String promocao) {
		this.promocao = promocao;
	}

	public String getIndicacao() {
		return this.indicacao;
	}

	public void setIndicacao(String indicacao) {
		this.indicacao = indicacao;
	}
}