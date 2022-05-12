package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.junit.Test;

import clases.Repartidor;

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
		this.url = "jdbc:mysql://localhost:3306/tienda_online?serverTimezone=Europe/Madrid&useSSL=false";
		this.usuario = "root";
		this.contraseña = "abcd*1234";
	}

	@Test
	public void testOpenConnection() {
		try {
			conex = DriverManager.getConnection(url, usuario, contraseña);
		} catch (SQLException e) {
			fail("Error de conexión con la base de datos");
			e.printStackTrace();
		}
		
	}

	@Test
	public void testCloseConnection(){
		
		try {
			if (conex != null) {
				conex.close();
			}
			if (stmt != null) {
				conex.close();
			}
		} catch (SQLException e) {
			
			fail("Error al cerrar la conexión con la BD");
		}
		
		
	}

	@Test
	public void testAltaRepartidor() throws SQLException {
		this.testOpenConnection();
		Repartidor repartidor = new Repartidor();
		repartidor.setDniUsuario("79365183a");
		repartidor.setFechaAlta(LocalDate.parse("2022-05-12"));
		repartidor.setNombre("Juan");
		repartidor.setApellido("Contreras");
		repartidor.setIdRepartidor("RP-0003");
		
		try {
			stmt = conex.prepareStatement("INSERT INTO repartidor(ID_REPARTIDOR, FECHA_ALTA, NOMBRE, APELLIDO, DNI, ACTIVO) VALUES( ?, ?, ?, ?, ?,?)");
			
			stmt.setString(1, repartidor.getIdRepartidor());
			stmt.setDate(2, Date.valueOf(repartidor.getFechaAlta()));
			stmt.setString(3, repartidor.getNombre());
			stmt.setString(4, repartidor.getApellido());
			stmt.setString(5, repartidor.getDniUsuario());
			stmt.setBoolean(6, false);

			stmt.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("Alta fallida");
		}

		this.testCloseConnection();

		
	}

	@Test
	public void testBajaRepartidor() throws SQLException {
		this.testOpenConnection();

		try {
			stmt = conex.prepareStatement("UPDATE repartidor SET ACTIVO= false where ID_REPARTIDOR = ?");
			stmt.setString(1, "RP-0003");
			stmt.execute();
		} catch (SQLException e) {
			fail("Error al borrar repartidor");
			e.printStackTrace();
		} finally {
			this.testCloseConnection();
		}
		
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
