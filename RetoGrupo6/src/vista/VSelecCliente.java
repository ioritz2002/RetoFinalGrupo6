package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class VSelecCliente extends JDialog {
	private JButton btnAtras;
	private JButton btnVerHistorial;
	private JComboBox cmbxClientes;

	
	public VSelecCliente() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblSeleccionarCliente = new JLabel("Seleccionar cliente");
		lblSeleccionarCliente.setBounds(49, 73, 131, 14);
		getContentPane().add(lblSeleccionarCliente);
		
		cmbxClientes = new JComboBox();
		cmbxClientes.setBounds(176, 69, 103, 22);
		getContentPane().add(cmbxClientes);
		
		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.setBounds(30, 193, 91, 38);
		getContentPane().add(btnAtras);
		
		btnVerHistorial = new JButton("VER HISTORIAL");
		btnVerHistorial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerHistorial.setBounds(118, 128, 192, 38);
		getContentPane().add(btnVerHistorial);

	}

}
