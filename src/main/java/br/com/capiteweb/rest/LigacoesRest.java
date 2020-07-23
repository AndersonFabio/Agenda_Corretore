package br.com.capiteweb.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.capiteweb.business.CorretorBusiness;
import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.business.LigacoesBusiness;
import br.com.capiteweb.business.LoginBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Agenda;
import br.com.capiteweb.model.Ligacoes;
import br.com.capiteweb.model.Login;

@Path("/ligacoes")
public class LigacoesRest {
	private EntityManager em;
	private LigacoesBusiness ligacoesBusiness;
	private EmpresaBusiness empresaBusiness;
	private CorretorBusiness corretorBusiness;
	private LoginBusiness loginBusiness;

	public LigacoesRest() {
		this.ligacoesBusiness = new LigacoesBusiness(this.em);
		this.em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		this.ligacoesBusiness = new LigacoesBusiness(this.em);
		this.empresaBusiness = new EmpresaBusiness(this.em);
		this.corretorBusiness = new CorretorBusiness(this.em);
		this.loginBusiness = new LoginBusiness(this.em);
		
	}

	@POST
	@Path("/getPorIdAgenda")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public List<Ligacoes> getLigacoesByIdAgenda(Agenda agenda) {
		Login login = this.loginBusiness.checkLogin(agenda.getLogin());
		List<Ligacoes> ligacoes = new ArrayList<Ligacoes>();
		if (login.getAcesso().equals("S")) {
			ligacoes = this.ligacoesBusiness.buscaPorIdAgenda(agenda.getId());
		}

		this.closeSessions();
		return ligacoes;
	}

	@POST
	@Path("/salvar")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Ligacoes create(Ligacoes ligacoes) {
		Login login = this.loginBusiness.checkLogin(ligacoes.getLogin());
		if (login.getAcesso().equals("S")) {
			this.ligacoesBusiness.salvar(ligacoes);
		}

		this.closeSessions();
		return ligacoes;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/excluir")
	public void remove(Agenda agenda) {
		Login login = this.loginBusiness.checkLogin(agenda.getLogin());
		if (login.getAcesso().equals("S")) {
			this.ligacoesBusiness.excluir(agenda.getId());
		}

		this.closeSessions();
	}

	public void closeSessions() {
		this.ligacoesBusiness.getEm().close();
		this.empresaBusiness.getEm().close();
		this.corretorBusiness.getEm().close();
		this.loginBusiness.getEm().close();
	}
}