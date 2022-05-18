package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.ImplementacionClienteBD;
import modelo.InterfazCliente;

public class ImplementacionClienteBDTest {
	
	private InterfazCliente clien= new ImplementacionClienteBD();

	@Test
	public void testCancelarCompra() {
		assertTrue(clien.cancelarCompra("CE-0001"));
		
	}

	@Test
	public void testRealizarCompra() {
		clien.realizarCompra(null, null, null);
	
	}

	@Test
	public void testActualizarValoracion() {
		clien.actualizarValoracion(0, null, null);
	
	}

	@Test
	public void testAñadirProductoAAñade() {
		clien.añadirProductoAAñade(null);
	
	}

	@Test
	public void testComprobarCestaActiva() {
		clien.comprobarCestaActiva(null);
		
	}

}