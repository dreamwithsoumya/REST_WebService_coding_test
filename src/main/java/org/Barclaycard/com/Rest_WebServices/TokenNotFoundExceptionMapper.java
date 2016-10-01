package org.Barclaycard.com.Rest_WebServices;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
* Registering the exception to the jersey so that jersey can understand the exception. It is done by implementing the 
* ExceptionMapper
* 
*
* @author  soumya manna
* @version 0.1-snapshot
* @since   2016-10-01 
*/

@Provider // to register exception for jersey 
public class TokenNotFoundExceptionMapper implements ExceptionMapper<TokenNotFoundException>{

	@Override
	public Response toResponse(TokenNotFoundException exception) {
		
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}

}
