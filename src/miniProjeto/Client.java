package miniProjeto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		client.send("Projeto de Hardware.rar", client.socket);
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
				this.send("Projeto de hardware.rar", socket);
				socket.close();
			}catch(IOException e){	
				e.printStackTrace();
			}
		
	}

}
