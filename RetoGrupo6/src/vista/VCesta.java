package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clases.Cliente;
import clases.ListarTablaCesta;
import clases.Producto;
import clases.Repartidor;
import modelo.InterfazCliente;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VCesta extends JDialog implements ActionListener {

	/**
	 * campo de texto que muestra el precio total de la cesta
	 */
	private JTextField txtPrecioTotal;
	/**
	 * boton para ir a la ventana anterior
	 */
	private JButton btnAtras;
	/**
	 * boton para borrar la compra que esta en curso
	 */
	private JButton btnCancelarCompra;
	/**
	 * boton para confirmar la compra que esta en curso
	 */
	private JButton btnComprar;
	/**
	 * Se utiliza para guardar los datosAdmin que llegan como parámetro
	 */
	private InterfazCliente datosCliente;
	/**
	 * tabla para mostrar los productos que hay en la cesta del cliente
	 */
	private JTable table;
	/**
	 * crea una tabla por defecto
	 */
	private DefaultTableModel dtm;
	/**
	 * Lista para cargar los productos que hay en la cesta
	 */
	private List<ListarTablaCesta> productos;
	/**
	 * Lista para cargar los repartidores
	 */
	private List<Repartidor> repartidores;
	/**
	 * para guardar los datos de los usuarios que llegen por parametro
	 */
	private Cliente usuario;

	/**
	 * @author grupo6
	 * @param vProductos   La ventana padre
	 * @param b            Indica si la ventana es modal o no
	 * @param usuario      datos del usuario
	 * @param datosCliente Interfaz que contiene los metodos que utiliza el cliente
	 */

	public VCesta(VProductos vProductos, boolean b, Cliente usuario, InterfazCliente datosCliente) {
		super(vProductos);
		this.setModal(b);
		this.datosCliente = datosCliente;
		this.usuario = usuario;
		setBounds(100, 100, 620, 418);
		getContentPane().setLayout(null);

		JLabel lblPrecioTotal = new JLabel("Precio Total");
		lblPrecioTotal.setBounds(498, 75, 72, 14);
		getContentPane().add(lblPrecioTotal);

		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setBounds(477, 122, 106, 20);
		getContentPane().add(txtPrecioTotal);
		txtPrecioTotal.setColumns(10);

		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.setBounds(20, 311, 103, 33);
		getContentPane().add(btnAtras);

		btnCancelarCompra = new JButton("CANCELAR COMPRA");
		btnCancelarCompra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelarCompra.setBounds(146, 311, 181, 33);
		getContentPane().add(btnCancelarCompra);

		btnComprar = new JButton("COMPRAR");
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnComprar.setBounds(337, 311, 125, 33);
		getContentPane().add(btnComprar);

		cargarTabla(datosCliente, usuario);

		btnAtras.addActionListener(this);
		btnCancelarCompra.addActionListener(this);
		btnComprar.addActionListener(this);

		productos = datosCliente.listarCestaCompra(usuario.getDni());

		if (productos.size() <= 0) {
			btnCancelarCompra.setEnabled(false);
			btnComprar.setEnabled(false);
		}
	}

	/**
	 * @wbp.parser.constructor
	 */

	/**
	 * @author grupo6
	 * @param vMenuCliente ventana padre
	 * @param b            Indica si la ventana es modal o no
	 * @param datosCliente Interfaz que contiene los metodos que utiliza el cliente
	 * @param usuario      datos del usuario
	 */

	public VCesta(VMenuCliente vMenuCliente, boolean b, InterfazCliente datosCliente, Cliente usuario) {
		super(vMenuCliente);
		this.setModal(b);
		this.datosCliente = datosCliente;
		this.usuario = usuario;
		setBounds(100, 100, 620, 418);
		getContentPane().setLayout(null);

		JLabel lblPrecioTotal = new JLabel("Precio Total");
		lblPrecioTotal.setBounds(498, 75, 72, 14);
		getContentPane().add(lblPrecioTotal);

		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setBounds(477, 122, 106, 20);
		getContentPane().add(txtPrecioTotal);
		txtPrecioTotal.setColumns(10);

		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.setBounds(20, 311, 103, 33);
		getContentPane().add(btnAtras);

		btnCancelarCompra = new JButton("CANCELAR COMPRA");
		btnCancelarCompra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelarCompra.setBounds(146, 311, 181, 33);
		getContentPane().add(btnCancelarCompra);

		btnComprar = new JButton("COMPRAR");
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnComprar.setBounds(337, 311, 125, 33);
		getContentPane().add(btnComprar);

		cargarTabla(datosCliente, usuario);

		btnAtras.addActionListener(this);
		btnCancelarCompra.addActionListener(this);
		btnComprar.addActionListener(this);

		productos = datosCliente.listarCestaCompra(usuario.getDni());

		if (productos.size() <= 0) {
			btnCancelarCompra.setEnabled(false);
			btnComprar.setEnabled(false);
		}
	}

	/**
	 * Metodo para cargar la tabla de los productos que tiene en la cesta el
	 * cliente. Se declara un array de Strings con las cabeceras que queremos que
	 * tengan nuestra tablas y otro array con el numero de columnas que queremos.
	 * 
	 * 
	 * Se carga la lista productos con los productos que tiene el cliente en la
	 * cesta.
	 * 
	 * Se crea un for y por cada producto se cogera su nombre, tipo, y precio y se
	 * mostraran en sus respectivas columnas Por cada vuelta que da el for se irá
	 * cargando el precio de la cesta.
	 * 
	 * 
	 * @param datosCliente Interfaz que contiene los metodos que utiliza el cliente
	 * @param usuario      datos del usuario
	 */
	private void cargarTabla(InterfazCliente datosCliente, Cliente usuario) {

		double precio = 0;
		String[] cabeceras = { "Nombre", "Tipo", "Precio" };
		String[] fila = new String[3];
		dtm = new DefaultTableModel(null, cabeceras);
		productos = datosCliente.listarCestaCompra(usuario.getDni());

		for (int i = 0; i < productos.size(); i++) {
			fila[0] = productos.get(i).getNombre();
			fila[1] = productos.get(i).getTipo();
			fila[2] = String.valueOf(productos.get(i).getPrecio());
			dtm.addRow(fila);
			precio = productos.get(i).getPrecio() + precio;

		}

		table = new JTable(dtm);
		JScrollPane scroll = new JScrollPane(table);
		table.setBounds(50, 10, 420, 250);
		scroll.setViewportView(table);
		scroll.setBounds(20, 20, 442, 250);
		getContentPane().add(scroll, BorderLayout.CENTER);

		txtPrecioTotal.setText(String.valueOf(precio));
		txtPrecioTotal.setEditable(false);
		table.setEnabled(false);

	}

	/**
	 * Metodo para asignar de forma aleatoria una compra a un repartidor
	 * 
	 * Se carga la lista repartidores con los repartidores que hay.
	 * 
	 * Se declaran dos variables max y min; min sera 0 siempre y max que tendra el
	 * valor del tamaño de la lista menos uno ya que el size empieza a contar desde
	 * 0. Se genera un numero random con esas variables y devuelve el codigo del
	 * repartidor
	 * 
	 * @param datosCliente Interfaz que contiene los metodos que utiliza el cliente
	 * @return devuelve el codigo del repartidor al que se le a asignado la compra
	 */

	private String asignarRepartidor(InterfazCliente datosCliente) {

		repartidores = datosCliente.listarRepartidores();

		int min = 0;
		int max = repartidores.size() - 1;

		int random = (int) (Math.random() * (max - min + 1) + min);

		String codigo = repartidores.get(random).getIdRepartidor();

		return codigo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}
		if (e.getSource().equals(btnComprar)) {

			if (datosCliente.listarRepartidores().isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se puede realizar la compra, no hay repartidores disponibles en este momento");
			} else {

				if (JOptionPane.showConfirmDialog(null, "¿Realizar compra?", "Selecciona una opcion",
						JOptionPane.YES_NO_OPTION) == 0) {
					datosCliente.realizarCompra(productos.get(0).getCod_cesta(),
							Double.parseDouble(txtPrecioTotal.getText()), asignarRepartidor(datosCliente));
					JOptionPane.showMessageDialog(null, "Compra realizada", "Selecciona una opcion",
							JOptionPane.WARNING_MESSAGE);
					this.dispose();
				}
			}

		}

		if (e.getSource().equals(btnCancelarCompra)) {

			if (JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres cancelar esta cesta ?",
					"Selecciona una opcion", JOptionPane.YES_NO_OPTION) == 0) {
				productos = datosCliente.listarCestaCompra(usuario.getDni());
				datosCliente.cancelarCompra(productos.get(0).getCod_cesta());
				JOptionPane.showMessageDialog(null, "Cesta borrada", "Selecciona una opcion",
						JOptionPane.WARNING_MESSAGE);
				this.dispose();
			}
		}

	}
}
