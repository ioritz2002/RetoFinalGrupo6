package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.ImplementacionAmbosUsuarios;
import modelo.InterfazCliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import com.toedter.calendar.JCalendar;

import clases.Cliente;
import clases.Usuario;

import javax.swing.JPasswordField;


public class VDatosCliente extends JDialog implements ActionListener{


	private final JPanel contentPanel = new JPanel();
	private JTextField txtDni;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private InterfazCliente datosCliente;
	private JTextField txtFNacimiento;
	private JPasswordField txtContraseña;
	private JButton btnModificar;
	private JButton btnAtras;

	private JButton btnBorrar;
	private Cliente cliente;

	/**
	 * Create the dialog.
	 * 
	 * @param datosCliente
	 * @param b
	 * @param vPrincipal
	 * @param cliente2
	 */
	public VDatosCliente(VPrincipal vPrincipal, boolean b, InterfazCliente datosCliente, Cliente cliente) {
		super(vPrincipal);
		this.setModal(b);
		this.datosCliente = datosCliente;
		this.cliente = cliente;


	private Cliente usuario;
	

	/**
	 * Create the dialog.
	 * @param datosCliente 
	 * @param b 
	 * @param vMenuCliente 
	 */
	public VDatosCliente(VMenuCliente vMenuCliente, boolean b, Cliente usuario, InterfazCliente datosCliente) {
		super(vMenuCliente);
		this.setModal(b);
		this.datosCliente = datosCliente;
		this.usuario = usuario;

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
			txtContraseña = new JPasswordField();
			txtContraseña.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtContraseña.setColumns(10);
			txtContraseña.setBounds(231, 121, 263, 39);
			contentPanel.add(txtContraseña);
		}
		{
			btnBorrar = new JButton("BORRAR");
			btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnBorrar.setBounds(201, 512, 187, 48);
			contentPanel.add(btnBorrar);

			btnBorrar.addActionListener(this);
		}

		noEditable();
		mostrarDatos();
	}

	private void noEditable() {
		// TODO Auto-generated method stub
		txtContraseña.setEditable(false);
		txtDireccion.setEditable(false);
		txtDni.setEditable(false);
		txtEmail.setEditable(false);
		txtFNacimiento.setEditable(false);
		txtNombre.setEditable(false);
	}


		
		cargarDatos(usuario);
	}

	private void cargarDatos(Cliente usuario) {
		txtDni.setText(usuario.getDni());
		txtContraseña.setText(usuario.getContraseña());
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
		}
	}

	private void limpiar() {
		txtDni.setText("");
		txtContraseña.setText("");
		txtDireccion.setText("");
		txtEmail.setText("");
		txtFNacimiento.setText("");
	}
	
	private void modificar() {
		usuario.setDni(txtDni.getText());
		usuario.setNombre(txtNombre.getText());
		usuario.setContraseña(txtContraseña.getText());
		usuario.setDireccion(txtDireccion.getText());
		usuario.setEmail(txtEmail.getText());
		usuario.setFechaNacimiento(LocalDate.parse(txtFNacimiento.getText()));
		if (JOptionPane.showConfirmDialog(null, "Esta seguro que quiere modificar los datos?", "confirmacion", JOptionPane.YES_NO_OPTION) == 0) {
			datosCliente.modificarDatosCliente(usuario);
			JOptionPane.showMessageDialog(null, "La cuenta se a modificado con exito", "confirmacion", JOptionPane.WARNING_MESSAGE);
		}
		

	}

	private void mostrarDatos() {
		// TODO Auto-generated method stub

		txtDireccion.setText(cliente.getDireccion());
		txtContraseña.setText(cliente.getContraseña());
		txtDni.setText(cliente.getDni());
		txtEmail.setText(cliente.getEmail());
		txtNombre.setText(cliente.getNombre());
		txtFNacimiento.setText(String.valueOf(cliente.getFechaNacimiento()));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(btnBorrar)) {

			Cliente cliente = new Cliente();
			cliente.setDni(txtDni.getText());
				if (JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres darte de baja?",
						"Selecciona una opcion", JOptionPane.YES_NO_OPTION) == 0) {
					datosCliente.darseDeBaja(txtDni.getText());
					JOptionPane.showMessageDialog(null, "Cuenta borrada",
							"Selecciona una opcion", JOptionPane.WARNING_MESSAGE);
					this.dispose();
				}
				
		}

	}

}
