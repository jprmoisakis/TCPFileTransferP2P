package miniProjeto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRTT {
	
	private ServerSocket ss;
	private static long rtt;

	
	public ServerRTT() throws IOException {
		this.ss = new ServerSocket(8001);
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
				
				rtt = finalTime - initialTime - 1000;
				}
		} catch (Exception e) {
			e.printStackTrace();
	
		}
	}
	public static long getRTT(){
		return rtt;
	}
}
