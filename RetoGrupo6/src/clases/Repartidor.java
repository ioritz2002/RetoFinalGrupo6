package clases;

import java.time.LocalDate;

public class Repartidor {
	private String idRepartidor;
	private LocalDate fechaAlta;
	private String nombre;
	private String apellido;
	private String dniUsuario;
	
	public Repartidor() {
		
	}

	public String getIdRepartidor() {
		return idRepartidor;
	}

	public void setIdRepartidor(String idRepartidor) {
		this.idRepartidor = idRepartidor;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	
	
	
}
