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
import clases.ListarTablaHistorial;
import clases.Usuario;
import modelo.InterfazAdministrador;
import modelo.InterfazAmbosUsuarios;
import modelo.InterfazCliente;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class VHistorialCompras extends JDialog implements ActionListener {
	private JButton btnAtras;
	private InterfazAmbosUsuarios datosAmbos;
	private List<ListarTablaHistorial> historial;
	private Usuario usuario;
	private DefaultTableModel dtm;
	private JTable table;

	/**
	 * @wbp.parser.constructor
	 */
	public VHistorialCompras(VSelecCliente vSelecCliente, boolean b, InterfazAdministrador datosAdmin, String dni,
			InterfazAmbosUsuarios datosAmbos) {
		super(vSelecCliente);
		this.setModal(b);
		setBounds(100, 100, 501, 335);
		getContentPane().setLayout(null);

		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.addActionListener(this);
		btnAtras.setBounds(30, 252, 103, 33);
		getContentPane().add(btnAtras);

		// * Tabla Aqui se ponen las cabeceras y cuantas columnas va a tener la tabla
		String[] nombreColumnas = { "Codigo Cesta", "Importe", "Fecha", "Estado" };
		String[] fila = new String[4];

		dtm = new DefaultTableModel(null, nombreColumnas);
		historial = datosAmbos.buscarCestas(dni);

		if (historial.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El cliente seleccionado no ha realiado ninguna compra y no tiene ninguna compra en curso",
					"No se han encontrado datos", JOptionPane.OK_OPTION);
			
		}
			for (int i = 0; i < historial.size(); i++) {
				fila[0] = historial.get(i).getCodigo();
				fila[1] = String.valueOf(historial.get(i).getImporte());
				fila[2] = historial.get(i).getFecha().toString();
				fila[3] = historial.get(i).getEstado();

				dtm.addRow(fila);
			}

			table = new JTable(dtm);
			JScrollPane scroll = new JScrollPane(table);
			table.setBounds(50, 10, 420, 250);
			scroll.setViewportView(table);
			scroll.setBounds(20, 20, 441, 221);
			getContentPane().add(scroll, BorderLayout.CENTER);
		
		// Aqui se carga en una
		// coleccion todos los datos de los productos Clase se refiere a una de las
		// clases de nuestro proyecto Set<Clase> productosTabla = aqui va el metodo que
		// carga los datos de los productos en la implementacion;
		//
		// foreach para cargar la tabla for(){ fila[0]= getDato(); fila[1]= getDato();
		// fila[2]= getDato(); fila[3]= getDato();
		//
		// Aqui le decimos que nos añada una fila a la tabla con los datos previamente
		// introducidos dtm.addRow(fila); }
		//
		// Se crea la tabla con el modelo dtm table = new JTable(dtm);
		//
		// Se le añade un scrol a la tabla JScrollPane scroll = new JScrollPane(table);
		//
		// Se le pone las posiciones y el tamaño table.setBounds()
		//
		// Se le añade el evento de raton y despues se realiza la siguiente accion
		// scroll.setViewportView(table);
		//
		// Despues se le pone el tamaño y posiciones al scroll y se añade al panel con
		// scroll.setBounds(); getContentPane().add(scroll, BorderLayout.CENTER);
		//
	}

	public VHistorialCompras(VMenuCliente vMenuCliente, boolean b, Cliente usuario, InterfazCliente datosCliente,
			InterfazAmbosUsuarios datosAmbos) {
		super(vMenuCliente);
		this.setModal(b);
		this.usuario = usuario;
		setBounds(100, 100, 501, 335);
		getContentPane().setLayout(null);

		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.addActionListener(this);
		btnAtras.setBounds(30, 252, 103, 33);
		getContentPane().add(btnAtras);

		// Tabla Aqui se ponen las cabeceras y cuantas columnas va a tener la tabla
		String[] columnasHistorial = { "Codigo Cesta", "Importe", "Fecha", "Estado" };
		String[] fila = new String[4];

		dtm = new DefaultTableModel(null, columnasHistorial);
		historial = datosAmbos.buscarCestas(usuario.getDni());

		if (historial.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Usted no ha realiado ninguna compra y no tiene ninguna compra en curso",
					"No se han encontrado datos", JOptionPane.OK_OPTION);
			
		} 
			for (int i = 0; i < historial.size(); i++) {
				fila[0] = historial.get(i).getCodigo();
				fila[1] = String.valueOf(historial.get(i).getImporte());
				fila[2] = historial.get(i).getFecha().toString();
				fila[3] = historial.get(i).getEstado();

				dtm.addRow(fila);
			}

			table = new JTable(dtm);
			JScrollPane scroll = new JScrollPane(table);
			table.setBounds(50, 10, 420, 250);
			scroll.setViewportView(table);
			scroll.setBounds(20, 20, 441, 221);
			getContentPane().add(scroll, BorderLayout.CENTER);
		

		// Aqui se carga en una
		// coleccion todos los datos de los productos Clase se refiere a una de las
		// clases de nuestro proyecto Set<Clase> productosTabla = aqui va el metodo que
		// carga los datos de los productos en la implementacion;

		// foreach para cargar la tabla for(){ fila[0]= getDato(); fila[1]= getDato();
		// fila[2]= getDato(); fila[3]= getDato();

		// Aqui le decimos que nos añada una fila a la tabla con los datos previamente
		// introducidos dtm.addRow(fila); }

		// Se crea la tabla con el modelo dtm table = new JTable(dtm);

		// Se le añade un scrol a la tabla JScrollPane scroll = new JScrollPane(table);

		// Se le pone las posiciones y el tamaño table.setBounds()

		// Se le añade el evento de raton y despues se realiza la siguiente accion
		// scroll.setViewportView(table);

		// Despues se le pone el tamaño y posiciones al scroll y se añade al panel con
		// scroll.setBounds(); getContentPane().add(scroll, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}

	}

}
