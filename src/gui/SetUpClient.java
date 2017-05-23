package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import miniProjeto.Client;
import miniProjeto.Server;

public class SetUpClient extends JFrame {

	private JPanel contentPane;
	private JTextField portField;
	private JTextField ipField;
	private JLabel lblPorta;
	private JLabel lblIp;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetUpClient frame = new SetUpClient();
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
	public SetUpClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 318, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		portField = new JTextField();
		portField.setBounds(48, 49, 106, 22);
		contentPane.add(portField);
		portField.setColumns(10);
		
		ipField = new JTextField();
		ipField.setBounds(48, 82, 106, 22);
		contentPane.add(ipField);
		ipField.setColumns(10);
		
		JButton btnEnviar = new JButton("OK");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String portString = portField.getText();
				String address = ipField.getText();
				
				int port = Integer.parseInt(portString);
				
				GUI.setConnectToPort(port);
				GUI.setConnectToAddress(address);
				
				ipField.setText("");
				portField.setText("");
				
				GUI.showSetUpClient(false); //esconde a janela atual
				
				GUI.getMainFrame();
				GUI.showMainFrame(true);
				
				dispose();
			}
		});
		btnEnviar.setBounds(187, 69, 89, 23);
		contentPane.add(btnEnviar);
		
		
		JLabel portaLabel = new JLabel("Insira as informaçoes de quem você quer se conectar");
		portaLabel.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 9));
		portaLabel.setBounds(10, 11, 282, 27);
		contentPane.add(portaLabel);
		
		
		lblPorta = new JLabel("Porta");
		lblPorta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPorta.setBounds(10, 53, 46, 14);
		contentPane.add(lblPorta);
		
		lblIp = new JLabel("ip");
		lblIp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIp.setBounds(20, 85, 46, 14);
		contentPane.add(lblIp);
	}

}
