package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import miniProjeto.Client;
import miniProjeto.ClientRTT;
import miniProjeto.ServerRTT;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField fileNameField;
	private Thread t;
	private ClientRTT clientRTT;
	private Thread tRtt; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBar2 = new JProgressBar();
		progressBar2.setBounds(81, 97, 261, 29);
		contentPane.add(progressBar2);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(81, 57, 261, 29);
		contentPane.add(progressBar);
		
		JLabel lblRtt = new JLabel("rtt");
		lblRtt.setBounds(362, 57, 46, 14);
		contentPane.add(lblRtt);
		
		fileNameField = new JTextField();
		fileNameField.setBounds(161, 163, 217, 20);
		contentPane.add(fileNameField);
		fileNameField.setColumns(10);
		
		
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {//abre uma conexão com o cliente destino e envia o arquivo
			public void actionPerformed(ActionEvent e) {
				String filename = fileNameField.getText();
				progressBar.setValue(0);
				progressBar2.setValue(0);
				
				
				GUI.setFile2SendName(filename);
				try {
					clientRTT = new ClientRTT(GUI.getConnectToAddress(),lblRtt);//cria novo "cliente" para envio do rtt
					
					GUI.getServer().setProgressBar(progressBar2);//gerencia a progressbar diretamente
					GUI.setProgressBar2(progressBar2);
					GUI.setClient(new Client(GUI.getConnectToPort(),GUI.getConnectToAddress(),filename,progressBar,clientRTT));//cria novo "cliente" para envio do arquivo, nome do arquivo e tamanho
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tRtt = new Thread(clientRTT);
				tRtt.start();//inicia a thread de envio de rtt
				t= new Thread(GUI.getClient());
				t.start();//inicia a thread de envio de arquivo
				GUI.getServer().setProgressBar(progressBar2);
				GUI.showMainFrame(true);
				
				
			}
		});
		btnEnviar.setBounds(146, 213, 89, 23);
		contentPane.add(btnEnviar);
		
		JTextPane ipPane = new JTextPane();
		ipPane.setBounds(45, 11, 73, 20);
		contentPane.add(ipPane);
		
		JTextPane portPane = new JTextPane();
		portPane.setBounds(198, 11, 73, 20);
		contentPane.add(portPane);
		
		
		ipPane.setText( GUI.getConnectToAddress());
		portPane.setText( GUI.getConnectToPort() +"");
		
		JButton btnEditarInfoEnvio = new JButton("Editar Info Envio");
		btnEditarInfoEnvio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//chama tela de alterar as informacoes de envio
				
				GUI.getSetUpClient();
				GUI.showSetUpClient(true);
			
			}
		});
		btnEditarInfoEnvio.setBounds(309, 11, 155, 23);
		contentPane.add(btnEditarInfoEnvio);
		

		
		JLabel lblNomeDoArquivo = new JLabel("Nome do Arquivo");
		lblNomeDoArquivo.setBounds(10, 166, 89, 14);
		contentPane.add(lblNomeDoArquivo);
		
		JLabel lblPorta = new JLabel("porta");
		lblPorta.setBounds(142, 14, 46, 14);
		contentPane.add(lblPorta);
		
		JLabel lblIp = new JLabel("ip");
		lblIp.setBounds(36, 17, 46, 14);
		contentPane.add(lblIp);
		

		
		JLabel lblEnvio = new JLabel("Envio");
		lblEnvio.setBounds(10, 57, 46, 14);
		contentPane.add(lblEnvio);
		
		JLabel lblRecebido = new JLabel("Recebido");
		lblRecebido.setBounds(10, 100, 46, 14);
		contentPane.add(lblRecebido);
		
		JLabel lblRrtt = new JLabel("rtt");
		lblRrtt.setBounds(418, 57, 46, 14);
		contentPane.add(lblRrtt);
		



	}
}
