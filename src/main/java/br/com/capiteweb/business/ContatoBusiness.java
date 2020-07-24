package br.com.capiteweb.business;

import br.com.capiteweb.DAO.ContatoDAO;
import br.com.capiteweb.commons.Util;
import br.com.capiteweb.model.Contato;
import javax.mail.internet.ParseException;
import javax.persistence.EntityManager;

public class ContatoBusiness {
	private EntityManager em;
	private ContatoDAO contatoDAO;

	public ContatoBusiness(EntityManager em) {
		this.contatoDAO = new ContatoDAO(this.em);
		this.em = em;
		this.contatoDAO = new ContatoDAO(em);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Contato carregar(Long idContato) {
		return this.contatoDAO.carregar(idContato);
	}

	public void salvar(Contato contato) {
		this.contatoDAO.salvar(contato);
		String mensagem = "Contato Recebido \n\nNome: " + contato.getNome() + "\n\n" + "Telefone: "
				+ contato.getCelular() + "\n\n" + "Email: " + contato.getEmail() + "\n\n" + "Mensagem: "
				+ contato.getObservacao();

		try {
			Util.EnviarEmail(mensagem, "andersonfabio.1976@gmail.com", "Contato CapiteWeb");
		} catch (ParseException var4) {
			var4.printStackTrace();
		}

	}
	
	public void enviar(Contato contato) {
		String mensagem = "Contato\n\nNome: " + contato.getNome() + "\n\n" + "Telefone: "
				+ contato.getCelular() + "\n\n" + "Email: " + contato.getEmail() + "\n\n" + "Mensagem: "
				+ contato.getObservacao();

		try {
			Util.EnviarEmail(mensagem, "andersonfabio.1976@gmail.com", "Contato Simples Nacional Web");
			Util.EnviarEmail(mensagem, "contato@simplesnacionalweb.com.br", "Contato Simples Nacional Web");
			
		} catch (ParseException var4) {
			var4.printStackTrace();
		}
	}

	public Contato buscaPorEmail(String email) {
		return this.contatoDAO.buscarPorEmail(email);
	}

	public Contato buscaPorId(Long id) {
		return this.contatoDAO.buscarPorId(id);
	}

	public Contato buscaPorNome(String nome) {
		return this.contatoDAO.buscarPorNome(nome);
	}

	public boolean excluir(Long id) {
		return this.contatoDAO.excluir(id);
	}
}