package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.InterfazAdministrador;
import modelo.InterfazCliente;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VPrincipal extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtDni;
	private JTextField txtContraseña;
	private JButton btnRegistro;
	private JButton btnIniciarSesion;
	private InterfazAdministrador datosAdmin;
	private InterfazCliente datosCliente;

	
	public VPrincipal(InterfazAdministrador datosAdmin, InterfazCliente datosCliente) {
		this.datosAdmin = datosAdmin;
		this.datosCliente = datosCliente;
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
		
		txtContraseña = new JTextField();
		txtContraseña.setBounds(196, 178, 309, 36);
		contentPane.add(txtContraseña);
		txtContraseña.setColumns(10);
		
		btnIniciarSesion = new JButton("INICIAR SESION");
		btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIniciarSesion.setBounds(89, 275, 171, 67);
		contentPane.add(btnIniciarSesion);
		
		btnRegistro = new JButton("CREAR CUENTA");
		btnRegistro.addActionListener(this);
		btnRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegistro.setBounds(382, 275, 171, 67);
		contentPane.add(btnRegistro);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnRegistro)) {
			VRegistro venRegistro = new VRegistro(this, true, datosCliente);
			venRegistro.setVisible(true);
		}
		
	}
}
