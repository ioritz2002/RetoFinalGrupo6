package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.ListarTablaProductos;
import clases.Producto;
import clases.Usuario;
import clases.Valora;
import modelo.InterfazAdministrador;
import modelo.InterfazCliente;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

	/**
	 * @wbp.parser.constructor
	 */
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

		cargarTabla(datosAdmin);
	}
	
	public VProductos(VMenuCliente vMenuCliente, boolean b, InterfazCliente datosCliente, Usuario usuario) {
		super(vMenuCliente);
		this.setModal(b);
		this.datosCliente = datosCliente;

		setBounds(100, 100, 522, 453);
		getContentPane().setLayout(null);

		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.setBounds(26, 369, 103, 33);
		getContentPane().add(btnAtras);

		btnCarrito = new JButton("CARRITO");
		btnCarrito.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCarrito.setBounds(169, 369, 103, 33);
		getContentPane().add(btnCarrito);

		btnMasVendidos = new JButton("Mas vendidos");
		btnMasVendidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMasVendidos.setBounds(320, 369, 138, 33);
		getContentPane().add(btnMasVendidos);

		btnFiltrar = new JButton("FILTRAR");
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFiltrar.setBounds(320, 32, 138, 33);
		getContentPane().add(btnFiltrar);

		txtNombre = new JTextField();
		txtNombre.setBounds(320, 103, 138, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel = new JLabel("Por nombre:");
		lblNewLabel.setBounds(334, 86, 103, 16);
		getContentPane().add(lblNewLabel);

		JLabel lblPorTipo = new JLabel("Por tipo:");
		lblPorTipo.setBounds(330, 130, 103, 16);
		getContentPane().add(lblPorTipo);

		cmbxTipos = new JComboBox();
		cmbxTipos.setBounds(320, 159, 138, 22);
		getContentPane().add(cmbxTipos);

		JLabel lblPrecioMin = new JLabel("Precio Min:");
		lblPrecioMin.setBounds(334, 201, 103, 16);
		getContentPane().add(lblPrecioMin);

		txtPrecioMin = new JTextField();
		txtPrecioMin.setColumns(10);
		txtPrecioMin.setBounds(320, 216, 138, 20);
		getContentPane().add(txtPrecioMin);

		txtPrecioMax = new JTextField();
		txtPrecioMax.setColumns(10);
		txtPrecioMax.setBounds(320, 274, 138, 20);
		getContentPane().add(txtPrecioMax);

		JLabel lblPrecioMax = new JLabel("Precio Max:");
		lblPrecioMax.setBounds(334, 247, 103, 16);
		getContentPane().add(lblPrecioMax);

		/*
		 * Tabla Aqui se ponen las cabeceras y cuantas columnas va a tener la tabla
		 * String[] nombreColumnas = {"CAMPO1", "CAMPO2", "CAMPO3", "CAMPO4"}; String[]
		 * fila = new String[4];
		 * 
		 * dtm = new DefaultTableModel(null, nombreColumnas); Aqui se carga en una
		 * coleccion todos los datos de los productos Clase se refiere a una de las
		 * clases de nuestro proyecto Set<Clase> productosTabla = aqui va el metodo que
		 * carga los datos de los productos en la implementacion;
		 * 
		 * foreach para cargar la tabla for(){ fila[0]= getDato(); fila[1]= getDato();
		 * fila[2]= getDato(); fila[3]= getDato();
		 * 
		 * Aqui le decimos que nos añada una fila a la tabla con los datos previamente
		 * introducidos dtm.addRow(fila); }
		 * 
		 * Se crea la tabla con el modelo dtm table = new JTable(dtm);
		 * 
		 * Se le añade un scrol a la tabla JScrollPane scroll = new JScrollPane(table);
		 * 
		 * Se le pone las posiciones y el tamaño table.setBounds()
		 * 
		 * Se le añade el evento de raton y despues se realiza la siguiente accion
		 * scroll.setViewportView(table);
		 * 
		 * Despues se le pone el tamaño y posiciones al scroll y se añade al panel con
		 * scroll.setBounds(); getContentPane().add(scroll, BorderLayout.CENTER);
		 */

	}


	private void cargarTabla(InterfazAdministrador datosAdmin) {
		// Tabla
		String[] cabeceras = { "Nombre", "Tipo", "Precio", "Valoracion" };
		String[] fila = new String[4];

		dtm = new DefaultTableModel(null, cabeceras);
		productos = datosAdmin.listarProductos();
		List<Valora> valoraciones = datosAdmin.listarValoraciones();

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


	private Map<String, ListarTablaProductos> cargarLista(List<Producto> productos, List<Valora> valoraciones) {
		Map<String, ListarTablaProductos> listar = new TreeMap<String, ListarTablaProductos>();
		int contador = 0;
		
		for (int i = 0; i < productos.size(); i++) {
			contador = 0;
			for (int j = 0; j < valoraciones.size(); j++) {
				if (productos.get(i).getCodProducto().equalsIgnoreCase(valoraciones.get(j).getCodProducto())&& !listar.containsKey(productos.get(i).getCodProducto())) {
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

			} if(!listar.containsKey(productos.get(i).getCodProducto())) {
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

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(table)) {
			if (usuario instanceof Usuario && usuario.getTipo().equalsIgnoreCase("administrador")) {
				seleccionAdmin(listarProductos);
			}
		}
		
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
