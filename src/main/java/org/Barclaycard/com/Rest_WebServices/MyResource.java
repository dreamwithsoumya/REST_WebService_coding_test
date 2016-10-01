package org.Barclaycard.com.Rest_WebServices;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Two methods. formSubmit() method will be called while submitting the form by
 * POST method. validate() will be called by GET method.
 * 
 *
 * @author soumya manna
 * @version 0.1-snapshot
 * @since 2016-10-01
 */
@Path("user")
public class MyResource {

	/**
	 * collecting form params through @formParam attribute choosing 130 bits
	 * from a cryptographically secure random bit generator, and encoding them
	 * in base-32. get session from request object. if it can't find it will
	 * create a new one
	 * 
	 * @param request
	 *            this is HttpServletRequest type
	 * @param string
	 *            this is username from formParam
	 * @param String
	 *            this password from formParam
	 * @return Response this returns the randon generated token
	 */

	@POST
	@Path("/formSubmit")
	@Produces(MediaType.TEXT_PLAIN)
	public Response formSubmit(@Context HttpServletRequest request, @FormParam("username") String username,
			@FormParam("password") String password) {
		if (username == null || password == null) {
			throw new InvalidFormData("Invalid form data. Enter all the fields.");
		} else {
			SecureRandom random = new SecureRandom();
			String token = new BigInteger(130, random).toString();

			HttpSession session = request.getSession();
			session.setAttribute("token", token); // saving token in session

			return Response.status(200).entity(token).build(); // build the
																// response
																// and return
		}
	}

	/**
	 * 
	 * @param request
	 *            this is HttpServletRequest type
	 * @param string
	 *            this is token from queryParam
	 * @return Response this returns success or failure according to match
	 */

	@GET
	@Path("/validate")
	@Produces(MediaType.TEXT_PLAIN)
	public Response authenticate(@QueryParam("token") String receivedToken, @Context HttpServletRequest request)
			throws Exception {
		String status = null;

		HttpSession session = request.getSession(); // get the session
		String tokenval = (String) session.getAttribute("token"); // get the
																	// token
																	// saved in
																	// session
		if (receivedToken == null) {
			throw new TokenNotFoundException("Token not found"); // if no token
																	// found
																	// then
																	// throw
																	// exception

		} else {

			if (receivedToken.equalsIgnoreCase(tokenval)) { // return success if
															// matching found,
															// otherwise return
															// failure
				status = "succcess";
			} else {
				status = "failure";
			}
			return Response.status(200).entity(status).build(); // build the
																// response and
																// return

		}

	}

}
