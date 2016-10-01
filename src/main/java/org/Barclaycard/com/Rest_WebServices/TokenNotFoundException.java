package org.Barclaycard.com.Rest_WebServices;

public class TokenNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	TokenNotFoundException(String message){  
		super(message);
	}

}
