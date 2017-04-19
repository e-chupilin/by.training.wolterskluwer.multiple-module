package by.training.wolterskluwer.implementation;

import by.training.wolterskluwer.exceptions.DocumentException;
import by.training.wolterskluwer.interfaces.DocumentDao;
import by.training.wolterskluwer.model.Chapter;
import by.training.wolterskluwer.model.Document;
import static by.training.wolterskluwer.constants.Constants.*;

public class DocumentDaoSql implements DocumentDao {

	@Override
	public Document getDocument(long id) {
		throw new DocumentException(ERROR_NOT_SUPPORT);
	}

	@Override
	public String deleteDocument(long id) {
		throw new DocumentException(ERROR_NOT_SUPPORT);
	}

	@Override
	public long addDocument(Document document) {
		throw new DocumentException(ERROR_NOT_SUPPORT);
	}

	@Override
	public String updateDocument(long id, Chapter chapter) {
		throw new DocumentException(ERROR_NOT_SUPPORT);
	}

	@Override
	public boolean deleteChapters(long documentId) {
		throw new DocumentException(ERROR_NOT_SUPPORT);
	}
}
