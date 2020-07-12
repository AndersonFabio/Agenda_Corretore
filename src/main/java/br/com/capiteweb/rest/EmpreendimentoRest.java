package br.com.capiteweb.rest;

import br.com.capiteweb.business.CorretorBusiness;
import br.com.capiteweb.business.EmpreendimentoBusiness;
import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.business.LoginBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Empreendimento;
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

@Path("/empreendimento")
public class EmpreendimentoRest {
	private EntityManager em;
	private EmpreendimentoBusiness empreendimentoBusiness;
	private EmpresaBusiness empresaBusiness;
	private CorretorBusiness corretorBusiness;
	private LoginBusiness loginBusiness;

	public EmpreendimentoRest() {
		this.empreendimentoBusiness = new EmpreendimentoBusiness(this.em);
		this.em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		this.empreendimentoBusiness = new EmpreendimentoBusiness(this.em);
		this.empresaBusiness = new EmpresaBusiness(this.em);
		this.corretorBusiness = new CorretorBusiness(this.em);
		this.loginBusiness = new LoginBusiness(this.em);
	}

	@POST
	@Path("/get")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Empreendimento getEmpreendimentoById(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		Empreendimento empreendimento = new Empreendimento();
		if (login.getAcesso().equals("S")) {
			empreendimento = this.empreendimentoBusiness.buscaPorId(parametro.getIdEmpreendimento());
		}

		this.closeSessions();
		return empreendimento;
	}

	@POST
	@Path("/salvar")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Empreendimento create(Empreendimento empreendimento) {
		Login login = this.loginBusiness.checkLogin(empreendimento.getLogin());
		if (login.getAcesso().equals("S")) {
			this.empreendimentoBusiness.salvar(empreendimento);
		}

		this.closeSessions();
		return empreendimento;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/excluir")
	public void remove(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		if (login.getAcesso().equals("S")) {
			this.empreendimentoBusiness.excluir(parametro.getIdEmpreendimento());
		}

		this.closeSessions();
	}

	@POST
	@Consumes({"application/json"})
	@Path("/listPorNome")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Empreendimento> getListPorNome(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Empreendimento> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.empreendimentoBusiness.buscaPorNome(parametro);
		}

		this.closeSessions();
		return (List) lista;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/listPorEmpresa")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Empreendimento> getListPorEmpresa(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Empreendimento> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.empreendimentoBusiness.getListPorEmpresa(parametro.getLogin().getIdEmpresa());
		}

		this.closeSessions();
		return (List) lista;
	}

	public void closeSessions() {
		this.empreendimentoBusiness.getEm().close();
		this.empresaBusiness.getEm().close();
		this.corretorBusiness.getEm().close();
		this.loginBusiness.getEm().close();
	}
}