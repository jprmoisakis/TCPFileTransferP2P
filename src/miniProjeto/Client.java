package miniProjeto;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable{
	private int port;
	private String address;
	private Socket socket;
	
	public Client(int port, String address) throws UnknownHostException, IOException{
		this.port = port;
		this.address = address;
		this.socket = new Socket(address, port);
	}
	
	public static void main(String []args) throws UnknownHostException, IOException{
		Client client = new Client(8000,"127.0.0.1");
		client.receive();
	}
	
	public void receive() throws IOException{
		byte [] pkt = new byte[1024*16]; //tamanho do pkt
		InputStream input = socket.getInputStream();
		FileOutputStream output = new FileOutputStream("fileReceived.exe");//ajeitar para receber o nome do arquivo
		BufferedOutputStream buffer = new BufferedOutputStream(output);
		int pktSize = -1;
		
		while ((pktSize = input.read(pkt)) > 0) {//a medida que recebe, vai montando o arquivo
			System.out.println("d");
            output.write(pkt, 0, pktSize);
        }
		this.socket.close();
	    buffer.flush();
	    buffer.close();
	    
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
