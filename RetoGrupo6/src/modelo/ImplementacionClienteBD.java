package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import clases.Cesta;
import clases.Cliente;
import clases.ListarTablaCesta;
import clases.Producto;
import clases.Usuario;
import clases.Valora;

public class ImplementacionClienteBD implements InterfazCliente {

	private Connection conex;
	private PreparedStatement stmt;
	private ResourceBundle archivoConfig;

	// Conexion
	private String url;
	private String usuario;
	private String contraseña;

	// SQL
	private final String BUSCARproductos = "SELECT P.NOMBRE,P.TIPO,P.PRECIO,C.COD_CESTA FROM PRODUCTO P, CESTA C, AÑADE A WHERE P.COD_PRODUCTO = A.COD_PRODUCTO AND C.COD_CESTA=A.COD_CESTA AND C.ESTADO = 0 AND A.DNI = ?";
	private final String UPDATEcliente = "CALL MODIFICAR_CLIENTE(?,?,?,?,?,?)";
	private final String BUSCARDni = "SELECT * FROM cliente WHERE dni = ?";
	private final String introducirCliente = "CALL INSERT_CLIENTE( ?, ?, ?, ?, ?, ?)";
	private final String DELETEcliente = "DELETE FROM usuario WHERE DNI = ?";
	private final String DELETEcompra = "DELETE FROM cesta WHERE COD_CESTA LIKE ?";
	private final String HACERcompra = "UPDATE CESTA SET IMPORTE_TOTAL = ?,FECHA_COMPRA = ?, ESTADO = 1 WHERE COD_CESTA LIKE ?";
	
	
	public ImplementacionClienteBD() {
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
	public void registroCliente(Cliente usuario) {
		this.openConnection();
		try {
			stmt = conex.prepareStatement(introducirCliente);
			stmt.setString(1, usuario.getDni());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getContraseña());
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

	// ESTE
	@Override
	public List<ListarTablaCesta> listarCestaCompra(String dni) {
		// TODO Auto-generated method stub

		List<ListarTablaCesta> productos = new ArrayList<>();
		ResultSet rs = null;
		//Producto producto = null;
		ListarTablaCesta cesta = null;

		this.openConnection();

		try {

			stmt = conex.prepareStatement(BUSCARproductos);
			stmt.setString(1, dni);
			rs = stmt.executeQuery();

			while (rs.next()) {
				cesta = new ListarTablaCesta();
				cesta.setTipo(rs.getString(2));
				cesta.setNombre(rs.getString(1));
				cesta.setPrecio(rs.getDouble(3));
				cesta.setCod_cesta(rs.getString(4));
				productos.add(cesta);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {

			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return productos;
	}

	@Override
	public void añadirProductoACesta(Producto producto, String dni) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelarCompra(String codCesta) {
		// TODO Auto-generated method stub
		
		this.openConnection();
		
		try {
			
			stmt = conex.prepareStatement(DELETEcompra);
			stmt.setString(1, codCesta);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		try {
			this.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void asignarRepartidor() {
		
	}

	@Override
	public void realizarCompra(String codCesta,Double precio) {
		// TODO Auto-generated method stub
		
		openConnection();
		
		try {
			stmt = conex.prepareStatement(HACERcompra);
			
			stmt.setString(3, codCesta);
			
			
			stmt.setDouble(1, precio);
			stmt.setDate(2, Date.valueOf(LocalDate.now()));
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
		try {
			this.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
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

		// Abrimos la conexión

		this.openConnection();

		try {
			// Preparamos la sentencia stmt para borrar el cliente

			stmt = conex.prepareStatement(DELETEcliente);
			stmt.setString(1, dni);
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
	public void modificarDatosCliente(Cliente usuario) {
		openConnection();
		try {
			stmt = conex.prepareStatement(UPDATEcliente);

			stmt.setString(1, usuario.getDni());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getContraseña());
			stmt.setString(4, usuario.getNombre());
			stmt.setDate(5, Date.valueOf(usuario.getFechaNacimiento()));
			stmt.setString(6, usuario.getDireccion());

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
		ResultSet rs = null;
		boolean b = false;
		this.openConnection();

		try {
			stmt = conex.prepareStatement(BUSCARDni);
			stmt.setString(1, dni);
			rs = stmt.executeQuery();

			if (rs.next()) {
				b = true;
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
