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
	 * @param repartidor El repartidor al que se le va a dar de alta
	 * @return Retorna true en el caso de que se haya dado de alta al repartidor, false en el caso de que no.
	 */
	public boolean altaRepartidor(Repartidor repartidor);

	/**
	 * Dar de baja a un repartidor
	 * 
	 * @param identificador del repartidor
	 * @return Retorna true en el caso de que se haya dado de baja al repartidor, false en el caso de que no.
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
	 * @param producto El producto al que se le da de alta
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
	 * @param producto El producto  que se quiere modificar
	 * @return Retorna true si se ha actualizado algun producto, Retorna false si no se ha actulizado nada
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
	 * @param nombre Es el nombre que se quiere comprobar
	 * @return Retorna true si esta repetido, en el caso de que no este retorna false.
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
