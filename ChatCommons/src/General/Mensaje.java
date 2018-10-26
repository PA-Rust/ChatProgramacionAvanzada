package General;

import java.io.Serializable;

public class Mensaje implements Serializable {
	private static final long serialVersionUID = -4896675928614018531L;
	private String nombre;
	private String mensaje;
	
	public Mensaje(String nombre, String mensaje) {
		this.nombre = nombre;
		this.mensaje = mensaje;
	}

	public String getNombre() {
		return nombre;
	}

	public String getMensaje() {
		return mensaje;
	}
}
