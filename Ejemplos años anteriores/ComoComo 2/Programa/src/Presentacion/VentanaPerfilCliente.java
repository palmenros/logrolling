package Presentacion;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mxrck.autocompleter.TextAutoCompleter;

import Negocio.TransferUsuario;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class VentanaPerfilCliente {

	private JFrame frmCmocomo;
	private Controller ctrl;
	private String usuario;
	private JTextField textField;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JLabel lblUbicacion;
	private JLabel lblFoto;
	private TransferUsuario restaurante;

	public VentanaPerfilCliente(String usuario) {
		this.usuario = usuario;
		this.ctrl=Controller.getInstance();
		initialize();
		frmCmocomo.setVisible(true);
	}
	
	private void searchAction() {
		TransferUsuario restaurante = ctrl.buscarRestauranteNombre(textField.getText());
		if (restaurante.getNombre() != null) {
			lblNombre.setText(restaurante.getNombre());
			if (!restaurante.getDescripcion().equals("null")) lblDescripcion.setText(restaurante.getDescripcion());
			else lblDescripcion.setText("");
			if (!restaurante.getUbicacion().equals("null")) lblUbicacion.setText(restaurante.getUbicacion());
			else lblUbicacion.setText("");
			Image imagen= ctrl.getImagen(restaurante.getUsuario());
			if (imagen == null) {
				lblFoto.setIcon(null);
			}
			else {
			ImageIcon iconoEsc=new ImageIcon(imagen.getScaledInstance(270, 180, Image.SCALE_SMOOTH));
			lblFoto.setIcon(iconoEsc);
			}
			VentanaPerfilCliente.this.restaurante=restaurante;
			panel.setVisible(true);
		}
	}
	
	private void initialize() {
		frmCmocomo = new JFrame();
		frmCmocomo.setTitle("ComoComo");
		frmCmocomo.getContentPane().setBackground(new Color(234, 166, 93));
		frmCmocomo.setBounds(100, 100, 500, 700);
		frmCmocomo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCmocomo.getContentPane().setLayout(null);

		JButton btnEditarperfil = new JButton();
		btnEditarperfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEditarperfil.setBorderPainted(false);
		btnEditarperfil.setToolTipText("Editar perfil");
		btnEditarperfil.setBackground(new Color(234, 166, 93));
		ImageIcon icono = new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/editProfile2.png"));
		Image imagen = icono.getImage();
		ImageIcon iconoEsc = new ImageIcon(imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		btnEditarperfil.setIcon(iconoEsc);
		btnEditarperfil.setBounds(413, 131, 50, 60);
		btnEditarperfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frmCmocomo.setVisible(false);
				VentanaEditarPerfilCliente window = new VentanaEditarPerfilCliente(usuario);
			}
		});
		frmCmocomo.getContentPane().add(btnEditarperfil);

		JButton btnNewButton = new JButton();
		btnNewButton.setToolTipText("buscar");
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(234, 166, 93));
		ImageIcon icono1 = new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/search.png"));
		Image imagen1 = icono1.getImage();
		ImageIcon iconoEsc1 = new ImageIcon(imagen1.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnNewButton.setIcon(iconoEsc1);
		btnNewButton.setBounds(36, 143, 30, 39);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchAction();
			}

		});
		frmCmocomo.getContentPane().add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(71, 143, 188, 39);
		textField.setBorder(BorderFactory.createEmptyBorder());
		frmCmocomo.getContentPane().add(textField);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchAction();
			}

		});
		textField.setColumns(10);

		TextAutoCompleter textAutoCompleter = new TextAutoCompleter(textField);
		ArrayList<String> restaurantes = ctrl.listaRestaurante();
		for (String s : restaurantes) {
			textAutoCompleter.addItem(s);
		}

		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setToolTipText("filtrar");
		btnNewButton_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(new Color(234, 166, 93));
		ImageIcon icono11 = new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/selective.png"));
		Image imagen11 = icono11.getImage();
		ImageIcon iconoEsc11 = new ImageIcon(imagen11.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		btnNewButton_1.setIcon(iconoEsc11);
		btnNewButton_1.setBounds(271, 131, 51, 60);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frmCmocomo.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton2 = new JButton();
		btnNewButton2.setToolTipText("Ver reservas");
		btnNewButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton2.setBorderPainted(false);
		btnNewButton2.setBackground(new Color(234, 166, 93));
		ImageIcon icono1111=new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/reserved.png"));
		Image imagen1111=icono1111.getImage();
		ImageIcon iconoEsc1111=new ImageIcon(imagen1111.getScaledInstance(70,70, Image.SCALE_SMOOTH));
		btnNewButton2.setIcon(iconoEsc1111);
		btnNewButton2.setBounds(330, 131,71, 60);
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frmCmocomo.setVisible(false);
				VentanaReservasClientes window = new VentanaReservasClientes(usuario);
			}
		});
		frmCmocomo.getContentPane().add(btnNewButton2);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(36, 195, 427, 436);
		panel.setVisible(false);
		frmCmocomo.getContentPane().add(panel);
		panel.setLayout(null);

		lblFoto = new JLabel();
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBounds(0, 0, 427, 193);
		panel.add(lblFoto);

		lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNombre.setBounds(80, 205, 267, 33);
		panel.add(lblNombre);

		lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblDescripcion.setBounds(80, 272, 267, 22);
		panel.add(lblDescripcion);

		lblUbicacion = new JLabel("UBICACION");
		lblUbicacion.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblUbicacion.setBounds(80, 245, 267, 22);
		panel.add(lblUbicacion);

		JButton btnNewButton_3 = new JButton("Reservar");
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setOpaque(true);
		btnNewButton_3.setBackground(new Color(234, 166, 93));
		btnNewButton_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewButton_3.setForeground(Color.white);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCmocomo.setVisible(false);
				VentanaReserva reserva= new VentanaReserva(usuario, restaurante);
			}
		});
		btnNewButton_3.setBounds(83, 355, 261, 45);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("Ver menu");
		btnNewButton_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setOpaque(true);
		btnNewButton_2.setBackground(Color.white);
		btnNewButton_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBounds(147, 314, 133, 29);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_11 = new JButton("Cerrar sesion");
		btnNewButton_11.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		btnNewButton_11.setBorderPainted(false);
		btnNewButton_11.setOpaque(false);
		btnNewButton_11.setBackground(new Color(234, 166, 93));
		btnNewButton_11.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCmocomo.setVisible(false);
				VentanaIni inicioSesion=new VentanaIni();
			}
		});
		btnNewButton_11.setBounds(355, 6,127, 29);
		frmCmocomo.getContentPane().add(btnNewButton_11);
		
		JLabel lblNewLabel = new JLabel();
		ImageIcon icono111=new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/Negrosinfondo.png"));
		Image imagen111=icono111.getImage();
		ImageIcon iconoEsc111=new ImageIcon(imagen111.getScaledInstance(100,100, Image.SCALE_SMOOTH));
		lblNewLabel.setIcon(iconoEsc111);
		lblNewLabel.setBounds(199, 6, 100, 113);
		frmCmocomo.getContentPane().add(lblNewLabel);
	}
}
