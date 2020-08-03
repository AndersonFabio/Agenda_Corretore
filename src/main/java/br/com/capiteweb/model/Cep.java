package br.com.capiteweb.model;

import java.io.Serializable;


public class Cep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

    private long IdCep;
    private String cidade;
    private String tipo;
    private String endereco;
    private String endereco2;
    private String bairro;
    private String uf;
    private String cep;
    

	public long getIdCep() {
		return IdCep;
	}
	public void setIdCep(long idCep) {
		IdCep = idCep;
	}

	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	

	public String getEndereco2() {
		return endereco2;
	}
	public void setEndereco2(String endereco2) {
		this.endereco2 = endereco2;
	}
	

	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUF() {
		return uf;
	}
	public void setUF(String uF) {
		uf = uF;
	}

	public String getCEP() {
		return cep;
	}
	public void setCEP(String cep) {
		this.cep = cep;
	}
	
	

	
}
