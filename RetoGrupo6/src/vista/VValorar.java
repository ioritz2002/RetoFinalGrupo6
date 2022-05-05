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

public class VValorar extends JDialog implements ActionListener {
	private JComboBox<Integer> cmbxValoracion;
	private JButton btnAtras;
	private JButton btnConfirmacion;
	private JComboBox<String> cmbxProductos;
	private InterfazCliente datosCliente;
	private String dni;
	private List<Producto> listaProductos;

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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}
		
		if (e.getSource().equals(btnConfirmacion)) {
			
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

	public String sacarCodigo() {
		for (int i = 0; i < listaProductos.size(); i++) {
			if (listaProductos.get(i).getNombre().equalsIgnoreCase((String) cmbxProductos.getSelectedItem())) {
				return listaProductos.get(i).getCodProducto();
			}
		}
		return null;
	}

}
