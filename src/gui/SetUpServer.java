package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import miniProjeto.Server;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class SetUpServer extends JFrame {

	private JPanel contentPane;
	private JTextField portField;
	private Thread t;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetUpServer frame = new SetUpServer();
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
	public SetUpServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 318, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		portField = new JTextField();
		portField.setBounds(38, 70, 106, 22);
		contentPane.add(portField);
		portField.setColumns(10);
		
		JButton btnEnviar = new JButton("OK");
		btnEnviar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				String portString = portField.getText();
				int port = Integer.parseInt(portString);//transforma em int a porta
				GUI.setServerPort(port);
				try {
					GUI.setServer(new Server(port));//seta a parte servidor da aplicacao
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				t= new Thread(GUI.getServer());
				t.start();
				
				GUI.showSetUpServer(false); //esconde a janela atual

				
				GUI.getSetUpClient();
				GUI.showSetUpClient(true);//chama a proxima janela
				
				
				dispose();
			}
		});
		btnEnviar.setBounds(187, 69, 89, 23);
		contentPane.add(btnEnviar);
		

		
		JLabel portaLabel = new JLabel("Insira a porta a ser usada");
		portaLabel.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 16));
		portaLabel.setBounds(10, 11, 282, 27);
		contentPane.add(portaLabel);
	}
}
