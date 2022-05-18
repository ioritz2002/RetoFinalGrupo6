package clases;

import java.time.LocalDate;

/**
 * 
 * @author grupo6
 *
 */

public class Repartidor {
	/**
	 * identificacion del repartidor
	 */
	private String idRepartidor;
	/**
	 * fecha de alta del repartidor
	 */
	private LocalDate fechaAlta;
	/**
	 * nombre del repartidro
	 */
	private String nombre;
	/**
	 * apellido del repartidor
	 */
	private String apellido;
	/**
	 * dni de quien a dado de alta al repartidor
	 */
	private String dniUsuario;
	
	/**
	 * Constructor vacio
	 */
	public Repartidor() {
		
	}
	/**
	 * 
	 * @return identificacion del repartidor
	 */
	public String getIdRepartidor() {
		return idRepartidor;
	}
/**
 * 
 * @param idRepartidor, identificacion del repartidor
 */
	public void setIdRepartidor(String idRepartidor) {
		this.idRepartidor = idRepartidor;
	}
/**
 * 
 * @return fecha de alta del repartidor
 */
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
/**
 * 
 * @param fechaAlta del repartidor
 */
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
/**
 * 
 * @return nombre del repartidor
 */
	public String getNombre() {
		return nombre;
	}
/**
 * 
 * @param nombre del repartidor
 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
/**
 * 
 * @return apellido del repartidor
 */
	public String getApellido() {
		return apellido;
	}
/**
 *  
 * @param apellido del repartidor
 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
/**
 * 
 * @return de quien a dado de alta al repartidor
 */
	public String getDniUsuario() {
		return dniUsuario;
	}
/**
 * 
 * @param dniUsuario de quien a dado de alta al repartidor
 */
	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	
	
	
}
