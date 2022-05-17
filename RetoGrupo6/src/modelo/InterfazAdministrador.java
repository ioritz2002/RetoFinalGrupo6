package modelo;

import java.util.List;

import clases.Cliente;
import clases.ListarTablaProductosMasVendidos;
import clases.Producto;
import clases.Repartidor;
import clases.Usuario;
import clases.Valora;

public interface InterfazAdministrador {
	// Dar de alta a un repartidor
	public boolean altaRepartidor(Repartidor repartidor);

	// Dar de baja a un repartidor
	public boolean bajaRepartidor(String idRepartidor);

	// Listar todos los repartidores
	public List<Repartidor> listarRepartidores();

	// Dar de alta prodcutos
	public void altaProductos(Producto producto);

	// Dar de baja a un producto
	public void bajaProducto(String codProducto);

	// Listar todos los productos
	public List<Producto> listarProductos();

	// Modificar un producto
	public boolean modificarProducto(Producto producto);

	// Listar todos los clientes
	public List<Cliente> listarClientes();

	// Listar todas las valoraciones
	public List<Valora> listarValoraciones();

	// Buscar cuantos repartidores hay
	public int calcularCodRepartidor();

	// Compara productos para saber si el nuevo a introducir está repetido
	public boolean compararProductos(String nombre);

	// Buscar cuantos productos hay
	public int calcularCodProducto();

	// Mostrar los productos mas vendidos
	public List<ListarTablaProductosMasVendidos> listarProductosMasVendidosAdmin();
}
