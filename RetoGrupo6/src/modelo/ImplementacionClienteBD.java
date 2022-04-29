package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import clases.Cesta;
import clases.Cliente;
import clases.Producto;
import clases.Valora;

public class ImplementacionClienteBD implements InterfazCliente{

	private Connection conex;
	private PreparedStatement stmt;
	private ResourceBundle archivoConfig;
	
	//Conexion
	private String url;
	private String usuario;
	private String contrase�a;
	
	//SQL
	
	private final String BUSCARDni= "SELECT * FROM cliente WHERE dni = ?";
	private final String introducirCliente = "CALL INSERT_CLIENTE( ?, ?, ?, ?, ?, ?)";
	
	public ImplementacionClienteBD() {
		this.archivoConfig = ResourceBundle.getBundle("modelo.config");
		this.url = archivoConfig.getString("Conn");
		this.usuario = archivoConfig.getString("BDUser");
		this.contrase�a = archivoConfig.getString("BDPass");
	}
	
	public void openConnection() {
		try {
			conex = DriverManager.getConnection(url, usuario, contrase�a);
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
	public void registroCliente(Cliente usuario) {
this.openConnection();
		
		try {
			stmt= conex.prepareStatement(introducirCliente);
			stmt.setString(1, usuario.getDni());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getContrase�a());
			stmt.setString(4, usuario.getNombre());
			stmt.setDate(5, Date.valueOf(usuario.getFechaNacimiento()));
			stmt.setString(6, usuario.getDireccion());
			
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
	public List<Producto> listarProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Valora> listarValoracionesProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> listarCestaCompra(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void a�adirProductoACesta(Producto producto, String dni) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelarCompra(String codCesta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void realizarCompra(String codCesta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void darValoracion(String codProducto, String dni) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String productoExiste(String nombreProducto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean productoComprado(String codProducto, String dni) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cesta> verHistorialCompras(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void darseDeBaja(String dni) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarDatosCliente(Cliente usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Producto> listarProductosFiltradoNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> listarProductosPorTipo(String tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> listarProductos(double precioMin, double precioMax) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> listarProductosMasVendidos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean comprobarDni(String dni) {
		
		ResultSet rs= null;
		boolean b= false;
		this.openConnection();
		
		try {
			stmt=conex.prepareStatement(BUSCARDni);
			stmt.setString(1, dni);
			rs= stmt.executeQuery();
			
			if (rs.next()) {
			b= true;
			}
			
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
		return b;
	}

}
