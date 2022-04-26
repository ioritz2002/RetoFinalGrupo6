package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.InterfazCliente;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;

import com.toedter.calendar.JCalendar;
import javax.swing.JPasswordField;

public class VDatosCliente extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDni;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private InterfazCliente datosCliente;
	private JTextField txtFNacimiento;
	private JPasswordField txtContraseña;
	private JButton btnCrearCuenta;
	private JButton btnAtras;

	/**
	 * Create the dialog.
	 * @param datosCliente 
	 * @param b 
	 * @param vPrincipal 
	 */
	public VDatosCliente(VPrincipal vPrincipal, boolean b, InterfazCliente datosCliente) {
		super(vPrincipal);
		this.setModal(b);
		this.datosCliente = datosCliente;
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
			lbldireccin.setBounds(80, 389, 118, 48);
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
			btnCrearCuenta = new JButton("MODIFICAR");
			btnCrearCuenta.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnCrearCuenta.setBounds(451, 515, 187, 48);
			contentPanel.add(btnCrearCuenta);
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
			JButton btnBorrar = new JButton("BORRAR");
			btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnBorrar.setBounds(201, 512, 187, 48);
			contentPanel.add(btnBorrar);
		}
	}
}
