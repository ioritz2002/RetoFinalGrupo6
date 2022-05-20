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
 * Es la primera ventana de la aplicaci�n, en ella se podr� inicar sesi�n y crear una cuenta
 * @author 1dam
 * @version 1
 */
public class VPrincipal extends JFrame implements ActionListener {

	/**
	 * En el el ususario escribir� su DNI para identificarse
	 */
	private JTextField txtDni;
	/**
	 * Al pulsar el bot�n se abrir� la ventana VRegistro para crear la nueva cuenta
	 */
	private JButton btnCrearCuenta;
	/**
	 * Al pulsarlo se comprobar�n los datos introducidos en los txt para comfirmar la existencia del usuario, si existe y dependiendo que sea (administrador o cliente) ir� a VMenuCliente o VMenuAdministrador.
	 */
	private JButton btnIniciarSesion;
	/**
	 * Se igualar� a la interfaz datosAdmin que viene por par�metro para poder utilizarlo fuera del constructor
	 */
	private InterfazAdministrador datosAdmin;
	/**
	 * Se igualar� a la interfaz datosCliente que viene por par�metro para poder utilizarlo fuera del constructor
	 */
	private InterfazCliente datosCliente;
	/**
	 * Se igualar� a la interfaz datosAmbos que viene por par�metro para poder utilizarlo fuera del constructor
	 */
	private InterfazAmbosUsuarios datosAmbos;
	/**
	 * En el el usuario escribir� su contrase�a para iniciar sesi�n
	 */
	private JPasswordField txtContrase�a;

	/**
	 * 
	 * @param datosAdmin Es una de las interfaces que se usar�n en caso de necesitar la base de datos
	 * @param datosCliente Es una de las interfaces que se usar�n en caso de necesitar la base de datos
	 * @param datosAmbos Es una de las interfaces que se usar�n en caso de necesitar la base de datos
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

		txtContrase�a = new JPasswordField();
		txtContrase�a.setBounds(200, 181, 267, 31);
		contentPane.add(txtContrase�a);
	}

	/**
	 * Permite que al pulsar alg�n elemento de la ventana haga una acci�n concreta indicada en el m�todo
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
	 * M�todo para pasar los datos requeridos para iniciar sesi�n a la implementaci�n
	 */
	private void iniciarSesion() {
		String dni = txtDni.getText();
		String contrase�a = txtContrase�a.getText();
		Usuario usuario = null;

		if (dni.equalsIgnoreCase("") || contrase�a.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Error, tiene que rellenar ambos campos", "Error",
					JOptionPane.ERROR_MESSAGE);
			limpiar();
		} else {

			usuario = datosAmbos.buscarUsuarioLogin(dni);
			try {
				comprobarLogin(dni, contrase�a, usuario);
			} catch (LoginIncorrectoException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error, DNI o contrase�a incorrecto", "Error",
						JOptionPane.ERROR_MESSAGE);
				limpiar();
			}

		}
	}

	/**
	 * M�todo para vaciar los txt de la ventana al comprobar que alg�n dato no es correcto
	 */
	private void limpiar() {
		txtDni.setText("");
		txtContrase�a.setText("");
	}

	/**
	 * M�todo para abrir una ventana u otra en caso de ser administrador o cliente
	 * @param dni Es el dni que se envia para ser comprobado
	 * @param contrase�a Es la contrase�a que se envia para ser comprobada.
	 * @param usuario Es el usuario que se envia para comprobar que tipo de usuario es
	 * @throws LoginIncorrectoException Al saltar la excepci�n saldr� un mensaje de error
	 */
	private void comprobarLogin(String dni, String contrase�a, Usuario usuario) throws LoginIncorrectoException {
		if (usuario != null) {
			if (dni.equalsIgnoreCase(usuario.getDni()) && contrase�a.equals(usuario.getContrase�a())) {
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