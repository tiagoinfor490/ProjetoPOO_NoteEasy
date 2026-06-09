package br.com.noteeasy; // Define o pacote onde a classe está guardada

// IMPORTAÇÕES: Trazem as ferramentas nativas do Java Swing e AWT para construir a interface
import java.awt.EventQueue; // Gere a fila de eventos do Java (essencial para rodar a interface sem travar)
import java.awt.Font; // Permite modificar fontes, tamanhos e estilos de texto
import java.awt.event.ActionEvent; // Representa o evento de uma ação (ex: o clique de um botão)
import java.awt.event.ActionListener; // Interface que "escuta" e reage quando uma ação acontece

import javax.swing.JButton; // Componente de Botão clicável
import javax.swing.JFrame; // A janela principal do sistema
import javax.swing.JLabel; // Rótulo de texto fixo na tela (labels)
import javax.swing.JPanel; // Painel/Contentor onde colamos os componentes dentro da janela
import javax.swing.JPasswordField; // Caixa de texto especial que esconde os caracteres da senha
import javax.swing.JTextField; // Caixa de texto comum de uma linha para digitação
import javax.swing.border.EmptyBorder;
import java.awt.Color; // Cria uma borda invisível de espaçamento ao redor do painel

// A classe TelaLogin herda (extends) de JFrame, ou seja, ela É uma janela do Windows
public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L; // Identificador interno padrão do Java para classes serializáveis
	private JPanel contentPane; // Painel principal que vai segurar os botões e caixas de texto
	private JTextField AreaUsuario; // Variável global da caixa de texto do Usuário
	private JPasswordField passwordField; // Variável global da caixa de texto da Senha

	/**
	 * Método MAIN: É o ponto de partida do programa. Tudo começa a executar por aqui.
	 */
	public static void main(String[] args) {
		// EventQueue garante que a interface gráfica seja criada de forma segura na thread correta do Java
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin(); // Instancia (cria) a janela de login
					frame.setVisible(true); // Torna a janela visível no monitor do utilizador
				} catch (Exception e) {
					e.printStackTrace(); // Se der erro crítico ao abrir, mostra o relatório do erro no terminal
				}
			}
		});
	}

	/**
	 * CONSTRUTOR: Aqui é onde desenhamos a tela e configuramos os componentes.
	 */
	public TelaLogin() {
		// Define que, ao clicar no "X" da janela, o programa fecha completamente e limpa a memória
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Define a posição inicial (X=100, Y=100) e o tamanho da janela (Largura=450, Altura=300)
		setBounds(100, 100, 450, 300);
		
		// Centraliza a janela automaticamente no meio do ecrã do utilizador
		setLocationRelativeTo(null);
		
		// Inicializa o painel onde os elementos serão colados
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Define margens de 5 pixels nas bordas
		setContentPane(contentPane); // Define este painel como o principal da janela
		
		// ATENÇÃO: setLayout(null) ativa o "Absolute Layout". 
		// Isso remove os alinhamentos automáticos do Java e permite colocar componentes por coordenadas exatas x, y
		contentPane.setLayout(null);
		
		// --- TÍTULO PRINCIPAL ---
		JLabel lblNewLabel = new JLabel("NoteEasy - Acesso"); // Cria o texto
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Define fonte Arial, Negrito (Bold) e tamanho 20
		lblNewLabel.setBounds(121, 11, 207, 24); // Posiciona usando (X, Y, Largura, Altura)
		contentPane.add(lblNewLabel); // Adiciona o título ao painel principal
		
		// --- RÓTULO DO USUÁRIO ---
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setBounds(86, 85, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		// --- CAIXA DE TEXTO DO USUÁRIO ---
		AreaUsuario = new JTextField(); // Inicializa a caixa de digitação
		AreaUsuario.setBounds(155, 83, 163, 20);
		contentPane.add(AreaUsuario);
		AreaUsuario.setColumns(10); // Define um tamanho de colunas padrão interno
		
		// --- RÓTULO DA SENHA ---
		JLabel AreaSenha = new JLabel("Senha");
		AreaSenha.setFont(new Font("Arial", Font.BOLD, 15));
		AreaSenha.setBounds(86, 131, 46, 14);
		contentPane.add(AreaSenha);
		
		// --- CAIXA DE TEXTO DA SENHA ---
		passwordField = new JPasswordField(); // Inicializa o campo oculto de senha
		passwordField.setBounds(155, 129, 163, 20);
		contentPane.add(passwordField);
		
		// --- BOTÃO CONECTAR E SUA LÓGICA ---
		JButton btnButton = new JButton("Conectar"); // Cria o botão escrito "Conectar"
		
		// Adiciona um "ouvinte de ações" ao botão. O código lá dentro roda sempre que o botão é clicado
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 1. Guarda o texto que o utilizador digitou no campo de usuário
				String usuario = AreaUsuario.getText();
				
				// 2. Converte o array de caracteres seguro do JPasswordField para uma String legível
				String senha = new String(passwordField.getPassword());

				// 3. Validação das Credenciais (Compara se o login é "admin" e a senha é "1234")
				if (usuario.equals("admin") && senha.equals("1234")) {
				    
				    dispose(); // Fecha e destrói a tela de login atual para libertar memória
				    
				    // Cria a segunda interface (MenuView) e torna-a visível
				    MenuView telaMenu = new MenuView();
				    telaMenu.setVisible(true);
				    
				} else {
				    // Se o utilizador errar os dados, abre uma caixinha flutuante de aviso (Pop-up)
				    javax.swing.JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
				}
			}
		});
		
		// Configurações visuais do botão de Conectar
		btnButton.setFont(new Font("Arial", Font.BOLD, 15));
		btnButton.setBounds(162, 184, 105, 27);
		contentPane.add(btnButton); // Adiciona o botão ao painel

	}
}