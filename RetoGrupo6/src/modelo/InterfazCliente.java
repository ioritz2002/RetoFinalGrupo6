package modelo;

import clases.A�ade;
import clases.Cesta;
import clases.Cliente;
import clases.ListarTablaCesta;
import clases.ListarTablaProductosMasVendidos;
import clases.Producto;
import clases.Repartidor;
import clases.Usuario;
import clases.Valora;

import java.util.List;

public interface InterfazCliente {
	// Comprobar si el DNI est� repetido
	public boolean comprobarDni(String dni);

	// Da de alta a un cliente
	public void registroCliente(Cliente usuario);

	// Lista todos los productos en la ventana de productos
	public List<Producto> listarProductos();


	// Listar los productos que estan en la cesta de compra comprobando de que esta
	// en curso
	public List<ListarTablaCesta> listarCestaCompra(String dni);



	// Cancelar la compra borrando la cesta y toda la informacion de esta compra en
	// las tablas cesta y a�ade
	public void cancelarCompra(String codCesta);

	// Realizar la compra y pasar el estado de la cesta a realizado, ademas le
	// asignamos un repartidor aleatoriamente
	public void realizarCompra(String codCesta, Double precio, String codigo);

	// Darse de baja como cliente
	public void darseDeBaja(String dni);

	// Modificar los datos del propio cliente
	public void modificarDatosCliente(Cliente usuario);

	// Filtrar productos mediante precio
	public List<Producto> listarProductos(double precioMin, double precioMax);

	// Mostrar los productos mas vendidos
	public List<ListarTablaProductosMasVendidos> listarProductosMasVendidos();

	// Buscar productos ya comprados
	public List<Producto> listarProductosComprados(String dni);

	// Insertar valoracion de un producto
	public void insertarValoracion(String codigo, String dni, int valoracion);

	// Comprobar actualizar una valoraci�n
	public void actualizarValoracion(int valoracion, String codigo, String dni);

	// Comprobar si ya hay una valoracion
	public boolean comprobarValoracion(String dni, String codigo);

	// lista de rapartidores
	public List<Repartidor> listarRepartidores();

	// Listar todas las valoraciones
	public List<Valora> listarValoraciones();

	// A�adir los productos seleccionados a la tabla a�ade
	public void a�adirProductoAA�ade(A�ade a�ade);

	// A�adir la cesta a la tabla cesta
	public void a�adirCesta(Cesta cesta);

	// Filtrar productos mediante precio
	public List<Producto> listarProductosFiltradoPrecio(double precioMin, double precioMax);

	// Calcular el codigo de la cesta
	public int calcularCodCesta();

	// Saber cuantos repartidores hay
	public int calculoRepartidores();

	// Comprobar cestas activas por cliente
	public String comprobarCestaActiva(String dni);

	// Comprobar que el producto no este repetido en una cesta
	public boolean comprobarProductoRepetido(String dni, String codProducto);

	// Reducir stock cuando se a�ada al carrito
	public void reducirStock(String codProducto);

}
