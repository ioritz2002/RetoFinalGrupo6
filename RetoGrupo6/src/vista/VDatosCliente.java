package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.InterfazAmbosUsuarios;
import modelo.InterfazCliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.toedter.calendar.JCalendar;

import clases.Cliente;
import clases.Usuario;

import javax.swing.JPasswordField;

public class VDatosCliente extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDni;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private InterfazCliente datosCliente;
	private JTextField txtFNacimiento;
	private JPasswordField txtContrase�a;
	private JButton btnModificar;
	private JButton btnAtras;
	private Cliente usuario;
	private JButton btnBorrar;
	private InterfazAmbosUsuarios datosAmbos;

	/**
	 * Create the dialog.
	 * 
	 * @param datosCliente
	 * @param b
	 * @param vMenuCliente
	 */
	public VDatosCliente(VMenuCliente vMenuCliente, boolean b, Cliente usuario, InterfazCliente datosCliente, InterfazAmbosUsuarios datosAmbos) {
		super(vMenuCliente);
		this.setModal(b);
		this.datosCliente = datosCliente;
		this.usuario = usuario;
		this.datosAmbos= datosAmbos;
		setBounds(100, 100, 713, 629);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("DNI:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setBounds(132, 47, 77, 48);
			contentPanel.add(lblNewLabel);
		}
		{
			txtDni = new JTextField();
			txtDni.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtDni.setBounds(231, 52, 263, 39);
			contentPanel.add(txtDni);
			txtDni.setColumns(10);
			txtDni.setEnabled(false);
		}
		{
			JLabel lblcontraseai = new JLabel("Contrase\u00F1a:");
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
			JLabel lblnombre = new JLabel("Nombre:");
			lblnombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblnombre.setBounds(113, 247, 96, 48);
			contentPanel.add(lblnombre);
		}
		{
			JLabel lblfechaNacimiento = new JLabel("Fecha nacimiento:");
			lblfechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblfechaNacimiento.setBounds(22, 330, 187, 48);
			contentPanel.add(lblfechaNacimiento);
		}
		{
			JLabel lbldireccin = new JLabel("Direcci\u00F3n:");
			lbldireccin.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lbldireccin.setBounds(65, 389, 118, 48);
			contentPanel.add(lbldireccin);
		}
		{
			btnAtras = new JButton("ATR\u00C1S");
			btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAtras.setBounds(36, 515, 96, 48);
			contentPanel.add(btnAtras);
			btnAtras.addActionListener(this);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtEmail.setColumns(10);
			txtEmail.setBounds(231, 188, 263, 39);
			contentPanel.add(txtEmail);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtNombre.setColumns(10);
			txtNombre.setBounds(231, 258, 263, 39);
			contentPanel.add(txtNombre);
		}
		{
			txtDireccion = new JTextField();
			txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(231, 394, 263, 39);
			contentPanel.add(txtDireccion);
		}
		{
			btnModificar = new JButton("MODIFICAR");
			btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnModificar.setBounds(451, 515, 187, 48);
			contentPanel.add(btnModificar);
			btnModificar.addActionListener(this);

		}
		{
			txtFNacimiento = new JTextField();
			txtFNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtFNacimiento.setColumns(10);
			txtFNacimiento.setBounds(231, 330, 263, 39);
			contentPanel.add(txtFNacimiento);
		}
		{
			txtContrase�a = new JPasswordField();
			txtContrase�a.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtContrase�a.setColumns(10);
			txtContrase�a.setBounds(231, 121, 263, 39);
			contentPanel.add(txtContrase�a);
		}
		{
			btnBorrar = new JButton("BORRAR");
			btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnBorrar.setBounds(201, 512, 187, 48);
			contentPanel.add(btnBorrar);
			btnBorrar.addActionListener(this);

		}

		cargarDatos(usuario);
	}

	private void cargarDatos(Cliente usuario) {
		txtDni.setText(usuario.getDni());
		txtContrase�a.setText(usuario.getContrase�a());
		txtDireccion.setText(usuario.getDireccion());
		txtEmail.setText(usuario.getEmail());
		txtFNacimiento.setText(usuario.getFechaNacimiento().toString());
		txtNombre.setText(usuario.getNombre());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnModificar)) {
			modificar();
			
		}
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
			VMenuCliente vMenuCliente = new VMenuCliente(null, true, datosCliente, usuario, datosAmbos);
			vMenuCliente.setVisible(true);
		}

		if (e.getSource().equals(btnBorrar)) {

			if (JOptionPane.showConfirmDialog(null, "�Estas seguro que quieres darte de baja?", "Selecciona una opcion",
					JOptionPane.YES_NO_OPTION) == 0) {
				datosCliente.darseDeBaja(txtDni.getText());
				JOptionPane.showMessageDialog(null, "Cuenta borrada", "Selecciona una opcion",
						JOptionPane.WARNING_MESSAGE);
				this.dispose();
				
			}

		}

	}

	private void limpiar() {
		txtDni.setText("");
		txtContrase�a.setText("");
		txtDireccion.setText("");
		txtEmail.setText("");
		txtFNacimiento.setText("");
	}

	private void modificar() {
		if (validarEmail(txtEmail.getText())) {
			usuario.setDni(txtDni.getText());
			usuario.setNombre(txtNombre.getText());
			usuario.setContrase�a(txtContrase�a.getText());
			usuario.setDireccion(txtDireccion.getText());
			usuario.setEmail(txtEmail.getText());
			usuario.setFechaNacimiento(LocalDate.parse(txtFNacimiento.getText()));
			if (JOptionPane.showConfirmDialog(null, "Esta seguro que quiere modificar los datos?", "confirmacion",
					JOptionPane.YES_NO_OPTION) == 0) {
				datosCliente.modificarDatosCliente(usuario);
				JOptionPane.showMessageDialog(null, "La cuenta se a modificado con exito", "confirmacion",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	private boolean validarEmail(String gmail) {
		// Patr�n para validar el email
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
