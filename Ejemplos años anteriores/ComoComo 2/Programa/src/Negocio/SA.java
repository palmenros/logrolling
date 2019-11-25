package Negocio;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

public interface SA {

	void iniciarSesion(String text, char[] password, boolean cliente) throws Exception;

	void registrar(String user, String email, char[] password, boolean cliente, String nombre) throws Exception;

	void guardarCambiosRest(String usuarioAnt, String usuario, String email, String nombre, String ubicacion, String descripcion) throws Exception;

	void guardarCambiosCliente(String usuarioAnt, String usuario, String email, String nombre, String apellidos, String tarjeta) throws Exception;
	
	TransferUsuario buscar(String usuario, boolean cliente) throws Exception;

	ArrayList<String> listaRestaurentes();

	void eliminarCuenta(String usuario, boolean cliente) throws Exception;

	TransferUsuario buscarRestauranteNombre(String text);

	Image getImage(String usuario);

	void guardarImagen(File inFile, String usuario);

	boolean aniadirReserva(String cliente, String restaurante, Integer numComensales, String datetime);

	ArrayList<TransferReserva> getReservasR(String usuario);

	void borrarReserva(TransferReserva t);

	ArrayList<TransferReserva> getReservasC(String usuario);
}
