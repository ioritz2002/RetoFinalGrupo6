package modelo;

import clases.Añade;
import clases.Cesta;
import clases.Cliente;
import clases.ListarTablaCesta;
import clases.Producto;
import clases.Repartidor;
import clases.Usuario;
import clases.Valora;

import java.util.List;

public interface InterfazCliente{
	//Comprobar si el DNI está repetido
	public boolean comprobarDni(String dni);
	//Da de alta a un cliente
	public void registroCliente(Cliente usuario);
	//Lista todos los productos en la ventana de productos
	public List<Producto> listarProductos();
	//Lista la columna de valoraciones de cada uno de los productos en la ventana de productos
	public List<Valora> listarValoracionesProductos();
	//Listar los productos que estan en la cesta de compra comprobando de que esta en curso
	public List<ListarTablaCesta> listarCestaCompra(String dni);
	//Añadir los productos seleccionados a la tabla añade
	public void añadirProductoACesta(Producto producto, String dni);
	//Cancelar la compra borrando la cesta y toda la informacion de esta compra en las tablas cesta y añade
	public void cancelarCompra(String codCesta);
	//Realizar la compra y pasar el estado de la cesta a realizado, ademas le asignamos un repartidor aleatoriamente
	public void realizarCompra(String codCesta,Double precio,String codigo);
	//Valorar un producto previamente comprado
	public void darValoracion(String codProducto, String dni);
	//Comprueba de que el producto existe en la tabla producto
	public String productoExiste(String nombreProducto);
	//Comprobar de que el producto ha sido previamente comprado para posteriormente en otro metod valorar el producto
	public boolean productoComprado(String codProducto, String dni);
	//Listar todas las cestas del cliente
	public List<Cesta> verHistorialCompras(String dni);
	//Darse de baja como cliente
	public void darseDeBaja(String dni);
	//Modificar los datos del propio cliente
	public void modificarDatosCliente(Cliente usuario);
	//Filtrar productos por nombre
	public List<Producto> listarProductosFiltradoNombre(String nombre);
	//Filtrar produtos por tipo
	public List<Producto> listarProductosPorTipo(String tipo);
	//Filtrar productos mediante precio
	public List<Producto> listarProductos(double precioMin, double precioMax);
	//Mostrar los productos mas vendidos
	public List<Producto> listarProductosMasVendidos();
	//Buscar productos ya comprados
	public List<Producto> listarProductosComprados(String dni);
	//Insertar valoracion de un producto
	public void insertarValoracion(String codigo, String dni, int valoracion);
	//Comprobar actualizar una valoración
	public void actualizarValoracion(int valoracion, String codigo, String dni );
	//Comprobar si ya hay una valoracion
	public boolean comprobarValoracion(String dni, String codigo);
	//lista de rapartidores
	public List<Repartidor> listarRepartidores();
	
}
