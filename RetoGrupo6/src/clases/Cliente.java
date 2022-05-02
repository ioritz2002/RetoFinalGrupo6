package clases;

import java.time.LocalDate;

public class Cliente extends Usuario{
	private String nombre;
	private LocalDate fechaNacimiento;
	private String direccion;
	
	public Cliente() {
		super();
	}
	
	

	



	public Cliente(String dni, String email, String contraseña, String tipo, String nombre, LocalDate fechaNacimiento,
			String direccion) {
		super(dni, email, contraseña, tipo);
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
	}







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

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
}
