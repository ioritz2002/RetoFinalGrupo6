package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import clases.Usuario;

public class ImplementacionAmbosUsuarios implements InterfazAmbosUsuarios{
	private Connection conex;
	private PreparedStatement stmt;
	private ResourceBundle archivoConfig;
	
	//Conexion
	private String url;
	private String usuario;
	private String contraseña;
	
	//SQL
	
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
	
	public void closeConnection() throws SQLException{
		if (conex != null) {
			conex.close();
		}
		if (stmt != null) {
			conex.close();
		}
	}
	@Override
	public Usuario buscarUsuarioLogin(String dni, String contraseña) {
		// TODO Auto-generated method stub
		return null;
	}

}
