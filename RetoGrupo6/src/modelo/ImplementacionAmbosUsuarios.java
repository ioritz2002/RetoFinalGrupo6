package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import clases.Cliente;
import clases.ListarTablaHistorial;
import clases.Usuario;

/**
 * Es la implementación de la intrfaz de ambos usuarios
 * @author grupo6
 * @version 1
 */
public class ImplementacionAmbosUsuarios implements InterfazAmbosUsuarios {

	/**
	 * Prepara la conexión
	 */
	private Connection conex;
	/**
	 * Prepara la sentencia
	 */
	private PreparedStatement stmt;
	/**
	 * Lee el archivo de configuración
	 */
	private ResourceBundle archivoConfig;

	// Conexion
	/**
	 * Es la url para conectarse a la base de datos
	 */
	private String url;
	/**
	 * Es el usuario con el que se conectará a la base de datos
	 */
	private String usuario;
	/**
	 * Es la contraseña necesaria para conectarse a la base de datos
	 */
	private String contraseña;

	// SQL
	/**
	 * LLama al procedimiento SELECT_LOGIN() en la que saca los datos del usuario correspondiente al dni pasado por parámetro
	 */
	private final String SELECTlogin = "CALL SELECT_LOGIN(?)";
	/**
	 * Recoge los datos COD_CESTA, IMPORTE_TOTAL. FECHA_COMPRA y Estado de la tabla cesta correspndientes a la cesta la cual corresponde al DNi del usuario seleccionado
	 */
	private final String SELECTHistorial = "SELECT COD_CESTA, IMPORTE_TOTAL, FECHA_COMPRA, ESTADO FROM cesta WHERE COD_CESTA in (SELECT COD_CESTA FROM añade WHERE DNI = ?)";

	/**
	 * Es el constructor en el que se agregan los datos necesarios para conectarse a la base de datos
	 */
	public ImplementacionAmbosUsuarios() {
		this.archivoConfig = ResourceBundle.getBundle("modelo.config");
		this.url = archivoConfig.getString("Conn");
		this.usuario = archivoConfig.getString("BDUser");
		this.contraseña = archivoConfig.getString("BDPass");
	}

	/**
	 * Este método permite conectarse a la base datos 
	 */
	public void openConnection() {
		try {
			conex = DriverManager.getConnection(url, usuario, contraseña);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Este método permite cerrar la conexión con la base de datos
	 * @throws SQLException Cuando la conexión no se ha podido cerrar correctamente salta la excepción
	 */
	public void closeConnection() throws SQLException {
		if (conex != null) {
			conex.close();
		}
		if (stmt != null) {
			conex.close();
		}
	}

	@Override
	public Usuario buscarUsuarioLogin(String dni) {
		Usuario usuario = null;
		ResultSet rs = null;

		openConnection();
		try {
			stmt = conex.prepareStatement(SELECTlogin);
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

				} else {

					usuario = new Cliente();
					usuario.setDni(dni);
					usuario.setEmail(rs.getString(2));
					usuario.setContraseña(rs.getString(3));
					usuario.setTipo(rs.getString(4));
					((Cliente) usuario).setNombre(rs.getString(6));
					((Cliente) usuario).setFechaNacimiento(rs.getDate(7).toLocalDate());
					((Cliente) usuario).setDireccion(rs.getString(8));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return usuario;
	}

	@Override
	public List<ListarTablaHistorial> buscarCestas(String dni) {
		List<ListarTablaHistorial> listaHistorial = new ArrayList<>();
		ListarTablaHistorial historial;
		ResultSet rs = null;

		this.openConnection();
		try {
			stmt = conex.prepareStatement(SELECTHistorial);
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

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (rs != null) {
				rs.close();
			}
			this.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaHistorial;
	}

}