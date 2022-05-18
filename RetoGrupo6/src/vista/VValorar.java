package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clases.Cliente;
import clases.Producto;
import modelo.InterfazCliente;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Sirve para que el cliente pueda valorar los productos que ya ha comprado
 * 
 * @author grupo6
 * @version 1
 */
public class VValorar extends JDialog implements ActionListener {
	/**
	 * En el se guarda el valor de la valoración que le quiera dar al producto
	 */
	private JComboBox<Integer> cmbxValoracion;
	/**
	 * Al pulsarlo se volverá a la ventana anterior cerrando la actual
	 */
	private JButton btnAtras;
	/**
	 * Al pulsarlo se mandarán los datos de la valoración seleccionada y a que
	 * producto.
	 */
	private JButton btnConfirmacion;
	/**
	 * En el se guardan los productos que el cliente ya ha comprado para que los
	 * seleccione y los valore
	 */
	private JComboBox<String> cmbxProductos;
	/**
	 * Se igualará a la interfaz que viene por parámetro para poder utilizarla fura
	 * del constructor
	 */
	private InterfazCliente datosCliente;
	/**
	 * Se utiliza para guradar el dni del usuario que va ha realizar la valoración
	 */
	private String dni;
	/**
	 * En la lista se guardarán los productos comprados por el cliente y se
	 * guardaran el el comboBox correspondiente.
	 */
	private List<Producto> listaProductos;

	/**
	 * 
	 * @param vMenuCliente Es la ventana de la que viene
	 * @param b            Indica si el modal estrá activado o desactivado
	 * @param usuario      Son los datos del usuario que ha iniciado sesión
	 * @param datosCliente Es la interfaz que se utilizará en caso de necesitar
	 *                     utilizar la base datos.
	 */
	public VValorar(VMenuCliente vMenuCliente, boolean b, Cliente usuario, InterfazCliente datosCliente) {
		super(vMenuCliente);
		this.setModal(b);
		this.datosCliente = datosCliente;
		this.dni = usuario.getDni();

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("PRODUCTO A VALORAR:");
		lblNewLabel.setBounds(35, 80, 138, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblValoracion = new JLabel("VALORACION:");
		lblValoracion.setBounds(35, 124, 138, 14);
		getContentPane().add(lblValoracion);

		cmbxValoracion = new JComboBox<Integer>();
		cmbxValoracion.setBounds(196, 120, 117, 22);
		cmbxValoracion.addItem(1);
		cmbxValoracion.addItem(2);
		cmbxValoracion.addItem(3);
		cmbxValoracion.addItem(4);
		cmbxValoracion.addItem(5);
		getContentPane().add(cmbxValoracion);

		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.setBounds(23, 217, 103, 33);
		btnAtras.addActionListener(this);
		getContentPane().add(btnAtras);

		btnConfirmacion = new JButton("CONFIRMACION");
		btnConfirmacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirmacion.setBounds(236, 217, 151, 33);
		btnConfirmacion.addActionListener(this);
		getContentPane().add(btnConfirmacion);

		cmbxProductos = new JComboBox<String>();
		cmbxProductos.setBounds(196, 76, 117, 22);
		listaProductos = datosCliente.listarProductosComprados(usuario.getDni());
		for (int i = 0; i < listaProductos.size(); i++) {
			cmbxProductos.addItem(listaProductos.get(i).getNombre());
		}
		getContentPane().add(cmbxProductos);

	}

	/**
	 * Permite que al pulsar un elemnto indicado de la ventan se haga una acción
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}

		if (e.getSource().equals(btnConfirmacion)) {

			// Comprueba si el cliente ha comprado el producto para poder valorarlo o
			// actualizar la valoracion
			if (cmbxProductos.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Usted no ha seleccionado ningun producto", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (datosCliente.comprobarValoracion(dni, sacarCodigo())) {
					datosCliente.actualizarValoracion((int) cmbxValoracion.getSelectedItem(), sacarCodigo(), dni);
					JOptionPane.showMessageDialog(null, "La valoración se ha actualizado correctamente", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					datosCliente.insertarValoracion(sacarCodigo(), dni, (int) cmbxValoracion.getSelectedItem());
					JOptionPane.showMessageDialog(null, "La valoración se ha guardado correctamente", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}
	}

	/**
	 * Obtiene el codigo del producto que ha seleccionado en el comboBox
	 * 
	 * @return Devuelve el código del producto seleccionado
	 */
	public String sacarCodigo() {
		for (int i = 0; i < listaProductos.size(); i++) {
			if (listaProductos.get(i).getNombre().equalsIgnoreCase((String) cmbxProductos.getSelectedItem())) {
				return listaProductos.get(i).getCodProducto();
			}
		}
		return null;
	}

}
