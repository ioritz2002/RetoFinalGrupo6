package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import clases.Producto;
import modelo.InterfazAdministrador;
import modelo.InterfazCliente;

import javax.swing.JButton;

public class VDatosProducto extends JDialog implements ActionListener{
	private JTextField txtCodigo;
	private JTextField txtTipo;
	private JButton btnAtras;
	private JButton btnAlta;
	private JButton btnBaja;
	private JButton btnModificar;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private Producto producto;
	private InterfazAdministrador datosAdministrador;

	public VDatosProducto(VPrincipal vPrincipal, boolean b, InterfazAdministrador datosAdmin, Producto producto) {
		
		super(vPrincipal);
		this.setModal(b);
		this.datosAdministrador = datosAdmin;
		this.producto = producto;
		
		
		setBounds(100, 100, 709, 419);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("CODIGO:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCodigo.setBounds(39, 27, 101, 43);
		getContentPane().add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(150, 38, 318, 28);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblTipo = new JLabel("TIPO:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipo.setBounds(39, 79, 101, 43);
		getContentPane().add(lblTipo);
		
		txtTipo = new JTextField();
		txtTipo.setColumns(10);
		txtTipo.setBounds(150, 90, 318, 28);
		getContentPane().add(txtTipo);
		
		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre.setBounds(39, 132, 101, 43);
		getContentPane().add(lblNombre);
		
		JLabel lblPrecio = new JLabel("PRECIO:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrecio.setBounds(39, 195, 101, 43);
		getContentPane().add(lblPrecio);
		
		JLabel lblStock = new JLabel("STOCK:");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStock.setBounds(39, 252, 101, 43);
		getContentPane().add(lblStock);
		
		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAtras.setBounds(37, 316, 103, 53);
		getContentPane().add(btnAtras);
		
		btnAlta = new JButton("ALTA");
		btnAlta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAlta.setBounds(209, 316, 103, 53);
		getContentPane().add(btnAlta);
		
		btnBaja = new JButton("BAJA");
		btnBaja.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBaja.setBounds(348, 316, 103, 53);
		getContentPane().add(btnBaja);
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnModificar.setBounds(487, 316, 158, 53);
		getContentPane().add(btnModificar);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(150, 143, 318, 28);
		getContentPane().add(txtNombre);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(150, 210, 318, 28);
		getContentPane().add(txtPrecio);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(150, 267, 318, 28);
		getContentPane().add(txtStock);
		
		btnBaja.addActionListener(this);
		noEditable();
		mostrarDatos();

	}

	private void mostrarDatos() {
		// TODO Auto-generated method stub
		
		txtCodigo.setText(producto.getCodProducto());
		txtNombre.setText(producto.getNombre());
		txtPrecio.setText(String.valueOf(producto.getPrecio()));
		txtStock.setText(String.valueOf(producto.getStock()));
		txtTipo.setText(producto.getTipo());
		
	}

	private void noEditable() {
		// TODO Auto-generated method stub
		
		txtCodigo.setEditable(false);
		txtNombre.setEditable(false);
		txtPrecio.setEditable(false);
		txtStock.setEditable(false);
		txtTipo.setEditable(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Producto producto = new Producto();
		producto.setCodProducto(txtCodigo.getText());
		if(e.getSource().equals(btnBaja)) {
			if (JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres dar de baja este producto?",
					"Selecciona una opcion", JOptionPane.YES_NO_OPTION) == 0) {
				datosAdministrador.bajaProducto(txtCodigo.getText());
				JOptionPane.showMessageDialog(null, "Producto borrado",
						"Selecciona una opcion", JOptionPane.WARNING_MESSAGE);
				this.dispose();
			}
			
		}
		
	}
}
