package vista;



import javax.swing.JDialog;
import javax.swing.JLabel;

import clases.Cliente;
import modelo.InterfazAdministrador;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VSelecCliente extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAtras;
	private JButton btnVerHistorial;
	private JComboBox<String> cmbxClientes;
	private InterfazAdministrador datosAdmin;

	public VSelecCliente(VMenuAdministrador menuAdmin, boolean b, InterfazAdministrador datosAdmin) {
		super(menuAdmin);
		this.setModal(b);

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

	private void cargarCombo() {
		List<Cliente> listaClientes = datosAdmin.listarClientes();
		if (!listaClientes.isEmpty()) {
			for (int i = 0; i < listaClientes.size(); i++) {
				cmbxClientes.addItem(listaClientes.get(i).getDni() + " " + listaClientes.get(i).getNombre());
			}
			cmbxClientes.setSelectedIndex(-1);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}
		if (e.getSource().equals(btnVerHistorial)) {
			String dni= cmbxClientes.getSelectedItem().toString().substring(0, 9);
			VHistorialCompras historial= new VHistorialCompras(this, true, datosAdmin, dni);
			historial.setVisible(true);
		}

	}

}
