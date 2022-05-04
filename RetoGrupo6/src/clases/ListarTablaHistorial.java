package clases;

import java.time.LocalDate;

public class ListarTablaHistorial {

	private String codigo;
	private double importe;
	private LocalDate fecha;
	private String estado;
	
	public ListarTablaHistorial() {
		super();
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void sacarEstado(int e) {
		if (e==1) {
			estado= "Finalizado";
		}else {
			estado="En curso";
		}
	}

	
}
