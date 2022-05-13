package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Test;

import clases.Cliente;
import clases.ListarTablaHistorial;
import clases.Usuario;

public class ImplementacionAmbosUsuariosTest {

	private Connection conex;
	private PreparedStatement stmt;
	private ResourceBundle archivoConfig;

	// Conexion
	private String url;
	private String usuario;
	private String contraseña;
	
	public ImplementacionAmbosUsuariosTest() {
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
	public void testBuscarUsuarioLogin() {
		Usuario usuario = null;
		ResultSet rs = null;
		String dni= "79365183A";
		testOpenConnection();
		try {
			stmt = conex.prepareStatement("CALL SELECT_LOGIN(?)");
			stmt.setString(1, dni);

			rs = stmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equalsIgnoreCase("DNI no encontrado")) {
					usuario = null;
				} else if (rs.getString(4).equalsIgnoreCase("administrador")) {

					usuario = new Usuario();
					usuario.setDni(rs.getString(1));
					usuario.setEmail(rs.getString(2));
					usuario.setContraseña(rs.getString(3));
					usuario.setTipo(rs.getString(4));
					assertEquals("No son iguales","administrador", usuario.getTipo());
				} else {

					usuario = new Cliente();
					usuario.setDni(dni);
					usuario.setEmail(rs.getString(2));
					usuario.setContraseña(rs.getString(3));
					usuario.setTipo(rs.getString(4));
					((Cliente) usuario).setNombre(rs.getString(6));
					((Cliente) usuario).setFechaNacimiento(rs.getDate(7).toLocalDate());
					((Cliente) usuario).setDireccion(rs.getString(8));
					assertEquals("No son iguales","cliente", usuario.getTipo());
				}
			}
		
		} catch (SQLException e) {
			fail("Error al crear usuario");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				testCloseConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Test
	public void testBuscarUsuarioLoginDatoIncorrecto() {
		Usuario usuario = null;
		ResultSet rs = null;
		String dni= "79365183A";
		testOpenConnection();
		boolean funciona;
		try {
			stmt = conex.prepareStatement("CALL SELECT_LOGIN(?)");
			stmt.setString(1, dni);

			rs = stmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equalsIgnoreCase("DNI no encontrado")) {
					usuario = null;
				} else if (rs.getString(4).equalsIgnoreCase("administrador")) {

					usuario = new Usuario();
					usuario.setDni(rs.getString(1));
					usuario.setEmail(rs.getString(2));
					usuario.setContraseña(rs.getString(3));
					usuario.setTipo(rs.getString(4));
					if ("cliente".equalsIgnoreCase(usuario.getTipo())) {
						funciona= false;
					}else {
						funciona= true;
					}
					assertTrue("El dato es igual, no debería", funciona);  
				} else {

					usuario = new Cliente();
					usuario.setDni(dni);
					usuario.setEmail(rs.getString(2));
					usuario.setContraseña(rs.getString(3));
					usuario.setTipo(rs.getString(4));
					((Cliente) usuario).setNombre(rs.getString(6));
					((Cliente) usuario).setFechaNacimiento(rs.getDate(7).toLocalDate());
					((Cliente) usuario).setDireccion(rs.getString(8));
					assertNotSame("Son iguales","administrador", usuario.getTipo());
				}
			}
		} catch (SQLException e) {
			fail("Error al crear usuario");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				testCloseConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testBuscarCestas() {
		List<ListarTablaHistorial> listaHistorial = new ArrayList<>();
		ListarTablaHistorial historial;
		ResultSet rs = null;
		String dni= "";
		this.testOpenConnection();
		try {
			stmt = conex.prepareStatement("SELECT COD_CESTA, IMPORTE_TOTAL, FECHA_COMPRA, ESTADO FROM cesta WHERE COD_CESTA in (SELECT COD_CESTA FROM añade WHERE DNI = ?)");
			stmt.setString(1, dni);

			rs = stmt.executeQuery();

			while (rs.next()) {
				historial = new ListarTablaHistorial();
				historial.setCodigo(rs.getString(1));
				historial.setImporte(rs.getFloat(2));
				if (rs.getDate(3)==null) {
					historial.setFecha(LocalDate.now());
				}else {
					historial.setFecha(rs.getDate(3).toLocalDate());
				}	
				historial.sacarEstado(rs.getInt(4));
				listaHistorial.add(historial);
			}
			assertEquals(350, listaHistorial.get(1).getImporte());

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			if (rs != null) {
				rs.close();
			}
			this.testCloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
