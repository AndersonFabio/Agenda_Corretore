package br.com.capiteweb.rest;

import br.com.capiteweb.business.CorretorBusiness;
import br.com.capiteweb.business.MidiaBusiness;
import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.business.LoginBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Midia;
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

@Path("/midia")
public class MidiaRest {
	private EntityManager em;
	private MidiaBusiness midiaBusiness;
	private EmpresaBusiness empresaBusiness;
	private CorretorBusiness corretorBusiness;
	private LoginBusiness loginBusiness;

	public MidiaRest() {
		this.midiaBusiness = new MidiaBusiness(this.em);
		this.em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		this.midiaBusiness = new MidiaBusiness(this.em);
		this.empresaBusiness = new EmpresaBusiness(this.em);
		this.corretorBusiness = new CorretorBusiness(this.em);
		this.loginBusiness = new LoginBusiness(this.em);
	}

	@POST
	@Path("/get")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Midia getMidiaById(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		Midia midia = new Midia();
		if (login.getAcesso().equals("S")) {
			midia = this.midiaBusiness.buscaPorId(parametro.getIdMidia());
		}

		this.closeSessions();
		return midia;
	}

	@POST
	@Path("/salvar")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Midia create(Midia midia) {
		Login login = this.loginBusiness.checkLogin(midia.getLogin());
		if (login.getAcesso().equals("S")) {
			this.midiaBusiness.salvar(midia);
		}

		this.closeSessions();
		return midia;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/excluir")
	public void remove(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		if (login.getAcesso().equals("S")) {
			this.midiaBusiness.excluir(parametro.getIdMidia());
		}

		this.closeSessions();
	}

	@POST
	@Consumes({"application/json"})
	@Path("/listPorNome")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Midia> getListPorNome(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Midia> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.midiaBusiness.buscaPorNome(parametro);
		}

		this.closeSessions();
		return (List) lista;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/listPorEmpresa")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Midia> getListPorEmpresa(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Midia> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.midiaBusiness.getListPorEmpresa(parametro.getLogin().getIdEmpresa());
		}

		this.closeSessions();
		return (List) lista;
	}
	

	public void closeSessions() {
		this.midiaBusiness.getEm().close();
		this.empresaBusiness.getEm().close();
		this.corretorBusiness.getEm().close();
		this.loginBusiness.getEm().close();
	}
}