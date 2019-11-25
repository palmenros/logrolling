package Presentacion;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;

public class VentanaPrincipal {
	
	private JFrame frame;

	public VentanaPrincipal() {
		initialize();
		frame.setVisible(true);
	}


	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(234, 166, 93));
		frame.setBounds(100, 100, 500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		ImageIcon icono=new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/Negrosinfondo.png"));
		Image imagen=icono.getImage();
		ImageIcon iconoEsc=new ImageIcon(imagen.getScaledInstance(300,300, Image.SCALE_SMOOTH));
		lblNewLabel.setIcon(iconoEsc);
		lblNewLabel.setBounds(100, 85, 300, 308);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnComenzarLaExperiencia = new JButton("Comenzar la experiencia");
		btnComenzarLaExperiencia.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnComenzarLaExperiencia.setBorderPainted(false);
		btnComenzarLaExperiencia.setOpaque(true);
		btnComenzarLaExperiencia.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnComenzarLaExperiencia.setBackground(Color.WHITE);
		btnComenzarLaExperiencia.setForeground(new Color(0, 0, 0));
		btnComenzarLaExperiencia.setBounds(122, 488, 255, 45);
		frame.getContentPane().add(btnComenzarLaExperiencia);
		btnComenzarLaExperiencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				VentanaIni inicioSesion=new VentanaIni();
			}
		});
	}
}
