package controlador;

import clases.Usuario;
import modelo.ImplementacionAdministradorBD;
import modelo.ImplementacionAmbosUsuarios;
import modelo.ImplementacionClienteBD;
import modelo.InterfazAdministrador;
import modelo.InterfazAmbosUsuarios;
import modelo.InterfazCliente;
import vista.VPrincipal;

public class Main {

	public static void main(String[] args) {
		InterfazCliente datosCliente = new ImplementacionClienteBD();
		InterfazAdministrador datosAdmin = new ImplementacionAdministradorBD();
		InterfazAmbosUsuarios datosAmbos = new ImplementacionAmbosUsuarios();
		
		VPrincipal principal = new VPrincipal(datosAdmin, datosCliente,datosAmbos);
		principal.setVisible(true);
		
		
		
	}

}
