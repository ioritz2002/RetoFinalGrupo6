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
	
	public Valora(){
		
	}

	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}

	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	
	
}
