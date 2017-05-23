package miniProjeto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	private int serverPort;
	
	private Socket client;//temporario esse eh o socket do  serverSocket.accept();
	private ServerSocket serverSocket;
	
	public Server(int serverPort) throws IOException{
		this.serverPort = serverPort;
		this.serverSocket = new ServerSocket(serverPort);
	}

	
	public void receive() throws IOException{
		
		byte [] pkt = new byte[1024*16]; //tamanho do pkt
		InputStream input = this.client.getInputStream();
		FileOutputStream output = new FileOutputStream("fileReceivedeeyiio.rar");//ajeitar para receber o nome do arquivo
		BufferedOutputStream buffer = new BufferedOutputStream(output);
		int pktSize = -1;
		
		while ((pktSize = input.read(pkt)) > 0) {//a medida que recebe, vai montando o arquivo
			System.out.println("d");
            output.write(pkt, 0, pktSize);
        }
		
	    buffer.flush();
	    buffer.close();
	    this.client.close();
	    
	}


	@Override
	public void run() {
		while(true){	
			try {
				System.out.println("esperando");
				this.client =this.serverSocket.accept();
				this.receive();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
