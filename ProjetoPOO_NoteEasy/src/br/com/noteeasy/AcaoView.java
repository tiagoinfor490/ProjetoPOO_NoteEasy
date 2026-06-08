package br.com.noteeasy; // Define o pacote onde esta tela de ação está guardada

// IMPORTAÇÕES: Trazem as classes do Java necessárias para a interface, eventos e manipulação de arquivos
import java.awt.EventQueue; // Controla a fila de execução de telas gráficas do Java
import javax.swing.JFrame; // A janela base para a nossa tela de notas
import javax.swing.JPanel; // O painel base (container) onde os botões e textos ficam colados
import javax.swing.border.EmptyBorder; // Cria uma borda invisível de espaçamento nas extremidades
import javax.swing.JTextArea; // Área de texto gigante que permite pular linhas (diferente do JTextField)
import javax.swing.JButton; // Botões clicáveis de ação do sistema
import java.awt.Font; // Permite alterar famílias, tamanhos e estilos de fontes
import java.awt.event.ActionListener; // Interface ouvinte que detecta os cliques nos botões
import java.awt.event.ActionEvent; // Objeto que carrega os dados do evento de clique ocorrido

// A classe AcaoView herda (extends) de JFrame para se tornar uma janela gráfica completa
public class AcaoView extends JFrame {

	private static final long serialVersionUID = 1L; // Número de identificação interno padrão do Java para serialização
	private JPanel contentPane; // O painel container principal da janela

