package br.com.capiteweb.business;

import br.com.capiteweb.DAO.MidiaDAO;
import br.com.capiteweb.model.Midia;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;

public class MidiaBusiness {
	private EntityManager em;
	private MidiaDAO midiaDAO;

	public MidiaBusiness(EntityManager em) {
		this.em = em;
		this.midiaDAO = new MidiaDAO(em);
	}

	public Midia carregar(Long idMidia) {
		return this.midiaDAO.carregar(idMidia);
	}

	public Midia salvar(Midia midia) {
		return this.midiaDAO.salvar(midia);
	}

	public Midia buscaPorId(Long id) {
		return this.midiaDAO.buscarPorId(id);
	}

	public List<Midia> buscaPorNome(Parametro parametro) {
		return this.midiaDAO.buscarPorNome(parametro);
	}

	public List<Midia> getListPorEmpresa(Long id) {
		return this.midiaDAO.getListPorEmpresa(id);
	}
	
	public boolean excluir(long id) {
		return this.midiaDAO.excluir(id);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}