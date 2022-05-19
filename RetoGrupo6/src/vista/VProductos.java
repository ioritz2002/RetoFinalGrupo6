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


/**
 * Esta clase es una ventana en la que se mostrara una tabla y los filtros a elegir para los productos que se muestran en la tabla,
 * Desde esta ventana se puede añadir productos al carrito y se puede seleccionar un producto para modificarlo o borrarlo.
 * @author grupo6
 * @version 1
 * 
 *
 */
public class VProductos extends JDialog implements ActionListener, MouseListener {
	/**
	 * Crea una tabla para mostrar los productos
	 */
	private JTable table;
	/**
	 * Este atributo se usa para definir el modelo de tabla que usaremos, que en este caso sera uno por defecto
	 */
	private DefaultTableModel dtm;
	/**
	 * En este campo se escribira el nombre de un producto para filtrar la tabla.
	 */
	private JTextField txtNombre;
	/**
	 * Este boton sirve para cerrar esta ventana.
	 */
	private JButton btnAtras;
	/**
	 * Este boton lleva a la ventana de cesta para poder ver los productos de la cesta.
	 */
	private JButton btnCarrito;
	/**
	 * Este boton filtra la tabla y muestro los 3 productos mas vendidos de todos.
	 */
	private JButton btnMasVendidos;
	/**
	 * En este combo box saldran los tipos de productos que tenemos y se podra seleccionar uno para filtrar la tabla.
	 */
	private JComboBox cmbxTipos;
	/**
	 * Este boton aplica los filtros introducidos en los otros campos sobre la tabla.
	 */
	private JButton btnFiltrar;
	/**
	 * Esta es la interfaz de datos del administrador
	 */
	private InterfazAdministrador datosAdmin;
	/**
	 * Esta es la interfaz de datos del cliente
	 */
	private InterfazCliente datosCliente;
	/**
	 *  Este es un objeto de usuario para poder saber que usuario estamos usando
	 */
	private Usuario usuario;
	/** 
	 * Esta lista es para poder cargar los datos de los productos
	 */
	private List<ListarTablaProductos> listarProductos;
	/**
	 * Esta lista es para poder cargar los productos para poder cargar el map para cargar la tabla
	 */
	private List<Producto> productos;
	/**
	 * Esta lista es para poder cargar los productos que se añaden a la cesta cuando se selecciona un producto
	 */
	private List<Producto> productosCesta;
	/**
	 * Esta lista sirve para cargar las valoraciones desde la base de datos
	 */
	private List<Valora> valoraciones;
	/**
	 * Este array es para poder cargar los datos por cada columna
	 */
	private String[] fila;
	/**
	 * Este array contiene las cabeceras de la tabla 
	 */
	private String[] cabeceras = { "Nombre", "Tipo", "Precio", "Valoracion" };
	/**
	 * Este radio button es para poder seleccionar un rango de precio, en este caso: Mas de 200
	 */
	private JRadioButton rdbtnMAS200;
	/**
	 * Este radio button es para poder seleccionar un rango de precio, en este caso: Entre 100 y 200
	 */
	private JRadioButton rdbtn100A200;
	/**
	 * Este radio button es para poder seleccionar un rango de precio, en este caso: Entre 50 y 100
	 */
	private JRadioButton rdbtn50A100;
	/**
	 * Este radio button es para poder seleccionar un rango de precio, en este caso: Entre 0 y 50
	 */
	private JRadioButton rdbtn0A50;
	/**
	 * Este grupo de botones agrupa los radio button
	 */
	private ButtonGroup grupoRadio;
	/**
	 * Esta lista es para poder cargar los tipos de productos para el combo box
	 */
	private List<String> tiposProductos;


	/**
	 * Este constructor es para cuando el administrador entre a esta ventana. Aqui todos los filtros excepto el de los mas vendidos no aparecen.
	 * @param vMenuAdministrador Es la ventana padre, es decir la ventana desde la que se abre esta
	 * @param b Este paramentro boolean indica si esta ventana sera modal o no.
	 * @param usuario Este paramentro de tipo Usuario indica que tipo de usuario ha entrado ha esta ventana
	 * @param datosAdmin Es la interfaz que utilizara el administrador para poder acceder en caso de necesitar usar la base de datos
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
		btnMasVendidos.addActionListener(this);

		cargarTabla(datosAdmin, null, valoraciones);
	}

	/**
	 * Este constructor es para cuento el cliente entra a esta ventana. Aqui aparecen todas las opciones.
	 * @param vMenuCliente Es la ventana desde la que se ha abierto esta. 
	 * @param b Este paramentro boolean indica si esta ventana sera modal o no.
	 * @param usuario Este parametro de tipo Usuario indica que tipo de usuario ha entrado ha esta ventana.
	 * @param datosCliente Es la interfaz que utilizara el cliente para poder acceder en caso de necestiar usar la base de datos.
	 */
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
		
