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
	 * @param repartidor El repartidor que se quiere dar de alta
	 * @return Retorna true si se ha dado de alta, false si no
	 */
	public boolean altaRepartidor(Repartidor repartidor);

	/**
	 * Dar de baja a un repartidor
	 * 
	 * @param identificador del repartidor
	 * @return Retorna true si se ha dado de baja, false si no.
	 */
	public boolean bajaRepartidor(String idRepartidor);
	/**
	 * Listar todos los repartidores
	 * 
	 * @return lista con todos los repartidores
	 */
	
	public List<Repartidor> listarRepartidores();

	/**
	 * Dar de alta prodcutos
	 * 
	 * @param producto El producto a dar de alta
	 */
	public boolean altaProductos(Producto producto);

	/**
	 * Dar de baja a un producto
	 * 
	 * @param codigo del producto
	 */
	public boolean bajaProducto(String codProducto);

	/**
	 * Listar todos los productos
	 * 
	 * @return lista con los productos
	 */
	public List<Producto> listarProductos();

	/**
	 * Modificar un producto
	 * 
	 * @param producto Producto a modificar
	 */
	public boolean modificarProducto(Producto producto);

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
	 * @param nombre El nombre que se quiere comprobar
	 * @return Retorna un true si el nombre ya existe y un false si no lo es 
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
