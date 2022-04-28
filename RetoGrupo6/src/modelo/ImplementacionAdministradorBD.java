package modelo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.Date;

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
	
	private final String INSERTARProducto= "INSERT INTO productos(COD_PRODUCTO, TIPO, NOMBRE, STOCK, PRECIO, DNI) VALUES(?, ? ,? , ?, ?, ?)";
	private final String BUSCARNumRep= "SELECT COUNT(*) AS total FROM productos";
	private final String numRepartidor= "SELECT COUNT(*) AS total FROM repartidor"; 
	private final String altaRepartidor= "INSERT INTO repartidor(ID_REPARTIDOR, FECHA_ALTA, NOMBRE, APELLIDO, DNI) VALUES( ?, ?, ?, ?, ?)";
	
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
		this.openConnection();
		
		try {
			stmt= conex.prepareStatement(altaRepartidor);
			stmt.setString(1, repartidor.getIdRepartidor());
			stmt.setDate(2, Date.valueOf(repartidor.getFechaAlta()));
			stmt.setString(3, repartidor.getNombre());
			stmt.setString(4, repartidor.getApellido());
			stmt.setString(5, repartidor.getDniUsuario());
			
			stmt.executeUpdate();
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			this.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		
		
		this.openConnection();		
		try {
			stmt= conex.prepareStatement(INSERTARProducto);
			stmt.setString(1, producto.getCodProducto());
			stmt.setString(2, producto.getTipo());
			stmt.setString(3, producto.getNombre());
			stmt.setInt(4, producto.getStock());
			stmt.setDouble(5, producto.getPrecio());
			stmt.setString(6, producto.getDni());
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			this.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@Override
	public int calcularCodRepartidor() {
		
		ResultSet rs= null;
		int num = 0;
		
		this.openConnection();
		
		try {
			stmt= conex.prepareStatement(numRepartidor);
			
			rs= stmt.executeQuery();
			
			if (rs.next()) {
				num= rs.getInt("total");
			}
			
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		try {
			this.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
		
	}

	@Override
	public int calcularCodProducto() {
		ResultSet rs= null;
		int num = 0;
		
		this.openConnection();
		
		try {
			stmt= conex.prepareStatement(BUSCARNumRep);
			
			rs= stmt.executeQuery();
			
			if (rs.next()) {
				num= rs.getInt("total");
			}
			
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		try {
			this.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}
}
