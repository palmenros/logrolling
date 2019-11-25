package Presentacion;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import Negocio.SA;
import Negocio.SAImp;
import Negocio.TransferReserva;
import Negocio.TransferUsuario;

public class Controller {
	private static Controller instancia;
	private Controller() {}
	public static Controller getInstance() {
		if(instancia==null) {
			instancia=new Controller();
		}
		return instancia;
	}
	
	public void iniciarSesion(String text, char[] password, boolean cliente) throws Exception{
		SA servicioApp=new SAImp();
		servicioApp.iniciarSesion(text,password,cliente);
	}
	
	public void registrar(String user, String email, char[] password, boolean cliente, String nombre) throws Exception {
		SA servicioApp=new SAImp();
		servicioApp.registrar(user,email,password,cliente,nombre);
		
	}
	
	public void guardarCambiosRest(String usuarioAnt, String usuario, String email, String nombre, 
			String ubicacion, String descripcion) throws Exception {
		SA servicioApp=new SAImp();
		servicioApp.guardarCambiosRest(usuarioAnt, usuario, email, nombre, ubicacion, descripcion);
	}
	
	public void guardarCambiosCliente(String usuarioAnt, String usuario, String email, String nombre, 
			String apellidos, String tarjeta) throws Exception {
		SA servicioApp=new SAImp();
		servicioApp.guardarCambiosCliente(usuarioAnt, usuario, email, nombre, apellidos, tarjeta);
	}
	
	public TransferUsuario buscar(String text, boolean cliente) throws Exception{
		SA servicioApp=new SAImp();
		return servicioApp.buscar(text, cliente);
	}
	
	public ArrayList<String> listaRestaurante() {
		SA servicioApp=new SAImp();
		return servicioApp.listaRestaurentes();
	}
	
	public void eliminarCuenta(String usuario, boolean cliente) throws Exception {
		SA servicioApp=new SAImp();
		servicioApp.eliminarCuenta(usuario, cliente);
	}
	
	public TransferUsuario buscarRestauranteNombre(String text) {
		SA servicioApp=new SAImp();
		return servicioApp.buscarRestauranteNombre(text);
	}
	
	public Image getImagen(String usuario) {
		SA servicioApp=new SAImp();
		return servicioApp.getImage(usuario);
	}
	
	public void guardarImagen(File inFile, String usuario) {
		SA servicioApp=new SAImp();
		servicioApp.guardarImagen(inFile, usuario);
	}
	
	public boolean aniadirReserva(String cliente, TransferUsuario restaurante, Integer numComensales, String datetime) {
		SA servicioApp=new SAImp();
		return servicioApp.aniadirReserva(cliente, restaurante.getUsuario(), numComensales, datetime);
	}
	
	public ArrayList<TransferReserva> getReservasR(String usuario) {
		SA servicioApp=new SAImp();
		return servicioApp.getReservasR(usuario);
	}
	public void borrarReserva(TransferReserva t) {
		SA servicioApp=new SAImp();
		servicioApp.borrarReserva(t);
	}
	
	public ArrayList<TransferReserva> getReservasC(String usuario) {
		SA servicioApp=new SAImp();
		return servicioApp.getReservasC(usuario);
	}

}
