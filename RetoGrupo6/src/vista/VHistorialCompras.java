package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class VHistorialCompras extends JDialog {

	public VHistorialCompras() {
		setBounds(100, 100, 501, 335);
		getContentPane().setLayout(null);
		
		JButton btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.setBounds(33, 241, 103, 33);
		getContentPane().add(btnAtras);
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
		 * 	Aqui le decimos que nos a�ada una fila a la tabla con los datos previamente introducidos
		 * 	dtm.addRow(fila);
		 * }
		 * 
		 *Se crea la tabla con el modelo dtm
		 * table = new JTable(dtm);
		 * 
		 * Se le a�ade un scrol a la tabla
		 * JScrollPane scroll = new JScrollPane(table);
		 * 
		 * Se le pone las posiciones y el tama�o
		 * table.setBounds()
		 * 
		 * Se le a�ade el evento de raton
		 * y despues se realiza la siguiente accion
		 * scroll.setViewportView(table);
		 *	
		 * Despues se le pone el tama�o y posiciones al scroll y se a�ade al panel con
		 * scroll.setBounds();
		 * getContentPane().add(scroll, BorderLayout.CENTER);
		*/
	}

}
