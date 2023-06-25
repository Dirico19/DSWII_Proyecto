package com.cibertec.edu.entities;

import java.util.Date;

public class Error {

	private Date marcaTiempo;
	private String mensaje;
	private String urlOrigen;

	public Error(Date marcaTiempo, String mensaje, String urlOrigen) {
		this.marcaTiempo = marcaTiempo;
		this.mensaje = mensaje;
		this.urlOrigen = urlOrigen;
	}
	
	public Date getMarcaTiempo() {
		return marcaTiempo;
	}

	public void setMarcaTiempo(Date marcaTiempo) {
		this.marcaTiempo = marcaTiempo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getUrlOrigen() {
		return urlOrigen;
	}

	public void setUrlOrigen(String urlOrigen) {
		this.urlOrigen = urlOrigen;
	}

}
