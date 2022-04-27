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

public class VPrincipal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDni;
	private JButton btnCrearCuenta;
	private JButton btnIniciarSesion;
	private InterfazAdministrador datosAdmin;
	private InterfazCliente datosCliente;
	private InterfazAmbosUsuarios datosAmbos;
	private JPasswordField txtContraseña;

	public VPrincipal(InterfazAdministrador datosAdmin, InterfazCliente datosCliente,
			InterfazAmbosUsuarios datosAmbos) {
		this.datosAdmin = datosAdmin;
		this.datosCliente = datosCliente;
		this.datosAmbos = datosAmbos;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 427);
		contentPane = new JPanel();
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
		btnCrearCuenta.addActionListener(this);
		btnCrearCuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCrearCuenta.setBounds(382, 275, 171, 67);
		contentPane.add(btnCrearCuenta);
		btnCrearCuenta.addActionListener(this);

		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(200, 181, 267, 31);
		contentPane.add(txtContraseña);
	}

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

	private void limpiar() {
		txtDni.setText("");
		txtContraseña.setText("");
	}

	private void comprobarLogin(String dni, String contraseña, Usuario usuario) throws LoginIncorrectoException {
		if (usuario != null) {
			if (dni.equalsIgnoreCase(usuario.getDni()) && contraseña.equalsIgnoreCase(usuario.getContraseña())) {
				if (usuario instanceof Cliente) {
					VMenuCliente vMenuCliente = new VMenuCliente(this, true, datosCliente, (Cliente) usuario);
					vMenuCliente.setVisible(true);
				}
				if (usuario instanceof Usuario && usuario.getTipo().equalsIgnoreCase("administrador")) {
					VMenuAdministrador vMenuAdmin = new VMenuAdministrador(this, true, datosAdmin, usuario);
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
