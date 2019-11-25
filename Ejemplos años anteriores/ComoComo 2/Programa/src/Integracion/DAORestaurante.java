package Integracion;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import Negocio.TransferReserva;
import Negocio.TransferRestaurante;
import Negocio.TransferUsuario;

public interface DAORestaurante {
	public void anadir(TransferUsuario usuario) throws Exception;
	
	public void eliminar(String usuario) throws Exception;
	
	public TransferUsuario buscar(String usuario) throws Exception;

	public ArrayList<String> listaRestaurantes();

	public void actualizar(String usuarioAnt, TransferRestaurante t) throws Exception;

	public TransferUsuario buscarNombre(String text);

	public Image getImage(String usuario);

	public void guardarImagen(File inFile, String usuario);

	public ArrayList<TransferReserva> getReservas(String usuario);

	public void borrarReserva(TransferReserva t);
}