package clases;

public class ListarTablaCesta {
	
	private String cod_cesta;
	private String nombre;
	private String tipo;
	private double precio;
	
	
	public ListarTablaCesta() {
		super();
		this.cod_cesta = cod_cesta;
		this.nombre = nombre;
		this.tipo = tipo;
		this.precio = precio;
	}


	public String getCod_cesta() {
		return cod_cesta;
	}


	public void setCod_cesta(String cod_cesta) {
		this.cod_cesta = cod_cesta;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	

}
