package br.com.capiteweb.business;

import br.com.capiteweb.DAO.CaptacaoDAO;
import br.com.capiteweb.model.Captacao;
import br.com.capiteweb.model.Parametro;
import javax.persistence.EntityManager;

public class CaptacaoBusiness {
	private EntityManager em;
	private CaptacaoDAO captacaoDAO;

	public CaptacaoBusiness(EntityManager em) {
		this.em = em;
		this.captacaoDAO = new CaptacaoDAO(em);
	}

	public Captacao carregar(Long idCaptacao) {
		return this.captacaoDAO.carregar(idCaptacao);
	}

	public Captacao salvar(Captacao captacao) {
		return this.captacaoDAO.salvar(captacao);
	}

	public Captacao buscaPorId(Parametro parametro) {
		return this.captacaoDAO.buscarPorId(parametro);
	}

	public boolean excluir(long id) {
		return this.captacaoDAO.excluir(id);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}