	/**
	 * Método MAIN: Ponto de entrada padrão se quisermos rodar apenas esta tela direto pelo Eclipse
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcaoView frame = new AcaoView(); // Instancia (cria) a janela da nota
					frame.setVisible(true); // Torna a interface visível para o usuário
				} catch (Exception e) {
					e.printStackTrace(); // Exibe rastreamento de erros no terminal se o Swing falhar
				}
			}
		});
	}

	/**
	 * CONSTRUTOR: Onde o WindowBuilder monta o design e onde executamos as lógicas ao abrir a tela.
	 */
	public AcaoView() {
		// ATENÇÃO: Aqui usamos DISPOSE_ON_CLOSE. 
		// Isso significa que, ao fechar esta tela no "X", ela fecha APENAS ela mesma, deixando a MenuView aberta por trás!
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Define o tamanho inicial da tela (Largura = 450, Altura = 300)
		setBounds(100, 100, 450, 300);
		
		// Centraliza esta janela automaticamente bem no meio do monitor
		setLocationRelativeTo(null);
		
		// Configura o painel de fundo (container)
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Margem de 5 pixels
		setContentPane(contentPane); // Define este painel como o principal da tela
		contentPane.setLayout(null); // Ativa o Absolute Layout para podermos ditar as coordenadas (X, Y) na Palette
		
		// --- ÁREA DE TEXTO (BLOCO DE NOTAS) ---
		JTextArea txtAreaNota = new JTextArea(); // Instancia o editor de texto multilinhas
		txtAreaNota.setWrapStyleWord(true); // Faz o Java quebrar a linha respeitando as palavras inteiras (sem cortar sílabas no meio)
		txtAreaNota.setLineWrap(true); // Ativa a quebra automática de linha ao chegar na borda direita do componente
		txtAreaNota.setBounds(32, 43, 365, 173); // Define tamanho e posição da caixa de texto
		contentPane.add(txtAreaNota); // Adiciona a área de texto ao painel
		
		// --- BOTÃO SALVAR ANOTAÇÃO ---
		JButton btnSalvar = new JButton("Salvar Anotação "); // Cria o botão
		
		// Adiciona o ouvinte de clique para executar a rotina de salvamento customizado
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // 1. Cria o componente JFileChooser (Aquela janela clássica do Windows de Escolha de Arquivos)
			    javax.swing.JFileChooser seletorArquivos = new javax.swing.JFileChooser();
			    seletorArquivos.setDialogTitle("Salvar Anotação Como..."); // Título da janela pop-up
			    
			    // 2. Abre a tela do sistema operacional e espera o usuário escolher a pasta, digitar o nome e clicar em salvar
			    int retorno = seletorArquivos.showSaveDialog(null);
			    
			    // 3. Se (if) o usuário realmente confirmou e clicou em "Salvar" na caixinha
			    if (retorno == javax.swing.JFileChooser.APPROVE_OPTION) {
			        // Captura o arquivo completo com a rota (ex: C:\Users\Nome\Documents\minhanota)
			        java.io.File arquivoSelecionado = seletorArquivos.getSelectedFile();
			        
			        // Regra de validação: Pega o caminho em texto e checa se ele termina com ".txt"
			        String caminho = arquivoSelecionado.getAbsolutePath();
			        if (!caminho.toLowerCase().endsWith(".txt")) {
			            // Se o usuário esqueceu de digitar o ".txt", o Java adiciona automaticamente no nome do arquivo
			            arquivoSelecionado = new java.io.File(caminho + ".txt");
			        }
			        
			        // 4. Gravação física do arquivo no HD/SSD usando bloco Try-Catch
			        try {
			            String textoParaSalvar = txtAreaNota.getText(); // Captura tudo o que foi escrito no JTextArea
			            
			            // Abre o FileWriter apontando para o arquivo selecionado. O 'false' indica sobrescrever caso o arquivo já exista
			            java.io.FileWriter escritor = new java.io.FileWriter(arquivoSelecionado, false);
			            escritor.write(textoParaSalvar); // Escreve o texto de fato no documento
			            escritor.close(); // Fecha o fluxo de escrita para salvar fisicamente as alterações no disco
			            
			            // Avisa o usuário com uma caixinha de mensagem estilosa com o nome final do arquivo
			            javax.swing.JOptionPane.showMessageDialog(null, "Arquivo \"" + arquivoSelecionado.getName() + "\" salvo com sucesso!");
			            
			        } catch (java.io.IOException erro) {
			            // Caso dê algum erro de permissão de escrita no sistema, o catch captura e exibe o erro em um pop-up
			            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao tentar salvar o arquivo: " + erro.getMessage());
			        }
			    }
			}
		});
		// Configurações estéticas e posicionamento do botão Salvar
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setBounds(270, 227, 128, 23);
		contentPane.add(btnSalvar); // Adiciona o botão ao painel
		
		// --- BOTÃO VOLTAR ---
		JButton btnNewButton = new JButton("Voltar"); // Instancia o botão Voltar
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Destrói apenas esta tela aberta, fazendo com que o Menu principal (que estava aberto no fundo) reapareça
			}
		});
		btnNewButton.setBounds(10, 11, 78, 24); // Posiciona no canto superior esquerdo
		contentPane.add(btnNewButton); // Adiciona o botão Voltar ao painel
		
		// --- CÓDIGO AUTOMÁTICO DE CARREGAMENTO (AO INICIAR A TELA) ---
	    java.io.File arquivo = new java.io.File("notas.txt"); // Instancia uma referência ao arquivo padrão fixo "notas.txt"
	    
	    // Verifica se esse arquivo fixo de histórico existe na pasta raiz do projeto
	    if (arquivo.exists()) {
	        try {
	            java.io.FileReader leitor = new java.io.FileReader(arquivo); // Cria o fluxo de leitura do arquivo
	            java.io.BufferedReader buffer = new java.io.BufferedReader(leitor); // Armazena em cache para ler linha por linha rapidamente
	            
	            StringBuilder textoCompleto = new StringBuilder(); // Objeto dinâmico eficiente para concatenar strings
	            String linha;
	            
	            // Enquanto (while) a linha lida não for nula, o laço continua juntando o texto
	            while ((linha = buffer.readLine()) != null) {
	                textoCompleto.append(linha).append("\n"); // Adiciona a linha lida e pula uma linha
	            }
	            
	            buffer.close(); // Fecha o leitor de buffer
	            leitor.close(); // Fecha o arquivo
	            
	            // joga todo o texto histórico recuperado diretamente para dentro do campo visual do bloco de notas
	            txtAreaNota.setText(textoCompleto.toString().trim());
	            
	        } catch (java.io.IOException e) {
	            // Imprime um aviso discreto no console se falhar a leitura automática
	            System.out.println("Não foi possível carregar a nota anterior.");
	        }
	    }
	    // -----------------------------------------------------

	}
	
}