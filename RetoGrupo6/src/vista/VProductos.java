package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Cliente;
import clases.ListarTablaProductos;
import clases.ListarTablaProductosMasVendidos;
import clases.Producto;
import clases.Repartidor;
import clases.Usuario;
import clases.Valora;
import clases.Añade;
import clases.Cesta;
import modelo.InterfazAdministrador;
import modelo.InterfazCliente;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class VProductos extends JDialog implements ActionListener, MouseListener {
	private JTable table;
	private DefaultTableModel dtm;
	private JTextField txtNombre;
	private JButton btnAtras;
	private JButton btnCarrito;
	private JButton btnMasVendidos;
	private JComboBox cmbxTipos;
	private JButton btnFiltrar;
	private InterfazAdministrador datosAdmin;
	private InterfazCliente datosCliente;
	private Usuario usuario;
	private List<ListarTablaProductos> listarProductos;
	private List<Producto> productos;
	private List<Producto> productosCesta;
	private List<Valora> valoraciones;
	private String[] fila;
	private String[] cabeceras = { "Nombre", "Tipo", "Precio", "Valoracion" };
	private JRadioButton rdbtnMAS200;
	private JRadioButton rdbtn100A200;
	private JRadioButton rdbtn50A100;
	private JRadioButton rdbtn0A50;
	private ButtonGroup grupoRadio;
	private List<String> tiposProductos;

	/**
	 * @wbp.parser.constructor
	 */

	// Constructor para el administrador
	public VProductos(VMenuAdministrador vMenuAdministrador, boolean b, Usuario usuario,
			InterfazAdministrador datosAdmin) {
		super(vMenuAdministrador);
		this.setModal(b);
		this.datosAdmin = datosAdmin;
		this.usuario = usuario;
		setBounds(100, 100, 522, 453);
		getContentPane().setLayout(null);

		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.setBounds(26, 369, 103, 33);
		getContentPane().add(btnAtras);
		btnAtras.addActionListener(this);

		btnMasVendidos = new JButton("Mas vendidos");
		btnMasVendidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMasVendidos.setBounds(320, 369, 138, 33);
		getContentPane().add(btnMasVendidos);
		btnMasVendidos.addActionListener(this);

		cargarTabla(datosAdmin, null, valoraciones);
	}

	// Consttructor para el cliente porque tiene mas opciones que el administrador
	public VProductos(VMenuCliente vMenuCliente, boolean b, Usuario usuario, InterfazCliente datosCliente) {
		super(vMenuCliente);
		this.setModal(b);
		this.datosCliente = datosCliente;
		this.usuario = usuario;

		setBounds(100, 100, 736, 453);
		getContentPane().setLayout(null);

		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.setBounds(26, 369, 103, 33);
		getContentPane().add(btnAtras);
		btnAtras.addActionListener(this);

		btnCarrito = new JButton("CARRITO");
		btnCarrito.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCarrito.setBounds(169, 369, 103, 33);
		btnCarrito.addActionListener(this);
		getContentPane().add(btnCarrito);

		btnMasVendidos = new JButton("Mas vendidos");
		btnMasVendidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMasVendidos.setBounds(320, 369, 138, 33);
		getContentPane().add(btnMasVendidos);
		btnMasVendidos.addActionListener(this);

		btnFiltrar = new JButton("FILTRAR");
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFiltrar.setBounds(507, 36, 138, 33);
		getContentPane().add(btnFiltrar);
		btnFiltrar.addActionListener(this);

		txtNombre = new JTextField();
		txtNombre.setBounds(507, 104, 138, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel = new JLabel("Por nombre:");
		lblNewLabel.setBounds(517, 80, 103, 16);
		getContentPane().add(lblNewLabel);

		JLabel lblPorTipo = new JLabel("Por tipo:");
		lblPorTipo.setBounds(517, 150, 103, 16);
		getContentPane().add(lblPorTipo);

		cmbxTipos = new JComboBox();
		cmbxTipos.setBounds(507, 179, 138, 22);
		getContentPane().add(cmbxTipos);

		JLabel lblRangos = new JLabel("Rango de precios: ");
		lblRangos.setBounds(507, 237, 103, 16);
		getContentPane().add(lblRangos);

		rdbtn0A50 = new JRadioButton("0-50");
		rdbtn0A50.setBounds(511, 266, 109, 23);
		getContentPane().add(rdbtn0A50);

		rdbtn50A100 = new JRadioButton("50-100");
		rdbtn50A100.setBounds(507, 292, 109, 23);
		getContentPane().add(rdbtn50A100);

		rdbtn100A200 = new JRadioButton("100-200");
		rdbtn100A200.setBounds(507, 318, 109, 23);
		getContentPane().add(rdbtn100A200);

		rdbtnMAS200 = new JRadioButton(">200");
		rdbtnMAS200.setBounds(507, 344, 109, 23);
		getContentPane().add(rdbtnMAS200);

		grupoRadio = new ButtonGroup();

		grupoRadio.add(rdbtn0A50);
		grupoRadio.add(rdbtn100A200);
		grupoRadio.add(rdbtn50A100);
		grupoRadio.add(rdbtnMAS200);

		cargarTabla(null, datosCliente, valoraciones);
		productosCesta = new ArrayList<Producto>();

		tiposProductos = new ArrayList<String>();
		boolean encontrado;
		for (int i = 0; i < productos.size(); i++) {
			encontrado = false;
			for (int j = 0; j < tiposProductos.size(); j++) {
				if (productos.get(i).getTipo().equalsIgnoreCase(tiposProductos.get(j))) {
					encontrado = true;
				}
			}

			if (!encontrado) {
				tiposProductos.add(productos.get(i).getTipo());
			}
		}

		for (String t : tiposProductos) {
			cmbxTipos.addItem(t);
		}

		cmbxTipos.setSelectedIndex(-1);
	}

	// Este metodo sirve para crear la tabla y cargar sus datos
	private void cargarTabla(InterfazAdministrador datosAdmin, InterfazCliente datosCliente,
			List<Valora> valoraciones) {
		// Tabla

		fila = new String[4];

		dtm = new DefaultTableModel(null, cabeceras);
		if (datosAdmin == null) {
			productos = datosCliente.listarProductos();
			valoraciones = datosCliente.listarValoraciones();
		}
		if (datosCliente == null) {
			productos = datosAdmin.listarProductos();
			valoraciones = datosAdmin.listarValoraciones();
			
		}

		// Cargo el map con los datos de los productos y valoraciones
		Map<String, ListarTablaProductos> listaProductosMap = cargarLista(productos, valoraciones);
		listarProductos = new ArrayList<ListarTablaProductos>(listaProductosMap.values());

		Collections.sort(listarProductos);

		for (int i = 0; i < listarProductos.size(); i++) {
			fila[0] = listarProductos.get(i).getNombreProducto();
			fila[1] = listarProductos.get(i).getTipoProducto();
			fila[2] = String.valueOf(listarProductos.get(i).getPrecio());
			fila[3] = String.valueOf(listarProductos.get(i).getValoracion());

			dtm.addRow(fila);
		}

		table = new JTable(dtm);
		JScrollPane scroll = new JScrollPane(table);
		table.setBounds(50, 10, 420, 250);
		scroll.setViewportView(table);
		scroll.setBounds(20, 20, 420, 299);
		getContentPane().add(scroll, BorderLayout.CENTER);

		table.addMouseListener(this);

	}

	// Este metodo sirve para cargar el map para poder mostrar la informacion en la
	// tabla, los parametros que se le pasa son dos listas, una con las valoraciones
	// y otra con los productos
	private Map<String, ListarTablaProductos> cargarLista(List<Producto> productos, List<Valora> valoraciones) {
		Map<String, ListarTablaProductos> listar = new TreeMap<String, ListarTablaProductos>();
		int contador = 0;

		/*
		 * Este for recorre las valoraciones por cada producto para juntar los datos en
		 * un map, Creando una nueva linea en el caso de que no este en el listado pero
		 * si que tenga una valoracion asignandole la valoracion correspondiente desde
		 * la lista de valoraciones y el resto de datos de los productos En el caso de
		 * que exita la valoracion del producto y ademas esta ya en la lista se le suma
		 * la valoracion del producto a la linea del listado para posteriormente sacar
		 * una media de las valoraciones
		 */

		for (int i = 0; i < productos.size(); i++) {
			contador = 0;
			for (int j = 0; j < valoraciones.size(); j++) {
				if (productos.get(i).getCodProducto().equalsIgnoreCase(valoraciones.get(j).getCodProducto())
						&& !listar.containsKey(productos.get(i).getCodProducto())) {
					ListarTablaProductos linea = new ListarTablaProductos();
					linea.setCodigoProducto(productos.get(i).getCodProducto());
					linea.setNombreProducto(productos.get(i).getNombre());
					linea.setPrecio(productos.get(i).getPrecio());
					linea.setTipoProducto(productos.get(i).getTipo());
					linea.setValoracion(valoraciones.get(j).getValoracion());
					listar.put(linea.getCodigoProducto(), linea);

				} else if (productos.get(i).getCodProducto().equalsIgnoreCase(valoraciones.get(j).getCodProducto())) {
					listar.get(productos.get(i).getCodProducto())
							.setValoracion(listar.get(productos.get(i).getCodProducto()).getValoracion()
									+ valoraciones.get(j).getValoracion());
				}

			}
			// En el caso de que no exista valoraciones de ese producto ni tampoco este en
			// la lista entonces se crea una nueva linea con los datos del producto y se le
			// asigna como valoracion 0
			if (!listar.containsKey(productos.get(i).getCodProducto())) {
				ListarTablaProductos linea = new ListarTablaProductos();
				linea.setCodigoProducto(productos.get(i).getCodProducto());
				linea.setNombreProducto(productos.get(i).getNombre());
				linea.setPrecio(productos.get(i).getPrecio());
				linea.setTipoProducto(productos.get(i).getTipo());
				linea.setValoracion(0);
				listar.put(linea.getCodigoProducto(), linea);
			}
		}

		for (int i = 0; i < productos.size(); i++) {
			contador = 0;
			for (int j = 0; j < valoraciones.size(); j++) {
				if (productos.get(i).getCodProducto().equalsIgnoreCase(valoraciones.get(j).getCodProducto())) {
					contador++;
				}
			}
			if (contador > 0) {
				listar.get(productos.get(i).getCodProducto()).setValoracion(
						(Float) listar.get(productos.get(i).getCodProducto()).getValoracion() / contador);
			}

		}

		return listar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}
		if (e.getSource().equals(btnCarrito)) {

			VCesta vCesta = new VCesta(this, true, (Cliente) usuario, datosCliente);
			vCesta.setVisible(true);

		}

		if (e.getSource().equals(btnFiltrar)) {
			filtros();
		}
		if (e.getSource().equals(btnMasVendidos)) {
			masVendidos();
		}

	}

	private void filtros() {
		valoraciones = datosCliente.listarValoraciones();
		Map<String, ListarTablaProductos> listaTabla = null;
		List<Producto> productosRango = null;

		if (rdbtn0A50.isSelected()) {
			
			productosRango = datosCliente.listarProductosFiltradoPrecio(0, 50);

			listaTabla = cargarLista(productosRango, valoraciones);

			for (ListarTablaProductos p : listaTabla.values()) {
				fila[0] = p.getNombreProducto();
				fila[1] = p.getTipoProducto();
				fila[2] = String.valueOf(p.getPrecio());
				fila[3] = String.valueOf(p.getValoracion());

				dtm.addRow(fila);
			}
		}
		if (rdbtn50A100.isSelected()) {
			
			productosRango = datosCliente.listarProductosFiltradoPrecio(5, 100);

			listaTabla = cargarLista(productosRango, valoraciones);

			for (ListarTablaProductos p : listaTabla.values()) {
				fila[0] = p.getNombreProducto();
				fila[1] = p.getTipoProducto();
				fila[2] = String.valueOf(p.getPrecio());
				fila[3] = String.valueOf(p.getValoracion());

				dtm.addRow(fila);
			}
		}
		if (rdbtn100A200.isSelected()) {
			
			productosRango = datosCliente.listarProductosFiltradoPrecio(100, 200);

			listaTabla = cargarLista(productosRango, valoraciones);

			for (ListarTablaProductos p : listaTabla.values()) {
				fila[0] = p.getNombreProducto();
				fila[1] = p.getTipoProducto();
				fila[2] = String.valueOf(p.getPrecio());
				fila[3] = String.valueOf(p.getValoracion());

				dtm.addRow(fila);
			}
		}
		if (rdbtnMAS200.isSelected()) {
			
			productosRango = datosCliente.listarProductosFiltradoPrecio(200, 1000000);

			listaTabla = cargarLista(productosRango, valoraciones);

			for (ListarTablaProductos p : listaTabla.values()) {
				fila[0] = p.getNombreProducto();
				fila[1] = p.getTipoProducto();
				fila[2] = String.valueOf(p.getPrecio());
				fila[3] = String.valueOf(p.getValoracion());

				dtm.addRow(fila);
			}
		}

		if (cmbxTipos.getSelectedIndex() > -1) {
			vaciarTabla();
			String tipoSeleccionado = cmbxTipos.getSelectedItem().toString();

			for (int i = 0; i < listarProductos.size(); i++) {
				if (listarProductos.get(i).getTipoProducto().equalsIgnoreCase(tipoSeleccionado)) {
					fila[0] = listarProductos.get(i).getNombreProducto();
					fila[1] = listarProductos.get(i).getTipoProducto();
					fila[2] = String.valueOf(listarProductos.get(i).getPrecio());
					fila[3] = String.valueOf(listarProductos.get(i).getValoracion());

					dtm.addRow(fila);
				}
			}
		}

		if (txtNombre.getText() != null && !txtNombre.getText().equalsIgnoreCase("")) {
			
			String nombre = txtNombre.getText();

			for (int i = 0; i < listarProductos.size(); i++) {
				if (listarProductos.get(i).getNombreProducto().contains(nombre)) {
					fila[0] = listarProductos.get(i).getNombreProducto();
					fila[1] = listarProductos.get(i).getTipoProducto();
					fila[2] = String.valueOf(listarProductos.get(i).getPrecio());
					fila[3] = String.valueOf(listarProductos.get(i).getValoracion());

					dtm.addRow(fila);
				}
			}
		}
		vaciarTabla();
		

	}

	private void masVendidos() {
		int contador = 0;
		int limite = 0;
		List<ListarTablaProductosMasVendidos> lista = null;
		if (usuario instanceof Cliente) {
			valoraciones = datosCliente.listarValoraciones();
		}
		if (usuario instanceof Usuario && usuario.getTipo().equalsIgnoreCase("administrador")) {
			valoraciones = datosAdmin.listarValoraciones();
		}

		vaciarTabla();

		if (usuario instanceof Cliente) {
			lista = datosCliente.listarProductosMasVendidos();
		}
		if (usuario instanceof Usuario && usuario.getTipo().equalsIgnoreCase("administrador")) {
			lista = datosAdmin.listarProductosMasVendidosAdmin();
		}

		ListarTablaProductosMasVendidos[] productosMasVendidos = new ListarTablaProductosMasVendidos[3];
		

		limite = lista.size();
		if (lista != null && lista.size() > 0) {
			if (limite > 3) {
				limite = 3;
			}
			for (int i = 0; i < limite; i++) {
				productosMasVendidos[i] = lista.get(i);
			}

			for (int i = 0; i < productosMasVendidos.length; i++) {
				for (int j = 0; j < valoraciones.size(); j++) {
					if (productosMasVendidos[i] != null) {

						if (productosMasVendidos[i].getCodProducto().equalsIgnoreCase(
								valoraciones.get(j).getCodProducto()) && productosMasVendidos[i].getValoracion() > 0) {
							productosMasVendidos[i].setValoracion(
									productosMasVendidos[i].getValoracion() + valoraciones.get(j).getValoracion());
							contador++;
						}
						if (productosMasVendidos[i].getCodProducto().equalsIgnoreCase(
								valoraciones.get(j).getCodProducto()) && productosMasVendidos[i].getValoracion() == 0) {
							productosMasVendidos[i].setValoracion(valoraciones.get(j).getValoracion());
						}
					}

				}

				if (contador > 0) {
					productosMasVendidos[i].setValoracion((Float) productosMasVendidos[i].getValoracion() / contador);
				}
			}

			for (int i = 0; i < productosMasVendidos.length; i++) {
				if(productosMasVendidos[i]!=null) {
					fila[0] = productosMasVendidos[i].getNombre();
					fila[1] = productosMasVendidos[i].getTipo();
					fila[2] = String.valueOf(productosMasVendidos[i].getPrecio());
					fila[3] = String.valueOf(productosMasVendidos[i].getValoracion());

					dtm.addRow(fila);
				}
				
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(table)) {
			if (usuario instanceof Usuario && usuario.getTipo().equalsIgnoreCase("administrador")) {
				seleccionAdmin(listarProductos);
			}
			if (usuario instanceof Cliente) {
				seleccionCliente(listarProductos);
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private String calcularIdCesta() {
		int cant = datosCliente.calcularCodCesta() + 1;
		String cod;
		cant = 0000 + cant;
		if (cant >= 1000) {
			cod = "CE-" + cant;
		} else if (cant >= 100) {
			cod = "CE-0" + cant;
		} else if (cant >= 10) {
			cod = "CE-00" + cant;
		} else {
			cod = "CE-000" + cant;
		}
		return cod;
	}

	private Producto seleccionCliente(List<ListarTablaProductos> listarProductos) {
		Producto producto = new Producto();
		int filaSeleccionada = table.getSelectedRow();
		Añade añade = null;
		Cesta cesta = null;

		
		if (datosCliente.comprobarCestaActiva(usuario.getDni()) == null) {
			if (JOptionPane.showConfirmDialog(null, "Quieres añadir el producto a la cesta?", null,
					JOptionPane.YES_NO_OPTION) == 0) {
				cesta = new Cesta();
				cesta.setCodCesta(calcularIdCesta());
				cesta.setEstado(false);
				datosCliente.añadirCesta(cesta);
				

				producto.setNombre(String.valueOf(dtm.getValueAt(filaSeleccionada, 0)));
				producto.setCodProducto(obtenerCodigo(listarProductos, producto.getNombre()));
				producto.setPrecio(Double.parseDouble(String.valueOf(dtm.getValueAt(filaSeleccionada, 2))));
				producto.setStock(obtenerStock(producto));
				if (producto.getStock() == 0) {
					JOptionPane.showMessageDialog(null, "No se puede añadir el producto porque no tiene stock", null,
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (datosCliente.comprobarProductoRepetido(usuario.getDni(), producto.getCodProducto())) {
						JOptionPane.showMessageDialog(null,
								"Error, no se puede introducir el mismo producto mas de una vez en una misma cesta",
								null, JOptionPane.ERROR_MESSAGE);
					} else {

						añade = new Añade();
						añade.setCodCesta(cesta.getCodCesta());
						añade.setDni(usuario.getDni());
						añade.setIdProducto(producto.getCodProducto());

						datosCliente.añadirProductoAAñade(añade);
						datosCliente.reducirStock(producto.getCodProducto());
					}
				}

			}

		}

		else {
			if (JOptionPane.showConfirmDialog(null, "Quieres añadir el producto a la cesta?", null,
					JOptionPane.YES_NO_OPTION) == 0) {
				
				producto.setNombre(String.valueOf(dtm.getValueAt(filaSeleccionada, 0)));
				producto.setCodProducto(obtenerCodigo(listarProductos, producto.getNombre()));
				producto.setPrecio(Double.parseDouble(String.valueOf(dtm.getValueAt(filaSeleccionada, 2))));
				producto.setStock(obtenerStock(producto));
				if (producto.getStock() == 0) {
					JOptionPane.showMessageDialog(null, "No se puede añadir el producto porque no tiene stock", null,
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (datosCliente.comprobarProductoRepetido(usuario.getDni(), producto.getCodProducto())) {
						JOptionPane.showMessageDialog(null,
								"Error, no se puede introducir el mismo producto mas de una vez en una misma cesta",
								null, JOptionPane.ERROR_MESSAGE);
					} else {
						añade = new Añade();
						añade.setCodCesta(datosCliente.comprobarCestaActiva(usuario.getDni()));
						añade.setDni(usuario.getDni());
						añade.setIdProducto(producto.getCodProducto());

						datosCliente.añadirProductoAAñade(añade);
						datosCliente.reducirStock(producto.getCodProducto());
					}
				}
			}

		}

		return producto;
	}

	private void seleccionAdmin(List<ListarTablaProductos> listarProductos) {
		Producto producto = new Producto();
		int filaSeleccionada = table.getSelectedRow();

		producto.setNombre(String.valueOf(dtm.getValueAt(filaSeleccionada, 0)));
		producto.setCodProducto(obtenerCodigo(listarProductos, producto.getNombre()));
		producto.setTipo(String.valueOf(dtm.getValueAt(filaSeleccionada, 1)));
		producto.setPrecio(Double.parseDouble(String.valueOf(dtm.getValueAt(filaSeleccionada, 2))));
		producto.setStock(obtenerStock(producto));

		VDatosProducto vDatos = new VDatosProducto(this, true, datosAdmin, producto);
		vDatos.setVisible(true);
	}

	private int obtenerStock(Producto producto) {
		int stock = -1;
		for (Producto p : productos) {
			if (p.getCodProducto().equalsIgnoreCase(producto.getCodProducto())) {
				stock = p.getStock();
			}
		}
		return stock;
	}

	private String obtenerCodigo(List<ListarTablaProductos> listarProductos, String nombre) {
		String codigo = null;

		for (ListarTablaProductos i : listarProductos) {
			if (i.getNombreProducto().equalsIgnoreCase(nombre)) {
				codigo = i.getCodigoProducto();
			}
		}

		return codigo;
	}

	private void vaciarTabla() {
		table = null;
		dtm = new DefaultTableModel(null, cabeceras);

		table = new JTable(dtm);
		JScrollPane scroll = new JScrollPane(table);
		table.setBounds(50, 10, 420, 250);
		scroll.setViewportView(table);
		scroll.setBounds(20, 20, 420, 250);
		getContentPane().add(scroll, BorderLayout.CENTER);
		table.addMouseListener(this);

	}
}