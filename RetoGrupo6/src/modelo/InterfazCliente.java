package modelo;

import clases.Añade;
import clases.Cesta;
import clases.Cliente;
import clases.ListarTablaCesta;
import clases.ListarTablaProductosMasVendidos;
import clases.Producto;
import clases.Repartidor;
import clases.Usuario;
import clases.Valora;

import java.util.List;

/**
 * Esta es la interfaz de datos del cliente
 * @author grupo6
 * @version 1
 *
 */
public interface InterfazCliente {
	/**
	 * Comprobar si el DNI está repetido
	 * 
	 * @param dni Se pasa el dni para comprobar de que ese dni existe en la base de
	 *            datos
	 * @return Retorna true si se ha encontrado el dni, sino retorna false
	 */
	public boolean comprobarDni(String dni);

	/**
	 * Da de alta a un cliente
	 * 
	 * @param usuario Se pasa un usuario para darlo de alta en la base de datos
	 */

	public void registroCliente(Cliente usuario);

	/**
	 * Lista todos los productos en la ventana de productos
	 * 
	 * @return Retorna una lista con los productos
	 */
	public List<Producto> listarProductos();

	/**
	 * Listar los productos que estan en la cesta de compra comprobando de que esta
	 * en curso
	 * 
	 * @param dni Se pasa el dni para buscar la cesta y ver los productos de esa
	 *            cesta
	 * @return Retorna una lista con los productos de la cesta de compra
	 */
	public List<ListarTablaCesta> listarCestaCompra(String dni);

	/**
	 * Cancelar la compra borrando la cesta y toda la informacion de esta compra en
	 * las tablas cesta y añade
	 * 
	 * @param codCesta Se pasa el codigo de la cesta para encontrarla y borrarla
	 * @return Retorna true si la compra se ha cancelado, false si no.
	 */
	public boolean cancelarCompra(String codCesta);

	/**
	 * Relaizar la compra y pasar el estado de la cesta a realizado, ademas le
	 * asignamos un repartidor aleatoriamente
	 * 
	 * @param codCesta El codigo de la cesta se pasa para poder encontrar la cesta y
	 *                 cambiar el estado
	 * @param precio   El precio se pasa para poder asignarle el precio en el campo
	 *                 del precio total de la cesta
	 * @param codigo   El codigo es el codigo del repartidor que se le asigna
	 *                 aleatoriamente a la cesta
	 */
	public void realizarCompra(String codCesta, Double precio, String codigo);

	/**
	 * Darse de baja como cliente
	 * 
	 * @param dni El dni es para poder encontrar al cliente y poder darle de baja
	 */
	public void darseDeBaja(String dni);

	/**
	 * Modificar los datos del propio cliente
	 * 
	 * @param usuario Se pasa el usuario para tener los datos nuevos a la hora de
	 *                realizar la actualizacion
	 */
	// Modificar los datos del propio cliente
	public void modificarDatosCliente(Cliente usuario);

	/**
	 * Filtrar los productos mediante precio
	 * 
	 * @param precioMin Es el precio minimo desde el que se quiere comenzar a
	 *                  mostrar los productos
	 * @param precioMax Es el precio maximo hasta el que se quiere mostrar los
	 *                  productos
	 * @return Retorna una lista con los productos filtrados por el rango de precio
	 */
	public List<Producto> listarProductos(double precioMin, double precioMax);

	/**
	 * Mostrar los productos mas vendidos
	 * 
	 * @return Retorna una lista con los productos y cuantos se han vendido por cada
	 *         producto
	 */
	public List<ListarTablaProductosMasVendidos> listarProductosMasVendidos();

	/**
	 * Buscar productos ya comprados
	 * 
	 * @param dni Se pasa el dni para poder buscar los productos comprados por un
	 *            cliente en concreto
	 * @return Retorna los productos que ya han sido comprados por el cliente
	 */
	public List<Producto> listarProductosComprados(String dni);

	/**
	 * Insertar valoracion de un producto
	 * 
	 * @param codigo     Se pasa el codigo del producto para poder buscar el
	 *                   producto
	 * @param dni        Se pasa el dni para saber quien lo valora
	 * @param valoracion Se pasa la valoracion para guardarla en la base de datos
	 */
	public void insertarValoracion(String codigo, String dni, int valoracion);

	/**
	 * Comprueba que la valoracion existe y la valoracion
	 * 
	 * @param valoracion La valoracion nueva
	 * @param codigo     El codigo del producto para valorarlo
	 * @param dni        El dni de quien valora
	 */
	public void actualizarValoracion(int valoracion, String codigo, String dni);

	/**
	 * Comprueba si ya hay una valoracion para poder decidir posteriormente si
	 * actualizar o insertar una valoracion
	 * 
	 * @param dni    El dni del cliente que valora para poder mirar si ha valorado o
	 *               no
	 * @param codigo El codigo del producto para comprobar si ha valorado ese
	 *               producto en concreto
	 * @return Retorna true si ya hay una valoracion de ese producto para este
	 *         usuario y si no la hay retorna false
	 */
	public boolean comprobarValoracion(String dni, String codigo);

	/**
	 * Listar los repartidores
	 * 
	 * @return Retorna una lista de repartidores
	 */
	public List<Repartidor> listarRepartidores();

	/**
	 * Listar todas las valoraciones
	 * 
	 * @return Retorna una lista con todas las valoraciones
	 */
	public List<Valora> listarValoraciones();

	/**
	 * Añadir los productos seleccionados a la tabla añade
	 * 
	 * @param añade Se le passa los datos a introducir en la tabla añade
	 */
	public void añadirProductoAAñade(Añade añade);

	/**
	 * Añadir la cesta a la tabla cesta
	 * 
	 * @param cesta Se pasa la cesta para introducir los datos de la cesta
	 */
	public void añadirCesta(Cesta cesta);

	/**
	 * Filtrar los productos mediante precio
	 * 
	 * @param precioMin Este es el precio minimo desde el cual se comienza a listar
	 * @param precioMax Este es el precio maximo hasta el cual se va a listar
	 * @return Retorna una lista con los productos entre esos precios
	 */
	public List<Producto> listarProductosFiltradoPrecio(double precioMin, double precioMax);

	/**
	 * Calcular el codigo de la cesta
	 * 
	 * @return Retorna cuantas cestas hay para posteriormente generar un codigo
	 */
	public int calcularCodCesta();

	/**
	 * Este metodo sirve para saber cuantos repartidores hay
	 * @return Retorna el numero de repartidores que hay
	 */
	public int calculoRepartidores();

	/**
	 * Comprobar las cestas que estan activas para un cliente
	 * @param dni El dni para buscar el cliente
	 * @return Retorna si el estado de la esta es en curso o si esta finalizado
	 */
	public String comprobarCestaActiva(String dni);

	/**
	 * Comprobar que el producto no este repetido en una cesta
	 * @param dni El dni para comprobar de que en la cesta no haya el mismo producto para el mismo usuario
	 * @param codProducto El codigo de producto para comprobar de que no haya el mismo producto para el mismo usuario
	 * @return Retorna true si el producto esta repetido y false si no esta repetido
	 */
	public boolean comprobarProductoRepetido(String dni, String codProducto);

	/**
	 * Reducir stock cuando se añada al carrito
	 * @param codProducto Se pasa el codigo del producto para reducir el stock a un determinado producto
	 */
	public void reducirStock(String codProducto);

}
