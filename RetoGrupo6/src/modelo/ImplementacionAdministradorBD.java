package modelo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.Connection;
import clases.Cesta;
import clases.Cliente;
import clases.ListarTablaProductosMasVendidos;
import clases.Producto;
import clases.Repartidor;
import clases.Usuario;
import clases.Valora;
import java.sql.Date;

public class ImplementacionAdministradorBD implements InterfazAdministrador {
	private Connection conex;
	private PreparedStatement stmt;
	private ResourceBundle archivoConfig;

	// Conexion
	private String url;
	private String usuario;
	private String contraseña;

	// SQL
	
	private final String UPDATEProducto = "UPDATE producto SET TIPO = ?, NOMBRE = ?, STOCK = ?, PRECIO = ? WHERE COD_PRODUCTO LIKE ?";
	private final String LISTAClientes = "SELECT DNI, NOMBRE FROM cliente";
	private final String BUSCARNombreProducto = "SELECT NOMBRE FROM producto WHERE UPPER(nombre) LIKE ?";
	private final String INSERTARProducto = "INSERT INTO producto(COD_PRODUCTO, TIPO, NOMBRE, STOCK, PRECIO, DNI) VALUES(?, ? ,? , ?, ?, ?)";
	private final String BUSCARNumRep = "SELECT COUNT(*) AS total FROM producto";
	private final String NUMRepartidor = "SELECT COUNT(*) AS total FROM repartidor";
	private final String ALTARepartidor = "INSERT INTO repartidor(ID_REPARTIDOR, FECHA_ALTA, NOMBRE, APELLIDO, DNI) VALUES( ?, ?, ?, ?, ?)";
	private final String DELETEproducto = "DELETE FROM producto where COD_PRODUCTO = ?";
	private final String DELETErepartidor = "DELETE FROM repartidor where ID_REPARTIDOR = ?";
	private final String CONSULTARrepartidores = "SELECT * FROM repartidor";
	private final String CALCULOValoracion = "SELECT valora.* FROM valora";
	private final String SELECTProductos = "SELECT producto.* FROM producto";
	private final String SELECTProductosMasVendidos = "SELECT producto.*, COUNT(*) FROM producto, añade, cesta WHERE producto.COD_PRODUCTO = añade.COD_PRODUCTO AND añade.COD_CESTA = cesta.COD_CESTA AND cesta.ESTADO = 1 GROUP BY producto.COD_PRODUCTO";
	private final String SELECTproductosMasVendidos="CALL PRODUCTOS_MAS_VENDIDOS()";


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
		this.openConnection();

		try {
			stmt = conex.prepareStatement(ALTARepartidor);
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
		this.openConnection();
		try {
			stmt = conex.prepareStatement(INSERTARProducto);
			stmt.setString(1, producto.getCodProducto());
			stmt.setString(2, producto.getTipo());
			stmt.setString(3, producto.getNombre());
			stmt.setInt(4, producto.getStock());
			stmt.setDouble(5, producto.getPrecio());
			stmt.setString(6, producto.getDni());

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
		List<Cliente> listaClientes = new ArrayList<>();
		ResultSet rs = null;

		this.openConnection();

		try {
			stmt = conex.prepareStatement(LISTAClientes);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente cli = new Cliente();
				cli.setDni(rs.getString(1));
				cli.setNombre(rs.getString(2));
				listaClientes.add(cli);
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
		return listaClientes;
	}

	@Override
	public List<Producto> listarProductosMasVendidos() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public int calcularCodRepartidor() {
		ResultSet rs = null;
		int num = 0;

		this.openConnection();

		try {
			stmt = conex.prepareStatement(NUMRepartidor);

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
	public boolean compararProductos(String nombre) {
		ResultSet rs = null;
		boolean b = false;
		this.openConnection();

		try {
			stmt = conex.prepareStatement(BUSCARNombreProducto);
			stmt.setString(1, nombre.toUpperCase());
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
	public int calcularCodProducto() {
		ResultSet rs = null;
		int num = 0;

		this.openConnection();

		try {
			stmt = conex.prepareStatement(BUSCARNumRep);

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
	public List<ListarTablaProductosMasVendidos> listarProductosMasVendidosAdmin() {
		List<ListarTablaProductosMasVendidos> lista = new ArrayList<ListarTablaProductosMasVendidos>();
		ResultSet rs = null;
		ListarTablaProductosMasVendidos linea = null;
		
		openConnection();
		try {
			stmt = conex.prepareStatement(SELECTproductosMasVendidos);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				linea = new ListarTablaProductosMasVendidos();
				
				linea.setCodProducto(rs.getString(1));
				linea.setTipo(rs.getString(2));
				linea.setNombre(rs.getString(3));
				linea.setStock(rs.getInt(4));
				linea.setPrecio(rs.getDouble(5));
				linea.setContador(rs.getInt(7));
			
				lista.add(linea);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				closeConnection();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lista;
	}


}
