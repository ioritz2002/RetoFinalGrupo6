package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class VProductos extends JDialog {
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

	
	public VProductos() {
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
		
		btnMasVendidos = new JButton("MAS VENDIDOS");
		btnMasVendidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMasVendidos.setBounds(320, 369, 160, 33);
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
		lblNewLabel.setBounds(320, 86, 103, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPorTipo = new JLabel("Por tipo:");
		lblPorTipo.setBounds(320, 132, 103, 16);
		getContentPane().add(lblPorTipo);
		
		cmbxTipos = new JComboBox();
		cmbxTipos.setBounds(320, 159, 138, 22);
		getContentPane().add(cmbxTipos);
		
		JLabel lblPrecioMin = new JLabel("Precio Min:");
		lblPrecioMin.setBounds(320, 192, 103, 16);
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
		lblPrecioMax.setBounds(320, 247, 103, 16);
		getContentPane().add(lblPrecioMax);
		
		/*Tabla
		Aqui se ponen las cabeceras y cuantas columnas va a tener la tabla
		String[] nombreColumnas = {"CAMPO1", "CAMPO2", "CAMPO3", "CAMPO4"};
		String[] fila = new String[4];
		
		dtm = new DefaultTableModel(null, nombreColumnas);
		Aqui se carga en una coleccion todos los datos de los productos
		 * Clase se refiere a una de las clases de nuestro proyecto
		 * Set<Clase> productosTabla = aqui va el metodo que carga los datos de los productos en la implementacion;
		 * 
		 * foreach para cargar la tabla
		 * for(){
		 * 	fila[0]= getDato();
		 * 	fila[1]= getDato();
		 * 	fila[2]= getDato();
		 * 	fila[3]= getDato();
		 * 
		 * 	Aqui le decimos que nos añada una fila a la tabla con los datos previamente introducidos
		 * 	dtm.addRow(fila);
		 * }
		 * 
		 *Se crea la tabla con el modelo dtm
		 * table = new JTable(dtm);
		 * 
		 * Se le añade un scrol a la tabla
		 * JScrollPane scroll = new JScrollPane(table);
		 * 
		 * Se le pone las posiciones y el tamaño
		 * table.setBounds()
		 * 
		 * Se le añade el evento de raton
		 * y despues se realiza la siguiente accion
		 * scroll.setViewportView(table);
		 *	
		 * Despues se le pone el tamaño y posiciones al scroll y se añade al panel con
		 * scroll.setBounds();
		 * getContentPane().add(scroll, BorderLayout.CENTER);
		*/
		
	}
}
