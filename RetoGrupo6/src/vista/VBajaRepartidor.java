package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class VBajaRepartidor extends JDialog {
	private JButton btnAtras;
	private JButton btnBajaRepartidor;
	private JComboBox cmbxRepartidores;

	
	public VBajaRepartidor() {
		setBounds(100, 100, 524, 300);
		getContentPane().setLayout(null);
		
		JLabel lblSeleccionarRepartidor = new JLabel("Seleccionar repartidor:");
		lblSeleccionarRepartidor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSeleccionarRepartidor.setBounds(40, 62, 170, 43);
		getContentPane().add(lblSeleccionarRepartidor);
		
		cmbxRepartidores = new JComboBox();
		cmbxRepartidores.setBounds(245, 75, 149, 22);
		getContentPane().add(cmbxRepartidores);
		
		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAtras.setBounds(58, 181, 103, 53);
		getContentPane().add(btnAtras);
		
		btnBajaRepartidor = new JButton("DAR DE BAJA REPARTIDOR");
		btnBajaRepartidor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBajaRepartidor.setBounds(184, 115, 268, 34);
		getContentPane().add(btnBajaRepartidor);

	}
}
