package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class VCesta extends JDialog {
	private JTextField txtPrecioTotal;

	public VCesta() {
		setBounds(100, 100, 501, 335);
		getContentPane().setLayout(null);
		
		JLabel lblPrecioTotal = new JLabel("Precio Total");
		lblPrecioTotal.setBounds(353, 64, 72, 14);
		getContentPane().add(lblPrecioTotal);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setBounds(353, 86, 86, 20);
		getContentPane().add(txtPrecioTotal);
		txtPrecioTotal.setColumns(10);
		
		JButton btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.setBounds(33, 241, 103, 33);
		getContentPane().add(btnAtras);
		
		JButton btnCancelarCompra = new JButton("CANCELAR COMPRA");
		btnCancelarCompra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelarCompra.setBounds(179, 232, 141, 52);
		getContentPane().add(btnCancelarCompra);
		
		JButton btnComprar = new JButton("COMPRAR");
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnComprar.setBounds(350, 241, 103, 33);
		getContentPane().add(btnComprar);
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
