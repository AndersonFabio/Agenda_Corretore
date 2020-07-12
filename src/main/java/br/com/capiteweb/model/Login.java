package br.com.capiteweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {
	@JsonProperty
	private String email;
	@JsonProperty
	private String senha;
	@JsonProperty
	private String acesso;
	@JsonProperty
	private String cargo;
	@JsonProperty
	private String nome;
	@JsonProperty
	private Long idEmpresa;
	@JsonProperty
	private Long idAgenda;
	@JsonProperty
	private Long idCorretor;

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

	public String getAcesso() {
		return this.acesso;
	}

	public void setAcesso(String acesso) {
		this.acesso = acesso;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Long getIdAgenda() {
		return this.idAgenda;
	}

	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
	}
}