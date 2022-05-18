package clases;

public class ListarTablaProductosMasVendidos {
	private String codProducto;
	private String tipo;
	private String nombre;
	private int stock;
	private double precio;
	private int contador;
	private float valoracion;
	
	
	public float getValoracion() {
		return valoracion;
	}
	public void setValoracion(float valoracion) {
		this.valoracion = valoracion;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}