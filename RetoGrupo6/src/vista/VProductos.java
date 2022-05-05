package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Cliente;
import clases.ListarTablaProductos;
import clases.Producto;
import clases.Repartidor;
import clases.Usuario;
import clases.Valora;
import clases.Añade;
import clases.Cesta;
import modelo.InterfazAdministrador;
import modelo.InterfazCliente;

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

public class VProductos extends JDialog implements ActionListener, MouseListener {
	private JTable table;
	private DefaultTableModel dtm;
	private JTextField txtNombre;
	private JTextField txtPrecioMin;
	private JTextField txtPrecioMax;
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

	/**
	 * @wbp.parser.constructor
	 */

	
	//Constructor para el administrador
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

		cargarTabla(datosAdmin, null);
	}
	//Consttructor para el cliente porque tiene mas opciones que el administrador
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

		JLabel lblPrecioMin = new JLabel("Precio Min:");
		lblPrecioMin.setBounds(517, 218, 103, 16);
		getContentPane().add(lblPrecioMin);

		txtPrecioMin = new JTextField();
		txtPrecioMin.setColumns(10);
		txtPrecioMin.setBounds(507, 245, 138, 20);
		getContentPane().add(txtPrecioMin);

		txtPrecioMax = new JTextField();
		txtPrecioMax.setColumns(10);
		txtPrecioMax.setBounds(507, 302, 138, 20);
		getContentPane().add(txtPrecioMax);

		JLabel lblPrecioMax = new JLabel("Precio Max:");
		lblPrecioMax.setBounds(517, 275, 103, 16);
		getContentPane().add(lblPrecioMax);

		cargarTabla(null, datosCliente);
		productosCesta = new ArrayList<Producto>();
	}
	//Este metodo sirve para crear la tabla y cargar sus datos
	private void cargarTabla(InterfazAdministrador datosAdmin, InterfazCliente datosCliente) {
		// Tabla
		String[] cabeceras = { "Nombre", "Tipo", "Precio", "Valoracion" };
		String[] fila = new String[4];
		List<Valora> valoraciones = null;

		dtm = new DefaultTableModel(null, cabeceras);
		if (datosAdmin == null) {
			productos = datosCliente.listarProductos();
			valoraciones = datosCliente.listarValoraciones();
		}
		if (datosCliente == null) {
			productos = datosAdmin.listarProductos();
			valoraciones = datosAdmin.listarValoraciones();
		}
		
		//Cargo el map con los datos de los productos y valoraciones
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
		scroll.setBounds(20, 20, 420, 250);
		getContentPane().add(scroll, BorderLayout.CENTER);
		table.addMouseListener(this);
	}

	//Este metodo sirve para cargar el map para poder mostrar la informacion en la tabla, los parametros que se le pasa son dos listas, una con las valoraciones y otra con los productos
	private Map<String, ListarTablaProductos> cargarLista(List<Producto> productos, List<Valora> valoraciones) {
		Map<String, ListarTablaProductos> listar = new TreeMap<String, ListarTablaProductos>();
		int contador = 0;

		/*Este for recorre las valoraciones por cada producto para juntar los datos en un map, 
		*Creando una nueva linea en el caso de que no este en el listado pero si que tenga una valoracion asignandole la valoracion correspondiente desde la lista de valoraciones y el resto de datos de los productos
		*En el caso de que exita la valoracion del producto y ademas esta ya en la lista se le suma la valoracion del producto a la linea del listado para posteriormente sacar una media de las valoraciones
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
			// En el caso de que no exista valoraciones de ese producto ni tampoco este en la lista entonces se crea una nueva linea con los datos del producto y se le asigna como valoracion 0
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
			/*
			 * VCesta vCesta = new VCesta(this, true, usuario, datosCliente);
			 * vCesta.setVisible(true);
			 */

		}

		if (e.getSource().equals(btnFiltrar)) {

		}
		if (e.getSource().equals(btnMasVendidos)) {

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
		
		System.out.println("Entra metodo");
		if (datosCliente.comprobarCestaActiva(usuario.getDni()) == null) {
			cesta = new Cesta();
			cesta.setCodCesta(calcularIdCesta());
			cesta.setEstado(false);
			datosCliente.añadirCesta(cesta);
			System.out.println("Entra if 1");
			
			producto.setNombre(String.valueOf(dtm.getValueAt(filaSeleccionada, 0)));
			producto.setCodProducto(obtenerCodigo(listarProductos, producto.getNombre()));
			producto.setPrecio(Double.parseDouble(String.valueOf(dtm.getValueAt(filaSeleccionada, 2))));
			producto.setStock(obtenerStock(producto));
			if(producto.getStock() == 0) {
				JOptionPane.showMessageDialog(null, "No se puede añadir el producto porque no tiene stock", null, JOptionPane.ERROR_MESSAGE);
			} else {
				if (datosCliente.comprobarProductoRepetido(usuario.getDni(), producto.getCodProducto())) {
					JOptionPane.showMessageDialog(null, "Error, no se puede introducir el mismo producto mas de una vez en una misma cesta", null, JOptionPane.ERROR_MESSAGE);
				} else {
					
					añade = new Añade();
					añade.setCodCesta(cesta.getCodCesta());
					añade.setDni(usuario.getDni());
					añade.setIdProducto(producto.getCodProducto());
					System.out.println(producto.getCodProducto());
					System.out.println(añade.getIdProducto());
					
					datosCliente.añadirProductoAAñade(añade);
				}
			}
			
		} 
		
		else{
			if (JOptionPane.showConfirmDialog(null, "Quieres añadir el producto a la cesta?", null,
					JOptionPane.YES_NO_OPTION) == 0) {
				System.out.println("Entra if 2");
				producto.setNombre(String.valueOf(dtm.getValueAt(filaSeleccionada, 0)));
				producto.setCodProducto(obtenerCodigo(listarProductos, producto.getNombre()));
				producto.setPrecio(Double.parseDouble(String.valueOf(dtm.getValueAt(filaSeleccionada, 2))));
				producto.setStock(obtenerStock(producto));
				if(producto.getStock() == 0) {
					JOptionPane.showMessageDialog(null, "No se puede añadir el producto porque no tiene stock", null, JOptionPane.ERROR_MESSAGE);
				} else {
					if (datosCliente.comprobarProductoRepetido(usuario.getDni(), producto.getCodProducto())) {
						JOptionPane.showMessageDialog(null, "Error, no se puede introducir el mismo producto mas de una vez en una misma cesta", null, JOptionPane.ERROR_MESSAGE);
					} else {
						añade = new Añade();
						añade.setCodCesta(datosCliente.comprobarCestaActiva(usuario.getDni()));
						añade.setDni(usuario.getDni());
						añade.setIdProducto(producto.getCodProducto());
						
						datosCliente.añadirProductoAAñade(añade);
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

}
