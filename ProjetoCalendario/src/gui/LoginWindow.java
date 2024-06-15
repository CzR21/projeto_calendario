package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailTextField;
	private JTextField senhaTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
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
	public LoginWindow() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("E-mail:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(88, 78, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(88, 148, 45, 13);
		contentPane.add(lblSenha);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(88, 95, 236, 19);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		senhaTextField = new JTextField();
		senhaTextField.setBounds(88, 167, 236, 19);
		contentPane.add(senhaTextField);
		senhaTextField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(224, 242, 100, 21);
		contentPane.add(loginButton);
		
		JButton cadastrarButton = new JButton("Cadastre-se");
		cadastrarButton.setVerticalAlignment(SwingConstants.BOTTOM);
		cadastrarButton.setBounds(88, 242, 100, 21);
		contentPane.add(cadastrarButton);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(239, 191, 85, 21);
		contentPane.add(btnNewButton);
	}
}
