package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Repartidor;
import clases.Usuario;
import modelo.InterfazAdministrador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class VAltaRepartidor extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JButton btnDarAlta;
	private JButton btnAtras;
	private InterfazAdministrador datosAdmin;
	private Usuario us;

	/**
	 * Create the dialog.
	 * 
	 * @param b
	 */
	public VAltaRepartidor(VMenuAdministrador menuAdmin, boolean b, InterfazAdministrador datosAdmin, Usuario usuario) {
		super(menuAdmin);
		this.setModal(b);
		this.datosAdmin = datosAdmin;
		us = usuario;

		setBounds(100, 100, 566, 436);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Id:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setBounds(150, 86, 51, 25);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNombre.setBounds(113, 145, 88, 25);
			contentPanel.add(lblNombre);
		}
		{
			JLabel lblApellido = new JLabel("Apellido:");
			lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblApellido.setBounds(107, 203, 94, 25);
			contentPanel.add(lblApellido);
		}
		{
			txtId = new JTextField();
			txtId.setBounds(257, 88, 174, 28);
			contentPanel.add(txtId);
			txtId.setEditable(false);
			txtId.setText(calcularId());

			txtId.setColumns(10);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(257, 146, 174, 31);
			contentPanel.add(txtNombre);
		}
		{
			txtApellido = new JTextField();
			txtApellido.setColumns(10);
			txtApellido.setBounds(257, 204, 174, 31);
			contentPanel.add(txtApellido);
		}
		{
			btnAtras = new JButton("ATR\u00C1S");
			btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAtras.setBounds(83, 298, 101, 47);
			btnAtras.addActionListener(this);
			contentPanel.add(btnAtras);
		}
		{
			btnDarAlta = new JButton("DAR DE ALTA REPARTIDOR");
			btnDarAlta.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnDarAlta.setBounds(237, 293, 289, 56);
			btnDarAlta.addActionListener(this);
			contentPanel.add(btnDarAlta);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}
		if (e.getSource().equals(btnDarAlta)) {
			if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El nombre y el apellido no pueden estar vacios", "Error",
						JOptionPane.OK_OPTION);
			} else {
				nuevoCodRepartidor();
			}
		}
	}

	// Hace que los nombres y apellidos sean escritos correctamente
	private String letraMayusMinus(String palabras) {
		return palabras.toUpperCase().charAt(0) + palabras.toLowerCase().substring(1, palabras.length());

	}

	// Cacular un nuevo codigo para el repartidor
	private void nuevoCodRepartidor() {
		String cod = calcularId();
		String dni = us.getDni();
		// String dni= us.getDni();
		// Cargar datos en el repartidor
		Repartidor rep = new Repartidor();
		rep.setIdRepartidor(cod);
		rep.setNombre(letraMayusMinus(txtNombre.getText()));
		rep.setApellido(letraMayusMinus(txtApellido.getText()));
		rep.setFechaAlta(LocalDate.now());
		rep.setDniUsuario(dni);

		JOptionPane.showMessageDialog(null, "Los datos se han introducido correctamente", "Correcto",
				JOptionPane.OK_OPTION);

		datosAdmin.altaRepartidor(rep);
		this.dispose();

	}

	// Calcula el nuevo Id del repartidor
	private String calcularId() {
		int cant = datosAdmin.calcularCodRepartidor() + 1;
		String cod;
		cant = 0000 + cant;
		if (cant >= 1000) {
			cod = "RP-" + cant;
		} else if (cant >= 100) {
			cod = "RP-0" + cant;
		} else if (cant >= 10) {
			cod = "RP-00" + cant;
		} else {
			cod = "RP-000" + cant;
		}
		return cod;
	}

}
