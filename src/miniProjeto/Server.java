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

import javax.swing.JProgressBar;

public class Server implements Runnable {
	
	private int serverPort;
	
	private Socket client;
	private ServerSocket serverSocket;
	private String fileName;
	private int fileSize;
	private ServerSocket ss;
	private JProgressBar progressBar;
	private int auxValue;
	
	public Server(int serverPort,JProgressBar progressBar) throws IOException{
		this.serverPort = serverPort;
		this.serverSocket = new ServerSocket(serverPort);
		this.ss = new ServerSocket(8100);
		this.progressBar = progressBar;
	}
	
	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	
	public void receive() throws IOException{
		
		byte [] pkt = new byte[1024]; //tamanho do pkt
		InputStream input = this.client.getInputStream();
		FileOutputStream output = new FileOutputStream(this.fileName);
		BufferedOutputStream buffer = new BufferedOutputStream(output);
		int pktSize = -1;
		
		while ((pktSize = input.read(pkt)) > 0) {//a medida que recebe, vai montando o arquivo
            this.manageProgressBar(pktSize, this.fileSize);
            output.write(pkt, 0, pktSize);
        }
		
	    buffer.flush();
	    buffer.close();
	    this.client.close();
	    
	}
	public void manageProgressBar(int value,int total){//gerencia a barra de progresso
		this.progressBar.setMaximum(total);
		this.auxValue +=value;
		this.progressBar.setValue(this.auxValue);
	}

	public void receiveFileName() throws IOException{//recebe o nome do arquivo e o tamanho
		Socket a = ss.accept();
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
				this.receiveFileName();//recebe o nome e tamanho
				System.out.println("esperando");
				this.client =this.serverSocket.accept();
				this.receive();//recebe o arquivo
				this.progressBar.setValue(0);
				this.auxValue = 0;
				//this.progressBar.setValue(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
