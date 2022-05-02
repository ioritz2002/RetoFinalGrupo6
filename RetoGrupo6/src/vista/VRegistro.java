package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.InterfazCliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.toedter.calendar.JCalendar;

import clases.Cliente;
import clases.Repartidor;
import clases.Usuario;

import javax.swing.JPasswordField;

public class VRegistro extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDni;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JButton btnAtras;
	private InterfazCliente datosCliente;
	private JPasswordField txtContraseña;
	private JButton btnCrearCuenta;
	private JCalendar calendar;
	private char letra[] = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
			'H', 'L', 'C', 'K', 'E' };

	/**
	 * Create the dialog.
	 * 
	 * @param datosCliente
	 * @param b
	 * @param vPrincipal
	 */
	public VRegistro(VPrincipal vPrincipal, boolean b, InterfazCliente datosCliente) {
		super(vPrincipal);
		this.setModal(b);
		this.datosCliente = datosCliente;

		setBounds(100, 100, 713, 895);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("*DNI:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setBounds(132, 47, 77, 48);
			contentPanel.add(lblNewLabel);
		}
		{
			txtDni = new JTextField();
			txtDni.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtDni.setBounds(231, 52, 210, 39);
			contentPanel.add(txtDni);
			txtDni.setColumns(10);
		}
		{
			JLabel lblcontraseai = new JLabel("*Contrase\u00F1a:");
			lblcontraseai.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblcontraseai.setBounds(65, 121, 144, 48);
			contentPanel.add(lblcontraseai);
		}
		{
			JLabel lblemail = new JLabel("Email:");
			lblemail.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblemail.setBounds(132, 180, 77, 48);
			contentPanel.add(lblemail);
		}
		{
			JLabel lblnombre = new JLabel("*Nombre:");
			lblnombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblnombre.setBounds(113, 247, 96, 48);
			contentPanel.add(lblnombre);
		}
		{
			JLabel lblfechaNacimiento = new JLabel("*Fecha nacimiento:");
			lblfechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblfechaNacimiento.setBounds(22, 383, 187, 48);
			contentPanel.add(lblfechaNacimiento);
		}
		{
			JLabel lbldireccin = new JLabel("*Direcci\u00F3n:");
			lbldireccin.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lbldireccin.setBounds(91, 504, 118, 48);
			contentPanel.add(lbldireccin);
		}
		{
			btnAtras = new JButton("ATR\u00C1S");
			btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAtras.addActionListener(this);
			btnAtras.setBounds(113, 649, 103, 53);
			contentPanel.add(btnAtras);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Los campos con * son obligatorios");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(175, 754, 333, 31);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtEmail.setColumns(10);
			txtEmail.setBounds(231, 188, 210, 39);
			contentPanel.add(txtEmail);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtNombre.setColumns(10);
			txtNombre.setBounds(231, 258, 210, 39);
			contentPanel.add(txtNombre);
		}
		{
			txtDireccion = new JTextField();
			txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(231, 509, 210, 39);
			contentPanel.add(txtDireccion);
		}
		{
			btnCrearCuenta = new JButton("CREAR CUENTA");
			btnCrearCuenta.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnCrearCuenta.addActionListener(this);
			btnCrearCuenta.setBounds(422, 645, 187, 60);
			contentPanel.add(btnCrearCuenta);
		}

		calendar = new JCalendar();
		calendar.setBounds(236, 329, 205, 153);
		contentPanel.add(calendar);
		{
			txtContraseña = new JPasswordField();
			txtContraseña.setBounds(231, 134, 210, 35);
			contentPanel.add(txtContraseña);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}

		// Comprobar que los datos introducidos son válidos o no dependiendo de la forma
		// o cantidad que escribe el usuario y de la base de datos
		if (e.getSource().equals(btnCrearCuenta)) {
			darAlta();
		}
	}

	private String letraMayus(String palabras) {
		return palabras.toUpperCase().charAt(0) + palabras.toLowerCase().substring(1, palabras.length());

	}

	private void darAlta() {
		if (txtDni.getText().isEmpty() || txtNombre.getText().isEmpty() || txtDireccion.getText().isEmpty()
				|| txtContraseña.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Los apartados marcados con * deben estar completos", "Error",
					JOptionPane.OK_OPTION);
		} else if (dniRepetido()) {
			JOptionPane.showMessageDialog(null, "El DNI introducido ya está en la base datos", "Error",
					JOptionPane.OK_OPTION);
		} else if (txtDni.getText().length() != 9) {
			JOptionPane.showMessageDialog(null, "El DNI debe contener 9 caracteres", "Error", JOptionPane.OK_OPTION);
		} else if (!esNumero(txtDni.getText().substring(0, 8))) {
			JOptionPane.showMessageDialog(null, "Los primeros 8 caracteres del DNI deben ser números", "Error", JOptionPane.OK_OPTION);

		} else if (!hayLetra(txtDni.getText().toUpperCase().charAt(8))) {
			JOptionPane.showMessageDialog(null, "El DNI debe contener una letra en la última posición", "Error",
					JOptionPane.OK_OPTION);
		} else if (txtDni.getText().toUpperCase().charAt(8) != clacularLetraDni(txtDni.getText())) {
			JOptionPane.showMessageDialog(null, "El DNI introducido es incorrecto", "Error", JOptionPane.OK_OPTION);
		} else if (calendar.getDate().toString().isBlank()) {
			JOptionPane.showMessageDialog(null, "Se debe marcar la fecha", "Error", JOptionPane.OK_OPTION);
		} else if (txtContraseña.getText().length() > 8) {
			JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres", "Error",
					JOptionPane.OK_OPTION);
		} else if (txtEmail.getText().isBlank()) {
			nuevoCliente();
		} else if (validarEmail(txtEmail.getText())) {
			nuevoCliente();
		} else {
			JOptionPane.showMessageDialog(null, "Email mal introducido.\nEjemplo formato: andrew@example.com", "Error",
					JOptionPane.OK_OPTION);
		}
	}
	 public static boolean esNumero(String cadena) {
	        boolean resultado;
	        try {
	            Integer.parseInt(cadena);
	            resultado = true;
	        } catch (NumberFormatException excepcion) {
	            resultado = false;
	        }
	        return resultado;
	    }
	
	private boolean dniRepetido() {
		return datosCliente.comprobarDni(txtDni.getText());
	}

	private void nuevoCliente() {
		LocalDate fechaNac = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Cliente cli = new Cliente();
		cli.setDni(txtDni.getText());
		cli.setNombre(letraMayus(txtNombre.getText()));
		cli.setContraseña(txtContraseña.getText());
		cli.setFechaNacimiento(fechaNac);
		cli.setDireccion(txtDireccion.getText());
		if (txtEmail.getText().isBlank()) {
			cli.setEmail(null);
		} else {
			cli.setEmail(txtEmail.getText());
		}
		JOptionPane.showMessageDialog(null, "Cuenta creada correctamente", "Exito", JOptionPane.OK_OPTION);
		datosCliente.registroCliente(cli);
		this.dispose();

	}

	private char clacularLetraDni(String text) {
		text = text.substring(0, 8);
		int numDni = Integer.parseInt(text);
		int numLetra = numDni % 23;

		for (int i = 0; i < letra.length; i++) {
			if (numLetra == i) {
				return letra[i];
			}
		}
		return 0;
	}

	private boolean hayLetra(char c) {
		for (int i = 0; i < letra.length; i++) {
			if (letra[i] == c) {
				return true;
			}
		}
		return false;

	}

	private boolean validarEmail(String gmail) {
		// Patrón para validar el email
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		// El email a validar
		String email = gmail;

		Matcher mather = pattern.matcher(email);

		if (mather.find() == true) {
			return true;
		} else {
			return false;
		}
	}

}
