package clases;

public class ListarTablaProductos implements Comparable<ListarTablaProductos>{
	private String codigoProducto;
	private String nombreProducto;
	private String tipoProducto;
	private double precio;
	private float valoracion;
	
	
	public ListarTablaProductos() {
		super();
	}


	public String getNombreProducto() {
		return nombreProducto;
	}


	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}


	public String getTipoProducto() {
		return tipoProducto;
	}


	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public float getValoracion() {
		return valoracion;
	}


	public void setValoracion(float valoracion) {
		this.valoracion = valoracion;
	}


	public String getCodigoProducto() {
		return codigoProducto;
	}


	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}


	@Override
	public int compareTo(ListarTablaProductos o) {
		// TODO Auto-generated method stub
		return Float.compare(o.getValoracion(), this.valoracion);
	}
	
}
