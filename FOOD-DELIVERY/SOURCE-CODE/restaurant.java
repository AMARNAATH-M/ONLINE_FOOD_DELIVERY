import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Cursor;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextField;



public class restaurant extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField restaurant;
	static String rest;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {			
					restaurant frame = new restaurant();
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
	public restaurant() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 304);
		contentPane = new JPanel();
		contentPane.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
			}
		});
		contentPane.setLocation(-565, 0);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(674, 10, 102, 51);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setSize(panel.getWidth(),171);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setSize(panel.getWidth(),50);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCoupon = new JButton("Coupon");
		btnCoupon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setSize(panel.getWidth(),171);
			}
		});
		btnCoupon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coupon.main(null);
			}
		});
		btnCoupon.setBounds(10, 10, 82, 30);
		panel.add(btnCoupon);
		btnCoupon.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		JButton btnAccount = new JButton("Account");
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setSize(panel.getWidth(),171);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setSize(panel.getWidth(),50);
			}
		});
		btnAccount.setBounds(10, 50, 82, 30);
		panel.add(btnAccount);
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account.main(null);
			}
		});
		btnAccount.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		JButton btnDeveloper = new JButton("About");
		btnDeveloper.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setSize(panel.getWidth(),171);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setSize(panel.getWidth(),50);
			}
		});
		btnDeveloper.setBounds(10, 90, 82, 30);
		panel.add(btnDeveloper);
		btnDeveloper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				about.main(null);
			}
		});
		btnDeveloper.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","");
					PreparedStatement ps1=conn.prepareStatement("delete from log");
					ps1.executeUpdate();
					dispose();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setSize(panel.getWidth(),171);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setSize(panel.getWidth(),50);
			}
		});
		btnLogout.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnLogout.setBounds(10, 130, 82, 30);
		panel.add(btnLogout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 430, 180);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(40);
		table.setFont(new Font("Agency FB", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		
		JSlider slider = new JSlider();
		slider.setValue(0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","");
					if(slider.getValue()==0)
					{
						PreparedStatement pst=conn.prepareStatement("select name,cast(rating as decimal(10,1)) as rating from restaurant");
						ResultSet rst=pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rst));
					}
					else
					{
						PreparedStatement ps=conn.prepareStatement("select name,rating from restaurant where rating>="+slider.getValue()+"");
						ResultSet rs=ps.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		slider.setFont(new Font("Agency FB", Font.PLAIN, 20));
		slider.setMaximum(5);
		slider.setBounds(450, 100, 200, 22);
		contentPane.add(slider);
		
		JLabel lblNewLabel = new JLabel("                     Rating");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblNewLabel.setBounds(450, 62, 200, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblRestaurant = new JLabel("Restaurant");
		lblRestaurant.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblRestaurant.setBounds(450, 146, 92, 23);
		contentPane.add(lblRestaurant);
		
		restaurant = new JTextField();
		restaurant.setFont(new Font("Agency FB", Font.PLAIN, 20));
		restaurant.setColumns(10);
		restaurant.setBounds(450, 179, 248, 34);
		contentPane.add(restaurant);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					if(restaurant.getText().equals(""))
					{
						error=1;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","");
					Statement stmt=conn.createStatement();
					String sql="Select * from "+restaurant.getText()+"";
					ResultSet rs=stmt.executeQuery(sql);
					rest=restaurant.getText();
					PreparedStatement ps=conn.prepareStatement("delete from ordered");
					ps.executeUpdate();
					menu.main(null);
					dispose();
				}
				catch(Exception e1)
				{
					if(error!=1)
						restaurant.setText("");
					JOptionPane.showMessageDialog(null,"Restaurant Doesn't Exists");
				}
			}
		});
		btnConfirm.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnConfirm.setBounds(606, 223, 92, 34);
		contentPane.add(btnConfirm);
		}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
