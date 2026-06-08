package br.com.noteeasy; // Define o pacote onde esta tela está guardada

// IMPORTAÇÕES: Trazem os componentes gráficos e de eventos necessários para o Menu
import java.awt.EventQueue; // Gere a fila de eventos gráficos do Java
import javax.swing.JFrame; // Classe base para criar janelas principais
import javax.swing.JPanel; // Painel que serve como container para os elementos da tela
import javax.swing.border.EmptyBorder; // Cria margens invisíveis ao redor do painel
import javax.swing.JMenuBar; // A barra horizontal superior que segura os menus suspensos
import javax.swing.JMenu; // Cada uma das abas principais da barra (ex: Arquivos, Formatar)
import javax.swing.JMenuItem; // As opções individuais e clicáveis dentro de cada JMenu
import java.awt.Color; // Permite gerir e alterar as cores dos componentes
import javax.swing.JLabel; // Rótulo de texto fixo na tela para mensagens
import java.awt.Font; // Controla o estilo, tamanho e tipo das fontes de texto
import java.awt.event.ActionEvent; // Evento disparado quando um item do menu é clicado
import java.awt.event.ActionListener; // Interface que executa uma ação após o disparo do evento

// A classe MenuView herda de JFrame, transformando-se numa janela funcional
public class MenuView extends JFrame {

	private static final long serialVersionUID = 1L; // Identificador de controlo de versão para serialização do Java
	private JPanel contentPane; // Painel interno da tela onde colocaremos o texto de boas-vindas

	/**
	 * Método MAIN: Ponto de partida que executa a Tela de Menu de forma isolada se necessário
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView frame = new MenuView(); // Instancia (cria) a tela de menu
					frame.setVisible(true); // Faz a tela aparecer no monitor
				} catch (Exception e) {
					e.printStackTrace(); // Exibe erros no terminal caso a tela falhe ao iniciar
				}
			}
		});
	}

	/**
	 * CONSTRUTOR: Executa a configuração visual da barra de menus e da janela.
	 */
	public MenuView() {
	
		// Define que o programa encerra por completo ao clicar no "X" da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Define o tamanho da janela do Menu (Largura = 450, Altura = 300)
		setBounds(100, 100, 450, 300);
		
		// Centraliza automaticamente a janela do menu no centro do ecrã do utilizador
		setLocationRelativeTo(null);
		
		// 1. CRIAÇÃO DA BARRA DE MENUS (JMenuBar)
		JMenuBar menuBar = new JMenuBar(); // Cria a barra física horizontal
		setJMenuBar(menuBar); // Define esta barra criada como a oficial desta janela
		
		// 2. CRIAÇÃO DA ABA "ARQUIVOS" (JMenu)
		JMenu mnNewMenu = new JMenu("Arquivos "); // Cria a primeira aba principal
		menuBar.add(mnNewMenu); // Cola a aba "Arquivos" dentro da barra superior
		
		// 3. CRIAÇÃO DA OPÇÃO "NOVO" (JMenuItem)
		JMenuItem mntmNewMenuItem = new JMenuItem("Novo "); // Cria a opção de criar nota
		
		// Adiciona a ação de clique para a opção "Novo"
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1. Cria a instância da Terceira Interface (a tela do bloco de notas/editor)
				AcaoView telaEditor = new AcaoView();

				// 2. Torna a tela do editor visível para o utilizador
				telaEditor.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem); // Coloca a opção "Novo" dentro do menu "Arquivos"
		
		// 4. CRIAÇÃO DA OPÇÃO "SAIR" (JMenuItem)
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Sair"); // Cria a opção de encerrar o sistema
		
		// Adiciona a ação de clique para a opção "Sair"
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Força o encerramento imediato de toda a aplicação Java
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3); // Coloca a opção "Sair" dentro do menu "Arquivos"
		
		// 5. CRIAÇÃO DA ABA "FORMATAR" (JMenu)
		JMenu mnNewMenu_1 = new JMenu("Formatar"); // Cria a segunda aba principal da barra
		menuBar.add(mnNewMenu_1); // Adiciona-a à barra superior
		
		// Cria os submenus da aba Formatar (Apenas visuais neste passo)
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Mudar tema(Escuro/Claro)");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Mudar fonte");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		// 6. CRIAÇÃO DA ABA "AJUDA" (JMenu)
		JMenu mnNewMenu_2 = new JMenu("Ajuda"); // Cria a terceira aba principal
		menuBar.add(mnNewMenu_2); // Adiciona-a à barra superior
		
		// Cria o submenu da aba Ajuda (Apenas visual neste passo)
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Sobre o NoteEasy");
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		// --- CONFIGURAÇÃO DO CORPO DA TELA (Área abaixo do Menu) ---
		contentPane = new JPanel(); // Cria o painel para o fundo da janela
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Define margens de segurança
		setContentPane(contentPane); // Define este painel como ativo
		contentPane.setLayout(null); // Ativa o Absolute Layout para posicionamento livre por coordenadas
		
		// Texto centralizado dando as boas-vindas ao utilizador
		JLabel lblNewLabel = new JLabel("Bem-Vindo ao NoteEasy"); // Cria a mensagem escrita
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // Define fonte Arial, estilo Comum (Plain) e tamanho 20
		lblNewLabel.setBounds(100, 28, 230, 29); // Define a posição exata (X, Y, Largura, Altura)
		contentPane.add(lblNewLabel); // Adiciona a mensagem ao painel de fundo
	}
}