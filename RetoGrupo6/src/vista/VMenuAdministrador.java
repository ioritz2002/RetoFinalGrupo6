package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.InterfazAdministrador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class VMenuAdministrador extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JButton btnHistorialCliente;
	private JButton btnAtras;
	private JMenuBar menuBar;
	private JMenu menAlta;
	private JMenuItem itmAltaRepartidor;
	private JMenuItem itmAltaProducto;
	private JMenu menBaja;
	private JMenuItem itmBajaRepartidor;
	private JMenu menModificacion;
	private JMenuItem itmModificarProducto;
	private InterfazAdministrador datosAdmin;
	private Usuario us;
	private String botonElegido;

	public VMenuAdministrador(VPrincipal vPrincipal, boolean b, InterfazAdministrador datosAdmin, Usuario usuario) {
		super(vPrincipal);
		this.setModal(b);
		this.datosAdmin = datosAdmin;
		us= usuario;
		
		setBounds(100, 100, 575, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnAtras = new JButton("ATR\u00C1S");
			btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAtras.addActionListener(this);
			btnAtras.setBounds(68, 260, 105, 50);
			contentPanel.add(btnAtras);
		}
		{
			btnHistorialCliente = new JButton("HISTORIAL CLIENTE");
			btnHistorialCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnHistorialCliente.addActionListener(this);
			btnHistorialCliente.setBounds(287, 260, 213, 50);
			contentPanel.add(btnHistorialCliente);
		}
		{
			menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 559, 50);
			contentPanel.add(menuBar);
			
			menAlta = new JMenu("                    ALTA                ");
			menAlta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			menuBar.add(menAlta);
			
			itmAltaRepartidor = new JMenuItem("REPARTIDOR");
			itmAltaRepartidor.addActionListener(this);
			menAlta.add(itmAltaRepartidor);
			
			itmAltaProducto = new JMenuItem("PRODUCTO");
			itmAltaProducto.addActionListener(this);
			menAlta.add(itmAltaProducto);
			
			menBaja = new JMenu("                    BAJA                   ");
			menBaja.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			menuBar.add(menBaja);
			
			itmBajaRepartidor = new JMenuItem("REPARTIDOR");
			menBaja.add(itmBajaRepartidor);
			
			JMenuItem itmBajaProducto = new JMenuItem("PRODUCTO");
			menBaja.add(itmBajaProducto);
			
			menModificacion = new JMenu("               MODIFICACION               ");
			menModificacion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			menuBar.add(menModificacion);
			
			itmModificarProducto = new JMenuItem("PRODUCTO");
			menModificacion.add(itmModificarProducto);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(itmAltaRepartidor)) {
			VAltaRepartidor a = new VAltaRepartidor(this, true, datosAdmin, us);
			a.setVisible(true);
		}
		if (e.getSource().equals(itmAltaProducto)) {
			botonElegido= "Alta";
			VDatosProducto prod= new VDatosProducto(this, true, datosAdmin, us);
			prod.setVisible(true);
		}
		if (e.getSource().equals(btnHistorialCliente)) {
			VSelecCliente comp= new VSelecCliente(this, true, datosAdmin);
			comp.setVisible(true);
		}
	}
}
