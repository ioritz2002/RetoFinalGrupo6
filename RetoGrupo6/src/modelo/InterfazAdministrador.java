package modelo;

import java.util.List;

import clases.Cliente;
import clases.Producto;
import clases.Repartidor;
import clases.Usuario;
import clases.Valora;


public interface InterfazAdministrador{


public interface InterfazAdministrador{

	//Dar de alta a un repartidor
	public void altaRepartidor(Repartidor repartidor);
	//Buscar cuantos repartidores hay
	public int calcularCodRepartidor();
	//Dar de baja a un repartidor
	public void bajaRepartidor(String idRepartidor);
	//Listar todos los repartidores
	public List<Repartidor> listarRepartidores();
	//Dar de alta prodcutos
	public void altaProductos(Producto producto);
	//Dar de baja a un producto
	public void bajaProducto(String codProducto);
	//Listar todos los productos
	public List<Producto> listarProductos();
	//Modificar un producto
	public void modificarProducto(Producto producto);
	//Listar todos los clientes
	public List<Cliente> listarClientes();
	//Mostrar los productos mas vendidos
	public List<Producto> listarProductosMasVendidos();

	//Buscar cuantos productos hay
	public int calcularCodProducto();
	//Compara productos para saber si el nuevo a introducir est� repetido
	public boolean compararProductos(String nombre);

	
	public List<Valora> listarValoraciones();

}
