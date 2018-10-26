package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import General.Mensaje;

public class Servidor {
	int puerto = 3000;
	ArrayList<ManejadorUsuario> manejadores;
	
	public Servidor(int puerto) {
		ServerSocket servidor;
		try {
			manejadores = new ArrayList<ManejadorUsuario>();
			servidor = new ServerSocket(puerto);
			
			System.out.println("Servidor iniciado en puerto " + puerto);
			
			while (true) {
				Socket nuevoUsuario = servidor.accept();
				ManejadorUsuario nuevoManejador = new ManejadorUsuario(nuevoUsuario, this);
				manejadores.add(nuevoManejador);
				System.out.println("nuevo cliente!");
				nuevoManejador.start();
				// nuevoMensaje(null, new Mensaje("Servidor", "NUEVO USUARIO CONECTADO"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void nuevoMensaje(ManejadorUsuario sender, Mensaje mensaje) {
		if (manejadores.isEmpty()) {
			return;
		}
		System.out.println(mensaje.getNombre() + ": " + mensaje.getMensaje());
		for (ManejadorUsuario manejador: manejadores) {
			if (manejador == sender) {
				// No le vuelve a enviar el mensaje al usuario que envio
				// ese mensaje.
				continue;
			}
			try {
				manejador.enNuevoMensaje(mensaje);
			} catch (IOException e) {
				System.out.println("Error al notificar al cliente: " + e);
			}
		}
	}
	
	public void deregistrarManejador(ManejadorUsuario manejador) {
		System.out.println("Deregistrando manejador");
		manejadores.remove(manejador);
	}
	
	public static void main(String[] args) {
		new Servidor(3000);
	}
}
