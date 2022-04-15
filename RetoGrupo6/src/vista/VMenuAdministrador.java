package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class VMenuAdministrador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnHistorialCliente;
	private JButton btnAtras;

	public VMenuAdministrador() {
		setBounds(100, 100, 575, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnAtras = new JButton("ATR\u00C1S");
			btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAtras.setBounds(68, 260, 105, 50);
			contentPanel.add(btnAtras);
		}
		{
			btnHistorialCliente = new JButton("HISTORIAL CLIENTE");
			btnHistorialCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnHistorialCliente.setBounds(287, 260, 213, 50);
			contentPanel.add(btnHistorialCliente);
		}
		{
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 559, 50);
			contentPanel.add(menuBar);
			
			JMenu menAlta = new JMenu("                    ALTA                ");
			menAlta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			menuBar.add(menAlta);
			
			JMenuItem itmAltaRepartidor = new JMenuItem("REPARTIDOR");
			menAlta.add(itmAltaRepartidor);
			
			JMenuItem itmAltaProducto = new JMenuItem("PRODUCTO");
			menAlta.add(itmAltaProducto);
			
			JMenu menBaja = new JMenu("                    BAJA                   ");
			menBaja.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			menuBar.add(menBaja);
			
			JMenuItem itmBajaRepartidor = new JMenuItem("REPARTIDOR");
			menBaja.add(itmBajaRepartidor);
			
			JMenu menModificacion = new JMenu("               MODIFICACION               ");
			menModificacion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			menuBar.add(menModificacion);
			
			JMenuItem itmModificarProducto = new JMenuItem("PRODUCTO");
			menModificacion.add(itmModificarProducto);
		}
	}
}
