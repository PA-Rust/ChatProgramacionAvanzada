package Cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textIp;
	private JTextField textPort;
	private JTextField textUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLogin frame = new FrameLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 320, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{147, 147, 0};
		gbl_contentPane.rowHeights = new int[]{39, 39, 39, 39, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		textIp = new JTextField();
		textIp.setText("localhost");
		GridBagConstraints gbc_textIp = new GridBagConstraints();
		gbc_textIp.fill = GridBagConstraints.BOTH;
		gbc_textIp.insets = new Insets(0, 0, 5, 5);
		gbc_textIp.gridx = 0;
		gbc_textIp.gridy = 0;
		contentPane.add(textIp, gbc_textIp);
		textIp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("IP del Server");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textPort = new JTextField();
		textPort.setText("3000");
		GridBagConstraints gbc_textPort = new GridBagConstraints();
		gbc_textPort.fill = GridBagConstraints.BOTH;
		gbc_textPort.insets = new Insets(0, 0, 5, 5);
		gbc_textPort.gridx = 0;
		gbc_textPort.gridy = 1;
		contentPane.add(textPort, gbc_textPort);
		textPort.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Puerto del Server");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textUsuario = new JTextField();
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.fill = GridBagConstraints.BOTH;
		gbc_textUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuario.gridx = 0;
		gbc_textUsuario.gridy = 2;
		contentPane.add(textUsuario, gbc_textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblUsuaro = new JLabel("Usuario");
		lblUsuaro.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblUsuaro = new GridBagConstraints();
		gbc_lblUsuaro.fill = GridBagConstraints.BOTH;
		gbc_lblUsuaro.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsuaro.gridx = 1;
		gbc_lblUsuaro.gridy = 2;
		contentPane.add(lblUsuaro, gbc_lblUsuaro);
		
		JButton buttonLoguear = new JButton("Loguearse");
		
		FrameLogin esteFrame = this;
		buttonLoguear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrameSala(esteFrame, textIp.getText(), Integer.parseInt(textPort.getText()), textUsuario.getText());
			}
		});
		textUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrameSala(esteFrame, textIp.getText(), Integer.parseInt(textPort.getText()), textUsuario.getText());
			}
		});
		GridBagConstraints gbc_buttonLoguear = new GridBagConstraints();
		gbc_buttonLoguear.gridwidth = 2;
		gbc_buttonLoguear.insets = new Insets(0, 0, 0, 5);
		gbc_buttonLoguear.fill = GridBagConstraints.BOTH;
		gbc_buttonLoguear.gridx = 0;
		gbc_buttonLoguear.gridy = 3;
		textUsuario.requestFocusInWindow();
		contentPane.add(buttonLoguear, gbc_buttonLoguear);
	}
}
