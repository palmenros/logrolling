package Integracion;

import java.sql.*;
import java.util.ArrayList;

import Negocio.TransferCliente;
import Negocio.TransferReserva;
import Negocio.TransferUsuario;

public class DAOClienteImp implements DAOCliente{
	protected Connection con;
	private final static String driver = "com.mysql.cj.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/ingS?serverTimezone=UTC";
	private final static String user = "root";
	private final static String password = "20031999";
	
	public DAOClienteImp() {
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error connecting with driver");
		} catch (SQLException e) {
			System.out.println("Error connecting with BBDD(PD: cambiar contraseña)");
		}
	}

	public void anadir(TransferUsuario usuario) throws Exception {
		try {
			if (usuario.getUsuario().equals("") || usuario.getContrasena().equals("")
				|| usuario.getEmail().equals("") || usuario.getNombre().equals("")) throw new Exception("Todos los campos son obligatorios");
			
			if (usuario.getUsuario().length() > 10) throw new Exception("Nombre de usuario demasiado largo: maximo 10 caracteres");
			if (usuario.getContrasena().length() > 10) throw new Exception("Contrasena demasiado larga: maximo 10 caracteres");
			if (usuario.getEmail().length() > 30) throw new Exception("Email demasiado largo: maximo 30 caracteres");
			if (usuario.getNombre().length() > 20) throw new Exception("Nombre demasiado largo: maximo 20 caracteres");
			
			String s = "insert into Clientes values ('" + usuario.getUsuario() + "', '" + usuario.getContrasena() +
						"', '" + usuario.getEmail() + "', '" + usuario.getNombre() + "', '" + usuario.getApellidos() + 
						"', '" + usuario.getTarjeta() + "')";
			
			PreparedStatement stmt = con.prepareStatement(s);  
			stmt.executeUpdate();
			
			} catch (SQLIntegrityConstraintViolationException e) {
				throw new Exception("El usuario/email ya han sido utilizados. Elija otros.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public TransferUsuario buscar(String usuario) throws Exception {
		TransferCliente cliente = new TransferCliente();
		try {
			
			PreparedStatement stmt = con.prepareStatement("select * from Clientes where usuario = '" + usuario + "'");
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				cliente.setUsuario(rs.getString("usuario"));
				cliente.setContrasena(rs.getString("contrasena"));
				cliente.setEmail(rs.getString("email"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellidos(rs.getString("apellidos"));
				cliente.setTarjeta(rs.getString("tarjeta"));
			}
			else throw new Exception("El usuario no existe");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	public void actualizar(String usuarioAnt, TransferCliente t) throws Exception {
		if (t.getUsuario().length() > 10) throw new Exception("Nombre de usuario demasiado largo: maximo 10 caracteres");
		if (t.getNombre().length() > 20) throw new Exception("Nombre demasiado largo: maximo 20 caracteres");
		if (t.getEmail().length() > 30) throw new Exception("Email demasiado largo: maximo 30 caracteres");
		if (t.getApellidos().length() > 40) throw new Exception("Campo apellidos demasiado largo: maximo 40 caracteres");
		if (t.getTarjeta().length() > 16) throw new Exception("Tarjeta demasiado larga: maximo 16 caracteres");
		
		PreparedStatement stmt;
		try {
			if (!t.getNombre().isEmpty()) {
			stmt = con.prepareStatement("update Clientes set nombre = '" + t.getNombre() +
					"' where usuario = '" + usuarioAnt + "'");
			stmt.executeUpdate();
			}
			if (!t.getEmail().isEmpty()) {
				stmt = con.prepareStatement("update Clientes set email = '" + t.getEmail() +
						"' where usuario = '" + usuarioAnt + "'");
				stmt.executeUpdate();
			}
			if (!t.getApellidos().isEmpty()) {
				stmt = con.prepareStatement("update Clientes set apellidos = '" + t.getApellidos() +
						"' where usuario = '" + usuarioAnt + "'");
				stmt.executeUpdate();
			}
			if (!t.getTarjeta().isEmpty()) {
				stmt = con.prepareStatement("update Clientes set tarjeta = '" + t.getTarjeta() +
						"' where usuario = '" + usuarioAnt + "'");
				stmt.executeUpdate();
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new Exception("El email ya existe. Elija otro");
		}
		try {
			if (!t.getUsuario().isEmpty()) {
				stmt = con.prepareStatement("update Clientes set usuario = '" + t.getUsuario() +
						"' where usuario = '" + usuarioAnt + "'");
				stmt.executeUpdate();
			}
		} catch(SQLIntegrityConstraintViolationException e) {
			throw new Exception("El usuario ya existe. Elija otro");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void eliminar(String usuario) throws Exception{
		try {
			buscar(usuario);
			PreparedStatement s = con.prepareStatement("delete from Reservas where cliente = '" + usuario + "'");  
			s.executeUpdate();
			PreparedStatement stmt = con.prepareStatement("delete from Clientes where usuario = '" + usuario + "'");  
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception("El usuario no ha sido eliminado correctamente");
		}
	}

	public boolean anadirReserva(String cliente, String restaurante, Integer numComensales, String datetime) {
		String[] d = datetime.split(" ");
		try {
			PreparedStatement stmt = con.prepareStatement("insert into Reservas values ('" + restaurante + "', '" +
					cliente + "'," + numComensales + ", '" + d[5] + "-" + toNumber(d[1]) + "-" + d[2] + " " + d[3] + "')");  
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ArrayList<TransferReserva> getReservas(String usuario) {
		ArrayList<TransferReserva> r = new ArrayList<TransferReserva>();
		try {
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM Reservas WHERE cliente = '" + usuario + "' ORDER BY fecha");  
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			TransferReserva t = new TransferReserva();
			t.setCliente(usuario);
			t.setRestaurante(rs.getString("restaurante"));
			t.setNumComensales(rs.getInt("numComensales"));
			t.setDateTime(rs.getString("fecha"));
			r.add(t);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	private int toNumber(String v) {
		int a = 0;
		switch (v.toLowerCase()) {
		case "january": a = 1; break;
		case "february": a = 2; break;
		case "march": a = 3; break;
		case "april": a = 4; break;
		case "may": a = 5; break;
		case "june": a = 6; break;
		case "july": a = 7; break;
		case "august": a = 8; break;
		case "september": a = 9; break;
		case "october": a = 10; break;
		case "november": a = 11; break;
		case "december": a = 12; break;
		}
		return a;
	}

}
