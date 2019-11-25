package Negocio;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import Integracion.DAORestaurante;
import Integracion.DAOCliente;
import Integracion.DAOClienteImp;
import Integracion.DAORestauranteImp;

public class SAImp implements SA{

	public void iniciarSesion(String text, char[] password, boolean cliente) throws Exception{
		TransferUsuario user;
		if(cliente) {
			DAOCliente dao=new DAOClienteImp();
			user=dao.buscar(text);
		}
		else {
			DAORestaurante dao=new DAORestauranteImp();
			user=dao.buscar(text);
		}
		if (!user.getContrasena().equals(String.valueOf(password))) throw new Exception("Contraseña incorrecta");
	}

	
	public void registrar(String user, String email, char[] password, boolean cliente, String nombre) 
			throws Exception {		
		
		if(cliente) {
			DAOCliente dao=new DAOClienteImp();
			TransferUsuario u=new TransferCliente();
			u.setUsuario(user);
			u.setEmail(email);
			u.setContrasena(String.valueOf(password));
			u.setNombre(nombre);
			dao.anadir(u);
		}
		else {
			DAORestaurante dao=new DAORestauranteImp();
			TransferUsuario u=new TransferRestaurante();
			u.setUsuario(user);
			u.setEmail(email);
			u.setContrasena(String.valueOf(password));
			u.setNombre(nombre);
			dao.anadir(u);
		}		
	}

	public void guardarCambiosRest(String usuarioAnt, String usuario, String email, String nombre, String ubicacion, String descripcion) throws Exception{
		DAORestauranteImp dao = new DAORestauranteImp();
		TransferRestaurante t = new TransferRestaurante();
		t.setUsuario(usuario);
		t.setEmail(email);
		t.setNombre(nombre);
		t.setUbicacion(ubicacion);
		t.setDescripcion(descripcion);
		dao.actualizar(usuarioAnt, t);
	}
	
	public void guardarCambiosCliente(String usuarioAnt, String usuario, String email, String nombre, String apellidos, String tarjeta) throws Exception{
		DAOClienteImp dao = new DAOClienteImp();
		TransferCliente t = new TransferCliente();
		t.setUsuario(usuario);
		t.setEmail(email);
		t.setNombre(nombre);
		t.setApellidos(apellidos);
		t.setTarjeta(tarjeta);
		dao.actualizar(usuarioAnt, t);
	}
	

	public TransferUsuario buscar(String usuario, boolean cliente) throws Exception{
		TransferUsuario user;
		if(cliente) {
			DAOCliente dao=new DAOClienteImp();
			user=dao.buscar(usuario);
		}
		else {
			DAORestaurante dao=new DAORestauranteImp();
			user=dao.buscar(usuario);
		}
		return user;
	}
	
	public TransferUsuario buscarRestauranteNombre(String text) {
		DAORestauranteImp dao = new DAORestauranteImp();
		return dao.buscarNombre(text);
	}

	public ArrayList<String> listaRestaurentes() {
		DAORestauranteImp dao = new DAORestauranteImp();
		return dao.listaRestaurantes();
	}

	public void eliminarCuenta(String usuario, boolean cliente) throws Exception{
		if(cliente) {
			DAOCliente dao=new DAOClienteImp();
			dao.eliminar(usuario);
		}
		else {
			DAORestaurante dao=new DAORestauranteImp();
			dao.eliminar(usuario);
		}
	}


	public Image getImage(String usuario) {
		DAORestaurante dao=new DAORestauranteImp();
		return dao.getImage(usuario);
	}

	public void guardarImagen(File inFile, String usuario) {
		DAORestaurante dao=new DAORestauranteImp();
		dao.guardarImagen(inFile, usuario);
	}

	public boolean aniadirReserva(String cliente, String restaurante, Integer numComensales, String datetime) {
		DAOCliente dao=new DAOClienteImp();
		return dao.anadirReserva(cliente, restaurante, numComensales, datetime);
	}

	public ArrayList<TransferReserva> getReservasR(String usuario) {
		DAORestaurante dao=new DAORestauranteImp();
		return dao.getReservas(usuario);
	}

	public void borrarReserva(TransferReserva t) {
		DAORestaurante dao=new DAORestauranteImp();
		dao.borrarReserva(t);
	}

	public ArrayList<TransferReserva> getReservasC(String usuario) {
		DAOCliente dao=new DAOClienteImp();
		return dao.getReservas(usuario);
	}
}
