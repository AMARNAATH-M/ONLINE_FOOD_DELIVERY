import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class booked extends JFrame {

	private JPanel contentPane;
	private JTextField payment;
	private JTextField tota;
	private JTextField dedu;
	private JTextField topa;
	static double tot;
	static double dedud;
	static double top;
	static String rest;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					booked frame = new booked();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public booked() {
		tot=confirm.cost;
		dedud=confirm.cost-confirm.endcost;
		top=confirm.endcost;
		rest=restaurant.rest;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPayementMethod = new JLabel("Payement Method:");
		lblPayementMethod.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblPayementMethod.setBounds(10, 13, 139, 23);
		contentPane.add(lblPayementMethod);
		
		payment = new JTextField();
		payment.setFont(new Font("Agency FB", Font.PLAIN, 20));
		payment.setColumns(10);
		payment.setBounds(151, 13, 248, 34);
		contentPane.add(payment);
		
		JLabel label_1 = new JLabel("Total:");
		label_1.setFont(new Font("Agency FB", Font.PLAIN, 25));
		label_1.setBounds(10, 57, 92, 23);
		contentPane.add(label_1);
		
		tota = new JTextField();
		tota.setText(Double.toString(tot));
		tota.setFont(new Font("Agency FB", Font.PLAIN, 20));
		tota.setColumns(10);
		tota.setBounds(151, 57, 248, 34);
		contentPane.add(tota);
		
		JLabel lblDeduction = new JLabel("Deduction:");
		lblDeduction.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblDeduction.setBounds(10, 101, 92, 23);
		contentPane.add(lblDeduction);
		
		dedu = new JTextField();
		dedu.setText(Double.toString(dedud));
		dedu.setFont(new Font("Agency FB", Font.PLAIN, 20));
		dedu.setColumns(10);
		dedu.setBounds(151, 101, 248, 34);
		contentPane.add(dedu);
		
		JLabel lblToPay = new JLabel("To Pay:");
		lblToPay.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblToPay.setBounds(10, 145, 92, 23);
		contentPane.add(lblToPay);
		
		topa = new JTextField();
		topa.setText(Double.toString(top));
		topa.setFont(new Font("Agency FB", Font.PLAIN, 20));
		topa.setColumns(10);
		topa.setBounds(151, 145, 248, 34);
		contentPane.add(topa);
		
		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","");
					Statement stmt=conn.createStatement();
					PreparedStatement pst=conn.prepareStatement("insert into history values(?,?)");
					pst.setString(1,rest);
					pst.setDouble(2,top);
					pst.executeUpdate();
					dispose();
					rating.main(null);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		btnPlaceOrder.setFont(new Font("Agency FB", Font.PLAIN, 17));
		btnPlaceOrder.setBounds(307, 189, 92, 34);
		contentPane.add(btnPlaceOrder);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				confirm.cost=0;
				confirm.ded=0;
				confirm.endcost=0;
				confirm.main(null);
			}
		});
		btnBack.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnBack.setBounds(205, 189, 92, 34);
		contentPane.add(btnBack);
		
		payment.setText("Cash On Delivery");
	}

}
