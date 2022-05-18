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

/**
 * En esta ventana se mostrará el historial de las compras que ha realizado el cliente seleccionado o el cliente que entre a la ventana
 * @author grupo6
 * @version 1
 */
public class VHistorialCompras extends JDialog implements ActionListener {
	/**
	 * Al pulsarlo cerrará la ventana e irá a la ventana anterior
	 */
	private JButton btnAtras;
	/**
	 * En la lista se guardarán los atributos de las compras que queremos mostrar en la tabla
	 */
	private List<ListarTablaHistorial> historial;
	/**
	 * Se usa para definir el modelo de la tabla que queremos
	 */
	private DefaultTableModel dtm;
	/**
	 * Crea una tabla para mostrar los productos comprados del cliente
	 */
	private JTable table;
	/**
	 * Se igualará al la variable usuario que llega por parámetro para poder utilizarla fuera del constructor.
	 */
	private Usuario usuario;

	/**
	 * 
	 * @param vSelecCliente Es la ventana de la que viene
	 * @param b Indica si el modal estará activo o no.
	 * @param datosAdmin Es la interfaz que usaremos en caso de necesitar la base datos
	 * @param dni Es el DNI del cliente seleccionado
	 * @param datosAmbos Es la interfaz que usaremos en caso de necesitar la base de datos
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
			table.setEnabled(false);
		
		
	}

	/**
	 * 
	 * @param vMenuCliente Es la ventana de la que viene
	 * @param b Indica si el modal estará activado o desactivado 
	 * @param usuario Son los datos del cliente que ha iniciado sesión.
	 * @param datosCliente Es la interfaz que usaremos en caso de necesitar utilizar la base de datos
	 * @param datosAmbos Es la interfaz que usaremos en caso de necesitar utilizar la base de datos
	 */
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
				if(historial.get(i).getFecha() != null) {
					fila[2] = historial.get(i).getFecha().toString();
				} else {
					fila[2] = "";
				}
				fila[3] = historial.get(i).getEstado();

				dtm.addRow(fila);
			}

			table = new JTable(dtm);
			JScrollPane scroll = new JScrollPane(table);
			table.setBounds(50, 10, 420, 250);
			scroll.setViewportView(table);
			scroll.setBounds(20, 20, 441, 221);
			getContentPane().add(scroll, BorderLayout.CENTER);
			table.setEnabled(false);;
		


	}

	/**
	 * Permite que al pulsar algún elemento de la ventana haga una acción concreta
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}

	}

}
