package br.com.capiteweb.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;



@Path("/arquivo")
public class FileUploadRest {

/*	@POST
	@Path("/upload")
	 @Consumes(MediaType.MULTIPART_FORM_DATA)
	public void uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file")  FormDataContentDisposition fileDetail, @FormDataParam("id") String id) {

		 File f = new File("program.txt"); 
		  
         // Get the absolute path of file f 
         String absolute = f.getAbsolutePath(); 
         System.out.println(absolute);
		// Path format //10.217.14.97/Installables/uploaded/
		String path = "/home/capiteweb/imagensCampanha/";
		System.out.println("path::" + path);
		String uploadedFileLocation = path + "campanha_"+id+"_"+fileDetail.getFileName();

		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

	}*/

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			FileOutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
