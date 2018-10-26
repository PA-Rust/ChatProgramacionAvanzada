package Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import General.Mensaje;

public class FrameChat extends JFrame {
	private static final long serialVersionUID = 4639606563694408188L;
	
	private LabelCliente label;
	
	public FrameChat(String nombreUsuario){
		setBounds(200, 200, 500, 500);
		setTitle("Chat de"+nombreUsuario);
		label = new LabelCliente(nombreUsuario);
		add(label);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class LabelCliente extends JPanel implements MessageReceiver {
	private static final long serialVersionUID = 2878970920497105366L;
	
	private JButton botonEnviar;
	private JTextField campoMensaje;
	private JTextArea boxDeMensajes;
	private String nombre;
	private Cliente cliente;
	
	public LabelCliente(String nombre) {
		this.nombre = nombre;
		
		botonEnviar = new JButton("Enviar");
		campoMensaje = new JTextField(15);		
		boxDeMensajes = new JTextArea(30,30);
		boxDeMensajes.setLocation(40, 40);

		EnviarMensajeEvent mensajeEvent = new EnviarMensajeEvent();
		
		botonEnviar.addActionListener(mensajeEvent);
		campoMensaje.addActionListener(mensajeEvent);
		
		cliente = new Cliente(this);
		
		add(botonEnviar);
		add(campoMensaje);
		add(boxDeMensajes);
	}
	
	public void enNuevoMensaje(Mensaje mensaje) {
		boxDeMensajes.append(mensaje.getNombre() + ": " + mensaje.getMensaje());
	}
	
	private class EnviarMensajeEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (campoMensaje.getText() == "") {
				return;
			}
			
			Mensaje nuevoMensaje = new Mensaje(nombre, campoMensaje.getText());
			boxDeMensajes.append(nuevoMensaje.getNombre() + ": " + nuevoMensaje.getMensaje() + "\n");
			cliente.enviarMensaje(nuevoMensaje);
			campoMensaje.setText("");
		}
	}
}