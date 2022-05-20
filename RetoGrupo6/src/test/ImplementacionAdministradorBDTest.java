package test;

import static org.junit.Assert.*;


import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import clases.Cliente;
import clases.Producto;
import clases.Repartidor;
import clases.Valora;
import modelo.ImplementacionAdministradorBD;
import modelo.InterfazAdministrador;
import modelo.InterfazAmbosUsuarios;


public class ImplementacionAdministradorBDTest {

	
	private InterfazAdministrador admin= new ImplementacionAdministradorBD();
	
	
	

	@Test
	public void testAltaRepartidor() throws SQLException {
		
		Repartidor repartidor= new Repartidor();
		repartidor.setDniUsuario("79365183a");
		repartidor.setFechaAlta(LocalDate.parse("2022-05-12"));
		repartidor.setNombre("Juan");
		repartidor.setApellido("Contreras");
		repartidor.setIdRepartidor("RP-0004");
		assertTrue(admin.altaRepartidor(repartidor));
		
		//Para borrar en la base datos
		//delete from repartidor where nombre like "Juan"
	}

	@Test
	public void testBajaRepartidor() throws SQLException {
	
		
		assertTrue(admin.bajaRepartidor("RP-0004"));
		
		
	}

	@Test
	public void testListarRepartidores() {
			//Para comprobar las 2 opciones hay que modificar la base de datos
			//assertNull(null, admin.listarRepartidores());
	
			assertEquals("Alonso", admin.listarRepartidores().get(0).getNombre());
		
	
		
	}
	

	@Test
	public void testModificarProducto() {
		Producto producto= new Producto();
		producto.setCodProducto("PO-0001");
		producto.setNombre("Ordenador");
		producto.setTipo("Electrónico");
		producto.setStock(20);
		producto.setPrecio(950);
		assertTrue("No ejecuta la acción",admin.modificarProducto(producto));	
	}


	@Test
	public void testCalcularCodRepartidor() {
		assertNotNull("No devuelve nada",admin.calcularCodRepartidor());
		
	}

	@Test
	public void testCompararProductos() {
		assertTrue(admin.compararProductos("Ordenador"));
		assertFalse(admin.compararProductos("Rastrillo"));
		
	}

	@Test
	public void testListarProductosMasVendidosAdmin() {
		assertNotNull("Es nulo", admin.listarProductosMasVendidosAdmin());
		//Para comprobar las dos opciones hay que modificar la base de datos
		//assertNull(admin.listarProductosMasVendidosAdmin());
	}

	@Test
	public void testListarValoraciones() {
		assertNotNull(admin.listarValoraciones());
		
		//Para comprobar las dos opciones hay que modificar la base de datos
		//assertNull(admin.listarValoraciones());
		
	}
	
	@Test 
	public void testListarClientes() {
		assertNotNull(admin.listarClientes());
		
		//Para comprobar las dos opciones hay que modificar la base de datos
		//assertNull(admin.listarClientes());
	}
	
	@Test
	public void testCalcularCodProducto() {
		assertNotEquals(-1, admin.calcularCodProducto());
	}
	
	@Test
	public void testListarProductos() {
		assertNotNull(admin.listarProductos());
		
		//Para comprobar las dos opciones hay que modificar la base de datos
		//assertNull(admin.listarProductos());
	}
	
	@Test
	public void testAltaProductos() {
		Producto producto= new Producto();
		producto.setCodProducto("PO-0009");
		producto.setDni("79365183A");
		producto.setNombre("Raqueta de ping pong");
		producto.setTipo("Deporte");
		producto.setStock(30);
		producto.setPrecio(12);
		assertTrue(admin.altaProductos(producto));
		
		
	}
	
	@Test
	public void bajaProducto() {
		assertTrue(admin.bajaProducto("PO-0009"));
	}
	
}