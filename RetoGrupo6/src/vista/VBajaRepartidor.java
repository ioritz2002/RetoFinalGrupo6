package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import clases.Repartidor;
import modelo.InterfazAdministrador;
import modelo.InterfazCliente;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class VBajaRepartidor extends JDialog implements ActionListener {
	private JButton btnAtras;
	private JButton btnBajaRepartidor;
	private JComboBox<String> cmbxRepartidores;
	private InterfazAdministrador datosAdministrador;
	private Repartidor repartidor;
	private List<Repartidor> repartidores;

	public VBajaRepartidor(VMenuAdministrador vMenuAdministrador, boolean b, InterfazAdministrador datosAdministrador) {

		super(vMenuAdministrador);
		this.setModal(b);
		this.datosAdministrador = datosAdministrador;
		// this.repartidor = repartidor;

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

		cargarRepartidores(datosAdministrador);

		btnBajaRepartidor.addActionListener(this);
		btnAtras.addActionListener(this);

	}

	private void cargarRepartidores(InterfazAdministrador datosAdministrador) {
		// TODO Auto-generated method stub

		repartidores = datosAdministrador.listarRepartidores();

		if (!repartidores.isEmpty()) {
			for (Repartidor repartidor : repartidores) {
				cmbxRepartidores.addItem(repartidor.getIdRepartidor() + " " + repartidor.getNombre());
			}
		}
		cmbxRepartidores.setSelectedIndex(-1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(btnBajaRepartidor)) {
			String cadena = (String) cmbxRepartidores.getSelectedItem();
			int pos = cadena.indexOf(" ");
			String id = cadena.substring(0, pos);
			Repartidor repartidor = new Repartidor();
			repartidor.setIdRepartidor(id);

			if (JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres dar de baja a este repartidor ?",
					"Selecciona una opcion", JOptionPane.YES_NO_OPTION) == 0) {
				datosAdministrador.bajaRepartidor(id);
				JOptionPane.showMessageDialog(null, "Repartidor borrado", "Selecciona una opcion",
						JOptionPane.WARNING_MESSAGE);
				this.dispose();
			}
		}
		if(e.getSource().equals(btnAtras)) {
			this.dispose();
		}

	}
}
