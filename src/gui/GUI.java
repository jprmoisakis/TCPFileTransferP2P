package gui;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import miniProjeto.Client;
import miniProjeto.Server;
import miniProjeto.ServerRTT;
//classe criada com a funcao de armazenar e modificar valores facilmente
public abstract class GUI {
	private static Client client;
	private static int connectToPort;
	private static String connectToAddress;
	private static SetUpClient setUpClient;
	
	private static SetUpServer setUpServer;
	private static Server server;
	private static int serverPort;
	
	private static JProgressBar progressBar1;
	private static JProgressBar progressBar2;
	
	private static MainFrame mainFrame;
	
	private static String file2SendName;
	
	private static ServerRTT serverRTT;
	private static JLabel lblRtt;
	
	public static JLabel getLblRtt() {
		return lblRtt;
	}

	public static void setLblRtt(JLabel lblRtt) {
		GUI.lblRtt = lblRtt;
	}

	public static ServerRTT getServerRTT() {
		return serverRTT;
	}

	public static void setServerRTT(ServerRTT serverRTT) {
		GUI.serverRTT = serverRTT;
	}

	public static JProgressBar getProgressBar1() {
		return progressBar1;
	}

	public static void setProgressBar1(JProgressBar progressBar1) {
		GUI.progressBar1 = progressBar1;
	}

	public static JProgressBar getProgressBar2() {
		return progressBar2;
	}

	public static void setProgressBar2(JProgressBar progressBar2) {
		GUI.progressBar2 = progressBar2;
	}


	//funções auxiliares para manter o controle da gui e das informações obtidas por ela
	
	public static String getFile2SendName() {
		return file2SendName;
	}

	public static void setFile2SendName(String file2SendName) {
		GUI.file2SendName = file2SendName;
	}

	public static Client getClient() {
		return client;
	}

	public static void setClient(Client client) {
		GUI.client = client;
	}
	
	public static String getConnectToAddress() {
		return connectToAddress;
	}

	public static void setConnectToAddress(String connectToAddress) {
		GUI.connectToAddress = connectToAddress;
	}

	public static int getConnectToPort() {
		return connectToPort;
	}
	
	public static void setConnectToPort(int connectToPort) {
		GUI.connectToPort = connectToPort;
	}
	
	public int getServerPort(){
		return serverPort;
	}
	public static void setServerPort(int serverPort){
		GUI.serverPort = serverPort;
	}
	public static void setServer(Server server){
		GUI.server = server;
	}
	public static Server getServer(){
		return GUI.server;
	}
	public static synchronized SetUpServer getSetUpServer() {
		if(setUpServer==null) {
			setUpServer = new SetUpServer();
		}
		return setUpServer;
	}
	public synchronized static void showSetUpServer(boolean show) {
		setUpServer.setVisible(show);
	}

	public synchronized static void showSetUpClient(boolean show) {
		setUpClient.setVisible(show);
	}

	public static synchronized SetUpClient getSetUpClient() {
		if(setUpClient==null) {
			setUpClient = new SetUpClient();
		}
		return setUpClient;
	}

	
	public static synchronized MainFrame getMainFrame() {
		if(mainFrame == null){
			mainFrame = new MainFrame();
		}
		return mainFrame;
	}

	public static synchronized void showMainFrame(boolean show) {
		mainFrame.setVisible(show);
	}
	
	public static void main(String[] args) {
		
		SetUpServer telaSetUpServer= GUI.getSetUpServer();//boot inicial
		telaSetUpServer.setVisible(true);
		
		
	}

	//FILENAME EH ENVIADO NA PORTA 8100 POR PADRAO
	
	//RTT NA 8001






}
