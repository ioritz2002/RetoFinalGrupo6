package clases;

/**
 * 
 * @author grupo6
 *
 */

public class ListarTablaCesta {
	/**
	 * Codigo de la cesta
	 */
	private String cod_cesta;
	/**
	 * Nombre del producto
	 */
	private String nombre;
	/**
	 * Tipo de producto
	 */
	private String tipo;
	/**
	 * Precio del producto
	 */
	private double precio;

	/**
	 * Constructor vacio
	 */

	public ListarTablaCesta() {

	}

	/**
	 * 
	 * @return codigo de la cesta
	 */

	public String getCod_cesta() {
		return cod_cesta;
	}

	/**
	 * 
	 * @param cod_cesta codigo de la cesta
	 */

	public void setCod_cesta(String cod_cesta) {
		this.cod_cesta = cod_cesta;
	}
	/**
	 * 
	 * @return nombre del producto
	 */

	public String getNombre() {
		return nombre;
	}
	/**
	 * 
	 * @param nombre del producto
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * 
	 * @return tipo de producto
	 */

	public String getTipo() {
		return tipo;
	}
	/**
	 * 
	 * @param tipo de producto
	 */

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * 
	 * @return precio del producto
	 */

	public double getPrecio() {
		return precio;
	}
	/**
	 * 
	 * @param precio del producto
	 */

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
