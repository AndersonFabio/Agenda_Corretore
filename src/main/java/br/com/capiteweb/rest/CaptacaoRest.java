package br.com.capiteweb.rest;

import br.com.capiteweb.business.CaptacaoBusiness;
import br.com.capiteweb.business.CorretorBusiness;
import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.business.LoginBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Captacao;
import br.com.capiteweb.model.Login;
import br.com.capiteweb.model.Parametro;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/captacao")
public class CaptacaoRest {
	private EntityManager em;
	private CaptacaoBusiness captacaoBusiness;
	private EmpresaBusiness empresaBusiness;
	private CorretorBusiness corretorBusiness;
	private LoginBusiness loginBusiness;

	public CaptacaoRest() {
		this.captacaoBusiness = new CaptacaoBusiness(this.em);
		this.em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		this.captacaoBusiness = new CaptacaoBusiness(this.em);
		this.empresaBusiness = new EmpresaBusiness(this.em);
		this.corretorBusiness = new CorretorBusiness(this.em);
		this.loginBusiness = new LoginBusiness(this.em);
	}

	@POST
	@Path("/get")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Captacao getCaptacaoById(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		Captacao captacao = new Captacao();
		if (login.getAcesso().equals("S")) {
			captacao = this.captacaoBusiness.buscaPorId(parametro);
		}

		this.closeSessions();
		return captacao;
	}

	@POST
	@Path("/salvar")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Captacao create(Captacao captacao) {
		Login login = this.loginBusiness.checkLogin(captacao.getLogin());
		if (login.getAcesso().equals("S")) {
			this.captacaoBusiness.salvar(captacao);
		}

		this.closeSessions();
		return captacao;
	}

	public void closeSessions() {
		this.captacaoBusiness.getEm().close();
		this.empresaBusiness.getEm().close();
		this.corretorBusiness.getEm().close();
		this.loginBusiness.getEm().close();
	}
}