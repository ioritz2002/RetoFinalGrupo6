package clases;

/**
 * 
 * @author grupo6
 *
 */

public class ListarTablaProductos implements Comparable<ListarTablaProductos> {

	/**
	 * codigo del producto
	 */
	private String codigoProducto;
	/**
	 * nombre del producto
	 */
	private String nombreProducto;
	/**
	 * tipo del producto
	 */
	private String tipoProducto;
	/**
	 * precio del producto
	 */
	private double precio;
	/**
	 * valoracion del producto  
	 */
	private float valoracion;

	/**
	 * Constructor vacio
	 */
	public ListarTablaProductos() {
		super();
	}

	/**
	 * 
	 * @return nombre del producto
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}

	/**
	 * 
	 * @param nombreProducto del Producto
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	/**
	 * 
	 * @return tipo de producto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * 
	 * @param tipoProducto tipo de Producto
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
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
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * 
	 * @param codigoProducto del Producto
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * metodo para ordenar por valoracion
	 */
	@Override
	public int compareTo(ListarTablaProductos o) {
		// TODO Auto-generated method stub
		return Float.compare(o.getValoracion(), this.valoracion);
	}

}