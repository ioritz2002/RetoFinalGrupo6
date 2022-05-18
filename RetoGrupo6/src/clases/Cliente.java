package clases;

import java.time.LocalDate;
/**
 * 
 * @author 1dam
 *
 */
public class Cliente extends Usuario{
	private String nombre;
	private LocalDate fechaNacimiento;
	private String direccion;
	
	public Cliente() {
		super();
	}

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}
/**
 * 
 * @param direccion
 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
}
