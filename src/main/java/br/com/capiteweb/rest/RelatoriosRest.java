package br.com.capiteweb.rest;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.com.capiteweb.commons.Util;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Path("/relatorio")
public class RelatoriosRest {

	
	
	
	
    @GET
	@Path("/resumoPorCorretor")
	@Produces("application/pdf")
	public Response resumoPorCorretor(@QueryParam("idCorretor") Integer idCorretor) {

    	Connection conexao = null;
		try {
			conexao = Util.getConexao();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File arquivoGerado = null;
		String caminhoArquivoRelatorio = null;
		String path = this.getClass().getClassLoader().getResource("").getPath()+"relatorios";
		try {
		String caminhoRelatorio = path;
		String caminhoArquivoJasper = caminhoRelatorio + File.separator + "resumoPorCorretor.jrxml";
		
		//String relatorioJasper = caminhoArquivoJasper.replace("jrxml", "jasper");

		Map<String, Object> parametrosRelatorio = new HashMap<String, Object>();
		//Integer idCorretor = 256;
		parametrosRelatorio.put("idCorretor", idCorretor);
		//JasperReport relatorioJasper = (JasperReport) JRLoader.loadObject(caminhoArquivoJasper);
		JasperReport relatorioJasper = JasperCompileManager.compileReport(caminhoArquivoJasper);
		//String relatorioJasper = caminhoArquivoJasper;
		
        JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, conexao);
		JRExporter tipoArquivoExportado = null;
		String extensaoArquivoExportado = "";
		

		tipoArquivoExportado = new JRPdfExporter();
		extensaoArquivoExportado = "pdf";
		
		if(caminhoRelatorio.contains("home")) {
			caminhoArquivoRelatorio = "/home/capiteweb/temp/ResumoDeContatosCorretor_" + idCorretor+"." + extensaoArquivoExportado;
		} else {
			caminhoArquivoRelatorio = "ResumoDeContatosCorretor_" + idCorretor+"." + extensaoArquivoExportado;
		}
		arquivoGerado = new java.io.File(caminhoArquivoRelatorio);
		tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		

		tipoArquivoExportado.exportReport() ;
		arquivoGerado.deleteOnExit();		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
        
	ResponseBuilder rb = null;
		
/*        String filePath = reportService.gerarRelatorio();
        file = new File(filePath);*/

        rb = Response.ok((Object) arquivoGerado);
        rb.header("Content-Disposition", "filename="+caminhoArquivoRelatorio);
		
	 return rb.build();
    }
}
