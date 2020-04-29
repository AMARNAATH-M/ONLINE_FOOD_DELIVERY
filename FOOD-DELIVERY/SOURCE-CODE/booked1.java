import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class booked1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField uname;
	private JPasswordField pass;
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
					booked1 frame = new booked1();
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
	public booked1() {
		tot=confirm.cost;
		dedud=confirm.cost-confirm.endcost;
		top=confirm.endcost;
		rest=restaurant.rest;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Place Order");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					if(uname.getText().equals("")||pass.getText().equals(""))
					{
						error=1;
						throw new Exception();
					}
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
					if(error==1)
					{
						JOptionPane.showMessageDialog(null,"Fill All The Forms");
					}
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		button.setFont(new Font("Agency FB", Font.PLAIN, 17));
		button.setBounds(689, 123, 92, 34);
		contentPane.add(button);
		
		JLabel label = new JLabel("Payement Method:");
		label.setFont(new Font("Agency FB", Font.PLAIN, 25));
		label.setBounds(10, 38, 139, 23);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setText("Net Banking");
		textField.setFont(new Font("Agency FB", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(151, 38, 248, 34);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("Total:");
		label_1.setFont(new Font("Agency FB", Font.PLAIN, 25));
		label_1.setBounds(10, 82, 92, 23);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setText(Double.toString(tot));
		textField_1.setFont(new Font("Agency FB", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(151, 82, 248, 34);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("Deduction:");
		label_2.setFont(new Font("Agency FB", Font.PLAIN, 25));
		label_2.setBounds(10, 126, 92, 23);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setText(Double.toString(dedud));
		textField_2.setFont(new Font("Agency FB", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(151, 126, 248, 34);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText(Double.toString(top));
		textField_3.setFont(new Font("Agency FB", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(151, 170, 248, 34);
		contentPane.add(textField_3);
		
		JLabel label_3 = new JLabel("To Pay:");
		label_3.setFont(new Font("Agency FB", Font.PLAIN, 25));
		label_3.setBounds(10, 170, 92, 23);
		contentPane.add(label_3);
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				confirm.main(null);
			}
		});
		button_1.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button_1.setBounds(587, 122, 92, 34);
		contentPane.add(button_1);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblUsername.setBounds(441, 38, 92, 23);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblPassword.setBounds(441, 82, 92, 23);
		contentPane.add(lblPassword);
		
		uname = new JTextField();
		uname.setFont(new Font("Agency FB", Font.PLAIN, 20));
		uname.setColumns(10);
		uname.setBounds(533, 38, 248, 34);
		contentPane.add(uname);
		
		pass = new JPasswordField();
		pass.setFont(new Font("Agency FB", Font.PLAIN, 20));
		pass.setEchoChar('*');
		pass.setBounds(533, 78, 248, 34);
		contentPane.add(pass);
	}

}
