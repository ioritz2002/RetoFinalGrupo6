package clases;

public class Usuario {
	private String dni;
	private String email;
	private String contraseña;
	private String tipo;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario() {
		
	}

	public Usuario(String dni, String email, String contraseña, String tipo) {
		super();
		this.dni = dni;
		this.email = email;
		this.contraseña = contraseña;
		this.tipo = tipo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
}
