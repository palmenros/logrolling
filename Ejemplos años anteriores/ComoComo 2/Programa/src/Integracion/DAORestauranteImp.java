package Integracion;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import Negocio.TransferReserva;
import Negocio.TransferRestaurante;
import Negocio.TransferUsuario;

public class DAORestauranteImp implements DAORestaurante{
	protected Connection con;
	private final static String driver = "com.mysql.cj.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/ingS?serverTimezone=UTC";
	private final static String user = "root";
	private final static String password = "20031999";
	
	public DAORestauranteImp() {
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
			if (usuario.getNombre().length() > 30) throw new Exception("Nombre demasiado largo: maximo 30 caracteres");
			
			String s = "insert into Restaurantes values ('" + usuario.getUsuario() + "', '" 
		                + usuario.getContrasena() + "', '" + usuario.getEmail() + "', '"
				                + usuario.getNombre() + "', '" + usuario.getUbicacion() + "', '"
				                	+ usuario.getDescripcion() + "')";
			
			PreparedStatement stmt = con.prepareStatement(s);  
			stmt.executeUpdate();
			
			} catch(SQLIntegrityConstraintViolationException e) {
				throw new Exception("El usuario/email ya han sido utilizados. Elija otros.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public TransferRestaurante buscar(String usuario) throws Exception{
		TransferRestaurante restaurante = new TransferRestaurante();
		try {
			
			PreparedStatement stmt = con.prepareStatement("select * from Restaurantes where usuario = '" + usuario + "'");
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				restaurante.setUsuario(rs.getString("usuario"));
				restaurante.setContrasena(rs.getString("contrasena"));
				restaurante.setEmail(rs.getString("email"));
				restaurante.setNombre(rs.getString("nombre"));
				restaurante.setUbicacion(rs.getString("ubicacion"));
				restaurante.setDescripcion(rs.getString("descripcion"));
			}
			else throw new Exception("El usuario no existe");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurante;
	}

	public ArrayList<String> listaRestaurantes() {
		ArrayList<String> rts = new ArrayList<String>();
		try {
			PreparedStatement stmt = con.prepareStatement("select nombre from Restaurantes");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				rts.add(rs.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rts;
	}

	public void actualizar(String usuarioAnt, TransferRestaurante t) throws Exception {
		if (t.getUsuario().length() > 10) throw new Exception("Nombre de usuario demasiado largo: maximo 10 caracteres");
		if (t.getNombre().length() > 30) throw new Exception("Nombre demasiado largo: maximo 30 caracteres");
		if (t.getEmail().length() > 30) throw new Exception("Email demasiado largo: maximo 30 caracteres");
		if (t.getUbicacion().length() > 50) throw new Exception("Ubicacion demasiado larga: maximo 50 caracteres");
		if (t.getDescripcion().length() > 100) throw new Exception("Descripcion demasiado larga: maximo 100 caracteres");
		
		PreparedStatement stmt;
		try {
			if (!t.getNombre().isEmpty()) {
			stmt = con.prepareStatement("update Restaurantes set nombre = '" + t.getNombre() +
					"' where usuario = '" + usuarioAnt + "'");
			stmt.executeUpdate();
			}
			if (!t.getEmail().isEmpty()) {
				stmt = con.prepareStatement("update Restaurantes set email = '" + t.getEmail() +
						"' where usuario = '" + usuarioAnt + "'");
				stmt.executeUpdate();
			}
			if (!t.getUbicacion().isEmpty()) {
				stmt = con.prepareStatement("update Restaurantes set ubicacion = '" + t.getUbicacion() +
						"' where usuario = '" + usuarioAnt + "'");
				stmt.executeUpdate();
			}
			if (!t.getDescripcion().isEmpty()) {
				stmt = con.prepareStatement("update Restaurantes set descripcion = '" + t.getDescripcion() +
						"' where usuario = '" + usuarioAnt + "'");
				stmt.executeUpdate();
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new Exception("El email ya existe. Elija otro");
		}
		try {
			if (!t.getUsuario().isEmpty()) {
				stmt = con.prepareStatement("update Restaurantes set usuario = '" + t.getUsuario() +
						"' where usuario = '" + usuarioAnt + "'");
				stmt.executeUpdate();
			}
		} catch(SQLIntegrityConstraintViolationException e) {
			throw new Exception("El usuario ya existe. Elija otro");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public TransferUsuario buscarNombre(String nombre){
		TransferRestaurante restaurante = new TransferRestaurante();
		try {
			
			PreparedStatement stmt = con.prepareStatement("select * from Restaurantes where nombre = '" + nombre + "'");
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				restaurante.setUsuario(rs.getString("usuario"));
				restaurante.setContrasena(rs.getString("contrasena"));
				restaurante.setEmail(rs.getString("email"));
				restaurante.setNombre(rs.getString("nombre"));
				restaurante.setUbicacion(rs.getString("ubicacion"));
				restaurante.setDescripcion(rs.getString("descripcion"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurante;
	}

	public void eliminar(String usuario) throws Exception {
		try {
			buscar(usuario);
			PreparedStatement st = con.prepareStatement("delete from Imagenes where idImagen = '" + usuario + "'");  
			st.executeUpdate();
			PreparedStatement s = con.prepareStatement("delete from Reservas where restaurante = '" + usuario + "'");  
			s.executeUpdate();
			PreparedStatement stmt = con.prepareStatement("delete from Restaurantes where usuario = '" + usuario + "'");  
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("El usuario no ha sido eliminado correctamente");
		}
	}
	
	public void guardarImagen(File file, String usuario){
		FileInputStream fis = null;
		try {
			PreparedStatement st = con.prepareStatement("select * from Imagenes where idImagen = '" + usuario + "'");
			ResultSet r = st.executeQuery();
			if (r.next()) {
				con.setAutoCommit(false);
				fis = new FileInputStream(file);
				PreparedStatement u = con.prepareStatement("update Imagenes set imagen = ? where idImagen = '" + usuario + "'");
				u.setBinaryStream(1, fis,(int)file.length());
				u.executeUpdate();
				con.commit();
			}
			else {
				String insert = "insert into Imagenes(imagen, idImagen) values(?,?)";
				con.setAutoCommit(false);
				fis = new FileInputStream(file);
				PreparedStatement ps = con.prepareStatement(insert);
				ps.setBinaryStream(1,fis,(int)file.length());
				ps.setString(2, usuario);
				ps.executeUpdate();
				con.commit();
				ps.close();
			}
		 } catch (Exception e) {
			 Logger.getLogger(DAORestauranteImp.class.getName()).log(Level.SEVERE, null, e);
		 }finally{
			 try {
			 	fis.close();
			 } catch (Exception ex) {
				 ex.printStackTrace();
				 Logger.getLogger(DAORestauranteImp.class.getName()).log(Level.SEVERE, null, ex);
			 }
		 }
	}

	public Image getImage(String usuario) {
		BufferedImage img = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT imagen FROM Imagenes WHERE idImagen = '" + usuario + "'");  
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Blob blob = rs.getBlob("imagen");
				byte[] data = blob.getBytes(1, (int)blob.length());
				try {
					img = ImageIO.read(new ByteArrayInputStream(data));
				} catch (IOException ex) {
					Logger.getLogger(DAORestauranteImp.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			rs.close();
		 	} catch (SQLException ex) {
		 		Logger.getLogger(DAORestauranteImp.class.getName()).log(Level.SEVERE, null, ex);
		 	}
		return img;
	}

	public ArrayList<TransferReserva> getReservas(String usuario) {
		ArrayList<TransferReserva> r = new ArrayList<TransferReserva>();
		try {
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM Reservas WHERE restaurante = '" + usuario + "' ORDER BY fecha");  
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			TransferReserva t = new TransferReserva();
			t.setRestaurante(usuario);
			t.setCliente(rs.getString("cliente"));
			t.setNumComensales(rs.getInt("numComensales"));
			t.setDateTime(rs.getString("fecha"));
			r.add(t);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public void borrarReserva(TransferReserva t) {
		try {
			PreparedStatement s = con.prepareStatement("delete from Reservas where restaurante = '" 
		+ t.getRestaurante() + "' AND cliente = '" + t.getCliente() + "' AND fecha = '" + t.getDateTime() + "'");  
			s.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
