package br.com.noteeasy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AcaoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcaoView frame = new AcaoView();
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
	public AcaoView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtAreaNota = new JTextArea();
		txtAreaNota.setWrapStyleWord(true);
		txtAreaNota.setLineWrap(true);
		txtAreaNota.setBounds(32, 43, 365, 173);
		contentPane.add(txtAreaNota);
		
		JButton btnSalvar = new JButton("Salvar Anotação ");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // 1. Cria a caixinha de diálogo para escolher onde salvar o arquivo
			    javax.swing.JFileChooser seletorArquivos = new javax.swing.JFileChooser();
			    seletorArquivos.setDialogTitle("Salvar Anotação Como...");
			    
			    // 2. Abre a janela de salvar e armazena a resposta do usuário (se ele clicou em salvar ou cancelar)
			    int retorno = seletorArquivos.showSaveDialog(null);
			    
			    // 3. Se o usuário escolheu um local e clicou em "Salvar"
			    if (retorno == javax.swing.JFileChooser.APPROVE_OPTION) {
			        // Pega o arquivo completo com o caminho e o nome que o usuário digitou
			        java.io.File arquivoSelecionado = seletorArquivos.getSelectedFile();
			        
			        // Garante que o arquivo vai terminar com a extensão .txt se o usuário esquecer de digitar
			        String caminho = arquivoSelecionado.getAbsolutePath();
			        if (!caminho.toLowerCase().endsWith(".txt")) {
			            arquivoSelecionado = new java.io.File(caminho + ".txt");
			        }
			        
			        // 4. Pega o texto da tela e grava no arquivo escolhido
			        try {
			            String textoParaSalvar = txtAreaNota.getText(); // Lembre-se de usar o nome correto da sua área de texto
			            
			            java.io.FileWriter escritor = new java.io.FileWriter(arquivoSelecionado, false);
			            escritor.write(textoParaSalvar);
			            escritor.close();
			            
			            // Mensagem de sucesso mostrando o nome que o usuário escolheu!
			            javax.swing.JOptionPane.showMessageDialog(null, "Arquivo \"" + arquivoSelecionado.getName() + "\" salvo com sucesso!");
			            
			        } catch (java.io.IOException erro) {
			            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao tentar salvar o arquivo: " + erro.getMessage());
			        }
			    }
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setBounds(270, 227, 128, 23);
		contentPane.add(btnSalvar);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(10, 11, 78, 24);
		contentPane.add(btnNewButton);
		// --- CÓDIGO PARA CARREGAR A NOTA AO INICIAR A TELA ---
	    java.io.File arquivo = new java.io.File("notas.txt");
	    
	    // Se o arquivo já existir no computador, vamos lê-lo
	    if (arquivo.exists()) {
	        try {
	            java.io.FileReader leitor = new java.io.FileReader(arquivo);
	            java.io.BufferedReader buffer = new java.io.BufferedReader(leitor);
	            
	            StringBuilder textoCompleto = new StringBuilder();
	            String linha;
	            
	            // Lê linha por linha do arquivo e junta tudo
	            while ((linha = buffer.readLine()) != null) {
	                textoCompleto.append(linha).append("\n");
	            }
	            
	            buffer.close();
	            leitor.close();
	            
	            // Coloca o texto lido de volta dentro da sua área de texto da tela
	            txtAreaNota.setText(textoCompleto.toString().trim());
	            
	        } catch (java.io.IOException e) {
	            System.out.println("Não foi possível carregar a nota anterior.");
	        }
	    }
	    // -----------------------------------------------------

	}
	
}
