package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import entities.Usuario;
import services.UsuarioService;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailTextField;
	private JTextField senhaTextField;

	public LoginWindow() {
		iniciarComponentes();
	}
	
	public void logar() {
		try {
			Usuario usuario = UsuarioService.logar(emailTextField.getText(), senhaTextField.getText());
			
			if(usuario == null) {
				JOptionPane.showMessageDialog(null, "Usuario não encontrado", "Erro ao realizar login", JOptionPane.ERROR_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Bem vindo, " + usuario.getNome(), "Usuario logado com sucesso", JOptionPane.INFORMATION_MESSAGE);
			}			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao realizar login", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void iniciarComponentes(){
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 374);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome de usuário");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(88, 122, 108, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(88, 188, 45, 13);
		contentPane.add(lblSenha);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(88, 139, 236, 19);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		senhaTextField = new JPasswordField();
		senhaTextField.setBounds(88, 207, 236, 19);
		contentPane.add(senhaTextField);
		senhaTextField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(new Color(0, 128, 128));
		loginButton.setBounds(206, 276, 118, 21);
		contentPane.add(loginButton);
		
		JButton cadastrarButton = new JButton("Cadastre-se");
		cadastrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
                
                CadastroWindow secondFrame = new CadastroWindow();
                secondFrame.setVisible(true);
			}
		});
		cadastrarButton.setForeground(new Color(0, 128, 128));
		cadastrarButton.setBackground(new Color(255, 255, 255));
		cadastrarButton.setVerticalAlignment(SwingConstants.BOTTOM);
		cadastrarButton.setBounds(88, 276, 108, 21);
		contentPane.add(cadastrarButton);
		
		JLabel lblNewLabel_1 = new JLabel("LOGIN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setBounds(149, 41, 108, 48);
		contentPane.add(lblNewLabel_1);
	}
}
