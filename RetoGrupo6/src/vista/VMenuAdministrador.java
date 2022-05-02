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
	private InterfazAdministrador datosAdmin;
	private JMenuItem itmAltaRepartidor;
	private JMenuItem itmAltaProducto;
	private JMenuItem itmBajaRepartidor;
	private JMenuItem itmBajaProducto;
	private JMenuItem itmModificarProducto;
	private Usuario usuario;

	public VMenuAdministrador(VPrincipal vPrincipal, boolean b, InterfazAdministrador datosAdmin, Usuario usuario) {
		super(vPrincipal);
		this.setModal(b);
		this.datosAdmin = datosAdmin;
		this.usuario = usuario;
		setBounds(100, 100, 639, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnAtras = new JButton("ATR\u00C1S");
			btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAtras.setBounds(68, 260, 105, 50);
			contentPanel.add(btnAtras);
			btnAtras.addActionListener(this);
		}
		{
			btnHistorialCliente = new JButton("HISTORIAL CLIENTE");
			btnHistorialCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnHistorialCliente.setBounds(287, 260, 273, 50);
			contentPanel.add(btnHistorialCliente);
		}
		{
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 623, 50);
			contentPanel.add(menuBar);
			
			JMenu menAlta = new JMenu("                    ALTA                ");
			menAlta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			menuBar.add(menAlta);
			
			itmAltaRepartidor = new JMenuItem("REPARTIDOR");
			menAlta.add(itmAltaRepartidor);
			itmAltaRepartidor.addActionListener(this);
			
			itmAltaProducto = new JMenuItem("PRODUCTO");
			menAlta.add(itmAltaProducto);
			itmAltaProducto.addActionListener(this);
			
			JMenu menBaja = new JMenu("                    BAJA                   ");
			menBaja.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			menuBar.add(menBaja);
			
			itmBajaRepartidor = new JMenuItem("REPARTIDOR");
			menBaja.add(itmBajaRepartidor);
			itmBajaRepartidor.addActionListener(this);
			
			itmBajaProducto = new JMenuItem("PRODUCTO");
			menBaja.add(itmBajaProducto);
			itmBajaProducto.addActionListener(this);
			
			JMenu menModificacion = new JMenu("               MODIFICACION               ");
			menModificacion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			menuBar.add(menModificacion);
			
			itmModificarProducto = new JMenuItem("PRODUCTO");
			menModificacion.add(itmModificarProducto);
			itmModificarProducto.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}
		if (e.getSource().equals(itmModificarProducto)) {
			VProductos vProductos = new VProductos(this, true, usuario, datosAdmin);
			vProductos.setVisible(true);
		}
		if(e.getSource().equals(itmAltaRepartidor)) {
			VAltaRepartidor a = new VAltaRepartidor(this, true, datosAdmin, usuario);
			a.setVisible(true);
		}
		if (e.getSource().equals(itmAltaProducto)) {
			VDatosProducto prod= new VDatosProducto(this, true, datosAdmin, usuario);
			prod.setVisible(true);
		}
		if (e.getSource().equals(btnHistorialCliente)) {
			VSelecCliente comp= new VSelecCliente(this, true, datosAdmin);
			comp.setVisible(true);
		}
		if (e.getSource().equals(itmBajaRepartidor)) {
			VBajaRepartidor vBajaRepartidor = new VBajaRepartidor(this, true, datosAdmin);
			vBajaRepartidor.setVisible(true);
		}
		if (e.getSource().equals(itmBajaProducto)) {
			VProductos vProductos = new VProductos(this, true, usuario, datosAdmin);
			vProductos.setVisible(true);
			
			
		}
	}
}
