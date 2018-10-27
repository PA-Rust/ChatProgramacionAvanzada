package Cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import General.Mensaje;

import java.awt.GridBagLayout;
import javax.swing.JScrollBar;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JEditorPane;

public class FrameSala extends JFrame implements MessageReceiver {

	private JPanel contentPane;
	private JTextField textMensaje;
	private JScrollPane scrollPane;
	private String ip;
	private int puerto;
	private String nombre;
	private Cliente cliente;
	private JEditorPane textChat;
	private HTMLEditorKit kit;
	private HTMLDocument document;

	/**
	 * Create the frame.
	 */
	public FrameSala(FrameLogin frameLogin, String ip, int puerto, String nombre) {
		super("Sala de chat - " + nombre + " -");
		this.nombre = nombre;
		setVisible(true);
		setLocationRelativeTo(frameLogin);
		frameLogin.dispose();
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 19;
		gbc_scrollPane.gridwidth = 27;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		document = new HTMLDocument();
		kit = new HTMLEditorKit();
		textChat = new JEditorPane();
		textChat.setContentType("text/html");
		textChat.setEditable(false);
		textChat.setDocument(document);
		textChat.setEditorKit(kit);
		scrollPane.setViewportView(textChat);
		
		textMensaje = new JTextField();
		textMensaje.requestFocus();
		textMensaje.requestFocusInWindow();
		
		GridBagConstraints gbc_textMensaje = new GridBagConstraints();
		gbc_textMensaje.gridwidth = 25;
		gbc_textMensaje.insets = new Insets(0, 0, 0, 5);
		gbc_textMensaje.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMensaje.gridx = 0;
		gbc_textMensaje.gridy = 19;
		contentPane.add(textMensaje, gbc_textMensaje);
		textMensaje.setColumns(10);
		textMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textMensaje.getText() == "") {
					return;
				}
				
				Mensaje nuevoMensaje = new Mensaje(nombre, textMensaje.getText());
				enNuevoMensaje(nuevoMensaje);
				cliente.enviarMensaje(nuevoMensaje);
				textMensaje.setText("");
			}
		});
		
		JButton textEnviar = new JButton("Enviar");
		textEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textMensaje.getText() == "") {
					return;
				}
				
				Mensaje nuevoMensaje = new Mensaje(nombre, textMensaje.getText());
				enNuevoMensaje(nuevoMensaje);
				cliente.enviarMensaje(nuevoMensaje);
				textMensaje.setText("");
			}
		});
		GridBagConstraints gbc_textEnviar = new GridBagConstraints();
		gbc_textEnviar.gridwidth = 2;
		gbc_textEnviar.insets = new Insets(0, 0, 0, 5);
		gbc_textEnviar.gridx = 25;
		gbc_textEnviar.gridy = 19;
		contentPane.add(textEnviar, gbc_textEnviar);
		

		cliente = new Cliente(this, ip, puerto);
		cliente.start();
	}
	
	public void enNuevoMensaje(Mensaje mensaje) {
		try {
			System.out.println(document.getText(0, document.getLength()));
			kit.insertHTML(document, document.getLength(), "[<b>" + mensaje.getNombre() + ": </b>]" + mensaje.getMensaje(), 0, 0, null);
			textChat.setDocument(document);
		} catch (BadLocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
