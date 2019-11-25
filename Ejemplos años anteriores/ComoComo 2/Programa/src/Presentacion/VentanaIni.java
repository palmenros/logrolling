package Presentacion;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class VentanaIni {

	private JFrame frame;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private Controller ctrl;
	
	private JRadioButton rdbtnRestaurante;
	private JRadioButton rdbtnCliente;
	private JLabel lblNewLabel;
	
	public VentanaIni() {		
		this.ctrl=Controller.getInstance();
		initialize();
		this.frame.setVisible(true);
	}
	
	private void initialize() {
		AccionBotones actBoton=new AccionBotones();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(234, 166, 93));
		frame.getContentPane().setLayout(null);
		
		rdbtnRestaurante = new JRadioButton("Restaurante");
		rdbtnRestaurante.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rdbtnRestaurante.setForeground(Color.WHITE);
		rdbtnRestaurante.setBackground(new Color(234, 166, 93));
		rdbtnRestaurante.setBounds(125, 410, 144, 29);
		
		
		rdbtnCliente = new JRadioButton("Cliente");
		rdbtnCliente.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rdbtnCliente.setForeground(Color.WHITE);
		rdbtnCliente.setBackground(new Color(234, 166, 93));
		rdbtnCliente.setBounds(279, 410, 101, 29);
		rdbtnCliente.setSelected(true);
		
		ButtonGroup grupoBotones = new ButtonGroup();
		grupoBotones.add(rdbtnRestaurante);
		grupoBotones.add(rdbtnCliente );
		frame.getContentPane().add(rdbtnRestaurante);
		frame.getContentPane().add(rdbtnCliente);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textField_1.setToolTipText("Inserte usuario");
		textField_1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		textField_1.setForeground(Color.white);
		textField_1.setBackground(null);
		textField_1.setBounds(164, 295, 216, 29);
		textField_1.setText("Usuario");
		textField_1.addMouseListener(new MouseAdapter(){ 
		      @Override 
		      public void mouseClicked(MouseEvent e){ 
		       textField_1.setText(""); 
		      } 
		});
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnRe = new JButton("Registrarse");
		btnRe.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnRe.setBorderPainted(false);
		btnRe.setOpaque(true);
		btnRe.setBackground(Color.white);
		btnRe.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRe.addActionListener(actBoton);
		btnRe.setActionCommand("R");
		btnRe.setBounds(259, 561, 121, 29);
		frame.getContentPane().add(btnRe);
		
		JLabel lblnoTienesCuenta = new JLabel("Si no tienes cuenta");
		lblnoTienesCuenta.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblnoTienesCuenta.setForeground(Color.WHITE);
		lblnoTienesCuenta.setBounds(125, 564, 128, 20);
		frame.getContentPane().add(lblnoTienesCuenta);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesion");
		btnIniciarSesion.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnIniciarSesion.setBorderPainted(false);
		btnIniciarSesion.setOpaque(true);
		btnIniciarSesion.setBackground(Color.white);
		btnIniciarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnIniciarSesion.setBounds(123, 488, 255, 45);
		frame.getContentPane().add(btnIniciarSesion);
		btnIniciarSesion.addActionListener(actBoton);
		btnIniciarSesion.setActionCommand("IS");
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		passwordField.setToolTipText("Inserte contrasena");
		passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		passwordField.setForeground(Color.white);
		passwordField.setBackground(null);
		passwordField.setBounds(164, 352, 216, 26);
		passwordField.setText("Contrasena");
		passwordField.addMouseListener(new MouseAdapter(){ 
		      @Override 
		      public void mouseClicked(MouseEvent e){ 
		       passwordField.setText(""); 
		      } 
		});
		passwordField.addActionListener(actBoton);
		passwordField.setActionCommand("IS");
		frame.getContentPane().add(passwordField);
		
		lblNewLabel = new JLabel();
		ImageIcon icono=new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/cooker (1).png"));
		Image imagen=icono.getImage();
		ImageIcon iconoEsc=new ImageIcon(imagen.getScaledInstance(150,150, Image.SCALE_SMOOTH));
		lblNewLabel.setIcon(iconoEsc);
		lblNewLabel.setBounds(175, 73, 150, 167);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_user = new JLabel();
		ImageIcon icono1=new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/user2.png"));
		Image imagen1=icono1.getImage();
		ImageIcon iconoEsc1=new ImageIcon(imagen1.getScaledInstance(20,20, Image.SCALE_SMOOTH));
		lblNewLabel_user.setIcon(iconoEsc1);
		lblNewLabel_user.setBounds(125, 295, 20, 29);
		frame.getContentPane().add(lblNewLabel_user);
		
		
		JLabel lblNewLabel_p = new JLabel();
		ImageIcon icono11=new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/password2.png"));
		Image imagen11=icono11.getImage();
		ImageIcon iconoEsc11=new ImageIcon(imagen11.getScaledInstance(25,25, Image.SCALE_SMOOTH));
		lblNewLabel_p.setIcon(iconoEsc11);
		lblNewLabel_p.setBounds(123, 352, 27, 29);
		frame.getContentPane().add(lblNewLabel_p);
		
		frame.setBounds(100, 100, 500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private class AccionBotones implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals("IS")) {
				boolean cliente=true;
				Object[] opcion=rdbtnCliente.getSelectedObjects();
				if(opcion==null)cliente=false;
				try{
					ctrl.iniciarSesion(textField_1.getText(),passwordField.getPassword(),cliente);
					if(cliente) {
						frame.setVisible(false);
						VentanaPerfilCliente window1 = new VentanaPerfilCliente(textField_1.getText());
					}
					else {
						frame.setVisible(false);
						VentanaPerfilRestaurante window = new VentanaPerfilRestaurante(textField_1.getText());
					} 
				} catch(Exception e) {
					JOptionPane.showMessageDialog(frame,
							e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (event.getActionCommand().equals("R")) {
				frame.setVisible(false);
				VentanaRegistro ventanaReg=new VentanaRegistro();
			} 
		}
	}
}
