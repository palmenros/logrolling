package Presentacion;

import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class VentanaRegistro {

	private JFrame frame;

	private Controller ctrl;
	private JTextField txtInserteNombre;

	public VentanaRegistro() {
		this.ctrl = Controller.getInstance();
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();

		JLabel lblNewLabel = new JLabel();
		ImageIcon icono = new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/cooker (1).png"));
		Image imagen = icono.getImage();
		ImageIcon iconoEsc = new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
		lblNewLabel.setIcon(iconoEsc);
		lblNewLabel.setBounds(175, 73, 150, 167);
		frame.getContentPane().add(lblNewLabel);

		frame.getContentPane().setBackground(new Color(234, 166, 93));
		frame.setBounds(100, 100, 500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextField txtUserName = new JTextField();
		txtUserName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtUserName.setToolTipText("Inserte nombre de usuario");
		txtUserName.setText("Nombre de usuario");
		txtUserName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		txtUserName.setForeground(Color.white);
		txtUserName.setBackground(null);
		txtUserName.addMouseListener(new MouseAdapter(){ 
		      @Override 
		      public void mouseClicked(MouseEvent e){ 
		       txtUserName.setText(""); 
		      } 
		});
		txtUserName.setBounds(117, 272, 266, 26);
		frame.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);

		JTextField txtEmailDeUsuario = new JTextField();
		txtEmailDeUsuario.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtEmailDeUsuario.setToolTipText("Inserte email");
		txtEmailDeUsuario.setText("Email");
		txtEmailDeUsuario.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		txtEmailDeUsuario.setForeground(Color.white);
		txtEmailDeUsuario.setBackground(null);
		txtEmailDeUsuario.addMouseListener(new MouseAdapter(){ 
		      @Override 
		      public void mouseClicked(MouseEvent e){ 
		       txtEmailDeUsuario.setText(""); 
		      } 
		});
		txtEmailDeUsuario.setBounds(117, 356, 266, 26);
		frame.getContentPane().add(txtEmailDeUsuario);
		txtEmailDeUsuario.setColumns(10);

		JPasswordField pwdContrasea = new JPasswordField();
		pwdContrasea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		pwdContrasea.setToolTipText("Inserte contrasena");
		pwdContrasea.setText("Contrasena");
		pwdContrasea.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		pwdContrasea.setForeground(Color.white);
		pwdContrasea.setBackground(null);
		pwdContrasea.addMouseListener(new MouseAdapter(){ 
		      @Override 
		      public void mouseClicked(MouseEvent e){ 
		       pwdContrasea.setText(""); 
		      } 
		});
		pwdContrasea.setBounds(117, 398, 266, 26);
		frame.getContentPane().add(pwdContrasea);

		txtInserteNombre = new JTextField();
		txtInserteNombre.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtInserteNombre.setToolTipText("Inserte nombre restaurante o cliente");
		txtInserteNombre.setText("Nombre restaurante/cliente");
		txtInserteNombre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		txtInserteNombre.setForeground(Color.white);
		txtInserteNombre.setBackground(null);
		txtInserteNombre.addMouseListener(new MouseAdapter(){ 
		      @Override 
		      public void mouseClicked(MouseEvent e){ 
		       txtInserteNombre.setText(""); 
		      } 
		});
		txtInserteNombre.setBounds(117, 314, 266, 26);
		frame.getContentPane().add(txtInserteNombre);
		txtInserteNombre.setColumns(10);

		JRadioButton rdbtnRestaurante = new JRadioButton("Restaurante");
		rdbtnRestaurante.setForeground(Color.WHITE);
		rdbtnRestaurante.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rdbtnRestaurante.setBackground(new Color(234, 166, 93));
		rdbtnRestaurante.setBounds(117, 456, 144, 29);
		frame.getContentPane().add(rdbtnRestaurante);

		JRadioButton rdbtnCliente = new JRadioButton("Cliente");
		rdbtnCliente.setForeground(Color.WHITE);
		rdbtnCliente.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rdbtnCliente.setBackground(new Color(234, 166, 93));
		rdbtnCliente.setBounds(282, 456, 101, 29);
		frame.getContentPane().add(rdbtnCliente);
		rdbtnCliente.setSelected(true);

		ButtonGroup grupoBotones = new ButtonGroup();
		grupoBotones.add(rdbtnRestaurante);
		grupoBotones.add(rdbtnCliente);
		frame.getContentPane().add(rdbtnRestaurante);
		frame.getContentPane().add(rdbtnCliente);

		JButton btnNewButton = new JButton("Registrarse");
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setOpaque(true);
		btnNewButton.setBackground(Color.white);
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(122, 525, 255, 45);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean cliente = true;
				Object[] opcion = rdbtnCliente.getSelectedObjects();
				if (opcion == null)
					cliente = false;
				
				if (txtUserName.getText().equals("") || txtEmailDeUsuario.getText().equals("")
						|| pwdContrasea.getPassword().length == 0 || txtInserteNombre.getText().equals("")) {
					JOptionPane.showMessageDialog(frame,
							"Alguno de los campos esta vacio", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						ctrl.registrar(txtUserName.getText(), txtEmailDeUsuario.getText(), pwdContrasea.getPassword(),
								cliente, txtInserteNombre.getText());
						frame.setVisible(false);
						VentanaIni ventanaInicioSesion = new VentanaIni();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}

			}

		});
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBounds(222, 602, 56, 41);
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
				VentanaIni inicioSesion = new VentanaIni();
			}
		});
		frame.getContentPane().add(btnNewButton_1);

	}
}
