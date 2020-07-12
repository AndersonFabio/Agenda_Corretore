package br.com.capiteweb.business;

import br.com.capiteweb.DAO.AgendaDAO;
import br.com.capiteweb.model.Agenda;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;

public class AgendaBusiness {
	private EntityManager em;
	private AgendaDAO agendaDAO;

	public AgendaBusiness(EntityManager em) {
		this.em = em;
		this.agendaDAO = new AgendaDAO(em);
	}

	public Agenda carregar(Long idAgenda) {
		return this.agendaDAO.carregar(idAgenda);
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