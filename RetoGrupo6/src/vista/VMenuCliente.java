package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VMenuCliente extends JDialog{
	private JButton btnAtras;
	private JButton btnCarrito;
	private JButton btnHistorialCompra;
	private JButton btnInfoPersonal;
	private JButton btnComprar;

	public VMenuCliente() {
		setBounds(100, 100, 521, 457);
		getContentPane().setLayout(null);
		
		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAtras.setBounds(44, 325, 103, 53);
		getContentPane().add(btnAtras);
		
		btnInfoPersonal = new JButton("INFORMACION PERSONAL");
		btnInfoPersonal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInfoPersonal.setBounds(24, 37, 212, 44);
		getContentPane().add(btnInfoPersonal);
		
		btnComprar = new JButton("COMPRAR");
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnComprar.setBounds(278, 37, 181, 44);
		getContentPane().add(btnComprar);
		
		JButton btnValorarProducto = new JButton("VALORAR PRODUCTO");
		btnValorarProducto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnValorarProducto.setBounds(266, 139, 212, 44);
		getContentPane().add(btnValorarProducto);
		
		btnCarrito = new JButton("CARRITO");
		btnCarrito.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCarrito.setBounds(36, 139, 181, 44);
		getContentPane().add(btnCarrito);
		
		btnHistorialCompra = new JButton("HISTORIAL COMPRA");
		btnHistorialCompra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHistorialCompra.setBounds(278, 255, 181, 44);
		getContentPane().add(btnHistorialCompra);
		
		

	}

	

}
