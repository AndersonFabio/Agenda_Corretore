package br.com.capiteweb.rest;

import br.com.capiteweb.business.CorretorBusiness;
import br.com.capiteweb.business.FormLeadgenBusiness;
import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.business.LoginBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.FormLeadgen;
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

@Path("/formLeadgen")
public class FormLeadgenRest {
	private EntityManager em;
	private FormLeadgenBusiness formLeadgenBusiness;
	private EmpresaBusiness empresaBusiness;
	private CorretorBusiness corretorBusiness;
	private LoginBusiness loginBusiness;

	public FormLeadgenRest() {
		this.formLeadgenBusiness = new FormLeadgenBusiness(this.em);
		this.em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		this.formLeadgenBusiness = new FormLeadgenBusiness(this.em);
		this.empresaBusiness = new EmpresaBusiness(this.em);
		this.corretorBusiness = new CorretorBusiness(this.em);
		this.loginBusiness = new LoginBusiness(this.em);
	}

	@POST
	@Path("/get")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public FormLeadgen getFormLeadgenById(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		FormLeadgen formLeadgen = new FormLeadgen();
		if (login.getAcesso().equals("S")) {
			formLeadgen = this.formLeadgenBusiness.buscaPorId(parametro.getIdFormLeadgen());
		}

		this.closeSessions();
		return formLeadgen;
	}

	@POST
	@Path("/salvar")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public FormLeadgen create(FormLeadgen formLeadgen) {
		Login login = this.loginBusiness.checkLogin(formLeadgen.getLogin());
		if (login.getAcesso().equals("S")) {
			this.formLeadgenBusiness.salvar(formLeadgen);
		}

		this.closeSessions();
		return formLeadgen;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/excluir")
	public void remove(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		if (login.getAcesso().equals("S")) {
			this.formLeadgenBusiness.excluir(parametro.getIdFormLeadgen());
		}

		this.closeSessions();
	}

	@POST
	@Consumes({"application/json"})
	@Path("/listPorNome")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<FormLeadgen> getListPorNome(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<FormLeadgen> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.formLeadgenBusiness.buscaPorNome(parametro);
		}

		this.closeSessions();
		return (List) lista;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/listPorEmpresa")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<FormLeadgen> getListPorEmpresa(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<FormLeadgen> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.formLeadgenBusiness.getListPorEmpresa(parametro.getLogin().getIdEmpresa());
		}

		this.closeSessions();
		return (List) lista;
	}
	
	@POST
	@Consumes({"application/json"})
	@Path("/listPorEmpresaDisponivel")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<FormLeadgen> getListPorEmpresaDisponivel(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<FormLeadgen> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.formLeadgenBusiness.getListPorEmpresaDisponivel(parametro.getLogin().getIdEmpresa());
		}

		this.closeSessions();
		return (List) lista;
	}

	public void closeSessions() {
		this.formLeadgenBusiness.getEm().close();
		this.empresaBusiness.getEm().close();
		this.corretorBusiness.getEm().close();
		this.loginBusiness.getEm().close();
	}
}