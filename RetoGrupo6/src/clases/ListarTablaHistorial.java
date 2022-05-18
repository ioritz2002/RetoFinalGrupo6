package clases;

import java.time.LocalDate;


/**
 * 
 * @author grupo6
 *
 */

public class ListarTablaHistorial {

	/**
	 * Codigo de la cesta
	 */
	private String codigo;
	/**
	 * Precio de la cesta
	 */
	private double importe;
	/**
	 * Fecha de la compra
	 */
	private LocalDate fecha;
	/**
	 * Estado en el que se encuentra la compra
	 */
	private String estado;
	
	
	/**
	 * Constructor vacio
	 */
	
	public ListarTablaHistorial() {
		super();
	}
	
	/**
	 * 
	 * @return codigo de la cesta
	 */
	
	public String getCodigo() {
		return codigo;
	}
	/**
	 * 
	 * @param codigo de la cesta
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * 
	 * @return precio de la cesta
	 */
	public double getImporte() {
		return importe;
	}
	
	/**
	 * 
	 * @param importe de la cesta
	 */
	public void setImporte(float importe) {
		this.importe = importe;
	}
	/**
	 * 
	 * @return fecha una vez realizada la compra o cuando se creo la cesta en caso de todavia estar en curso
	 */
	public LocalDate getFecha() {
		return fecha;
	}
	/**
	 * 
	 * @param fecha una vez realizada la compra o cuando se creo la cesta en caso de todavia estar en curso
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	/**
	 * 
	 * @return estado en el que se encuentra la compra
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * 
	 * @param estado en el que se encuentra la cesta
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * Dependiendo el estado de, e, pone la guardara la cesta de una u otra manera
	 * @param e estado de la cesta 
	 */
	public void sacarEstado(int e) {
		if (e==1) {
			estado= "Finalizado";
		}else {
			estado="En curso";
		}
	}

	
}
