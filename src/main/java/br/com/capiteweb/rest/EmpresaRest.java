package br.com.capiteweb.rest;

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

import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.business.MidiaBusiness;
import br.com.capiteweb.business.SituacaoBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.model.Email;
import br.com.capiteweb.model.Empresa;
import br.com.capiteweb.model.Login;
import br.com.capiteweb.model.Midia;
import br.com.capiteweb.model.Parametro;
import br.com.capiteweb.model.Situacao;

@Path("/empresa")
public class EmpresaRest {
	private EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private EmpresaBusiness empresaBusiness;
	private SituacaoBusiness situacaoBusiness;
	private MidiaBusiness midiaBusiness;

	public EmpresaRest() {
		this.empresaBusiness = new EmpresaBusiness(this.em);
		this.situacaoBusiness = new SituacaoBusiness(this.em);
		this.midiaBusiness = new MidiaBusiness(this.em);
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
		Login login = new Login();
		login.setEmail(empresa.getEmail());
		login.setSenha(empresa.getSenha());
		empresa = empresaBusiness.buscaPorLogin(login);
		List<Situacao> listaSit = situacaoBusiness.getListPorEmpresa(empresa.getId());
		if(listaSit.size() == 0) {
			Situacao situacao = new Situacao();
			situacao.setNome("Descartado");
			situacao.setIdEmpresa(empresa.getId());
			situacaoBusiness.salvar(situacao);
			situacao = new Situacao();
			situacao.setNome("Contato");
			situacao.setIdEmpresa(empresa.getId());
			situacaoBusiness.salvar(situacao);
			situacao = new Situacao();
			situacao.setIdEmpresa(empresa.getId());
			situacao.setNome("Agendado");
			situacaoBusiness.salvar(situacao);
			
			Midia midia = new Midia();
			midia.setIdEmpresa(empresa.getId());
			midia.setNome("Facebook");
			midiaBusiness.salvar(midia);
		}

		
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
		this.situacaoBusiness.getEm().close();
		this.midiaBusiness.getEm().close();
	}
}