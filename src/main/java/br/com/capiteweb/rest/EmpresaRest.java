package br.com.capiteweb.rest;

import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Email;
import br.com.capiteweb.model.Empresa;
import br.com.capiteweb.model.Parametro;
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

@Path("/empresa")
public class EmpresaRest {
	private EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private EmpresaBusiness empresaBusiness;

	public EmpresaRest() {
		this.empresaBusiness = new EmpresaBusiness(this.em);
	}

	@GET
	@Path("/get")
	@Produces({"application/json"})
	public Empresa getEmpresaById(@QueryParam("id") Long id) {
		Empresa empresa = this.empresaBusiness.buscaPorId(id);
		this.closeSessions();
		return empresa;
	}

	@POST
	@Path("/salvar")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Empresa create(Empresa empresa) {
		if (empresa.getVencimento() == null) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			if (empresa.getPromocao() != null && empresa.getPromocao().equals("XNSSX")) {
				c.add(5, 90);
			} else {
				c.add(5, 30);
			}

			empresa.setVencimento(c.getTime());
		}

		this.empresaBusiness.salvar(empresa);
		this.closeSessions();
		return empresa;
	}

	@GET
	@Path("/excluir")
	public void remove(@QueryParam("id") Long id) {
		this.empresaBusiness.excluir(id);
		this.closeSessions();
	}

	@GET
	@Path("/listAll")
	@Produces({"application/json"})
	public List<Empresa> getEmpresaListAll() {
		List<Empresa> lista = this.empresaBusiness.getListAll();
		this.closeSessions();
		return lista;
	}

	@GET
	@Path("/getbyemail")
	@Produces({"application/json"})
	public Email getEmpresaByEmail(@QueryParam("email") String email) {
		Email emailRet = new Email();
		emailRet.setEmail(this.empresaBusiness.buscaPorEmail(email));
		this.closeSessions();
		return emailRet;
	}

	@POST
	@Path("/getEmpresaPorId")
	@Produces({"application/json"})
	public Empresa getEmpresaPorId(Parametro parametro) {
		Empresa empresa = this.empresaBusiness.buscaPorId(parametro.getLogin().getIdEmpresa());
		this.closeSessions();
		return empresa;
	}

	public void closeSessions() {
		this.empresaBusiness.getEm().close();
	}
}