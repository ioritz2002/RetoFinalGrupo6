package clases;

public class Producto {
	private String codProducto;
	private String tipo;
	private String nombre;
	private int stock;
	private double precio;
	private String dni;
	
	public Producto() {
		
	}
	
	

	public Producto(String codProducto, String tipo, String nombre, int stock, double precio, String dni) {
		super();
		this.codProducto = codProducto;
		this.tipo = tipo;
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
		this.dni = dni;
	}



	public String getCodProducto() {
		return codProducto;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
}
