package miniProjeto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JLabel;

import gui.GUI;

public class ServerRTT implements Runnable{
	
	private ServerSocket ss;
	private static long rtt;
	private JLabel lblRtt;
	
	public ServerRTT(JLabel lblRtt) throws IOException {
		this.ss = new ServerSocket(8001);
		this.lblRtt = lblRtt;//nao usado
	}

	public void run() {
		
		try {
			
			Socket client = this.ss.accept();
			DataInputStream socketInRTT = new DataInputStream(client.getInputStream());
			DataOutputStream socketOutRTT = new DataOutputStream(client.getOutputStream());
			while (true) {
				long initialTime = System.currentTimeMillis();
				socketInRTT.readByte();
				
				socketOutRTT.writeByte(1);
				long finalTime = System.currentTimeMillis();
				
				rtt = finalTime - initialTime - 1000;//calcula o rtt pra o receptor, porem nao consegui terminar a tempo
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
	
		}
	}
	public static long getRTT(){
		return rtt;
	}
}
