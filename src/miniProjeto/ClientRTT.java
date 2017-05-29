package miniProjeto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientRTT implements Runnable{

	private String address;
	private static long rtt;
	
	public ClientRTT(String address){
		this.address = address;
	}

	public void run() {
		try {
			Socket socket = new Socket(address, 8001);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			while(true){
				long initialTime = System.currentTimeMillis();
				out.writeByte(1);
				in.readByte();
				long finalTime = System.currentTimeMillis();
				
				rtt= initialTime - finalTime;
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static long getRtt(){
		return rtt;
		
	}

	
	
}
