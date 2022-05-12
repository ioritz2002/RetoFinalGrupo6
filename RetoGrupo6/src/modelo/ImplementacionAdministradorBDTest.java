package modelo;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.junit.Test;

public class ImplementacionAdministradorBDTest {
	
	private Connection conex;
	private PreparedStatement stmt;
	private ResourceBundle archivoConfig;

	// Conexion
	private String url;
	private String usuario;
	private String contraseña;
	
	
	
	
	
	
	public ImplementacionAdministradorBDTest() {
		this.archivoConfig = ResourceBundle.getBundle("modelo.config");
		this.url = archivoConfig.getString("Conn");
		this.usuario = archivoConfig.getString("BDUser");
		this.contraseña = archivoConfig.getString("BDPass");
	}
	

	@Test
	public void testOpenConnection() {
		try {
			conex = DriverManager.getConnection(url, usuario, contraseña);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

	@Test
	public void testCloseConnection() throws SQLException {
		if (conex != null) {
			conex.close();
		}
		if (stmt != null) {
			conex.close();
		}
		fail("Not yet implemented");
	}

	@Test
	public void testAltaRepartidor() {
		fail("Not yet implemented");
	}

	@Test
	public void testBajaRepartidor() {
		fail("Not yet implemented");
	}

	@Test
	public void testListarRepartidores() {
		fail("Not yet implemented");
	}

	@Test
	public void testAltaProductos() {
		fail("Not yet implemented");
	}

	@Test
	public void testBajaProducto() {
		fail("Not yet implemented");
	}

	@Test
	public void testListarProductos() {
		fail("Not yet implemented");
	}

	@Test
	public void testModificarProducto() {
		fail("Not yet implemented");
	}

	@Test
	public void testListarClientes() {
		fail("Not yet implemented");
	}

	@Test
	public void testListarValoraciones() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcularCodRepartidor() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompararProductos() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcularCodProducto() {
		fail("Not yet implemented");
	}

	@Test
	public void testListarProductosMasVendidosAdmin() {
		fail("Not yet implemented");
	}

}
