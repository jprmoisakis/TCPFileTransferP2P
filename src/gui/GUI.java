package gui;

import miniProjeto.Client;
import miniProjeto.Server;

public abstract class GUI {
	private static Client client;
	private static int connectToPort;
	private static String connectToAddress;
	private static SetUpClient setUpClient;
	
	private static SetUpServer setUpServer;
	private static Server server;
	private static int serverPort;
	
	
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
	
	public static void main(String[] args) {
		
		SetUpServer telaSetUpServer= GUI.getSetUpServer();
		telaSetUpServer.setVisible(true);
		
		
	}





}
