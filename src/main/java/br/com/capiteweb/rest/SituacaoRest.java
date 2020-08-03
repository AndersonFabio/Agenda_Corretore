package br.com.capiteweb.rest;

import br.com.capiteweb.business.CorretorBusiness;
import br.com.capiteweb.business.SituacaoBusiness;
import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.business.LoginBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Situacao;
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

@Path("/situacao")
public class SituacaoRest {
	private EntityManager em;
	private SituacaoBusiness situacaoBusiness;
	private EmpresaBusiness empresaBusiness;
	private CorretorBusiness corretorBusiness;
	private LoginBusiness loginBusiness;

	public SituacaoRest() {
		this.situacaoBusiness = new SituacaoBusiness(this.em);
		this.em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		this.situacaoBusiness = new SituacaoBusiness(this.em);
		this.empresaBusiness = new EmpresaBusiness(this.em);
		this.corretorBusiness = new CorretorBusiness(this.em);
		this.loginBusiness = new LoginBusiness(this.em);
	}

	@POST
	@Path("/get")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Situacao getSituacaoById(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		Situacao situacao = new Situacao();
		if (login.getAcesso().equals("S")) {
			situacao = this.situacaoBusiness.buscaPorId(parametro.getIdSituacao());
		}

		this.closeSessions();
		return situacao;
	}

	@POST
	@Path("/salvar")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Situacao create(Situacao situacao) {
		Login login = this.loginBusiness.checkLogin(situacao.getLogin());
		if (login.getAcesso().equals("S")) {
			this.situacaoBusiness.salvar(situacao);
		}

		this.closeSessions();
		return situacao;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/excluir")
	public void remove(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		if (login.getAcesso().equals("S")) {
			this.situacaoBusiness.excluir(parametro.getIdSituacao());
		}

		this.closeSessions();
	}

	@POST
	@Consumes({"application/json"})
	@Path("/listPorNome")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Situacao> getListPorNome(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Situacao> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.situacaoBusiness.buscaPorNome(parametro);
		}

		this.closeSessions();
		return (List) lista;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/listPorEmpresa")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Situacao> getListPorEmpresa(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Situacao> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.situacaoBusiness.getListPorEmpresa(parametro.getLogin().getIdEmpresa());
		}

		this.closeSessions();
		return (List) lista;
	}
	

	public void closeSessions() {
		this.situacaoBusiness.getEm().close();
		this.empresaBusiness.getEm().close();
		this.corretorBusiness.getEm().close();
		this.loginBusiness.getEm().close();
	}
}