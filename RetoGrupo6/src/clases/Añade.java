package clases;

/**
 * Permite crear objetos y guardar la informacion que se vaya añadiendo
 * @author grupo6
 * @version 1
 *
 */

public class Añade {
	/**
	 * Guarda el DNI de quien añade un producto a la cesta
	 */
	private String dni;
	/**
	 * Indica a que cesta pertenece el producto
	 */
	private String codCesta;
	/**
	 * Indica que producto se ha metido en la cesta
	 */
	private String idProducto;
	
	/**
	 * Constructor vacio
	 */
	public Añade() {
		
	}

	/**
	 * Devuelve el valor del atributo DNI
	 * @return Devuelve el valor de DNI
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Guarda el valor de la varible dni
	 * @param dni Es el dni del usuario que ha metido el producto a la cesta
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Muestra el codCesta correspondiente
	 * @return Devuelve el valor de codCesta
	 */
	public String getCodCesta() {
		return codCesta;
	}

	/**
	 * Guarda el valor de la variable codCesta
	 * @param codCesta Es el codCesta de la cesta en el que se ha añadido el producto
	 */
	public void setCodCesta(String codCesta) {
		this.codCesta = codCesta;
	}

	/**
	 * Muestra el idProducto correspondiente
	 * @return Devuelve el idProducto del producto que esta en una cesta
	 */
	public String getIdProducto() {
		return idProducto;
	}

	/**
	 * Guarda el valor de la variable idProducto
	 * @param idProducto Es el idProducto del producto que se va a añadir a una cesta
	 */
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

}