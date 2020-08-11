package br.com.capiteweb.rest;

import br.com.capiteweb.business.ClienteBusiness;
import br.com.capiteweb.business.CorretorBusiness;
import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.business.LoginBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Cliente;
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

@Path("/cliente")
public class ClienteRest {
	private EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private ClienteBusiness clienteBusiness;
	private EmpresaBusiness empresaBusiness;
	private CorretorBusiness corretorBusiness;
	private LoginBusiness loginBusiness;

	public ClienteRest() {
		this.clienteBusiness = new ClienteBusiness(this.em);
		this.empresaBusiness = new EmpresaBusiness(this.em);
		this.corretorBusiness = new CorretorBusiness(this.em);
		this.loginBusiness = new LoginBusiness(this.em);
	}

	@POST
	@Path("/get")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Cliente getClienteById(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		Cliente cliente = new Cliente();
		if (login.getAcesso().equals("S")) {
			cliente = this.clienteBusiness.buscaPorId(parametro.getIdCliente());
		}

		this.closeSessions();
		return cliente;
	}

	@POST
	@Path("/salvar")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Cliente create(Cliente cliente) {
		Login login = this.loginBusiness.checkLogin(cliente.getLogin());
		if (login.getAcesso().equals("S")) {
			this.clienteBusiness.salvar(cliente);
		}

		this.closeSessions();
		return cliente;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/excluir")
	public void remove(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		if (login.getAcesso().equals("S")) {
			this.clienteBusiness.excluir(parametro.getIdCliente());
		}

		this.closeSessions();
	}

	@POST
	@Consumes({"application/json"})
	@Path("/listPorNome")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Cliente> getListPorNome(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Cliente> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.clienteBusiness.buscaPorNome(parametro);
		}

		this.closeSessions();
		return (List) lista;
	}
	
	@POST
	@Consumes({"application/json"})
	@Path("/countPorCorretor")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public Long getCountPorCorretor(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		Long total = 0L;
		if (login.getAcesso().equals("S")) {
			total = this.clienteBusiness.countPorCorretor(parametro);
		}

		this.closeSessions();
		return total;
	}
	
	@POST
	@Consumes({"application/json"})
	@Path("/listPorNomeSituacao")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Cliente> getListPorNomeSituacao(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Cliente> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.clienteBusiness.buscaPorNomeSituacao(parametro);
		}

		this.closeSessions();
		return (List) lista;
	}

	@POST
	@Consumes({"application/json"})
	@Path("/listPorEmpresa")
	@Produces({"application/json"})
	@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
	public List<Cliente> getListPorEmpresa(Parametro parametro) {
		Login login = this.loginBusiness.checkLogin(parametro);
		List<Cliente> lista = new ArrayList();
		if (login.getAcesso().equals("S")) {
			lista = this.clienteBusiness.getListPorEmpresa(parametro.getLogin().getIdEmpresa());
		}

		this.closeSessions();
		return (List) lista;
	}

	public void closeSessions() {
		this.clienteBusiness.getEm().close();
		this.corretorBusiness.getEm().close();
		this.empresaBusiness.getEm();
		this.loginBusiness.getEm().close();
	}
}