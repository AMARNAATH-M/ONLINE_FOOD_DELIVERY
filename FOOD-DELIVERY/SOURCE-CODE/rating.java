import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class rating extends JFrame {
	static String res;
	private JPanel contentPane;
	private JTextField ress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rating frame = new rating();
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
	public rating() {
		res=restaurant.rest;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Order Placed!");
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 30));
		lblNewLabel.setBounds(62, 52, 139, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblRate = new JLabel(" Rate Us");
		lblRate.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblRate.setBounds(10, 105, 63, 34);
		contentPane.add(lblRate);
		
		ress = new JTextField();
		ress.setText(res);
		ress.setFont(new Font("Agency FB", Font.PLAIN, 20));
		ress.setColumns(10);
		ress.setBounds(72, 105, 248, 34);
		contentPane.add(ress);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinner.setFont(new Font("Agency FB", Font.PLAIN, 20));
		spinner.setBounds(82, 149, 44, 32);
		contentPane.add(spinner);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double rate,nwrate,endrate=5;
				int per;
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","");
					Statement stmt=conn.createStatement();
					String sql="Select * from restaurant where name='"+res+"'";
					ResultSet rs=stmt.executeQuery(sql);
					if(!rs.next())
						throw new Exception();
					rate=rs.getDouble(2);
					per=rs.getInt(3);
					per+=1;
					nwrate=(int)spinner.getValue();
					endrate=(rate+nwrate)/per;
					PreparedStatement pst=conn.prepareStatement("update restaurant set rating="+endrate+",person="+per+" where name='"+res+"'");
					pst.executeUpdate();
					PreparedStatement ps=conn.prepareStatement("delete from ordered");
					ps.executeUpdate();
					dispose();
					restaurant.main(null);
				}
				catch(Exception e1)
				{
					if(endrate==6)
					{
						
					}
						JOptionPane.showConfirmDialog(null,e);
				}
			}
		});
		btnSubmit.setFont(new Font("Agency FB", Font.PLAIN, 17));
		btnSubmit.setBounds(136, 149, 92, 34);
		contentPane.add(btnSubmit);
	}
}
