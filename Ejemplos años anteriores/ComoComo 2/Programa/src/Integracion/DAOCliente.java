package Integracion;

import java.util.ArrayList;

import Negocio.TransferReserva;
import Negocio.TransferUsuario;

public interface DAOCliente {
	public void anadir(TransferUsuario usuario) throws Exception;
	
	public void eliminar(String usuario) throws Exception;
	
	public TransferUsuario buscar(String usuario) throws Exception;

	public boolean anadirReserva(String cliente, String restaurante, Integer numComensales, String datetime);

	public ArrayList<TransferReserva> getReservas(String usuario);
}
