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

public class VDatosCliente extends JDialog implements ActionListener {

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
			txtDni.setBounds(231, 52, 210, 39);
			contentPanel.add(txtDni);
			txtDni.setColumns(10);
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
			txtDireccion.setBounds(231, 394, 210, 39);
			contentPanel.add(txtDireccion);
		}
		{
			btnModificar = new JButton("MODIFICAR");
			btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnModificar.setBounds(451, 515, 187, 48);
			contentPanel.add(btnModificar);
		}
		{
			txtFNacimiento = new JTextField();
			txtFNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtFNacimiento.setColumns(10);
			txtFNacimiento.setBounds(231, 330, 210, 39);
			contentPanel.add(txtFNacimiento);
		}
		{
			txtContraseña = new JPasswordField();
			txtContraseña.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtContraseña.setColumns(10);
			txtContraseña.setBounds(231, 121, 210, 39);
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

	private void limpiar() {
		txtContraseña.setText("");
		txtDireccion.setText("");
		txtDni.setText("");
		txtEmail.setText("");
		txtFNacimiento.setText("");
		txtNombre.setText("");
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

		/*
		 * Si se pulsa el boton borrar, se crea un cliente, se coge su dni del txtdni y
		 * se le pregunta a la persona si quiere borrar de verdad su cuenta para
		 * confirma, si da que si se borrará y se cerrara la ventana. Si no solo se
		 * cerrara la ventana y no se borrar el cliente
		 */
		if (e.getSource().equals(btnBorrar)) {

			Cliente cliente = new Cliente();
			cliente.setDni(txtDni.getText());
				if (JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres darte de baja?",
						"Selecciona una opcion", JOptionPane.YES_NO_OPTION) == 0) {
					datosCliente.darseDeBaja(txtDni.getText());
					this.dispose();
				}
			
		}

	}

}
