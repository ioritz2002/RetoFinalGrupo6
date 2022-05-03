package clases;

import java.time.LocalDate;

public class Cesta {
	private String codCesta;
	private double importeTotal;
	private LocalDate fechaCompra;
	private boolean estado;
	private String codRepartidor;
	
	public Cesta() {
		this.estado = false;
	}

	public String getCodCesta() {
		return codCesta;
	}

	public void setCodCesta(String codCesta) {
		this.codCesta = codCesta;
	}

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getCodRepartidor() {
		return codRepartidor;
	}

	public void setCodRepartidor(String codRepartidor) {
		this.codRepartidor = codRepartidor;
	}
	
	public void ponerEstado(String estado) {
		if (estado.equalsIgnoreCase("En curso")) {
			this.estado = false;
		}
		if (estado.equalsIgnoreCase("Finalizado")) {
			this.estado = true;
		}
	}
	
	
	
}
