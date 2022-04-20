package modelo;

import clases.Usuario;

public interface InterfazAmbosUsuarios {
	//Recoge todos los datos del usuario para hacer login y utilizarlo despues
	public Usuario buscarUsuarioLogin(String dni, String contraseña);
}
