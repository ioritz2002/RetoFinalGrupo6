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

/**
 * 
 * Implementacion del administrador
 * @author grupo6
 *
 */
public class ImplementacionAdministradorBD implements InterfazAdministrador {
	/**
	 * prepara la conexion
	 */
	private Connection conex;
	/**
	 * prepara la sentancia
	 */
	private PreparedStatement stmt;
	/**
	 * lee el archivo de configuracion
	 */
	private ResourceBundle archivoConfig;

	// Conexion
	/**
	 * url para conectarse a la base de datos
	 */
	private String url;
	/**
	 * usuario para conectarse a la base de datos
	 */
	private String usuario;
	/**
	 * contraseña para conectarse a la base de datos
	 */
	private String contraseña;

	// SQL
	/**
	 * selecciona todos los datos de la tabla valora
	 */
	private final String CALCULOValoracion = "SELECT valora.* FROM valora";
	/**
	 * selecciona todos los datos de la tabla productos
	 */
	private final String SELECTProductos = "SELECT producto.* FROM producto";
	/**
	 * actualiza el tipo, nombre, stock, precio de los productos que tengan el mismo
	 * codigo que el que se le pasa por parametro
	 */
	private final String UPDATEProducto = "UPDATE producto SET TIPO = ?, NOMBRE = ?, STOCK = ?, PRECIO = ? WHERE COD_PRODUCTO LIKE ?";
	/**
	 * selecciona los dnis y nombres de la tabla clientes
	 */
	private final String LISTAClientes = "SELECT DNI, NOMBRE FROM cliente";
	/**
	 * selecciona el nombre de los productos, que sean iguales al nombre pasado por
	 * parametro
	 */
	private final String BUSCARNombreProducto = "SELECT NOMBRE FROM producto WHERE UPPER(nombre) LIKE ?";
	/**
	 * inserta en la tabla producto nuevos productos con los datos que se le pasa
	 * como parametro
	 */
	private final String INSERTARProducto = "INSERT INTO producto(COD_PRODUCTO, TIPO, NOMBRE, STOCK, PRECIO, DNI) VALUES(?, ? ,? , ?, ?, ?)";
	/**
	 * cuenta el numero de productos que hay
	 */
	private final String BUSCARNumRep = "SELECT COUNT(*) AS total FROM producto";
	/**
	 * cuenta el numero de repartidores que hay
	 */
	private final String NUMRepartidor = "SELECT COUNT(*) AS total FROM repartidor";
	/**
	 * inserta en la tabla repartidor nuevos repartidores con los datos que se le
	 * pasan como parametro
	 */
	private final String ALTARepartidor = "INSERT INTO repartidor(ID_REPARTIDOR, FECHA_ALTA, NOMBRE, APELLIDO, DNI, ACTIVO) VALUES( ?, ?, ?, ?, ?, ?)";
	/**
	 * borrad de la tabla producto todos los producto que sus codigo sea igual que
	 * el que se le pasa por parametro
	 */
	private final String DELETEproducto = "DELETE FROM producto where COD_PRODUCTO = ?";
	/**
	 * actualiza el estado de activo del repartido a false al repartidor que tenga
	 * el mismo id que el que se pasa por parametro
	 */
	private final String DELETErepartidor = "UPDATE repartidor SET ACTIVO= false WHERE ID_REPARTIDOR = ?";
	/**
	 * selecciona todos los datos de la tabla repartidores
	 */
	private final String CONSULTARrepartidores = "SELECT * FROM repartidor WHERE ACTIVO = 1";
	/**
	 * llama a un procedimiento para mostrar los productos mas vendidos
	 */
	private final String SELECTproductosMasVendidos="CALL PRODUCTOS_MAS_VENDIDOS()";

	
	/**
	 * constructor en el que se agragan los datos necesarios para conectarse a la
	 * base de datos
	 */
	public ImplementacionAdministradorBD() {
		this.archivoConfig = ResourceBundle.getBundle("modelo.config");
		this.url = archivoConfig.getString("Conn");
		this.usuario = archivoConfig.getString("BDUser");
		this.contraseña = archivoConfig.getString("BDPass");
	}
	/**
	 * abre la conexion con la base de datos
	 */
	public void openConnection() {
		try {
			conex = DriverManager.getConnection(url, usuario, contraseña);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Cierra la conexion con la base de datos
	 * 
	 * @throws SQLException
	 */
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
			stmt.setBoolean(6, true);

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
