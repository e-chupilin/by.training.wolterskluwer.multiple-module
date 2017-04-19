package by.training.wolterskluwer.controller;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import by.training.wolterskluwer.enums.AccessType;
import by.training.wolterskluwer.factories.DocumentDaoFactory;
import by.training.wolterskluwer.interfaces.DocumentDao;
import by.training.wolterskluwer.interfaces.RSDocumentsService;
import by.training.wolterskluwer.model.Chapter;
import by.training.wolterskluwer.model.Document;
import static by.training.wolterskluwer.constants.Constants.*;

public class RSDocumentsServiceImpl implements RSDocumentsService {
	private static final Logger LOGGER = Logger.getLogger(RSDocumentsServiceImpl.class);
	private DocumentDao documentDao = DocumentDaoFactory.getAccessObject(AccessType.MEMORY);

	@Override
	public Response createDocument(Document document) {
		LOGGER.info("Try to document create: " + document.getName());
		Long createdDocumentId = documentDao.addDocument(document);

		// Return id for new document as JSON
		return Response.status(Status.CREATED).header(ID, createdDocumentId).build();
	}

	@Override
	public Response readDocument(long id) {
		LOGGER.info("Try to get document. Id: " + id);
		return Response.status(Status.OK).entity(documentDao.getDocument(id)).build();
	}

	@Override
	public Response updateDocument(long documentId, Chapter chapter) {
		LOGGER.info("Try to update document. Id: " + documentId + " New chapter: " + chapter);
		return Response.status(Status.ACCEPTED).header(OK, documentDao.updateDocument(documentId, chapter)).build();
	}

	@Override
	public Response deleteDocument(long id) {
		LOGGER.info("Try to delete document. Id: " + id);
		return Response.status(Status.ACCEPTED).header(OK, documentDao.deleteDocument(id)).build();
	}

	@Override
	public Response deleteChapters(long id) {
		LOGGER.info("Try to delete all document`s chapters. Id: " + id);
		if (documentDao.deleteChapters(id)) {
			return Response.status(Status.ACCEPTED).header(OK, OK_DELETE_CHAPTERS).build();
		} else {
			return Response.status(Status.FORBIDDEN).header(ERROR, ERROR_DELETE_CHAPTERS).build();
		}
	}
}