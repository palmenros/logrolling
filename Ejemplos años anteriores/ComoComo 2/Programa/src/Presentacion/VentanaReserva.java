package Presentacion;

import java.awt.Color;
import java.util.Date;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import Negocio.TransferUsuario;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VentanaReserva {

	private JFrame frame;
	private Controller ctrl;
	private String cliente;
	private TransferUsuario restaurante;

	public VentanaReserva(String cliente, TransferUsuario restaurante) {
		this.ctrl=Controller.getInstance();
		this.cliente = cliente;
		this.restaurante = restaurante;
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(234, 166, 93));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel();
		ImageIcon icono=new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/Negrosinfondo.png"));
		Image imagen=icono.getImage();
		ImageIcon iconoEsc=new ImageIcon(imagen.getScaledInstance(100,100, Image.SCALE_SMOOTH));
		lblNewLabel.setIcon(iconoEsc);
		lblNewLabel.setBounds(200, 18, 100, 113);
		frame.getContentPane().add(lblNewLabel);
		
		JSpinner spinner = new JSpinner(new SpinnerDateModel(Calendar.getInstance().getTime(),null,null,Calendar.DAY_OF_YEAR));
		spinner.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		spinner.setBounds(160, 309, 179, 26);
		frame.getContentPane().add(spinner);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnMenu.setBounds(191, 367, 117, 29);
		frame.getContentPane().add(btnMenu);
		
		JButton btnMesa = new JButton("Mesa");
		btnMesa.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnMesa.setBounds(191, 408, 117, 29);
		frame.getContentPane().add(btnMesa);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		spinner_1.setBounds(318, 257, 59, 26);
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 15, 1));
		frame.getContentPane().add(spinner_1);
		
		JLabel lblNPersonas = new JLabel("N\u00BA comensales:");
		lblNPersonas.setBackground(Color.WHITE);
		lblNPersonas.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNPersonas.setForeground(Color.BLACK);
		lblNPersonas.setBounds(122, 262, 117, 16);
		frame.getContentPane().add(lblNPersonas);
		
		JButton btnConfirmarPedido = new JButton("Confirmar reserva");
		btnConfirmarPedido.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnConfirmarPedido.setBounds(122, 488, 255, 45);
		btnConfirmarPedido.setBorderPainted(false);
		btnConfirmarPedido.setOpaque(true);
		btnConfirmarPedido.setBackground(Color.WHITE);
		btnConfirmarPedido.setForeground(Color.BLACK);
		btnConfirmarPedido.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnConfirmarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ctrl.aniadirReserva(cliente, restaurante, (Integer) spinner_1.getValue(), spinner.getValue().toString())) {
					frame.setVisible(false);
					VentanaPerfilCliente inicioSesion = new VentanaPerfilCliente(cliente);
				}
				else JOptionPane.showMessageDialog(frame,
						"No se anadio el pedido correctamente", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				}
			
		});
		frame.getContentPane().add(btnConfirmarPedido);
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBackground(new Color(234, 166, 93));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ImageIcon icono1 = new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/return.png"));
		Image imagen1 = icono1.getImage();
		ImageIcon iconoEsc1 = new ImageIcon(imagen1.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		btnNewButton_1.setIcon(iconoEsc1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				VentanaPerfilCliente perfilcliente = new VentanaPerfilCliente(cliente);
			}
		});
		btnNewButton_1.setBounds(222, 561, 56, 41);
		frame.getContentPane().add(btnNewButton_1);
	}
}
