package br.com.capiteweb.rest;

import br.com.capiteweb.business.ContatoBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Contato;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/contato")
public class ContatoRest {
	private EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private ContatoBusiness contatoBusiness;

	public ContatoRest() {
		this.contatoBusiness = new ContatoBusiness(this.em);
	}

	@GET
	@Path("/get")
	@Produces({"application/json"})
	public Contato getContatoById(@QueryParam("id") Long id) {
		Contato contato = this.contatoBusiness.buscaPorId(id);
		this.closeSessions();
		return contato;
	}

	@POST
	@Path("/salvar")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Contato create(Contato contato) {
		this.contatoBusiness.salvar(contato);
		this.closeSessions();
		return contato;
	}

	@GET
	@Path("/excluir")
	public String remove(@QueryParam("id") Long id) {
		this.contatoBusiness.excluir(id);
		this.closeSessions();
		return "Excluido";
	}

	public void closeSessions() {
		this.contatoBusiness.getEm().close();
	}
}