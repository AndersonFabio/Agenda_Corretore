package br.com.capiteweb.business;

import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;

import br.com.capiteweb.DAO.AgendaDAO;
import br.com.capiteweb.model.Agenda;
import br.com.capiteweb.model.Parametro;

public class AgendaBusiness {
	private EntityManager em;
	private AgendaDAO agendaDAO;

	public AgendaBusiness(EntityManager em) {
		this.em = em;
		this.agendaDAO = new AgendaDAO(em);
		Locale locale = new Locale("PT","BR");
	}

	public Agenda carregar(Long idAgenda) {
		return this.agendaDAO.carregar(idAgenda);
	}
	
	public Agenda carregarPorCliente(Long idCliente) {
		return this.agendaDAO.carregarPorCliente(idCliente);
	}

	public Agenda salvar(Agenda agenda) {
		return this.agendaDAO.salvar(agenda);
	}

	public Agenda buscaPorId(Long id) {
		return this.agendaDAO.buscarPorId(id);
	}

	public List<Agenda> buscaPorCorretor(Parametro parametro) {
		return this.agendaDAO.buscarPorCorretor(parametro);
	}
	
	public List<Agenda> buscaPorCorretorSituacao(Parametro parametro) {
		return this.agendaDAO.buscarPorCorretorSituacao(parametro);
	}
	
	public Long countPorCorretor(Parametro parametro) {
		return this.agendaDAO.countPorCorretor(parametro);
	}

	public boolean excluir(long id) {
		return this.agendaDAO.excluir(id);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}