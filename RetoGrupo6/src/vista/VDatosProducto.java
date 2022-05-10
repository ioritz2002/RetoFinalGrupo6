package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

import clases.Producto;
import clases.Usuario;
import modelo.InterfazAdministrador;

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
	private InterfazAdministrador datosAdmin;
	private Usuario us;
	private String cod;

	/**
	 * @wbp.parser.constructor
	 */
	public VDatosProducto(VMenuAdministrador menuAdmin, boolean b, InterfazAdministrador datosAdmin, Usuario usuario) {
		super(menuAdmin);
		this.us = usuario;
		this.datosAdmin = datosAdmin;
		this.setModal(b);

		cod = calcularId();
		setBounds(100, 100, 709, 419);
		getContentPane().setLayout(null);

		JLabel lblCodigo = new JLabel("CODIGO:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCodigo.setBounds(39, 27, 101, 43);
		getContentPane().add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(150, 38, 318, 28);
		txtCodigo.setText(cod);
		txtCodigo.setEditable(false);
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
		btnAtras.addActionListener(this);
		btnAtras.setBounds(37, 316, 103, 53);
		getContentPane().add(btnAtras);

		btnAlta = new JButton("ALTA");
		btnAlta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAlta.addActionListener(this);
		btnAlta.setBounds(238, 316, 144, 53);
		getContentPane().add(btnAlta);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(150, 143, 318, 28);
		getContentPane().add(txtNombre);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(150, 206, 318, 28);
		getContentPane().add(txtPrecio);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(150, 267, 318, 28);
		getContentPane().add(txtStock);

	}
	
	public VDatosProducto(VProductos vProductos, boolean b, InterfazAdministrador datosAdmin, Producto producto) {
		super(vProductos);
		this.setModal(b);
		this.datosAdmin = datosAdmin;
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
		txtCodigo.setEnabled(false);
		
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
		btnAtras.addActionListener(this);
		
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnModificar.setBounds(487, 316, 158, 53);
		getContentPane().add(btnModificar);
		btnModificar.addActionListener(this);
		
		btnBaja = new JButton("BAJA");
		btnBaja.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBaja.setBounds(348, 316, 103, 53);
		getContentPane().add(btnBaja);
		btnBaja.addActionListener(this);
		
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
		
		
		cargarDatos(producto);
		
	}

	private void cargarDatos(Producto producto) {
		txtCodigo.setText(producto.getCodProducto());
		txtNombre.setText(producto.getNombre());
		txtPrecio.setText(String.valueOf(producto.getPrecio()));
		txtStock.setText(String.valueOf(producto.getStock()));
		txtTipo.setText(producto.getTipo());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Se introducen los datos introducidos en un objeto producto para pasarlo a la base de datos para modificarla
		if (e.getSource().equals(btnModificar)) {
			Producto producto = new Producto();
			producto.setCodProducto(txtCodigo.getText());
			producto.setNombre(txtNombre.getText());
			producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
			producto.setStock(Integer.parseInt(txtStock.getText()));
			producto.setTipo(txtTipo.getText());
			
			if(JOptionPane.showConfirmDialog(null, "Esta seguro que quiere modificar el producto", null, JOptionPane.YES_NO_OPTION) == 0){
				datosAdmin.modificarProducto(producto);
				JOptionPane.showMessageDialog(null, "Producto modificado");
			}
			
		}
		
		if (e.getSource().equals(btnAlta)) {
			if (txtTipo.getText().isBlank() || txtNombre.getText().isBlank() || txtPrecio.getText().isBlank()
					|| txtStock.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "Todos los campos deben estar rellenados", "Error",
						JOptionPane.OK_OPTION);
			}else if (comprobarNumero(txtPrecio.getText())) {
				JOptionPane.showMessageDialog(null, "El precio no debe contener letras", "Error",
						JOptionPane.OK_OPTION);
			}else if (comprobarNumero(txtStock.getText())) {
				JOptionPane.showMessageDialog(null, "El stock no debe contener letras", "Error",
						JOptionPane.OK_OPTION);
			} else if (prodRepetido(txtNombre.getText())) {
				JOptionPane.showMessageDialog(null, "Un producto con ese nombre ya ha sido registrado", "Error",
						JOptionPane.OK_OPTION);
			} else if (Double.parseDouble(txtPrecio.getText()) <= 0) {
				JOptionPane.showMessageDialog(null, "El precio del producto no puede ser menor que 0", "Error",
						JOptionPane.OK_OPTION);
			} else if (Integer.parseInt(txtStock.getText()) <= 0) {
				JOptionPane.showMessageDialog(null, "El stock debe ser mayor que 0", "Error", JOptionPane.OK_OPTION);
			}  else {
				nuevoProducto();
				this.dispose();
			}
		}
		
		if(e.getSource().equals(btnBaja)) {
			Producto producto = new Producto();
			producto.setCodProducto(txtCodigo.getText());
			
			if (JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres dar de baja este producto?",
					"Selecciona una opcion", JOptionPane.YES_NO_OPTION) == 0) {
				datosAdmin.bajaProducto(txtCodigo.getText());
				JOptionPane.showMessageDialog(null, "Producto borrado",
						"Selecciona una opcion", JOptionPane.WARNING_MESSAGE);
				this.dispose();
			}
			
		}
		
		
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}
	}
	
	private boolean comprobarNumero(String num) {
		char numeros[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		boolean letra = false;
		for (int i = 0; i < num.length(); i++) {
			letra = false;
			for (int j = 0; j < numeros.length; j++) {
				if (num.charAt(i) == numeros[j]) {
					letra = true;
				} else {
					return letra;
				}
			}
		}
		return letra;
	}

	private String letraMayusMinus(String palabras) {
		return palabras.toUpperCase().charAt(0) + palabras.toLowerCase().substring(1, palabras.length());
	}

	private void nuevoProducto() {
		Producto prod = new Producto();

		prod.setNombre(letraMayusMinus(txtNombre.getText()));
		prod.setPrecio(Double.parseDouble(txtPrecio.getText()));
		prod.setTipo(letraMayusMinus(txtTipo.getText()));
		prod.setStock(Integer.parseInt(txtStock.getText()));
		prod.setDni(us.getDni());
		prod.setCodProducto(cod);

		datosAdmin.altaProductos(prod);
		JOptionPane.showMessageDialog(null, "Producto dado de alta correctamente", null, JOptionPane.OK_OPTION);

	}

	private String calcularId() {
		int cant = datosAdmin.calcularCodProducto() + 1;
		String cod;
		cant = 0000 + cant;
		if (cant >= 1000) {
			cod = "PO-" + cant;
		} else if (cant >= 100) {
			cod = "PO-0" + cant;
		} else if (cant >= 10) {
			cod = "PO-00" + cant;
		} else {
			cod = "PO-000" + cant;
		}
		return cod;
	}

	private boolean prodRepetido(String nombre) {
		return datosAdmin.compararProductos(nombre);
	}
	
}
