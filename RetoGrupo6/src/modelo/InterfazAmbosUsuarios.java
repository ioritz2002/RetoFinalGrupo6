package modelo;

import java.util.List;

import clases.Producto;
import clases.Usuario;
import clases.Valora;

public interface InterfazAmbosUsuarios {
	// Recoge todos los datos del usuario para hacer login y utilizarlo despues
	public Usuario buscarUsuarioLogin(String dni);

	
}
