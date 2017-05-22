package gui;

import miniProjeto.Server;

public abstract class GUI {
	
	private static int serverPort;
	private static SetUpServer setUpServer;
	private static Server server;
	
	public int getserverPort(){
		return serverPort;
	}
	public static void setserverPort(int serverPort){
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

	public static void main(String[] args) {
		
		SetUpServer telaSetUpServer= GUI.getSetUpServer();
		telaSetUpServer.setVisible(true);
		System.out.println("fd");
	}

}
