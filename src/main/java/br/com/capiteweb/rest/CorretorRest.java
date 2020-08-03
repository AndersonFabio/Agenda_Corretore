package br.com.capiteweb.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.annotation.JacksonFeatures;

import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.capiteweb.business.CorretorBusiness;
import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.business.LoginBusiness;
import br.com.capiteweb.business.MidiaBusiness;
import br.com.capiteweb.business.SituacaoBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Corretor;
import br.com.capiteweb.model.Email;
import br.com.capiteweb.model.Login;
import br.com.capiteweb.model.Midia;
import br.com.capiteweb.model.Parametro;
import br.com.capiteweb.model.Situacao;

@Path("/corretor")
public class CorretorRest {
	private EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private EmpresaBusiness empresaBusiness;
	private CorretorBusiness corretorBusiness;
	private LoginBusiness loginBusiness;
	private SituacaoBusiness situacaoBusiness;
	private MidiaBusiness midiaBusiness;

	public CorretorRest() {
		this.empresaBusiness = new EmpresaBusiness(this.em);
		this.corretorBusiness = new CorretorBusiness(this.em);
		this.loginBusiness = new LoginBusiness(this.em);
		this.situacaoBusiness = new SituacaoBusiness(this.em);
		this.midiaBusiness = new MidiaBusiness(this.em);
	}

	@GET
	@Path("/get")
	@Produces({"application/json"})
	public Corretor getUsuarioById(@QueryParam("id") Long id) {
		Corretor corretor = this.corretorBusiness.buscaPorId(id);
		this.closeSessions();
		return corretor;
	}

	@GET
	@Path("/getbyemail")
	@Produces({"application/json"})
	public Email getUsuarioByEmail(@QueryParam("email") String email) {
		Email emailRet = new Email();
		emailRet.setEmail(this.corretorBusiness.buscaPorEmail(email));
		this.closeSessions();
		return emailRet;
	}

	@POST
	@Path("/salvar")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Corretor create(Corretor corretor) {
		if (corretor.getVencimento() == null) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			if (corretor.getPromocao() != null && corretor.getPromocao().equals("XNSSX")) {
				c.add(5, 90);
			} else {
				c.add(5, 30);
			}

			corretor.setVencimento(c.getTime());
		}
		corretor = this.corretorBusiness.salvar(corretor);
		List<Situacao> listaSit = situacaoBusiness.getListPorEmpresa(corretor.getIdEmpresa());
		if(listaSit.size() == 0) {
			Situacao situacao = new Situacao();
			situacao.setNome("Descartado");
			situacao.setIdEmpresa(corretor.getIdEmpresa());
			situacaoBusiness.salvar(situacao);
			situacao = new Situacao();
			situacao.setNome("Contato");
			situacao.setIdEmpresa(corretor.getIdEmpresa());
			situacaoBusiness.salvar(situacao);
			situacao = new Situacao();
			situacao.setIdEmpresa(corretor.getIdEmpresa());
			situacao.setNome("Agendado");
			situacaoBusiness.salvar(situacao);
			
			Midia midia = new Midia();
			midia.setIdEmpresa(corretor.getIdEmpresa());
			midia.setNome("Facebook");
			midiaBusiness.salvar(midia);
		}


		
		this.closeSessions();
		return corretor;
	}

	@POST
	@Path("/vincularCorretor")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Corretor vincularCorretor(Login login) {
		login.setCargo("Corretor");
		Long idEmpresaNova = login.getIdEmpresa();
		login = this.loginBusiness.checkLogin(login);
		Corretor corretor = new Corretor();
		if (login.getAcesso() != null && login.getAcesso().equals("S")) {
			this.corretorBusiness.vincularCorretor(login.getIdCorretor(), login.getIdEmpresa(), idEmpresaNova);
			corretor = this.corretorBusiness.buscaPorId(login.getIdCorretor());
		}

		this.closeSessions();
		return corretor;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/getListCorretorPorIdEmpresa")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Corretor> getListCorretorPorIdEmpresa(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Corretor> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.corretorBusiness.getListCorretorPorIdEmpresa(parametro);
		}

		this.closeSessions();
		return (List) lista;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/getCorretorPorEmail")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public Corretor getCorretorPorEmail(Email email) {
		Corretor corretor = this.corretorBusiness.buscaCorretorPorEmail(email.getEmail());
		this.closeSessions();
		return corretor;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/getListSupervisorPorIdEmpresa")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Corretor> getListSupervisorPorIdEmpresa(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Corretor> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.corretorBusiness.getListSupervisorPorIdEmpresa(parametro.getLogin().getIdEmpresa());
		}

		this.closeSessions();
		return (List) lista;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/getListSupervisorPorIdSupervisor")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Corretor> getListSupervisorPorIdSupervisor(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Corretor> corretor = new ArrayList();
		if (login.getAcesso().equals("S")) {
			corretor = this.corretorBusiness.getListSupervisorPorIdSupervisor(parametro.getLogin().getIdCorretor());
		}

		this.closeSessions();
		return (List) corretor;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/getListCorretorPorIdSupervisor")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Corretor> getListCorretorPorIdSupervisor(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Corretor> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.corretorBusiness.getListCorretorPorIdSupervisor(parametro.getLogin().getIdCorretor());
		}

		this.closeSessions();
		return (List) lista;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/getListCorretorPorIdCorretor")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Corretor> getCorretorPorIdCorretor(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Corretor> listCorretor = new ArrayList();
		if (login.getAcesso().equals("S")) {
			listCorretor = this.corretorBusiness.getListCorretorPorIdCorretor(parametro.getLogin().getIdCorretor());
		}

		this.closeSessions();
		return (List) listCorretor;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/getListSupervisorPorIdCorretor")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Corretor> getSupervisorPorIdCorretor(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Corretor> listCorretor = new ArrayList();
		if (login.getAcesso().equals("S")) {
			listCorretor = this.corretorBusiness.getListSupervisorPorIdCorretor(parametro.getLogin().getIdCorretor());
		}

		this.closeSessions();
		return (List) listCorretor;
	}

	@GET
	@Path("/excluir")
	public void remove(@QueryParam("id") Long id) {
		this.corretorBusiness.excluir(id);
		this.closeSessions();
	}

	public void closeSessions() {
		this.empresaBusiness.getEm().close();
		this.corretorBusiness.getEm().close();
		this.loginBusiness.getEm().close();
		this.situacaoBusiness.getEm().close();
		this.midiaBusiness.getEm().close();
	}
}