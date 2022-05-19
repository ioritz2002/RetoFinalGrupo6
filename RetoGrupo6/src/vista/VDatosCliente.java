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

/**
 * Esta clase es una ventana para mostrar los datos del cliente y poder modificar sus datos o poder darse de baja
 * @author grupo6
 * @version 1 
 */
public class VDatosCliente extends JDialog implements ActionListener {

	/**
	 * Se crea el panel de la ventana para poder añadir las consas
	 */
	private final JPanel contentPanel = new JPanel();
	/**
	 * Es un campo de texto en el que se mostrara el dni del cliente
	 */
	private JTextField txtDni;
	/**
	 * Es un campo de texto en el que se mostrara el email del cliente
	 */
	private JTextField txtEmail;
	/**
	 * Es un campo de texto en el que se mostrara el nombre del cliente
	 */
	private JTextField txtNombre;
	/**
	 * Es un campo de texto en el que se mostrara la direccion del cliente
	 */
	private JTextField txtDireccion;
	/**
	 * Es la interfaz de datos del cliente
	 */
	private InterfazCliente datosCliente;
	/**
	 * Es un campo de texto en el que se mostrara la fecha de nacimiento del cliente
	 */
	private JTextField txtFNacimiento;
	/**
	 * Es un campo de contraseña en el que se mostrara la contraseña de la cuenta del cliente oculto
	 */
	private JPasswordField txtContraseña;
	/**
	 * Es un boton para ejecutar la consulta de modificar los datos del cliente
	 */
	private JButton btnModificar;
	/**
	 * Es un boton que cierra la ventana para volver a la anterior
	 */
	private JButton btnAtras;
	/**
	 * Es un objeto para recibir el usuario que esta conectado a traves de la ventana anterior
	 */
	private Cliente usuario;
	/**
	 * Es un boton para ejecutar la consulta para darse de baja
	 */
	private JButton btnBorrar;
	/**
	 * Es la interfaz de datos de ambos usuarios
	 */
	private InterfazAmbosUsuarios datosAmbos;

	/**
	 * 
	 * Este es el constructor de la ventana
	 * @param datosCliente Es la implementacion de los datos del cliente
	 * @param b Es el parametro que indica si la ventana sera modal o no
	 * @param vMenuCliente Este paramentro indica cual es el padre de esta ventana es decir desde donde se ha llamado
	 * @param datosAmbos Es la implementacion de los datos de ambos usuarios
	 */
	public VDatosCliente(VMenuCliente vMenuCliente, boolean b, Cliente usuario, InterfazCliente datosCliente, InterfazAmbosUsuarios datosAmbos) {
		super(vMenuCliente);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
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

		cargarDatos(usuario);
	}

	/**
	 * Este metodo carga los datos del usuario en los campos correspondientes
	 * @param usuario Se pasa como parametro un usuario para poder cargar los datos correspondiente
	 */
	private void cargarDatos(Cliente usuario) {
		txtDni.setText(usuario.getDni());
		txtContraseña.setText(usuario.getContraseña());
		txtDireccion.setText(usuario.getDireccion());
		txtEmail.setText(usuario.getEmail());
		txtFNacimiento.setText(usuario.getFechaNacimiento().toString());
		txtNombre.setText(usuario.getNombre());
	}

	/**
	 * Este metodo ejecuta los eventos de los botones
	 */
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
			if (JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres darte de baja?", "Selecciona una opcion",
					JOptionPane.YES_NO_OPTION) == 0) {
				datosCliente.darseDeBaja(txtDni.getText());
				JOptionPane.showMessageDialog(null, "Cuenta borrada", "Selecciona una opcion",
						JOptionPane.WARNING_MESSAGE);
				this.dispose();
				
			}

		}

	}


	/**
	 * Este metodo modifica los datos del cliente en la base de datos
	 */
	private void modificar() {
	
			usuario.setDni(txtDni.getText());
			usuario.setNombre(txtNombre.getText());
			usuario.setContraseña(txtContraseña.getText());
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
	
	/**
	 * Este metodo dice si un email es valido o no
	 * @param gmail Este paramentro sting es el email a validar
	 * @return Retorna si el email es valido o no
	 */
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
