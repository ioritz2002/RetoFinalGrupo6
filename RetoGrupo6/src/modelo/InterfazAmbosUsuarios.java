package modelo;

import java.util.List;

import clases.ListarTablaHistorial;
import clases.Usuario;

/**
 * Es la interfaz de datos de ambos usuarios
 * @author grupo6
 * @version 1
 */
public interface InterfazAmbosUsuarios {
	/**
	 * Recoge todos los datos del usuario para hacer login y utilizarlo despues
	 * @param dni Es el DNI del usuario que se quiere buscar
	 * @return Devuelve todos los datos del usuario buscado
	 */
	public Usuario buscarUsuarioLogin(String dni);
	/**
	 * Busca los datos del historial de un determinado cliente
	 * @param dni Es el DNI del cliente del que se quiere saber el historial
	 * @return Devuelve una lista con los datos que se van a mostrar en la tabla
	 */
	public List<ListarTablaHistorial> buscarCestas(String dni);
}
