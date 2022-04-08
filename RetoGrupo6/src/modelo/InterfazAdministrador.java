package modelo;

import java.util.List;

import clases.Cliente;
import clases.Producto;
import clases.Repartidor;
import clases.Usuario;

public interface InterfazAdministrador {
	//Recoge todos los datos del usuario para hacer login y utilizarlo despues
	public Usuario buscarUsuarioLogin(String dni, String contraseña);
	//Dar de alta a un repartidor
	public void altaRepartidor(Repartidor repartidor);
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
	public void modificarProducto(String codProducto);
	//Listar todos los clientes
	public List<Cliente> listarClientes();
	//Mostrar los productos mas vendidos
	public List<Producto> listarProductosMasVendidos();
}
