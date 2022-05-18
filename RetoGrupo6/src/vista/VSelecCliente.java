package vista;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import clases.Cliente;
import modelo.InterfazAdministrador;
import modelo.InterfazAmbosUsuarios;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ventana para que el administrador seleccione el cliente del cual quiere saber su historial
 * @author 1grupo6
 * @version 1
 */
public class VSelecCliente extends JDialog implements ActionListener {
	/**
	 * Al pulsarlo cerrará la ventana actual y volverá a la anterior
	 */
	private JButton btnAtras;
	/**
	 * Al pulsarlo se abrirá la ventana del historial del cliente seleccionado en el comboBox
	 */
	private JButton btnVerHistorial;
	/**
	 * En el se guardarán los clientes que hayan comprado algo
	 */
	private JComboBox<String> cmbxClientes;
	/**
	 * La interfaz se igualará a la interfaz datosAdmin que viene por parámetro para poder utilizarla fuera del constructor
	 */
	private InterfazAdministrador datosAdmin;
	/**
	 * La interfaz se igualará a la interfaz datosAmbos que viene por parámetro para poder utilizarla fuera del constructor
	 */
	private InterfazAmbosUsuarios datosAmbos;

	/**
	 * 
	 * @param menuAdmin Es la ventana de la que viene
	 * @param b Indica si el modal esta activo o no en la ventana
	 * @param datosAdmin Es la interfaz que se va a utilizar en caso de necesitar la base de datos
	 * @param datosAmbos Es la interfaz que se va a utilizar en caso de necesitar la base de datos
	 */
	public VSelecCliente(VMenuAdministrador menuAdmin, boolean b, InterfazAdministrador datosAdmin,
			InterfazAmbosUsuarios datosAmbos) {
		super(menuAdmin);
		this.setModal(b);
		this.datosAmbos = datosAmbos;
		this.datosAdmin = datosAdmin;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblSeleccionarCliente = new JLabel("Seleccionar cliente");
		lblSeleccionarCliente.setBounds(49, 73, 131, 14);
		getContentPane().add(lblSeleccionarCliente);

		cmbxClientes = new JComboBox<String>();
		cmbxClientes.setBounds(176, 69, 103, 22);
		cargarCombo();
		getContentPane().add(cmbxClientes);

		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.addActionListener(this);
		btnAtras.setBounds(30, 193, 91, 38);
		getContentPane().add(btnAtras);

		btnVerHistorial = new JButton("VER HISTORIAL");
		btnVerHistorial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerHistorial.addActionListener(this);
		btnVerHistorial.setBounds(118, 128, 192, 38);
		getContentPane().add(btnVerHistorial);

	}

	/**
	 * Sirve para añadir los datos requeridos en el comboBox
	 */
	private void cargarCombo() {
		List<Cliente> listaClientes = datosAdmin.listarClientes();
		if (!listaClientes.isEmpty()) {
			for (int i = 0; i < listaClientes.size(); i++) {
				cmbxClientes.addItem(listaClientes.get(i).getDni() + " " + listaClientes.get(i).getNombre());
			}
			cmbxClientes.setSelectedIndex(-1);
		}
	}

	/**
	 *  Permite que al pulsar algún elemento de la ventana haga una acción concreta indicada en el método
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}
		if (e.getSource().equals(btnVerHistorial)) {

			if (cmbxClientes.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun cliente", "Error",
						JOptionPane.OK_OPTION);
			} else {
				String dni = cmbxClientes.getSelectedItem().toString().substring(0, cmbxClientes.getSelectedItem().toString().indexOf(" "));
				VHistorialCompras historial = new VHistorialCompras(this, true, datosAdmin, dni, datosAmbos);
				historial.setVisible(true);
			}
		}
	}
}
