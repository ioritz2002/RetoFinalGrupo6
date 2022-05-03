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
	
	public VProductos(VMenuAdministrador vMenuAdministrador, boolean b, Usuario usuario, InterfazAdministrador datosAdmin) {
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

	public VProductos(VMenuCliente vMenuCliente, boolean b, Usuario usuario, InterfazCliente datosCliente) {
		super(vMenuCliente);
		this.setModal(b);
		this.datosCliente = datosCliente;

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
		
		

		Map<String, ListarTablaProductos> listaProductosMap = cargarLista(productos, valoraciones);
		System.out.println(listaProductosMap.size());
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
		if(e.getSource().equals(btnCarrito)) {
			/*VCesta vCesta = new VCesta(this, true, usuario, datosCliente);
			vCesta.setVisible(true);*/
			Cesta cesta = new Cesta();
			Añade añade = new Añade();
			
			cesta.setCodCesta(calcularId());
			añade.setCodCesta(cesta.getCodCesta());
			cesta.setEstado(false);
			cesta.setFechaCompra(LocalDate.now());
			//Queda terminar esto
		}
		
		if(e.getSource().equals(btnFiltrar)) {
			
		}
		if(e.getSource().equals(btnMasVendidos)) {
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(table)) {
			if (usuario instanceof Usuario && usuario.getTipo().equalsIgnoreCase("administrador")) {
				seleccionAdmin(listarProductos);
			}
			if (usuario instanceof Cliente) {
				Producto producto = null;
				producto = seleccionCliente(listarProductos);
				if (producto != null) {
					productosCesta.add(producto);
				}
			}
		}

	}
	
	private String calcularId() {
		int cant= datosCliente.calcularCodCesta() +1;
		String cod;
		cant= 0000+cant;
		if (cant>=1000) {
			cod= "RP-"+cant;
		}else if(cant>=100) {
			cod= "RP-0"+cant;
		}else if(cant>=10){
			cod="RP-00"+cant;
		}else {
			cod="RP-000"+cant;
		}
		return cod;
	}

	private Producto seleccionCliente(List<ListarTablaProductos> listarProductos2) {
		Producto producto = new Producto();
		int filaSeleccionada = table.getSelectedRow();
		
		
		if (JOptionPane.showConfirmDialog(null, "Quieres añadir el producto a la cesta?", null, JOptionPane.YES_NO_OPTION) == 0) {
			producto.setNombre(String.valueOf(dtm.getValueAt(filaSeleccionada, 0)));
			producto.setCodProducto(obtenerCodigo(listarProductos, producto.getNombre()));
			producto.setTipo(String.valueOf(dtm.getValueAt(filaSeleccionada, 1)));
			producto.setPrecio(Double.parseDouble(String.valueOf(dtm.getValueAt(filaSeleccionada, 2))));
			producto.setStock(obtenerStock(producto));
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
