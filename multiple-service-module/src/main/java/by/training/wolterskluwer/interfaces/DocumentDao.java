package by.training.wolterskluwer.interfaces;

import by.training.wolterskluwer.model.Chapter;
import by.training.wolterskluwer.model.Document;

public interface DocumentDao {
	
	long addDocument(Document document);
	
	Document getDocument(long id);
	
	String deleteDocument(long id);
	
	String updateDocument(long documentId, Chapter chapter);
	
	boolean deleteChapters(long documentId);	
}
