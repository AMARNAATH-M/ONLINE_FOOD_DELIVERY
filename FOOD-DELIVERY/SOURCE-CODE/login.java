import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {
	
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 278, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 35, 75, 30);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setFont(new Font("Agency FB", Font.PLAIN, 20));
		username.setBounds(10, 71, 248, 34);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblPassword.setBounds(10, 115, 86, 30);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					if(username.getText().equals("")||password.getText().equals(""))
					{
						error=2;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","");
					Statement stmt=conn.createStatement();
					String sql="Select * from useracc where user_name='"+username.getText()+"'and password='"+password.getText()+"'";
					ResultSet rs=stmt.executeQuery(sql);
					if(rs.next())
					{
						PreparedStatement ps=conn.prepareStatement("insert into log values(?,?,?)");
						ps.setString(1,rs.getString(1));
						ps.setString(2,username.getText());
						ps.setString(3,password.getText());
						ps.executeUpdate();
						dispose();
						restaurant.main(null);
					}
					else
					{
						String sql1="Select * from useracc where user_name='"+username.getText()+"'";
						ResultSet rs1=stmt.executeQuery(sql1);
						if(!rs1.next())
						{
							error=1;
							throw new Exception();
						}
						else
							throw new Exception();
					}
				}
				catch(Exception e1)
				{
					if(error!=2)
					{
						username.setText("");
						password.setText("");
					}
					if(error==1)
						JOptionPane.showMessageDialog(null,"Username Doesn't Exists");
					else if(error==2)
						JOptionPane.showMessageDialog(null, "Fill All The Forms");
					else
						JOptionPane.showMessageDialog(null,"Incorrect Password");
				}
			}
		});
		btnLogin.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnLogin.setBounds(166, 223, 92, 34);
		contentPane.add(btnLogin);
		
		JCheckBox checkBox = new JCheckBox("Show password");
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox.isSelected())
					password.setEchoChar((char)0);
				else
					password.setEchoChar('*');
			}
		});
		checkBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		checkBox.setBounds(10, 187, 123, 21);
		contentPane.add(checkBox);
		
		password = new JPasswordField();
		password.setFont(new Font("Agency FB", Font.PLAIN, 20));
		password.setEchoChar('*');
		password.setBounds(10, 147, 248, 34);
		contentPane.add(password);
		
		JLabel label = new JLabel("DELIZH");
		label.setFont(new Font("Agency FB", Font.BOLD, 30));
		label.setBounds(110, 10, 64, 25);
		contentPane.add(label);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				weluser.main(null);
			}
		});
		button.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button.setBounds(64, 223, 92, 34);
		contentPane.add(button);
	}

}
