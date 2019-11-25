package Negocio;

public class TransferUsuario {
	private String usuario;
	private String contrasena;
	private String email;
	private String nombre;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//Estos son metodos que no se utilizan pero que necesitamos para acceder a sus valores desde transferUsuario
	public String getApellidos() {
		return null;
	}
	public String getTarjeta() {
		return null;
	}
	
	public String getUbicacion() {
		return null;
	}
	public String getDescripcion() {
		return null;
	}
}
