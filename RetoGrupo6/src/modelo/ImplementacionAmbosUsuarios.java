package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import clases.Cliente;
import clases.Usuario;

public class ImplementacionAmbosUsuarios implements InterfazAmbosUsuarios {

	private Connection conex;
	private PreparedStatement stmt;
	private ResourceBundle archivoConfig;

	// Conexion
	private String url;
	private String usuario;
	private String contraseña;

	// SQL
	private final String SELECTlogin = "CALL SELECT_LOGIN(?)";

	public ImplementacionAmbosUsuarios() {
		this.archivoConfig = ResourceBundle.getBundle("modelo.config");
		this.url = archivoConfig.getString("Conn");
		this.usuario = archivoConfig.getString("BDUser");
		this.contraseña = archivoConfig.getString("BDPass");
	}

	public void openConnection() {
		try {
			conex = DriverManager.getConnection(url, usuario, contraseña);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
			
			if(rs.next()) {
				if(rs.getString(1).equalsIgnoreCase("DNI no encontrado")) {
					usuario =  null;
				}
				else if (rs.getString(4).equalsIgnoreCase("administrador")) {
					
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

}
