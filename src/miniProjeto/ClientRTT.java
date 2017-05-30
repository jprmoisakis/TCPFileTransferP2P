package miniProjeto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JLabel;

public class ClientRTT implements Runnable{

	private String address;
	private static long rtt;
	private Socket socket;
	private JLabel lblRTT;
	
	public ClientRTT(String address, JLabel lblRTT){
		this.address = address;
		this.lblRTT = lblRTT;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void run() {//calcula o rtt
		try {
			this.socket = new Socket(address, 8001);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			while(true){
				long initialTime = System.currentTimeMillis();
				out.writeByte(1);
				in.readByte();
				long finalTime = System.currentTimeMillis();
				
				rtt= initialTime - finalTime;
				String tt = ""+rtt; //cast para string
				this.lblRTT.setText(tt);//muda o texto da mainframe para mostrar o rtt
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

	
	
}
