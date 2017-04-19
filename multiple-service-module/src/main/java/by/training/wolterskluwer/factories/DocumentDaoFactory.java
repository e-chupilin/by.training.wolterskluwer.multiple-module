package by.training.wolterskluwer.factories;

import by.training.wolterskluwer.enums.AccessType;
import by.training.wolterskluwer.exceptions.DocumentException;
import by.training.wolterskluwer.implementation.DocumentDaoMemory;
import by.training.wolterskluwer.implementation.DocumentDaoSql;
import by.training.wolterskluwer.interfaces.DocumentDao;
import static by.training.wolterskluwer.constants.Constants.*;
public class DocumentDaoFactory {
	private static DocumentDao objDocument = null;

	public static DocumentDao getAccessObject(AccessType type) {
		switch (type) {
		
		case MEMORY: {
			if (objDocument == null) {
				
				objDocument = new DocumentDaoMemory();
			}
			return objDocument;
		}
		
		case SQL: {
			if (objDocument == null) {
				objDocument = new DocumentDaoSql();
			}
			return objDocument;
		}
		default:
			throw new DocumentException(ERROR_NOT_SUPPORT);
		}
	}

}
