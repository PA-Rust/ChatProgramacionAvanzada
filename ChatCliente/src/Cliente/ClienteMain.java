package Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ClienteMain {
	public static void main(String[] args) {
		new FrameLogueo();
	}
}

class FrameLogueo extends JFrame {
	private static final long serialVersionUID = -5757130380734060552L;
	private LabelLogueo panel;
	FrameLogueo() {
		setBounds(100, 100, 250, 250);
		setTitle("Logueo");
		panel = new LabelLogueo(this);
		add(panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class LabelLogueo extends JPanel {
	private static final long serialVersionUID = -8846506013835240715L;
	private JButton botonLogueo;
	private JLabel labelLogueo;
	private JTextField campoLogueo;
	private JFrame padre;
	public LabelLogueo(JFrame padre){
		botonLogueo = new JButton("Ingresar");
		labelLogueo = new JLabel("Ingrese su nombre de usuario");
		campoLogueo = new JTextField(20);
		IngresarUsuario miEvento = new IngresarUsuario();
		botonLogueo.addActionListener(miEvento);
		campoLogueo.addActionListener(miEvento);
		this.padre = padre;
		add(botonLogueo);
		add(labelLogueo);
		add(campoLogueo);
	}

	private class IngresarUsuario implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			new FrameChat(campoLogueo.getText());
			padre.dispose();
		}
	}
	
	public String devuelveNombre() {
		return campoLogueo.getText();
	}
}





