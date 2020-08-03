package br.com.capiteweb.rest;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.capiteweb.business.CepBusiness;
import br.com.capiteweb.business.LoginBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Cep;


@Path("/cep")
public class CepRest  {

	private EntityManager em;
	private CepBusiness cepBusiness;
	private LoginBusiness loginBusiness;

	public CepRest() {
		this.em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		this.cepBusiness = new CepBusiness(this.em);
		this.loginBusiness = new LoginBusiness(this.em);
	}
	
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Cep getCeps(@QueryParam("cep") String cep) {
		Cep endereco = new Cep();
		endereco =  cepBusiness.getCep(cep);
		closeSessions();
        return endereco;
    }
	
	public void closeSessions() {
		this.cepBusiness.getEm().close();
		this.loginBusiness.getEm().close();
	}
}
