package clases;

/**
 * 
 * @author grupo6
 *
 */

public class Usuario {
	/**
	 * Dni del usuario
	 */
	private String dni;
	/**
	 * email del usuario
	 */
	private String email;
	/**
	 * contrase�a del usuario
	 */
	private String contrase�a;
	/**
	 * tipo de usuario
	 */
	private String tipo;
	
	/**
	 * 
	 * @return tipo de usuario
	 */
	
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * 
	 * @param tipo de usuario
	 */

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * Constructor vacio
	 */

	public Usuario() {
		
	}
	
	/**
	 * 
	 * @return dni del usuario
	 */

	public String getDni() {
		return dni;
	}
	/**
	 * 
	 * @param dni del usuario
	 */

	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * 
	 * @return email del usuario
	 */

	public String getEmail() {
		return email;
	}
	/**
	 * 
	 * @param email del usuario
	 */

	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 * @return contrase�a del usuario
	 */

	public String getContrase�a() {
		return contrase�a;
	}
	/**
	 * 
	 * @param contrase�a del usuario
	 */

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	
}