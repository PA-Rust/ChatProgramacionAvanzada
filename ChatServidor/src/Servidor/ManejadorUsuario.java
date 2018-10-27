package Servidor;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import General.Mensaje;

public class ManejadorUsuario extends Thread {
	private Socket usuario;
	private Servidor server;
	private ObjectOutputStream salida;
	
	public ManejadorUsuario(Socket usuario, Servidor server) {
		this.usuario = usuario;
		this.server = server;
		
		try {
			salida = new ObjectOutputStream(usuario.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			ObjectInputStream entrada =
				new ObjectInputStream(usuario.getInputStream());
			while (usuario.isConnected()) {
				try {
					Mensaje nuevoMensaje = (Mensaje)entrada.readObject();
					server.nuevoMensaje(this, nuevoMensaje);
					
				} catch (ClassNotFoundException e) {
					System.out.println("Error de formato de mensaje recibido");
				}
			}
			entrada.close();
		} catch (EOFException e) {
			System.out.println("connexion terminada");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error reading info from client");
		} finally {
			System.out.println("Cerrando manejador");
			try {
				server.deregistrarManejador(this);
				salida.close();
				usuario.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void enNuevoMensaje(Mensaje nuevoMensaje) throws IOException {
		salida.writeObject(nuevoMensaje);
	}
}
