
import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField confirmpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
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
	public signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		name = new JTextField();
		name.setFont(new Font("Agency FB", Font.PLAIN, 20));
		name.setBounds(10, 83, 248, 34);
		contentPane.add(name);
		name.setColumns(10);
		
		username = new JTextField();
		username.setFont(new Font("Agency FB", Font.PLAIN, 20));
		username.setColumns(10);
		username.setBounds(323, 83, 248, 34);
		contentPane.add(username);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 50, 45, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblUsername.setBounds(323, 49, 79, 25);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblPassword.setBounds(10, 146, 79, 23);
		contentPane.add(lblPassword);
		
		JLabel lblDelizh = new JLabel("DELIZH");
		lblDelizh.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblDelizh.setBounds(257, 10, 64, 25);
		contentPane.add(lblDelizh);
		
		password = new JPasswordField();
		password.setEchoChar('*');
		password.setFont(new Font("Agency FB", Font.PLAIN, 20));
		password.setBounds(10, 179, 248, 34);
		contentPane.add(password);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblConfirmPassword.setBounds(323, 146, 141, 23);
		contentPane.add(lblConfirmPassword);
		
		confirmpassword = new JPasswordField();
		confirmpassword.setEchoChar('*');
		confirmpassword.setFont(new Font("Agency FB", Font.PLAIN, 20));
		confirmpassword.setBounds(323, 179, 248, 34);
		contentPane.add(confirmpassword);
		
		JButton btnNewButton = new JButton("Signup");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try {
					if(name.getText().equals("")||username.getText().equals("")||password.getText().equals("")||confirmpassword.getText().equals(""))
					{
						error=2;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","");
					PreparedStatement ps=conn.prepareStatement("insert into useracc values(?,?,?)");
					if(!password.getText().equals(confirmpassword.getText()))
					{
						error=1;
						throw new Exception();
					}
					ps.setString(1,name.getText());
					ps.setString(2,username.getText());
					ps.setString(3,password.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Signup Successfull");
				}
				catch(Exception e1)
				{
					if(error!=2)
					{
						confirmpassword.setText("");
						username.setText("");
						password.setText("");
					}
					if(error==1)
						JOptionPane.showMessageDialog(null, "Passwords Doesn't Match");
					else if(error==2)
						JOptionPane.showMessageDialog(null, "Fill All The Forms");
					else
						JOptionPane.showMessageDialog(null, "Username Already Taken");
				}
			}
		});
		btnNewButton.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnNewButton.setBounds(479, 223, 92, 34);
		contentPane.add(btnNewButton);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show password");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected())
				{
					password.setEchoChar((char)0);
					confirmpassword.setEchoChar((char)0);
				}
				else
				{
					password.setEchoChar('*');
					confirmpassword.setEchoChar('*');
				}
					
			}
		});
		chckbxNewCheckBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		chckbxNewCheckBox.setBounds(10, 219, 153, 21);
		contentPane.add(chckbxNewCheckBox);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				weluser.main(null);
			}
		});
		button.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button.setBounds(372, 223, 92, 34);
		contentPane.add(button);
	}
}
