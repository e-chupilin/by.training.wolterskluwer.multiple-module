package by.training.wolterskluwer.implementation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import by.training.wolterskluwer.exceptions.DocumentException;
import by.training.wolterskluwer.interfaces.DocumentDao;
import by.training.wolterskluwer.model.Chapter;
import by.training.wolterskluwer.model.Document; 




import static by.training.wolterskluwer.constants.Constants.*;

public class DocumentDaoMemory implements DocumentDao {
	private static final Logger LOGGER = Logger.getLogger(DocumentDaoMemory.class);
	private Map<Long, Document> documents = new HashMap<Long, Document>();

	private long currentId = FIRST_ID;
	

	public DocumentDaoMemory() {
		super();
		// Memory not empty. For test.
		documents.put(currentId, new Document.Builder(currentId).name("I`m test document.")
				.addChapter(new Chapter(10, 1, 100)).addChapter(new Chapter(20, 2, 200)).build());
		LOGGER.info("Memory not empty: " + documents.get(currentId) + ". Elements in memory: " + documents.size());
	}

	@Override
	public Document getDocument(long id) {
		Document document = documents.get(id);
		if (document == null) {
			LOGGER.info("Document not found. Id: " + id);
			throw new DocumentException(ERROR_NOT_FOUND);
		}
		LOGGER.info("Get: " + document);
		return document;
	}

	@Override
	public String deleteDocument(long id) {
		if (documents.remove(id) == null) {
			LOGGER.info("Document not found. Id: " + id);
			throw new DocumentException(ERROR_NOT_FOUND);
		}
		LOGGER.info("Deleted. Id: " + id);
		return OK_DELETE;
	}

	@Override
	public long addDocument(Document document) {
		if (document.getName() == null) {
			LOGGER.info("Error add. Document name null.");
			throw new DocumentException(ERROR_ADD_DOCUMENT);
		}
		document.setId(++currentId);
		documents.put(currentId, document);
		LOGGER.info("Current Id: " + currentId + ". Current Document: " + document + " Elements in memory: "
				+ documents.size());
		return currentId;
	}

	@Override
	public String updateDocument(long documentId, Chapter chapter) {
		Document documentForUpdate = documents.get(documentId);
		LOGGER.info("documentForUpdate: " + documentForUpdate);
		if ((documentForUpdate == null) || (!documentForUpdate.setChapter(chapter))) {
			LOGGER.info("Error update document.");
			throw new DocumentException(ERROR_UPDATE_DOCUMENT);
		}
		return OK_UPDATE;
	}

	@Override
	public boolean deleteChapters(long documentId) {
		LOGGER.info("False to delete all chapters for document. Id: " + documentId);
		return false;
	}
}
