package modelo;

import java.util.List;

import clases.ListarTablaHistorial;
import clases.Usuario;

public interface InterfazAmbosUsuarios {
	//Recoge todos los datos del usuario para hacer login y utilizarlo despues
	public Usuario buscarUsuarioLogin(String dni);
	//Busca los datos del historial
	public List<ListarTablaHistorial> buscarCestas(String dni);
}
