package by.training.wolterskluwer.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import by.training.wolterskluwer.model.Chapter;
import by.training.wolterskluwer.model.Document;

@Service
public interface RSDocumentsService {
	

 	@POST
	@Path("/documents")
	@Consumes(MediaType.APPLICATION_XML)
	@Description("Method for create document.")
 	Response createDocument(Document document);

	@GET
	@Path("/documents/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	@Description("Method for getting document by id.")
	Response readDocument(@PathParam("id") long id);
	
	@PUT
	@Path("/documents/{documentId}")
	@Consumes(MediaType.APPLICATION_XML)
	@Description("Method for update document`s chapter.")
	Response updateDocument(@PathParam("documentId") long documentId, Chapter chapter);
	
	@DELETE
	@Path("documents/{id}")
	@Description("Method for delete document.")
	Response deleteDocument(@PathParam("id") long id);	
	
	@DELETE
	@Path("documents/{id}/chapters")
	@Description("Method for delete all document`s chapters.")
	Response deleteChapters(@PathParam("id") long id);	
}