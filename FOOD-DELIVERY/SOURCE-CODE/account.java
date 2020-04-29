import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class account extends JFrame {

	private JPanel contentPane;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					account frame = new account();
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
	public account() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 256, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 38, 214, 63);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Agency FB", Font.PLAIN, 20));
		table.setRowHeight(40);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","");
			PreparedStatement pst=conn.prepareStatement(" select name,user_name from log");
			ResultSet rst=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rst));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(138, 223, 92, 34);
		button.setFont(new Font("Agency FB", Font.PLAIN, 20));
		contentPane.add(button);
		
		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetpass.main(null);
			}
		});
		btnResetPassword.setBounds(57, 119, 112, 34);
		btnResetPassword.setFont(new Font("Agency FB", Font.PLAIN, 17));
		contentPane.add(btnResetPassword);
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteacc.main(null);
			}
		});
		btnDeleteAccount.setFont(new Font("Agency FB", Font.PLAIN, 14));
		btnDeleteAccount.setBounds(57, 163, 112, 34);
		contentPane.add(btnDeleteAccount);
	}

}

