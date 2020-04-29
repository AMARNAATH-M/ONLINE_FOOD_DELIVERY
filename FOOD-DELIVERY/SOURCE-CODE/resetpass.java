import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.JCheckBox;
public class resetpass extends JFrame {

	private JPanel contentPane;
	private JButton btnConfirm;
	private JLabel lblNewLabel;
	private JPasswordField passw;
	private JLabel lblEnterNewPassword;
	private JPasswordField password;
	private JLabel lblConfirmNewPassword;
	private JPasswordField confirmpassword;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					resetpass frame = new resetpass();
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
	public resetpass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try {
					ArrayList<String> pass=new ArrayList<String>();
					ArrayList<String> user=new ArrayList<String>();
					if(passw.getText().equals("")||password.getText().equals("")||confirmpassword.getText().equals(""))
					{
						error=2;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","");
					Statement stmt=conn.createStatement();
					String sql=" select user_name,password from log";
					ResultSet rst=stmt.executeQuery(sql);
					while(rst.next())
					{
						user.add(rst.getString(1));
						pass.add(rst.getString(2));
					}
					if(!pass.get(0).equals(passw.getText()))
						throw new Exception();
					if(!password.getText().equals(confirmpassword.getText()))
					{
						error=1;
						throw new Exception();
					}
					PreparedStatement ps=conn.prepareStatement("update useracc set password='"+password.getText()+"' where user_name='"+user.get(0)+"'");
					ps.executeUpdate();
					PreparedStatement ps1=conn.prepareStatement("update log set password='"+password.getText()+"'");
					ps1.executeUpdate();
					JOptionPane.showMessageDialog(null,"Reset Successfull");
					dispose();
				}
				catch(Exception e1)
				{
					if(error!=2)
					{
						passw.setText("");
						confirmpassword.setText("");
						password.setText("");
					}
					if(error==1)
						JOptionPane.showMessageDialog(null, "Passwords Doesn't Match");
					else if(error==2)
						JOptionPane.showMessageDialog(null, "Fill All The Forms");
					else
						JOptionPane.showMessageDialog(null,"Incorrect Password");
				}
			}
		});
		btnConfirm.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnConfirm.setBounds(447, 203, 92, 34);
		contentPane.add(btnConfirm);
		
		lblNewLabel = new JLabel("Current Password");
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 47, 178, 34);
		contentPane.add(lblNewLabel);
		
		passw = new JPasswordField();
		passw.setFont(new Font("Agency FB", Font.PLAIN, 20));
		passw.setEchoChar('*');
		passw.setBounds(10, 84, 248, 34);
		contentPane.add(passw);
		
		lblEnterNewPassword = new JLabel("New Password");
		lblEnterNewPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblEnterNewPassword.setBounds(291, 47, 178, 34);
		contentPane.add(lblEnterNewPassword);
		
		password = new JPasswordField();
		password.setFont(new Font("Agency FB", Font.PLAIN, 20));
		password.setEchoChar('*');
		password.setBounds(291, 84, 248, 34);
		contentPane.add(password);
		
		lblConfirmNewPassword = new JLabel("Confirm New Password");
		lblConfirmNewPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblConfirmNewPassword.setBounds(10, 166, 188, 34);
		contentPane.add(lblConfirmNewPassword);
		
		confirmpassword = new JPasswordField();
		confirmpassword.setFont(new Font("Agency FB", Font.PLAIN, 20));
		confirmpassword.setEchoChar('*');
		confirmpassword.setBounds(10, 203, 248, 34);
		contentPane.add(confirmpassword);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnBack.setBounds(345, 203, 92, 34);
		contentPane.add(btnBack);
		
		JCheckBox checkBox = new JCheckBox("Show password");
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox.isSelected())
				{
					password.setEchoChar((char)0);
					passw.setEchoChar((char)0);
					confirmpassword.setEchoChar((char)0);
				}
				else
				{
					password.setEchoChar('*');
					passw.setEchoChar('*');
					confirmpassword.setEchoChar('*');
				}
			}
		});
		checkBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		checkBox.setBounds(291, 124, 123, 21);
		contentPane.add(checkBox);
	}
}
