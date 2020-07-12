package br.com.capiteweb.rest;

import br.com.capiteweb.business.LoginBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Login;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/login")
public class LoginRest {
	private EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private LoginBusiness loginBusiness;

	public LoginRest() {
		this.loginBusiness = new LoginBusiness(this.em);
	}

	@Path("/validar")
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Login getUsuario(Login login) {
		login = this.loginBusiness.checkLogin(login);
		this.closeSessions();
		return login;
	}

	public void closeSessions() {
		this.loginBusiness.getEm().close();
	}
}