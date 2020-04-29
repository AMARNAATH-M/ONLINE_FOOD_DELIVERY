import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class weluser extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					weluser frame = new weluser();
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
	public weluser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 233, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("DELIZH");
		label.setFont(new Font("Agency FB", Font.BOLD, 30));
		label.setBounds(80, 33, 64, 25);
		contentPane.add(label);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				login.main(null);
			}
		});
		button.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button.setBounds(119, 137, 92, 34);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Signup");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				signup.main(null);
			}
		});
		button_1.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button_1.setBounds(10, 137, 92, 34);
		contentPane.add(button_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				welcome.main(null);
			}
		});
		btnBack.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnBack.setBounds(62, 199, 92, 34);
		contentPane.add(btnBack);
	}
}
