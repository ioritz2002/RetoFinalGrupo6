package clases;

public class Usuario {
	private String dni;
	private String email;
	private String contrase�a;
	private String tipo;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario() {
		
	}

	public Usuario(String dni, String email, String contrase�a, String tipo) {
		super();
		this.dni = dni;
		this.email = email;
		this.contrase�a = contrase�a;
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

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	
}
