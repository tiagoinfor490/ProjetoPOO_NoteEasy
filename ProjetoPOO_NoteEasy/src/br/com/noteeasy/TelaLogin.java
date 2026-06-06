package br.com.noteeasy;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField AreaUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NoteEasy - Acesso");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(121, 11, 207, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setBounds(86, 85, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		AreaUsuario = new JTextField();
		AreaUsuario.setBounds(155, 83, 163, 20);
		contentPane.add(AreaUsuario);
		AreaUsuario.setColumns(10);
		
		JLabel AreaSenha = new JLabel("Senha");
		AreaSenha.setFont(new Font("Arial", Font.BOLD, 15));
		AreaSenha.setBounds(86, 131, 46, 14);
		contentPane.add(AreaSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(155, 129, 163, 20);
		contentPane.add(passwordField);
		
		JButton btnButton = new JButton("Conectar");
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// Pega o que o usuário digitou das CAIXAS REAIS, não dos rótulos
				String usuario = AreaUsuario.getText();
				String senha = new String(passwordField.getPassword());

				// Validação simples (Usuário: admin / Senha: 123)
				if (usuario.equals("admin") && senha.equals("1234")) {
				    // 1. Fecha a tela de login atual
				    dispose();
				    
				    // 2. Cria e abre a tela de Menu
				    MenuView telaMenu = new MenuView();
				    telaMenu.setVisible(true);
				} else {
				    // Mostra um aviso caso erre
				    javax.swing.JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
				}
			}
		});
		btnButton.setFont(new Font("Arial", Font.BOLD, 15));
		btnButton.setBounds(162, 184, 105, 27);
		contentPane.add(btnButton);

	}
}
