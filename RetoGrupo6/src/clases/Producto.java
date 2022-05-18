package clases;

/**
 * 
 * @author grupo6
 *
 */

public class Producto {
	/**
	 * Codigo del producto
	 */
	private String codProducto;
	/**
	 *tipo de producto
	 */
	private String tipo;
	/**
	 * nombre del producto
	 */
	private String nombre;
	/**
	 * stock del producto
	 */
	private int stock;
	/**
	 * precio del producto
	 */
	private double precio;
	/**
	 * dni de quien a introducido el producto
	 */
	private String dni;
	
	/**
	 * constructor vacio
	 */
	
	public Producto() {
		
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
	 * @return stock del producto
	 */

	public int getStock() {
		return stock;
	}
	
	/**
	 * 
	 * @param stock del producto
	 */

	public void setStock(int stock) {
		this.stock = stock;
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
	/**
	 * 
	 * @return dni de quien a introducido el producto
	 */

	public String getDni() {
		return dni;
	}
	/**
	 * 
	 * @param dni de quien a introducido el producto
	 */

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
}
