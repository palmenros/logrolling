package Negocio;

public class TransferReserva {
	private String restaurante;
	private String cliente;
	private int numComensales;
	private String dateTime;
	
	public String getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(String restaurante) {
		this.restaurante = restaurante;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public int getNumComensales() {
		return numComensales;
	}
	public void setNumComensales(int numComensales) {
		this.numComensales = numComensales;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
