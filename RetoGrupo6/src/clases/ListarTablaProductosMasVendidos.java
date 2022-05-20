package clases;
/**
 * 
 * @author grupo6
 *
 */

public class ListarTablaProductosMasVendidos {
	/**
	 * codigo del producto
	 */
	private String codProducto;
	/**
	 * tipo de producto
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
	 * cuantos producto hay del tipo
	 */
	private int contador;
	/**
	 * valoracion del producto
	 */
	private float valoracion;
	
	/**
	 * 
	 * @return valoracion del producto
	 */
	public float getValoracion() {
		return valoracion;
	}
	/**
	 * 
	 * @param valoracion del producto
	 */
	public void setValoracion(float valoracion) {
		this.valoracion = valoracion;
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
	 * @return contador del numero de productos que hay
	 */
	public int getContador() {
		return contador;
	}
	/**
	 * 
	 * @param contador del numero de productos que hay
	 */
	public void setContador(int contador) {
		this.contador = contador;
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
	
	
}