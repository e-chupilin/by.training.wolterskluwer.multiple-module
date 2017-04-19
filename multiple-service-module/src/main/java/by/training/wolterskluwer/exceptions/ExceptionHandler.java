package by.training.wolterskluwer.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import static by.training.wolterskluwer.constants.Constants.*;

public class ExceptionHandler implements ExceptionMapper<DocumentException> {
	    public Response toResponse(DocumentException exception) {
	        return Response.status(Status.BAD_REQUEST).header(ERROR, exception.getMessage()).build();
	    }
}