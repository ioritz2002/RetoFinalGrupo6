package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class VAltaRepartidor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtApellido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VAltaRepartidor dialog = new VAltaRepartidor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VAltaRepartidor() {
		setBounds(100, 100, 566, 436);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Id:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setBounds(150, 86, 51, 25);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNombre.setBounds(113, 145, 88, 25);
			contentPanel.add(lblNombre);
		}
		{
			JLabel lblApellido = new JLabel("Apellido:");
			lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblApellido.setBounds(107, 203, 94, 25);
			contentPanel.add(lblApellido);
		}
		{
			txtId = new JTextField();
			txtId.setBounds(257, 88, 174, 28);
			contentPanel.add(txtId);
			txtId.setColumns(10);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(257, 146, 174, 31);
			contentPanel.add(txtNombre);
		}
		{
			txtApellido = new JTextField();
			txtApellido.setColumns(10);
			txtApellido.setBounds(257, 204, 174, 31);
			contentPanel.add(txtApellido);
		}
		{
			JButton btnAtras = new JButton("ATR\u00C1S");
			btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAtras.setBounds(83, 298, 101, 47);
			contentPanel.add(btnAtras);
		}
		{
			JButton btnDarDeAlta = new JButton("DAR DE ALTA REPARTIDOR");
			btnDarDeAlta.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnDarDeAlta.setBounds(237, 293, 289, 56);
			contentPanel.add(btnDarDeAlta);
		}
	}

}
