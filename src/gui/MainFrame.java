package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import miniProjeto.Client;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(81, 100, 261, 29);
		contentPane.add(progressBar);
		
		fileNameField = new JTextField();
		fileNameField.setBounds(125, 163, 217, 20);
		contentPane.add(fileNameField);
		fileNameField.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {//abre uma conexão com o cliente destino e envia o arquivo
			public void actionPerformed(ActionEvent e) {
				String filename = fileNameField.getText();
				progressBar.setMinimum(0);
				progressBar.setMaximum(100);
				GUI.setFile2SendName(filename);
				try {
					GUI.setClient(new Client(GUI.getConnectToPort(),GUI.getConnectToAddress(),filename,progressBar));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				t= new Thread(GUI.getClient());
				t.start();
				
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
			public void actionPerformed(ActionEvent e) {
				
				GUI.getSetUpClient();
				GUI.showSetUpClient(true);
			
			}
		});
		btnEditarInfoEnvio.setBounds(269, 11, 155, 23);
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
		


	}

}
