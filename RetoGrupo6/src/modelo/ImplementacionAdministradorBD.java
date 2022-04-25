package modelo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.Connection;
import clases.Cesta;
import clases.Cliente;
import clases.Producto;
import clases.Repartidor;
import clases.Usuario;
import clases.Valora;

public class ImplementacionAdministradorBD implements InterfazAdministrador{
	private Connection conex;
	private PreparedStatement stmt;
	private ResourceBundle archivoConfig;
	
	//Conexion
	private String url;
	private String usuario;
	private String contraseña;
	
	//SQL
	
	public ImplementacionAdministradorBD() {
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
	public void altaRepartidor(Repartidor repartidor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bajaRepartidor(String idRepartidor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Repartidor> listarRepartidores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void altaProductos(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bajaProducto(String codProducto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Producto> listarProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarProducto(String codProducto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cliente> listarClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> listarProductosMasVendidos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
