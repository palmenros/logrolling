package Presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class VentanaPerfilRestaurante {

	private JFrame frame;
	private Controller ctrl;
	private String usuario;

	public VentanaPerfilRestaurante(String usuario) {
		this.usuario=usuario;
		this.ctrl=Controller.getInstance();
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(234, 166, 93));
		frame.setBounds(100, 100, 500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnEditarperfil = new JButton();
		btnEditarperfil.setToolTipText("Editar perfil");
		btnEditarperfil.setBorderPainted(false);
		btnEditarperfil.setOpaque(true);
		btnEditarperfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEditarperfil.setBackground(Color.WHITE);
		ImageIcon icono=new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/editProfile.png"));
		Image imagen=icono.getImage();
		ImageIcon iconoEsc=new ImageIcon(imagen.getScaledInstance(110,110, Image.SCALE_SMOOTH));
		btnEditarperfil.setIcon(iconoEsc);
		btnEditarperfil.setBounds(106, 195, 130, 130);
		btnEditarperfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.setVisible(false);
				VentanaEditarPerfilRestaurante window = new VentanaEditarPerfilRestaurante(usuario);
			}
		});
		frame.getContentPane().add(btnEditarperfil);
		
		JButton button = new JButton("");
		button.setToolTipText("Mesas");
		button.setBorderPainted(false);
		button.setOpaque(true);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBackground(Color.WHITE);
		ImageIcon icono1=new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/tables.png"));
		Image imagen1=icono1.getImage();
		ImageIcon iconoEsc1=new ImageIcon(imagen1.getScaledInstance(110,110, Image.SCALE_SMOOTH));
		button.setIcon(iconoEsc1);
		button.setBounds(266, 195, 130, 130);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("");
		button_1.setToolTipText("Pedidos");
		button_1.setBorderPainted(false);
		button_1.setOpaque(true);
		button_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button_1.setBackground(Color.WHITE);
		ImageIcon icono11=new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/foods.png"));
		Image imagen11=icono11.getImage();
		ImageIcon iconoEsc11=new ImageIcon(imagen11.getScaledInstance(110,110, Image.SCALE_SMOOTH));
		button_1.setIcon(iconoEsc11);
		button_1.setBounds(106, 354,130, 130);
		frame.getContentPane().add(button_1);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setBorderPainted(false);
		btnNewButton.setOpaque(true);
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton.setToolTipText("Horario de reservas");
		btnNewButton.setBackground(Color.WHITE);
		ImageIcon icono111=new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/clock.png"));
		Image imagen111=icono111.getImage();
		ImageIcon iconoEsc111=new ImageIcon(imagen111.getScaledInstance(110,110, Image.SCALE_SMOOTH));
		btnNewButton.setIcon(iconoEsc111);
		btnNewButton.setBounds(266, 354,130, 130);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.setVisible(false);
				VentanaReservasRestaurante window = new VentanaReservasRestaurante(usuario);
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cerrar sesion");
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setBackground(new Color(234, 166, 93));
		btnNewButton_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				VentanaIni inicioSesion=new VentanaIni();
			}
		});
		btnNewButton_1.setBounds(355, 6,127, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel();
		ImageIcon icono1111=new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/Negrosinfondo.png"));
		Image imagen1111=icono1111.getImage();
		ImageIcon iconoEsc1111=new ImageIcon(imagen1111.getScaledInstance(100,100, Image.SCALE_SMOOTH));
		lblNewLabel.setIcon(iconoEsc1111);
		lblNewLabel.setBounds(199, 6, 100, 113);
		frame.getContentPane().add(lblNewLabel);
	}
}