		//En este for se coje todos los productos y se va cargando los tipos de los productos en un ArrayList sin que se repitan
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
		
		//Se carga este ultimo ArrayList en el combobox
		for (String t : tiposProductos) {
			cmbxTipos.addItem(t);
		}

		cmbxTipos.setSelectedIndex(-1);
	}

	/**
	 * Este metodo sirve para cargar la tabla con los datos nada mas abrir la ventana.
	 * @param datosAdmin Este parametro es para poder acceder desde el administrador a la base de datos, sera null si se ha entrado como cliente.
	 * @param datosCliente Este parametro es para poder acceder desde el cliente a la base de datos, sera null si se ha entrado como administrador.
	 * @param valoraciones Este paramentro es una lista de las valoraciones para poder cargar en la tabla.
	 */
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
			fila[2] = String.valueOf(listarProductos.get(i).getPrecio()) + "€";
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

	
	
	/**
	 * Este metodo sirve para cargar el map para poder mostrar la informacion en la tabla, los parametros que se le pasa son dos listas, una con las valoraciones <br/> y otra con los productos
	 * 
	 * @param productos Este parametro es una lista con todos los productos
	 * @param valoraciones Este parametro es una lista con todas las valoraciones
	 * @return Este metodo retorna un map con los productos y sus valoraciones medias.
	 */
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
			/* En el caso de que no exista valoraciones de ese producto ni tampoco este en
			la lista entonces se crea una nueva linea con los datos del producto y se le
			asigna como valoracion 0*/
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
		
		//Aqui se saca la media de las valoraciones por cada producto a partir de la suma de las valoraciones de cada producto anteriormente calculada
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

	/**
	 * Este metodo ejecuta los eventos en los botones indicados cuando son pulsados.
	 */
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

	/**
	 * Este metodo aplica los filtros cuando se pulsa el boton de filtrar teniendo en cuenta que filtro se ha seleccionado.
	 */
	private void filtros() {
		valoraciones = datosCliente.listarValoraciones();
		Map<String, ListarTablaProductos> listaTabla = null;
		List<Producto> productosRango = null;

		//Esta opcion filtra los productos que cuestan entre 0 y 50 euros
		if (rdbtn0A50.isSelected()) {
			vaciarTabla();
			productosRango = datosCliente.listarProductosFiltradoPrecio(0, 50);

			listaTabla = cargarLista(productosRango, valoraciones);

			for (ListarTablaProductos p : listaTabla.values()) {
				fila[0] = p.getNombreProducto();
				fila[1] = p.getTipoProducto();
				fila[2] = String.valueOf(p.getPrecio()) + "€";
				fila[3] = String.valueOf(p.getValoracion());

				dtm.addRow(fila);
			}
		}
		//Esta opcion filtra los productos que cuestan entre 50 y 100 euros
		if (rdbtn50A100.isSelected()) {
			vaciarTabla();
			productosRango = datosCliente.listarProductosFiltradoPrecio(50, 100);

			listaTabla = cargarLista(productosRango, valoraciones);

			for (ListarTablaProductos p : listaTabla.values()) {
				fila[0] = p.getNombreProducto();
				fila[1] = p.getTipoProducto();
				fila[2] = String.valueOf(p.getPrecio()) + "€";
				fila[3] = String.valueOf(p.getValoracion());

				dtm.addRow(fila);
			}
		}
		//Esta opcion filtra los productos que cuestan entre 100 y 200 euros
		if (rdbtn100A200.isSelected()) {
			vaciarTabla();
			productosRango = datosCliente.listarProductosFiltradoPrecio(100, 200);

			listaTabla = cargarLista(productosRango, valoraciones);

			for (ListarTablaProductos p : listaTabla.values()) {
				fila[0] = p.getNombreProducto();
				fila[1] = p.getTipoProducto();
				fila[2] = String.valueOf(p.getPrecio()) + "€";
				fila[3] = String.valueOf(p.getValoracion());

				dtm.addRow(fila);
			}
		}
		
		//Esta opcion filtra los productos que cuestan mas de 200 euros
		if (rdbtnMAS200.isSelected()) {
			vaciarTabla();
			productosRango = datosCliente.listarProductosFiltradoPrecio(200, 1000000);

			listaTabla = cargarLista(productosRango, valoraciones);

			for (ListarTablaProductos p : listaTabla.values()) {
				fila[0] = p.getNombreProducto();
				fila[1] = p.getTipoProducto();
				fila[2] = String.valueOf(p.getPrecio()) + "€";
				fila[3] = String.valueOf(p.getValoracion());

				dtm.addRow(fila);
			}
		}
		
		//Esta opcion aolica el filtro de tipo seleccionado en el combo box comprobando antes que no este seleccionado la opcion vacia
		if (cmbxTipos.getSelectedIndex() > -1) {
			vaciarTabla();
			String tipoSeleccionado = cmbxTipos.getSelectedItem().toString();

			for (int i = 0; i < listarProductos.size(); i++) {
				if (listarProductos.get(i).getTipoProducto().equalsIgnoreCase(tipoSeleccionado)) {
					fila[0] = listarProductos.get(i).getNombreProducto();
					fila[1] = listarProductos.get(i).getTipoProducto();
					fila[2] = String.valueOf(listarProductos.get(i).getPrecio()) + "€";
					fila[3] = String.valueOf(listarProductos.get(i).getValoracion());

					dtm.addRow(fila);
				}
			}
		}
		//Esta opcion aplica el filtro de buscar por nombre comprobando primero que el texto no es null y que no este vacio
		if (txtNombre.getText() != null && !txtNombre.getText().equalsIgnoreCase("")) {
			vaciarTabla();
			String nombre = txtNombre.getText();

			for (int i = 0; i < listarProductos.size(); i++) {
				if (listarProductos.get(i).getNombreProducto().toUpperCase().contains(nombre.toUpperCase())) {
					fila[0] = listarProductos.get(i).getNombreProducto();
					fila[1] = listarProductos.get(i).getTipoProducto();
					fila[2] = String.valueOf(listarProductos.get(i).getPrecio()) + "€";
					fila[3] = String.valueOf(listarProductos.get(i).getValoracion());

					dtm.addRow(fila);
				}
			}
		}

	}
	/**
	 * Este metodo filtra por el producto mas vendido.
	 */
	private void masVendidos() {
		int contador = 0;
		int limite = 0;
		List<ListarTablaProductosMasVendidos> lista = null;
		ListarTablaProductosMasVendidos[] productosMasVendidos;
		//Si el usuario es un cliente se ejecuta la sentecia de la implementacion de cliente
		if (usuario instanceof Cliente) {
			valoraciones = datosCliente.listarValoraciones();
		}
		//Si el usuario es administrador se ejecuta la del administrador
		if (usuario instanceof Usuario && usuario.getTipo().equalsIgnoreCase("administrador")) {
			valoraciones = datosAdmin.listarValoraciones();
		}

		vaciarTabla();

		//En estas dos condiciones obtenemos los productos mas vendidos y los introducimos en la lista, comprobando el usuario ya que el metodo estara en distintas implementaciones.
		if (usuario instanceof Cliente) {
			lista = datosCliente.listarProductosMasVendidos();
		}
		if (usuario instanceof Usuario && usuario.getTipo().equalsIgnoreCase("administrador")) {
			lista = datosAdmin.listarProductosMasVendidosAdmin();
		}

		//Como solo queremos los 3 mas vendidos usaremos un array de 3 posiciones para introducir alli los productos
		productosMasVendidos = new ListarTablaProductosMasVendidos[3];
		limite = lista.size();
		
		//Si la lista no es null y tiene mas de 0 productos en ella continuara realizando las siguientes operaciones, si no no lo hara para evitar que de un error de puntero nulo
		if (lista != null && lista.size() > 0) {
			//Como queremos que no haya mas productos que 3 entonces si es mayor que 3 lo ponemos a 3
			if (limite > 3) {
				limite = 3;
			}
			//Pasamos de la lista al array
			for (int i = 0; i < limite; i++) {
				productosMasVendidos[i] = lista.get(i);
			}
			
			//Aqui sumamos las valoraciones de cada producto para posteriormente sacar la media
			for (int i = 0; i < productosMasVendidos.length; i++) {
				for (int j = 0; j < valoraciones.size(); j++) {
					if (productosMasVendidos[i] != null) {

						if (productosMasVendidos[i].getCodProducto().equalsIgnoreCase(valoraciones.get(j).getCodProducto()) && productosMasVendidos[i].getValoracion() > 0) {
							productosMasVendidos[i].setValoracion(productosMasVendidos[i].getValoracion() + valoraciones.get(j).getValoracion());
							contador++;
						}
						if (productosMasVendidos[i].getCodProducto().equalsIgnoreCase(
								valoraciones.get(j).getCodProducto()) && productosMasVendidos[i].getValoracion() == 0) {
							productosMasVendidos[i].setValoracion(valoraciones.get(j).getValoracion());
						}
					}

				}
				// Aqui sacamos la media de las valoraciones de cada producto
				if (contador > 0) {
					productosMasVendidos[i].setValoracion((Float) productosMasVendidos[i].getValoracion() / contador);
				}
			}

			for (int i = 0; i < productosMasVendidos.length; i++) {
				if(productosMasVendidos[i]!=null) {
					fila[0] = productosMasVendidos[i].getNombre();
					fila[1] = productosMasVendidos[i].getTipo();
					fila[2] = String.valueOf(productosMasVendidos[i].getPrecio()) + "€";
					fila[3] = String.valueOf(productosMasVendidos[i].getValoracion());

					dtm.addRow(fila);
				}
				
			}
		}

	}

	/**
	 * Este metodo ejecuta un evento cuando se pulsa sobre una fila de la tabla
	 */
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

	/**
	 * @hidden
	 */
	@Override 
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	/**
	 * @hidden
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	/**
	 * @hidden
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	/**
	 * @hidden
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Este metodo calcula el codigo de la cesta correspondiente a la proxima cesta
	 * @return Retorna el codigo de la cesta calculado en base a las cestas que hay en la base de datos
	 */
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
		System.out.println(cod);
		return cod;
	}
	/**
	 * Este metodo se ejecuta si la seleccion del producto la ha realizado un cliente. Este metodo añade el producto seleccion
	 * @param listarProductos Esta lista contiene todos los productos que se estan visualizando
	 * @return Retorna un producto
	 */

	private Producto seleccionCliente(List<ListarTablaProductos> listarProductos) {
		Producto producto = new Producto();
		int filaSeleccionada = table.getSelectedRow();
		Añade añade = null;
		Cesta cesta = null;
		double precio = 0;
		int posicionSinbolo;
		

		//Si el cliente no tiene cesta activa
		if (datosCliente.comprobarCestaActiva(usuario.getDni()) == null) {
			//Si el cliente confirma que quiere añadir el producto a la cesta
			if (JOptionPane.showConfirmDialog(null, "Quieres añadir el producto a la cesta?", null,
					JOptionPane.YES_NO_OPTION) == 0) {
				cesta = new Cesta();
				cesta.setCodCesta(calcularIdCesta());
				//El estado es false si esta activo y true si esta finalizado
				cesta.setEstado(false);
				datosCliente.añadirCesta(cesta);
				System.out.println("Entra if 1");

				producto.setNombre(String.valueOf(dtm.getValueAt(filaSeleccionada, 0)));
				producto.setCodProducto(obtenerCodigo(listarProductos, producto.getNombre()));
				posicionSinbolo = String.valueOf(dtm.getValueAt(filaSeleccionada, 2)).toString().indexOf("€");
				precio = Double.parseDouble(String.valueOf(dtm.getValueAt(filaSeleccionada, 2)).substring(0, posicionSinbolo));
				producto.setPrecio(precio);
				producto.setStock(obtenerStock(producto));
				if (producto.getStock() == 0) {
					JOptionPane.showMessageDialog(null, "No se puede añadir el producto porque no tiene stock", null,
							JOptionPane.ERROR_MESSAGE);
				} 
				//Si el producto tiene stock
				else {
					//Y el producto ha sido introducido anteriormente en la cesta se muestra un error.
					if (datosCliente.comprobarProductoRepetido(usuario.getDni(), producto.getCodProducto())) {
						JOptionPane.showMessageDialog(null,
								"Error, no se puede introducir el mismo producto mas de una vez en una misma cesta",
								null, JOptionPane.ERROR_MESSAGE);
					} else {
						//Si no esta en la cesta
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
		//Si el cliente tiene cesta activa
		else {
			if (JOptionPane.showConfirmDialog(null, "Quieres añadir el producto a la cesta?", null,
					JOptionPane.YES_NO_OPTION) == 0) {
				System.out.println("Entra if 2");
				producto.setNombre(String.valueOf(dtm.getValueAt(filaSeleccionada, 0)));
				producto.setCodProducto(obtenerCodigo(listarProductos, producto.getNombre()));
				posicionSinbolo = String.valueOf(dtm.getValueAt(filaSeleccionada, 2)).toString().indexOf("€");
				precio = Double.parseDouble(String.valueOf(dtm.getValueAt(filaSeleccionada, 2)).substring(0, posicionSinbolo));
				producto.setPrecio(precio);
				producto.setStock(obtenerStock(producto));
				if (producto.getStock() == 0) {
					JOptionPane.showMessageDialog(null, "No se puede añadir el producto porque no tiene stock", null,
							JOptionPane.ERROR_MESSAGE);
				} 
				//Si el producto tiene stock
				else {
					//Y el produto ha sido introducido en la cesta previamente entonces muestra un error.
					if (datosCliente.comprobarProductoRepetido(usuario.getDni(), producto.getCodProducto())) {
						JOptionPane.showMessageDialog(null,
								"Error, no se puede introducir el mismo producto mas de una vez en una misma cesta",
								null, JOptionPane.ERROR_MESSAGE);
					} else {
						//Si no esta en la cesta
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

	/**
	 * Este metodo se ejecuta cuando la seleccion de la fila la realiza el administrador
	 * @param listarProductos Este parametro es una lista que tiene los productos que se estan listando en la tabla.
	 */
	private void seleccionAdmin(List<ListarTablaProductos> listarProductos) {
		Producto producto = new Producto();
		int filaSeleccionada = table.getSelectedRow();
		int posicionSinbolo;
		double precio;
		

		producto.setNombre(String.valueOf(dtm.getValueAt(filaSeleccionada, 0)));
		producto.setCodProducto(obtenerCodigo(listarProductos, producto.getNombre()));
		producto.setTipo(String.valueOf(dtm.getValueAt(filaSeleccionada, 1)));
		posicionSinbolo = String.valueOf(dtm.getValueAt(filaSeleccionada, 2)).toString().indexOf("€");
		precio = Double.parseDouble(String.valueOf(dtm.getValueAt(filaSeleccionada, 2)).substring(0, posicionSinbolo));
		producto.setPrecio(precio);
		producto.setStock(obtenerStock(producto));

		VDatosProducto vDatos = new VDatosProducto(this, true, datosAdmin, producto);
		vDatos.setVisible(true);
		this.dispose();
	}

	/**
	 * Este metodo calcula el stock del producto seleccionado por el cliente
	 * @param producto El paramentro es un producto seleccionado por el cliente en la tabla
	 * @return Este metodo retorna el stock restante del producto seleccionado por el cliente
	 */
	private int obtenerStock(Producto producto) {
		int stock = -1;
		for (Producto p : productos) {
			if (p.getCodProducto().equalsIgnoreCase(producto.getCodProducto())) {
				stock = p.getStock();
			}
		}
		return stock;
	}

	/**
	 * Este metodo obtiene el codigo del producto buscado por nombre desde una lista
	 * @param listarProductos Este parametro es una lista que tiene los productos que se muestran en la tabla
	 * @param nombre Este paramentro es un String del nombre por el que busca el producto
	 * @return Este metodo retorna el codigo obtenido del producto encontrado por el nombre
	 */
	private String obtenerCodigo(List<ListarTablaProductos> listarProductos, String nombre) {
		String codigo = null;

		for (ListarTablaProductos i : listarProductos) {
			if (i.getNombreProducto().equalsIgnoreCase(nombre)) {
				codigo = i.getCodigoProducto();
			}
		}

		return codigo;
	}

	/**
	 * Este metodo vacia la tabla
	 */
	private void vaciarTabla() {
		int numFilas = dtm.getRowCount();
		
		for (int i = 0; i < numFilas; i++) {
			dtm.removeRow(0);
		}

	}
}