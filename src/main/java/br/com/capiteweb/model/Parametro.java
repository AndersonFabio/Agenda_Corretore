package br.com.capiteweb.model;

public class Parametro {
	private Long idCorretor;
	private Long idEmpreendimento;
	private Long idCliente;
	private String pesquisar;
	private Login login;
	private Long idCaptacao;
	private String situacaoCliente;

	public Long getIdCorretor() {
		return this.idCorretor;
	}

	public void setIdCorretor(Long idCorretor) {
		this.idCorretor = idCorretor;
	}

	public Long getIdEmpreendimento() {
		return this.idEmpreendimento;
	}

	public void setIdEmpreendimento(Long idEmpreendimento) {
		this.idEmpreendimento = idEmpreendimento;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getPesquisar() {
		return this.pesquisar;
	}

	public void setPesquisar(String pesquisar) {
		this.pesquisar = pesquisar;
	}

	public Long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdCaptacao() {
		return idCaptacao;
	}

	public void setIdCaptacao(Long idCaptacao) {
		this.idCaptacao = idCaptacao;
	}

	public String getSituacaoCliente() {
		return situacaoCliente;
	}

	public void setSituacaoCliente(String situacaoCliente) {
		this.situacaoCliente = situacaoCliente;
	}
}