package miniProjeto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
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
	
	private Socket client;
	private ServerSocket serverSocket;
	private String fileName;
	private int fileSize;
	private ServerSocket s;
	
	public Server(int serverPort) throws IOException{
		this.serverPort = serverPort;
		this.serverSocket = new ServerSocket(serverPort);
		this.s = new ServerSocket(8100);
	}

	
	public void receive() throws IOException{
		
		byte [] pkt = new byte[1024*16]; //tamanho do pkt
		InputStream input = this.client.getInputStream();
		FileOutputStream output = new FileOutputStream(this.fileName + "rcv.zip");//ajeitar para receber o nome do arquivo
		BufferedOutputStream buffer = new BufferedOutputStream(output);
		int pktSize = -1;
		
		while ((pktSize = input.read(pkt)) > 0) {//a medida que recebe, vai montando o arquivo
			//System.out.println("d");
            output.write(pkt, 0, pktSize);
        }
		
	    buffer.flush();
	    buffer.close();
	    this.client.close();
	    
	}

	public void receiveFileName() throws IOException{//recebe o filename
		Socket a = s.accept();
		DataInputStream in = new DataInputStream(a.getInputStream());
		this.fileName = in.readUTF();
		this.fileSize = in.readInt();
		System.out.println(fileName +" " +fileSize);
		a.close();
		
		
	}

	@Override
	public void run() {
		while(true){	
			try {
				this.receiveFileName();
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
