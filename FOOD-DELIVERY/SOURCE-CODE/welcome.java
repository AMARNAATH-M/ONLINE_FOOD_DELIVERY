import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class welcome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					welcome frame = new welcome();
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
	public welcome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblDelizh = new JLabel(" DELIZH");
		lblDelizh.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblDelizh.setBounds(130, 10, 90, 25);
		contentPane.add(lblDelizh);
		
		JLabel lblGoGrabYour = new JLabel("Go, Grab Your Taste");
		lblGoGrabYour.setForeground(new Color(255, 140, 0));
		lblGoGrabYour.setFont(new Font("Agency FB", Font.PLAIN, 30));
		lblGoGrabYour.setBounds(80, 45, 188, 25);
		contentPane.add(lblGoGrabYour);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				adminlogin.main(null);
			}
		});
		btnAdmin.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnAdmin.setBounds(69, 111, 92, 34);
		contentPane.add(btnAdmin);
		
		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","");
					PreparedStatement ps1=conn.prepareStatement("delete from log");
					ps1.executeUpdate();
					dispose();
					weluser.main(null);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		btnUser.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnUser.setBounds(186, 111, 92, 34);
		contentPane.add(btnUser);
		
		JLabel lblNewLabel = new JLabel("Nothing Brings People Together Like...");
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 29));
		lblNewLabel.setBounds(10, 162, 426, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblGoodFood = new JLabel("Good Food.");
		lblGoodFood.setForeground(new Color(255, 165, 0));
		lblGoodFood.setFont(new Font("Agency FB", Font.PLAIN, 29));
		lblGoodFood.setBounds(233, 206, 93, 34);
		contentPane.add(lblGoodFood);
	}

}
