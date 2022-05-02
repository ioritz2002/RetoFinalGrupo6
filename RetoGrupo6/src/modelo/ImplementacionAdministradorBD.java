package modelo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import clases.Cesta;
import clases.Cliente;
import clases.Producto;
import clases.Repartidor;
import clases.Usuario;
import clases.Valora;

public class ImplementacionAdministradorBD implements InterfazAdministrador {
	private Connection conex;
	private PreparedStatement stmt;
	private ResourceBundle archivoConfig;

	// Conexion
	private String url;
	private String usuario;
	private String contraseña;


	//SQL

	private final String DELETEproducto = "DELETE FROM producto where COD_PRODUCTO = ?";
	private final String DELETErepartidor = "DELETE FROM repartidor where ID_REPARTIDOR = ?";
	private final String CONSULTARrepartidores = "SELECT * FROM repartidor";


	
	
	private final String CALCULOValoracion = "SELECT valora.* FROM valora";
	private final String SELECTProductos = "SELECT producto.* FROM producto";
	private final String UPDATEProducto = "UPDATE producto SET TIPO = ?, NOMBRE = ?, STOCK = ?, PRECIO = ? WHERE COD_PRODUCTO LIKE ?";
	

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

	public void closeConnection() throws SQLException {
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
		this.openConnection();

		try {
			stmt = conex.prepareStatement(DELETErepartidor);
			stmt.setString(1, idRepartidor);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Repartidor> listarRepartidores() {
		// TODO Auto-generated method stub

		List<Repartidor> repartidores = new ArrayList<>();
		ResultSet rs = null;
		Repartidor repartidor = null;
		
		this.openConnection();
		
		try {
			stmt = conex.prepareStatement(CONSULTARrepartidores);
			rs = stmt.executeQuery();

			while (rs.next()) {
				repartidor = new Repartidor();
				repartidor.setIdRepartidor(rs.getString(1));
				repartidor.setDniUsuario(rs.getString(5));
				repartidor.setNombre(rs.getString(3));
				repartidor.setApellido(rs.getString(4));
				repartidor.setFechaAlta(rs.getDate(2).toLocalDate());
				repartidores.add(repartidor);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return repartidores;
	}

	@Override
	public void altaProductos(Producto producto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bajaProducto(String codProducto) {
		// TODO Auto-generated method stub

		this.openConnection();

		try {
			stmt = conex.prepareStatement(DELETEproducto);
			stmt.setString(1, codProducto);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Producto> listarProductos() {
		List<Producto> productos = new ArrayList<Producto>();
		Producto producto = null;
		ResultSet rs = null;
		
		openConnection();
		try {
			stmt = conex.prepareStatement(SELECTProductos);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				producto = new Producto();
				producto.setCodProducto(rs.getString(1));
				producto.setTipo(rs.getString(2));
				producto.setNombre(rs.getString(3));
				producto.setStock(rs.getInt(4));
				producto.setPrecio(rs.getDouble(5));
				productos.add(producto);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return productos;
	}

	@Override

	public void modificarProducto(Producto producto) {
		openConnection();
		try {
			stmt = conex.prepareStatement(UPDATEProducto);
			
			stmt.setString(1, producto.getTipo());
			stmt.setString(2, producto.getNombre());
			stmt.setInt(3, producto.getStock());
			stmt.setDouble(4, producto.getPrecio());
			stmt.setString(5, producto.getCodProducto());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

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
	public List<Valora> listarValoraciones() {
		List<Valora> valoraciones = new ArrayList<Valora>();
		Valora valora = null;
		ResultSet rs = null;
		
		openConnection();
		try {
			stmt = conex.prepareStatement(CALCULOValoracion);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				valora = new Valora();
				
				valora.setCodProducto(rs.getString(1));
				valora.setDniUsuario(rs.getString(2));
				valora.setValoracion(rs.getInt(3));
				
				valoraciones.add(valora);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return valoraciones;
	}
	
	

}
