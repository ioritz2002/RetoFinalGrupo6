package clases;

import java.time.LocalDate;

/**
 * Permite crear y guardar los objetos necesarios para poder guardarlos y recogerlos en la base de datos 
 * @author grupo6
 * @version 1
 */
public class Cesta {
	/**
	 * Es el codigo de cesta de la compra en curso
	 */
	private String codCesta;
	/**
	 * Es a suma de todos los productos que se han añadido a la cesta
	 */
	private double importeTotal;
	/**
	 * Es la fecha en la que se ha realizado la compra
	 */
	private LocalDate fechaCompra;
	/**
	 * Indica si la compra esta en curso o ya esta finalizada
	 */
	private boolean estado;
	/**
	 * Es codigo de repartidor que va a repartir la cesta
	 */
	private String codRepartidor;
	
	/**
	 * Constructor en el que se indica al estado de la compra que por defecto sea false
	 */
	public Cesta() {
		this.estado = false;
	}

	/**
	 * Muestra el valor de codCesta
	 * @return Devuelve el valor de codCesta
	 */
	public String getCodCesta() {
		return codCesta;
	}

	/**
	 * Guarda el valor del parametro codCesta
	 * @param codCesta Es el codigo de cesta asignado a la compra
	 */
	public void setCodCesta(String codCesta) {
		this.codCesta = codCesta;
	}

	/**
	 * Muestra el valor del importe total de la cesta correspondiente
	 * @return Devuelve la suma del precio de los productos asignados a la cesta corresponiente
	 */
	public double getImporteTotal() {
		return importeTotal;
	}

	/**
	 * Guarda el valor del importe total
	 * @param importeTotal Es la suma del precio de los productos de la cesta
	 */
	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * Muestra la fecha en la que se ha realizado la compra
	 * @return Devuelve la fecha en la que se ha realizado la compra
	 */
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	/**
	 * Guarda la fecha en la que se ha realizado la compra
	 * @param fechaCompra Es la fecha en la que se ha realizado la compra
	 */
	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	/**
	 * Muestra dependiendo si es true o false si la compra esta en curso o no
	 * @return Deuelve un booleano
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * Guarda el estado de la compra
	 * @param estado Es el booleano que dependiendo de su valor indica si la compra esta en curso op no
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * Muestra el codigo del repartidor al que se ha asignado la cesta
	 * @return Devuelve el codRepartidor de la cesta correspondiente
	 */
	public String getCodRepartidor() {
		return codRepartidor;
	}

	/**
	 * Guarda el codRepartidor asignado al la cesta
	 * @param codRepartidor Es el codigo de repartido asignado aleatoriamente entre todos los repartidores
	 */
	public void setCodRepartidor(String codRepartidor) {
		this.codRepartidor = codRepartidor;
	}
}
