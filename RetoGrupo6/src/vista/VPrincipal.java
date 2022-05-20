package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Cliente;
import clases.Usuario;
import excepciones.LoginIncorrectoException;
import modelo.InterfazAdministrador;
import modelo.InterfazAmbosUsuarios;
import modelo.InterfazCliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

/**
 * Es la primera ventana de la aplicación, en ella se podrá inicar sesión y crear una cuenta
 * @author 1dam
 * @version 1
 */
public class VPrincipal extends JFrame implements ActionListener {

	/**
	 * En el el ususario escribirá su DNI para identificarse
	 */
	private JTextField txtDni;
	/**
	 * Al pulsar el botón se abrirá la ventana VRegistro para crear la nueva cuenta
	 */
	private JButton btnCrearCuenta;
	/**
	 * Al pulsarlo se comprobarán los datos introducidos en los txt para comfirmar la existencia del usuario, si existe y dependiendo que sea (administrador o cliente) irá a VMenuCliente o VMenuAdministrador.
	 */
	private JButton btnIniciarSesion;
	/**
	 * Se igualará a la interfaz datosAdmin que viene por parámetro para poder utilizarlo fuera del constructor
	 */
	private InterfazAdministrador datosAdmin;
	/**
	 * Se igualará a la interfaz datosCliente que viene por parámetro para poder utilizarlo fuera del constructor
	 */
	private InterfazCliente datosCliente;
	/**
	 * Se igualará a la interfaz datosAmbos que viene por parámetro para poder utilizarlo fuera del constructor
	 */
	private InterfazAmbosUsuarios datosAmbos;
	/**
	 * En el el usuario escribirá su contraseña para iniciar sesión
	 */
	private JPasswordField txtContraseña;

	/**
	 * 
	 * @param datosAdmin Es una de las interfaces que se usarán en caso de necesitar la base de datos
	 * @param datosCliente Es una de las interfaces que se usarán en caso de necesitar la base de datos
	 * @param datosAmbos Es una de las interfaces que se usarán en caso de necesitar la base de datos
	 */
	public VPrincipal(InterfazAdministrador datosAdmin, InterfazCliente datosCliente,
			InterfazAmbosUsuarios datosAmbos) {
		this.datosAdmin = datosAdmin;
		this.datosCliente = datosCliente;
		this.datosAmbos = datosAmbos;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 427);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDni.setBounds(65, 69, 140, 50);
		contentPane.add(lblDni);

		txtDni = new JTextField();
		txtDni.setBounds(200, 80, 267, 36);
		contentPane.add(txtDni);
		txtDni.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContrasea.setBounds(65, 167, 140, 50);
		contentPane.add(lblContrasea);

		btnIniciarSesion = new JButton("INICIAR SESION");
		btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIniciarSesion.setBounds(89, 275, 171, 67);
		contentPane.add(btnIniciarSesion);
		btnIniciarSesion.addActionListener(this);

		btnCrearCuenta = new JButton("CREAR CUENTA");
		btnCrearCuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCrearCuenta.setBounds(382, 275, 171, 67);
		contentPane.add(btnCrearCuenta);
		btnCrearCuenta.addActionListener(this);

		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(200, 181, 267, 31);
		contentPane.add(txtContraseña);
	}

	/**
	 * Permite que al pulsar algún elemento de la ventana haga una acción concreta indicada en el método
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnCrearCuenta)) {
			VRegistro venRegistro = new VRegistro(this, true, datosCliente);
			venRegistro.setVisible(true);
		}
		if (e.getSource().equals(btnIniciarSesion)) {
			iniciarSesion();

		}

	}
	
	/**
	 * Método para pasar los datos requeridos para iniciar sesión a la implementación
	 */
	private void iniciarSesion() {
		String dni = txtDni.getText();
		String contraseña = txtContraseña.getText();
		Usuario usuario = null;

		if (dni.equalsIgnoreCase("") || contraseña.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Error, tiene que rellenar ambos campos", "Error",
					JOptionPane.ERROR_MESSAGE);
			limpiar();
		} else {

			usuario = datosAmbos.buscarUsuarioLogin(dni);
			try {
				comprobarLogin(dni, contraseña, usuario);
			} catch (LoginIncorrectoException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error, DNI o contraseña incorrecto", "Error",
						JOptionPane.ERROR_MESSAGE);
				limpiar();
			}

		}
	}

	/**
	 * Método para vaciar los txt de la ventana al comprobar que algún dato no es correcto
	 */
	private void limpiar() {
		txtDni.setText("");
		txtContraseña.setText("");
	}

	/**
	 * Método para abrir una ventana u otra en caso de ser administrador o cliente
	 * @param dni Es el dni que se envia para ser comprobado
	 * @param contraseña Es la contraseña que se envia para ser comprobada.
	 * @param usuario Es el usuario que se envia para comprobar que tipo de usuario es
	 * @throws LoginIncorrectoException Al saltar la excepción saldrá un mensaje de error
	 */
	private void comprobarLogin(String dni, String contraseña, Usuario usuario) throws LoginIncorrectoException {
		if (usuario != null) {
			if (dni.equalsIgnoreCase(usuario.getDni()) && contraseña.equals(usuario.getContraseña())) {
				if (usuario instanceof Cliente) {
					VMenuCliente vMenuCliente = new VMenuCliente(this, true, datosCliente, (Cliente) usuario, datosAmbos);
					vMenuCliente.setVisible(true);
				}
				if (usuario instanceof Usuario && usuario.getTipo().equalsIgnoreCase("administrador")) {
					VMenuAdministrador vMenuAdmin = new VMenuAdministrador(this, true, datosAdmin, usuario, datosAmbos);
					vMenuAdmin.setVisible(true);
				}
			} else {
				throw new LoginIncorrectoException();
			}

		} else {
			throw new LoginIncorrectoException();
		}
	}

}