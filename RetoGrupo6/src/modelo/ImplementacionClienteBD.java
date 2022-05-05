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

import clases.Añade;
import clases.Cesta;
import clases.Cliente;
import clases.Producto;
import clases.Repartidor;
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
	private final String introducirCliente = "CALL INSERT_CLIENTE( ?, ?, ?, ?, ?, ?)";
	private final String DELETEcliente = "DELETE FROM usuario WHERE DNI = ?";
	private final String CALCULOValoracion = "SELECT valora.* FROM valora";
	private final String SELECTProductos = "SELECT producto.* FROM producto";
	private final String BUSCARCodCesta = "SELECT COUNT(*) AS total FROM cesta";
	private final String CONTARrepartidores = "SELECT COUNT(*) AS total FROM repartidor";
	private final String INSERTañade = "INSERT INTO añade(COD_PRODUCTO, COD_CESTA, DNI) VALUES(?,?,?)";
	private final String INSERTcesta = "INSERT INTO cesta(COD_CESTA, IMPORTE_TOTAL, ESTADO, ID_REPARTIDOR) VALUES(?,?,?,?)";
	private final String CONSULTARestadoCesta = "SELECT COD_CESTA FROM cesta WHERE ESTADO = 0 AND COD_CESTA IN (SELECT COD_CESTA FROM añade WHERE DNI = ?)"; 
	private final String COMPRBARproductoNoDuplicado = "SELECT COD_PRODUCTO FROM añade WHERE COD_CESTA IN (SELECT COD_CESTA FROM cesta WHERE ESTADO = 0) AND DNI = ? AND COD_PRODUCTO = ?";

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
	public void cancelarCompra(String codCesta) {
		// TODO Auto-generated method stub

	}

	@Override
	public int calcularCodCesta() {
		ResultSet rs = null;
		int num = 0;

		this.openConnection();

		try {
			stmt = conex.prepareStatement(BUSCARCodCesta);

			rs = stmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt("total");
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

		return productos;
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

			while (rs.next()) {
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

	@Override
	public int calculoRepartidores() {
		int contador = 0;
		ResultSet rs = null;

		openConnection();
		try {
			stmt = conex.prepareStatement(CONTARrepartidores);
			rs = stmt.executeQuery();

			if (rs.next()) {
				contador = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO: handle exception
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

		return contador;
	}

	@Override
	public void añadirProductoAAñade(Añade añade) {
		openConnection();
		
		try {
			stmt = conex.prepareStatement(INSERTañade);
			
			stmt.setString(1, añade.getIdProducto());
			stmt.setString(2, añade.getCodCesta());
			stmt.setString(3, añade.getDni());
			
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
	public void añadirCesta(Cesta cesta) {
		openConnection();
		try {
			stmt = conex.prepareStatement(INSERTcesta);

			stmt.setString(1, cesta.getCodCesta());
			stmt.setDouble(2, cesta.getImporteTotal());
			stmt.setBoolean(3, cesta.isEstado());
			stmt.setString(4, cesta.getCodRepartidor());

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
	public String comprobarCestaActiva(String dni) {
		String codCesta = null;
		ResultSet rs = null;
		openConnection();
		try {
			stmt = conex.prepareStatement(CONSULTARestadoCesta);
			stmt.setString(1, dni);

			rs = stmt.executeQuery();

			if (rs.next()) {
				codCesta = rs.getString(1);
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
		return codCesta;
	}

	@Override
	public boolean comprobarProductoRepetido(String dni, String codProducto) {
		ResultSet rs = null;
		boolean encontrado = false;
		
		openConnection();
		
		try {
			stmt = conex.prepareStatement(COMPRBARproductoNoDuplicado);
			stmt.setString(1, dni);
			stmt.setString(2, codProducto);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				encontrado = true;
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
		return encontrado;
	}

	

}
