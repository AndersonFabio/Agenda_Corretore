package br.com.capiteweb.rest;

import br.com.capiteweb.business.AgendaBusiness;
import br.com.capiteweb.business.CorretorBusiness;
import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.business.LoginBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Agenda;
import br.com.capiteweb.model.Login;
import br.com.capiteweb.model.Parametro;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.annotation.JacksonFeatures;

@Path("/agenda")
public class AgendaRest {
	private EntityManager em;
	private AgendaBusiness agendaBusiness;
	private EmpresaBusiness empresaBusiness;
	private CorretorBusiness corretorBusiness;
	private LoginBusiness loginBusiness;

	public AgendaRest() {
		this.agendaBusiness = new AgendaBusiness(this.em);
		this.em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		this.agendaBusiness = new AgendaBusiness(this.em);
		this.empresaBusiness = new EmpresaBusiness(this.em);
		this.corretorBusiness = new CorretorBusiness(this.em);
		this.loginBusiness = new LoginBusiness(this.em);
	}

	@POST
	@Path("/get")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Agenda getAgendaById(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		Agenda agenda = new Agenda();
		if (login.getAcesso().equals("S")) {
			agenda = this.agendaBusiness.buscaPorId(parametro.getLogin().getIdAgenda());
		}

		this.closeSessions();
		return agenda;
	}

	@POST
	@Path("/salvar")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Agenda create(Agenda agenda) {
		Login login = this.loginBusiness.checkLogin(agenda.getLogin());
		if (login.getAcesso().equals("S")) {
			this.agendaBusiness.salvar(agenda);
		}

		this.closeSessions();
		return agenda;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/excluir")
	public void remove(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		if (login.getAcesso().equals("S")) {
			this.agendaBusiness.excluir(parametro.getLogin().getIdAgenda());
		}

		this.closeSessions();
	}

	@POST
	@Consumes({"application/json"})
	@Path("/listPorCorretor")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Agenda> getListPorCorretor(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Agenda> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.agendaBusiness.buscaPorCorretor(parametro);
		}

		this.closeSessions();
		return (List) lista;
	}

	public void closeSessions() {
		this.agendaBusiness.getEm().close();
		this.empresaBusiness.getEm().close();
		this.corretorBusiness.getEm().close();
		this.loginBusiness.getEm().close();
	}
}