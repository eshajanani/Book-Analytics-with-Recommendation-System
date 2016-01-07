package com.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.spring.model.AuthorYear;

@Path("/authors")
public class AuthorYearController {
	static final String api_version = "1.01A rev.18729";
	static Logger logger = Logger.getLogger(AuthorYearController.class);
	static String xmlString = null;
	static Map<String, AuthorYear> authors = new HashMap<String, AuthorYear>();
	static {
		System.out.println("Initializing Internal DataStore...");
		authors.put("123", new AuthorYear("2003", "Africa Fine", "10"));
		authors.put("124", new AuthorYear("2005", "Peter Maas", "10"));
		authors.put("345", new AuthorYear("2004", "Mary-Kate", "9"));
		authors.put("345", new AuthorYear("2001", "Robin Cook", "8"));
		authors.put("345", new AuthorYear("2002", "Irvine Welsh", "10"));
	}
		
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version: " + api_version + "</p>";
	}

	// This is the default @PATH
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ArrayList<AuthorYear> getAllAuthors() {
		System.out.println("Getting all authors...");
		ArrayList<AuthorYear> actorList = new ArrayList<AuthorYear>(authors.values());
		return actorList;
	}
	
	@Path("{year}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public AuthorYear getActorById(@PathParam("year") String year) {
		System.out.println("Getting author by Year: " + year);

		AuthorYear author = authors.get(year);
	  if (author != null) {
		logger.info("Inside getAuthorByYear, returned: " + author.toString());
	  } else {
		logger.info("Inside getAuthorByYear, Year: " + year + ", NOT FOUND!");
	  }
	  return author;
	}
}
