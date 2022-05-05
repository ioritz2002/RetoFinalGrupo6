package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import clases.Cesta;
import clases.Cliente;
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
	private final String UPDATEcliente = "CALL MODIFICAR_CLIENTE(?,?,?,?,?,?)";
	private final String BUSCARDni = "SELECT * FROM cliente WHERE dni = ?";
	private final String INTRODUCIRCliente = "CALL INSERT_CLIENTE( ?, ?, ?, ?, ?, ?)";
	private final String DELETEcliente = "DELETE FROM usuario WHERE DNI = ?";
	private final String BUSCARProductosComprados= "SELECT COD_PRODUCTO, NOMBRE FROM producto WHERE COD_PRODUCTO IN (SELECT COD_PRODUCTO FROM añade WHERE DNI= ? AND COD_CESTA IN (SELECT COD_CESTA FROM cesta WHERE ESTADO=1))";
	private final String INSERTARValoracion= "INSERT INTO valora (COD_PRODUCTO, DNI, VALORACION) VALUES( ?, ?, ?)";
	private final String ACTUALIZARValoracion= "UPDATE valora SET VALORACION= ? WHERE COD_PRODUCTO= ? AND DNI= ?";
	private final String COMPROBARVloracion= "SELECT * FROM valora WHERE COD_PRODUCTO= ? AND DNI= ?";
	
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
			stmt = conex.prepareStatement(INTRODUCIRCliente);
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

	@Override
	public List<Producto> listarCestaCompra(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void añadirProductoACesta(Producto producto, String dni) {
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

	@Override
	public List<Producto> listarProductosComprados(String dni) {
		List<Producto> lista = new ArrayList<>();
		Producto prod= null;
		ResultSet rs= null;
		this.openConnection();
		
		try {
			stmt= conex.prepareStatement(BUSCARProductosComprados);
			stmt.setString(1, dni);
			rs= stmt.executeQuery();
			
			while (rs.next()) {
				prod= new Producto();
				prod.setCodProducto(rs.getString(1));
				prod.setNombre(rs.getString(2));
				lista.add(prod);
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
		return lista;
	}

	@Override
	public void insertarValoracion(String codigo, String dni, int valoracion) {
		
		this.openConnection();
		try {
			stmt= conex.prepareStatement(INSERTARValoracion);
			stmt.setString(1, codigo);
			stmt.setString(2, dni);
			stmt.setInt(3, valoracion);
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
	public void actualizarValoracion(int valoracion, String codigo, String dni) {
	
		this.openConnection();
		try {
			stmt= conex.prepareStatement(ACTUALIZARValoracion);
			stmt.setInt(1, valoracion);
			stmt.setString(2, codigo);
			stmt.setString(3, dni);
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
	public boolean comprobarValoracion(String dni, String codigo) {
		ResultSet rs= null;
		boolean hay=false;
		this.openConnection();
		
		try {
			stmt= conex.prepareStatement(COMPROBARVloracion);
			stmt.setString(1, codigo);
			stmt.setString(2, dni);
			rs= stmt.executeQuery();
			
			if (rs.next()) {
				hay=true;
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
		return hay;
	}

}
