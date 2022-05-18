package modelo;

import java.util.List;

import clases.Cliente;
import clases.ListarTablaProductosMasVendidos;
import clases.Producto;
import clases.Repartidor;
import clases.Usuario;
import clases.Valora;

public interface InterfazAdministrador {
	/**
	 * Dar de alta a un repartidor
	 * 
	 * @param repartidor
	 */
	public void altaRepartidor(Repartidor repartidor);

	/**
	 * Dar de baja a un repartidor
	 * 
	 * @param identificador del repartidor
	 */
	public void bajaRepartidor(String idRepartidor);
	/**
	 * Listar todos los repartidores
	 * 
	 * @return lista con todos los repartidores
	 */
	
	public List<Repartidor> listarRepartidores();

	/**
	 * Dar de alta prodcutos
	 * 
	 * @param producto
	 */
	public void altaProductos(Producto producto);

	/**
	 * Dar de baja a un producto
	 * 
	 * @param codigo del producto
	 */
	public void bajaProducto(String codProducto);

	/**
	 * Listar todos los productos
	 * 
	 * @return lista con los productos
	 */
	public List<Producto> listarProductos();

	/**
	 * Modificar un producto
	 * 
	 * @param producto
	 */
	public void modificarProducto(Producto producto);

	/**
	 * Listar todos los clientes
	 * 
	 * @return lista con los clientes
	 */
	public List<Cliente> listarClientes();

	/**
	 * Listar todas las valoraciones de un producto
	 * 
	 * @return lista con las valoraciones de un producto
	 */
	public List<Valora> listarValoraciones();
	/**
	 * Buscar cuantos repartidores hay
	 * 
	 * @return el numero de repartidores
	 */
	public int calcularCodRepartidor();
	/**
	 * Compara productos para saber si el nuevo a introducir está repetido
	 * 
	 * @param nombre
	 * @return
	 */
	public boolean compararProductos(String nombre);
	/**
	 * Buscar cuantos productos hay
	 * 
	 * @return el numero de codigos que hay
	 */
	public int calcularCodProducto();

	/**
	 * Mostrar los productos mas vendidos
	 * 
	 * @return lista con los productos mas vendidos
	 */
	public List<ListarTablaProductosMasVendidos> listarProductosMasVendidosAdmin();
}
