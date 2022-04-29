package vista;



import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class VValorar extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtProductoAValorar;
	private JComboBox<Object> cmbxValoracion;
	private JButton btnAtras;
	private JButton btnConfirmacion;

	
	public VValorar() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PRODUCTO A VALORAR:");
		lblNewLabel.setBounds(35, 80, 138, 14);
		getContentPane().add(lblNewLabel);
		
		txtProductoAValorar = new JTextField();
		txtProductoAValorar.setBounds(196, 77, 117, 17);
		getContentPane().add(txtProductoAValorar);
		txtProductoAValorar.setColumns(10);
		
		JLabel lblValoracion = new JLabel("VALORACION:");
		lblValoracion.setBounds(35, 124, 138, 14);
		getContentPane().add(lblValoracion);
		
		cmbxValoracion = new JComboBox<Object>();
		cmbxValoracion.setBounds(196, 120, 117, 22);
		getContentPane().add(cmbxValoracion);
		
		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.setBounds(23, 217, 103, 33);
		getContentPane().add(btnAtras);
		
		btnConfirmacion = new JButton("CONFIRMACION");
		btnConfirmacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirmacion.setBounds(236, 217, 151, 33);
		getContentPane().add(btnConfirmacion);

	}

}
