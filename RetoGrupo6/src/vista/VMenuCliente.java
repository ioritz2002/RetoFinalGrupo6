package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;

import clases.Cliente;

import clases.Usuario;

import modelo.InterfazCliente;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class VMenuCliente extends JDialog implements ActionListener{


	private JButton btnAtras;
	private JButton btnCarrito;
	private JButton btnHistorialCompra;
	private JButton btnInfoPersonal;
	private JButton btnComprar;
	private JButton btnValorarProducto;
	private InterfazCliente datosCliente;
	private Cliente usuario;


	public VMenuCliente(VPrincipal vPrincipal, boolean b, InterfazCliente datosCliente, Cliente usuario) {
		super(vPrincipal);
		this.setModal(b);
		this.datosCliente = datosCliente;
		this.usuario = usuario;
		setBounds(100, 100, 521, 457);
		getContentPane().setLayout(null);
		
		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAtras.setBounds(44, 325, 103, 53);
		getContentPane().add(btnAtras);
		btnAtras.addActionListener(this);
		
		btnInfoPersonal = new JButton("INFORMACION PERSONAL");
		btnInfoPersonal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInfoPersonal.setBounds(24, 37, 212, 44);
		getContentPane().add(btnInfoPersonal);
		btnInfoPersonal.addActionListener(this);
		
		btnComprar = new JButton("COMPRAR");
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnComprar.setBounds(278, 37, 181, 44);
		getContentPane().add(btnComprar);
		
		btnValorarProducto = new JButton("VALORAR PRODUCTO");
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


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}

		if (e.getSource().equals(btnComprar)) {
			VProductos vprod = new VProductos(this, true, datosCliente, cli);
			vprod.setVisible(true);
		}
		if (e.getSource().equals(btnCarrito)) {
			VCesta cest = new VCesta(this, true, datosCliente, cli);
			cest.setVisible(true);
		}
	}

		if (e.getSource().equals(btnInfoPersonal)) {
			VDatosCliente vDatosCliente = new VDatosCliente(this, true, (Cliente) usuario, datosCliente);
			vDatosCliente.setVisible(true);
		}
	}



}
