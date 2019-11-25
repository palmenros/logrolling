package Presentacion;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import Negocio.TransferReserva;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class VentanaReservasClientes {

		private JFrame frame;
		private Controller ctrl;
		private String usuario;
		private JTable table;

		public VentanaReservasClientes(String usuario) {
			this.ctrl=Controller.getInstance();
			this.usuario = usuario;
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
			
			JButton btnNewButton_1 = new JButton();
			btnNewButton_1.setBackground(new Color(234, 166, 93));
			btnNewButton_1.setBorderPainted(false);
			btnNewButton_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnNewButton_1.setBounds(222, 592, 56, 41);
			ImageIcon icono1 = new javax.swing.ImageIcon(VentanaIni.class.getResource("/Resources/return.png"));
			Image imagen1 = icono1.getImage();
			ImageIcon iconoEsc1 = new ImageIcon(imagen1.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
			btnNewButton_1.setIcon(iconoEsc1);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					frame.setVisible(false);
					VentanaPerfilCliente perfilcliente = new VentanaPerfilCliente(usuario);
				}
			});
			frame.getContentPane().add(btnNewButton_1);
			
			
			String[] columns = new String[] {
		            "Usuario", "Numero personas", "Fecha"
		     };
			
			ArrayList<TransferReserva> reservas = ctrl.getReservasC(usuario);
			
			Object[][] data = new Object[reservas.size()][3];
			for (int i =0;i<reservas.size();++i) {
				data[i][0] = reservas.get(i).getRestaurante();
				data[i][1] = reservas.get(i).getNumComensales();
				data[i][2] = reservas.get(i).getDateTime();
			}
			
	        table = new JTable(data,columns);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.setRowSelectionAllowed(true);
	        table.setShowGrid(false);
	        table.setShowHorizontalLines(false);
	        DefaultTableModel model = new DefaultTableModel(
	            	data,
	            	columns
	            );
	        table.setModel(model);
			table.setBounds(64, 147, 372, 369);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			frame.getContentPane().add(table);
			
			JButton btnBorrarReserva = new JButton("Borrar reserva");
			btnBorrarReserva.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			btnBorrarReserva.setBorderPainted(false);
			btnBorrarReserva.setOpaque(true);
			btnBorrarReserva.setBackground(Color.white);
			btnBorrarReserva.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnBorrarReserva.setBounds(64, 528, 372, 29);
			btnBorrarReserva.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() != -1) {
						TransferReserva t = new TransferReserva();
						t.setCliente(usuario);
						t.setRestaurante((String) table.getValueAt(table.getSelectedRow(), 0));
						t.setNumComensales((int) table.getValueAt(table.getSelectedRow(), 1));
						t.setDateTime((String) table.getValueAt(table.getSelectedRow(), 2));
						ctrl.borrarReserva(t);
						((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
						table.repaint();
					}
				}
			});
			frame.getContentPane().add(btnBorrarReserva);
		}
}
