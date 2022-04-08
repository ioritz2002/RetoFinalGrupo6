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
import com.toedter.calendar.JCalendar;

public class VRegistro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDni;
	private JTextField txtContraseña;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private InterfazCliente datosCliente;

	/**
	 * Create the dialog.
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
			JLabel lblemail = new JLabel("*Email:");
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
			JButton btnAtras = new JButton("ATR\u00C1S");
			btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
			txtContraseña = new JTextField();
			txtContraseña.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtContraseña.setColumns(10);
			txtContraseña.setBounds(231, 121, 210, 39);
			contentPanel.add(txtContraseña);
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
			JButton btnCrearCuenta = new JButton("CREAR CUENTA");
			btnCrearCuenta.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnCrearCuenta.setBounds(422, 645, 187, 60);
			contentPanel.add(btnCrearCuenta);
		}
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(236, 329, 205, 153);
		contentPanel.add(calendar);
	}
}
