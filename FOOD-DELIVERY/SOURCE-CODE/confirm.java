import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class confirm extends JFrame {
	private JComboBox comboBox;
	private JPanel contentPane;
	private JTable table;
	private JTextField total;
	private JTextField code;
	static double cost=0,endcost,ded=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					confirm frame = new confirm();
					frame.setVisible(true);
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","");
					Statement stmt=conn.createStatement();
					String sql1="Select * from ordered";
					ResultSet rst=stmt.executeQuery(sql1);
					frame.table.setModel(DbUtils.resultSetToTableModel(rst));
					ResultSet rs=stmt.executeQuery(sql1);
					while(rs.next())
						cost+=rs.getInt(3);
					frame.total.setText(Double.toString(cost));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public confirm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 38, 358, 180);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Agency FB", Font.PLAIN, 20));
		table.setRowHeight(40);
		scrollPane.setViewportView(table);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblTotal.setBounds(16, 228, 92, 23);
		contentPane.add(lblTotal);
		
		total = new JTextField();
		total.setFont(new Font("Agency FB", Font.PLAIN, 20));
		total.setColumns(10);
		total.setBounds(126, 225, 248, 34);
		contentPane.add(total);
		
		JLabel lblCouponoptional = new JLabel("Coupon(Optional)");
		lblCouponoptional.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCouponoptional.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblCouponoptional.setBounds(384, 38, 135, 34);
		contentPane.add(lblCouponoptional);
		
		code = new JTextField();
		code.setFont(new Font("Agency FB", Font.PLAIN, 20));
		code.setColumns(10);
		code.setBounds(384, 82, 248, 34);
		contentPane.add(code);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cash On Delivery", "Net Banking", "Debit Card"}));
		comboBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		comboBox.setBounds(384, 170, 248, 34);
		contentPane.add(comboBox);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method");
		lblPaymentMethod.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPaymentMethod.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblPaymentMethod.setBounds(384, 126, 135, 34);
		contentPane.add(lblPaymentMethod);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try
					{
						if(!code.getText().equals(""))
						{
							Class.forName("com.mysql.jdbc.Driver");
							Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","");
							Statement stmt=conn.createStatement();
							String sql="Select deduction from coupon where code='"+code.getText()+"'";
							ResultSet rs=stmt.executeQuery(sql);
							if(!rs.next())
								throw new Exception();
							ded=rs.getDouble(1);
						}
						endcost=cost-(cost*ded);
						if(comboBox.getSelectedItem().equals("Cash On Delivery"))
						{
							booked.main(null);
							dispose();						
						}
						else if(comboBox.getSelectedItem().equals("Net Banking"))
						{
							booked1.main(null);
							dispose();
						}
						else
						{
							booked2.main(null);
							dispose();	
						}
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null,"Coupon Code Doesn't Exists");
					}
			}
		});
		btnConfirm.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnConfirm.setBounds(540, 228, 92, 34);
		contentPane.add(btnConfirm);
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				menu.main(null);
			}
		});
		button_1.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button_1.setBounds(427, 228, 92, 34);
		contentPane.add(button_1);
		
		JButton button = new JButton("Coupon");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coupon.main(null);
			}
		});
		button.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button.setBounds(540, 10, 92, 34);
		contentPane.add(button);
	}

}
