package br.com.capiteweb.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.capiteweb.DAO.LigacoesDAO;
import br.com.capiteweb.model.Ligacoes;

public class LigacoesBusiness {
	private EntityManager em;
	private LigacoesDAO ligacoesDAO;

	public LigacoesBusiness(EntityManager em) {
		this.em = em;
		this.ligacoesDAO = new LigacoesDAO(em);
	}

	public Ligacoes carregar(Long idLigacoes) {
		return this.ligacoesDAO.carregar(idLigacoes);
	}

	public Ligacoes salvar(Ligacoes ligacoes) {
		return this.ligacoesDAO.salvar(ligacoes);
	}

	public List<Ligacoes> buscaPorIdAgenda(Long id) {
		return this.ligacoesDAO.buscarPorIdAgenda(id);
	}

	public boolean excluir(long id) {
		return this.ligacoesDAO.excluir(id);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}