package miniProjeto;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
	public static void main(String []args) throws IOException{

	}
	
	public void send(String path, Socket client) throws IOException {
		
		File file = new File(path); // caminho pro arquivo a ser enviado ex: "C:\\Program Files (x86)\\Steam\\Steam.exe"
		byte[] pkt = new byte [1024*16]; //define o tamanho maximo do pkt
		FileInputStream toSend = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(toSend);// cria buffer do arquivo
		OutputStream output = client.getOutputStream();
		int pktSize = -1;        
		while ((pktSize = buffer.read(pkt)) > 0) { //envia a medida que vai lendo o arquivo para nao causar falta de memoria
            output.write(pkt, 0, pktSize);
            System.out.println(pktSize);
        }
		
		output.flush();
		client.close();;
		System.out.println("prinf");
		
	}
	@Override
	public void run() {
		try{	
			System.out.println("aguardando na porta "+ this.serverPort);
			Socket socket =this.serverSocket.accept();
			this.send("C:\\Program Files (x86)\\Steam\\Steam.exe", socket);
			socket.close();
		}catch(IOException e){	
			e.printStackTrace();
		}
		
	}
	
}
