package clases;

import java.time.LocalDate;

/**
 * 
 * @author grupo6
 *
 */
public class Cliente extends Usuario {

	/**
	 * Nombre del cliente
	 */
	private String nombre;
	/**
	 * Fecha de nacimiento del cliente
	 */
	private LocalDate fechaNacimiento;
	/**
	 * Direccion del cliente
	 */
	private String direccion;

	/**
	 * Constructor vacio
	 */

	public Cliente() {
		super();
	}

	/**
	 * @return nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}

	/** 
	 * @param nombre del cliente
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** 
	 * @return la fecha de nacimiento del cliente
	 */

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento del cliente
	 */

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return la direccion del cliente
	 */

	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion del cliente
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}