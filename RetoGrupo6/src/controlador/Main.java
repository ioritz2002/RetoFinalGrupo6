package controlador;

import modelo.ImplementacionAdministradorBD;
import modelo.ImplementacionClienteBD;
import modelo.InterfazAdministrador;
import modelo.InterfazCliente;
import vista.VPrincipal;

public class Main {

	public static void main(String[] args) {
		InterfazCliente datosCliente = new ImplementacionClienteBD();
		InterfazAdministrador datosAdmin = new ImplementacionAdministradorBD();
		
		VPrincipal principal = new VPrincipal(datosAdmin, datosCliente);
		principal.setVisible(true);
	}

}
