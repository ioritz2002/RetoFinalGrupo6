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
import clases.ListarTablaCesta;
import clases.ListarTablaProductosMasVendidos;
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
	private final String BUSCARproductos = "SELECT P.NOMBRE,P.TIPO,P.PRECIO,C.COD_CESTA FROM PRODUCTO P, CESTA C, AÑADE A WHERE P.COD_PRODUCTO = A.COD_PRODUCTO AND C.COD_CESTA=A.COD_CESTA AND C.ESTADO = 0 AND A.DNI = ?";
	private final String UPDATEcliente = "CALL MODIFICAR_CLIENTE(?,?,?,?,?,?)";
	private final String BUSCARDni = "SELECT * FROM cliente WHERE dni = ?";
	private final String INTRODUCIRCliente = "CALL INSERT_CLIENTE( ?, ?, ?, ?, ?, ?)";
	private final String DELETEcliente = "DELETE FROM usuario WHERE DNI = ?";
	private final String DELETEcompra = "DELETE FROM cesta WHERE COD_CESTA LIKE ?";
	private final String BUSCARProductosComprados = "SELECT COD_PRODUCTO, NOMBRE FROM producto WHERE COD_PRODUCTO IN (SELECT COD_PRODUCTO FROM añade WHERE DNI= ? AND COD_CESTA IN (SELECT COD_CESTA FROM cesta WHERE ESTADO=1))";
	private final String INSERTARValoracion = "INSERT INTO valora (COD_PRODUCTO, DNI, VALORACION) VALUES( ?, ?, ?)";
	private final String ACTUALIZARValoracion = "UPDATE valora SET VALORACION= ? WHERE COD_PRODUCTO= ? AND DNI= ?";
	private final String COMPROBARVloracion = "SELECT * FROM valora WHERE COD_PRODUCTO= ? AND DNI= ?";
	private final String CONSULTARrepartidores = "SELECT * FROM repartidor WHERE ACTIVO = 1";
	private final String HACERcompra = "UPDATE CESTA SET IMPORTE_TOTAL = ?,FECHA_COMPRA = ?, ESTADO = 1, ID_REPARTIDOR = ? WHERE COD_CESTA LIKE ?";
	private final String REDUCIRstock = "UPDATE producto SET stock = stock - 1 WHERE COD_PRODUCTO = ?";
	private final String COMPRBARproductoNoDuplicado = "SELECT COD_PRODUCTO FROM añade WHERE COD_CESTA IN (SELECT COD_CESTA FROM cesta WHERE ESTADO = 0) AND DNI = ? AND COD_PRODUCTO = ?";
	private final String CONSULTARestadoCesta = "SELECT COD_CESTA FROM cesta WHERE ESTADO = 0 AND COD_CESTA IN (SELECT COD_CESTA FROM añade WHERE DNI = ?)";
	private final String CONTARrepartidores = "SELECT COUNT(*) AS total FROM repartidor";
	private final String SELECTfiltroPrecio = "SELECT * FROM producto WHERE producto.PRECIO >= ? AND producto.PRECIO <= ?";
	private final String SELECTproductosMasVendidos = "CALL PRODUCTOS_MAS_VENDIDOS()";
	private final String SELECTProductos = "SELECT producto.* FROM producto";
	private final String INSERTañade = "INSERT INTO añade(COD_PRODUCTO, COD_CESTA, DNI) VALUES(?,?,?)";
	private final String INSERTcesta = "INSERT INTO cesta(COD_CESTA, IMPORTE_TOTAL, ESTADO, ID_REPARTIDOR) VALUES(?,?,?,?)";
	private final String BUSCARCodCesta = "SELECT COUNT(*) AS total FROM cesta";
	private final String CALCULOValoracion = "SELECT valora.* FROM valora";

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
	public List<ListarTablaCesta> listarCestaCompra(String dni) {
		// TODO Auto-generated method stub

		List<ListarTablaCesta> productos = new ArrayList<>();
		ResultSet rs = null;
		// Producto producto = null;
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
	public boolean cancelarCompra(String codCesta) {
		// TODO Auto-generated method stub

		this.openConnection();

		try {

			stmt = conex.prepareStatement(DELETEcompra);
			stmt.setString(1, codCesta);
			return stmt.executeUpdate() > 0 ? true: false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
	public void realizarCompra(String codCesta, Double precio, String codigo) {
		// TODO Auto-generated method stub

		openConnection();

		try {
			stmt = conex.prepareStatement(HACERcompra);

			stmt.setString(4, codCesta);

			stmt.setDouble(1, precio);
			stmt.setDate(2, Date.valueOf(LocalDate.now()));
			stmt.setString(3, codigo);

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
	public List<Producto> listarProductos(double precioMin, double precioMax) {
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
	public List<ListarTablaProductosMasVendidos> listarProductosMasVendidos() {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
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
		Producto prod = null;
		ResultSet rs = null;
		this.openConnection();

		try {
			stmt = conex.prepareStatement(BUSCARProductosComprados);
			stmt.setString(1, dni);
			rs = stmt.executeQuery();

			while (rs.next()) {
				prod = new Producto();
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
			stmt = conex.prepareStatement(INSERTARValoracion);
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
			stmt = conex.prepareStatement(ACTUALIZARValoracion);
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
		ResultSet rs = null;
		boolean hay = false;
		this.openConnection();

		try {
			stmt = conex.prepareStatement(COMPROBARVloracion);
			stmt.setString(1, codigo);
			stmt.setString(2, dni);
			rs = stmt.executeQuery();

			if (rs.next()) {
				hay = true;
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
	public List<Producto> listarProductosFiltradoPrecio(double precioMin, double precioMax) {
		List<Producto> productos = new ArrayList<Producto>();
		Producto producto = null;
		ResultSet rs = null;

		openConnection();
		try {
			stmt = conex.prepareStatement(SELECTfiltroPrecio);

			stmt.setDouble(1, precioMin);
			stmt.setDouble(2, precioMax);

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
				if (rs != null) {
					rs.close();
				}
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return productos;
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

	@Override
	public void reducirStock(String codProducto) {
		openConnection();

		try {
			stmt = conex.prepareStatement(REDUCIRstock);

			stmt.setString(1, codProducto);
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

}
