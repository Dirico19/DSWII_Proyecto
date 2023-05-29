package edu.cibertec.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("Servicio")
public class Service {
	
	@GET
	@Path("/{nombre}")
	@Produces(MediaType.TEXT_PLAIN)
	public String saludar(@PathParam("nombre") String nombre) {
		return "Hello " + nombre + ". Message received at " + new Date() + ". Todo OK.";
	}
	
}
