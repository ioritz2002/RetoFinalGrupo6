package clases;

/**
 * 
 * @author 1dam
 *
 */
public class Valora {
	/**
	 * Guarda el DNI del usuario
	 */
	private String dniUsuario;
	/**
	 * Guarda el código del producto que ha valorado el usuario
	 */
	private String codProducto;
	/**
	 * Guarda la valoración que le ha dado el usuario al producto
	 */
	private int valoracion;
	
	/**
	 * Constructor vacio
	 */
	
	public Valora(){
		
	}
	/**
	 * 
	 * @return dni del usuario
	 */

	public String getDniUsuario() {
		return dniUsuario;
	}
	/**
	 * 
	 * @param dniUsuario dni del usuario
	 */

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	/**
	 * 
	 * @return codigo del producto
	 */

	public String getCodProducto() {
		return codProducto;
	}
	/**
	 * 
	 * @param codProducto codigo del producto
	 */

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	/**
	 * 
	 * @return valoracion del producto
	 */

	public int getValoracion() {
		return valoracion;
	}
	/**
	 * 
	 * @param valoracion del producto
	 */

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	
	
}