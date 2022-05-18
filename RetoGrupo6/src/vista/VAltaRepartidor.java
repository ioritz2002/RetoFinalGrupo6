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

/**
 * En esta ventana se crea un nuevo repartidor
 * 
 * @author grupo6
 * @version 1
 * @since 09/05/2022
 */

public class VAltaRepartidor extends JDialog implements ActionListener {

	/**
	 * En el, se mostrará el un Id generado automaticamente perteneciente al nuevo repartidor que se vaya a crear.
	 */
	private JTextField txtId;
	/**
	 * En el, el usuario escribirá el nombre del nuevo repartidor
	 */
	private JTextField txtNombre;
	/**
	 * En el, el usuario escribirá el apellido del nuevo repartidor
	 */
	private JTextField txtApellido;
	/**
	 * Si el usuario pulsa el botón los datos introducidos (si hay) se guardarán en
	 * la base datos
	 */
	private JButton btnDarAlta;
	/**
	 * Al pulsarlo volverá a la ventana de la que ha venido.
	 */
	private JButton btnAtras;
	/**
	 * Se utiliza para guardar los datosAdmin que llegan como parámetro
	 */
	private InterfazAdministrador datosAdmin;
	/**
	 * Se utiliza para guardar los datos que llegan por parámetro del usuario que ha
	 * inciado la sesión
	 */
	private Usuario us;

	/**
	 * @author grupo6
	 * @param menuAdmin  Es la ventana de la que viene.
	 * @param b          Es lo que indica si la ventana es modal o no. Si es true
	 *                   entonces será modal.
	 * @param datosAdmin Es la interfaz que contiene los métodos que puede utilizar
	 *                   el administrador.
	 * @param usuario    Son los datos del usuario que ha iniciado sesión.
	 * @since 09/05/2022
	 */
	public VAltaRepartidor(VMenuAdministrador menuAdmin, boolean b, InterfazAdministrador datosAdmin, Usuario usuario) {
		super(menuAdmin);
		this.setModal(b);
		this.datosAdmin = datosAdmin;
		us = usuario;

		JPanel contentPanel = new JPanel();
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
						JOptionPane.ERROR_MESSAGE);
			} else {
				nuevoCodRepartidor();
			}
		}
	}

	// Hace que los nombres y apellidos sean escritos correctamente
	/**
	 * Metodo para transformar la primera letra de una palabra en mayúscula y el
	 * resto en minúscula
	 * @param palabras
	 * @return String Devuelve la palabra enviada bien escrita
	 */
	private String letraMayusMinus(String palabras) {
		return palabras.toUpperCase().charAt(0) + palabras.toLowerCase().substring(1, palabras.length());

	}

	// Cacular un nuevo codigo para el repartidor
	/**
	 * Introduce todos los datos requeridos para la creación del repartidor en un objeto repartidor y se mandan por parámetro a la implementación.
	 */
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
				JOptionPane.INFORMATION_MESSAGE);

		datosAdmin.altaRepartidor(rep);
		this.dispose();

	}

	// Calcula el nuevo Id del repartidor
	/**
	 * Metodo para generar una Id según el número de repartidores que haya
	 * 
	 * @return String Devuelve un código para el repartidor
	 * @since 09/05/2022
	 */
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
