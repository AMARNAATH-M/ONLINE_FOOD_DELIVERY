import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class about extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					about frame = new about();
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
	public about() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 256, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DELIZH");
		lblNewLabel.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblNewLabel.setBounds(86, 10, 66, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblWebTechnologyProject = new JLabel("Web Technology Project");
		lblWebTechnologyProject.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblWebTechnologyProject.setBounds(31, 52, 187, 32);
		contentPane.add(lblWebTechnologyProject);
		
		JLabel lblDevelopedBy = new JLabel("Developers:-");
		lblDevelopedBy.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblDevelopedBy.setBounds(21, 94, 143, 32);
		contentPane.add(lblDevelopedBy);
		
		JLabel lblRoshanNihaalJ = new JLabel("Roshan Nihaal J (2018506097)");
		lblRoshanNihaalJ.setVerticalAlignment(SwingConstants.TOP);
		lblRoshanNihaalJ.setFont(new Font("Agency FB", Font.PLAIN, 18));
		lblRoshanNihaalJ.setBounds(21, 136, 187, 22);
		contentPane.add(lblRoshanNihaalJ);
		
		JLabel lblAmarnath = new JLabel("Amarnath ()");
		lblAmarnath.setVerticalAlignment(SwingConstants.TOP);
		lblAmarnath.setFont(new Font("Agency FB", Font.PLAIN, 18));
		lblAmarnath.setBounds(21, 168, 162, 32);
		contentPane.add(lblAmarnath);
		
		JLabel lblKamalnath = new JLabel("Kamalnath ()");
		lblKamalnath.setVerticalAlignment(SwingConstants.TOP);
		lblKamalnath.setFont(new Font("Agency FB", Font.PLAIN, 18));
		lblKamalnath.setBounds(21, 200, 143, 32);
		contentPane.add(lblKamalnath);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button.setBounds(140, 223, 92, 34);
		contentPane.add(button);
	}

}
