package miniProjeto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
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
	private String filePath;
	
	public Client(int port, String address, String filePath) throws UnknownHostException, IOException{
		this.port = port;
		this.address = address;
		this.socket = new Socket(address, port);
		this.filePath = filePath;
	}
	
	public static void main(String []args) throws UnknownHostException, IOException{
		//Client client = new Client(8000,"127.0.0.1");
		//client.send("Projeto de Hardware.rar", client.socket);
	}
	
	public void send(String path, Socket client) throws IOException {
		
		File file = new File(path); // caminho/nome pro arquivo a ser enviado ex: "C:\\Program Files (x86)\\Steam\\Steam.exe"->
									//de prefeferencia usar nome de arquivo localizado na pasta do projeto no eclipse
		byte[] pkt = new byte [1024*16]; //define o tamanho maximo do pkt
		FileInputStream toSend = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(toSend);//buffer do arquivo
		OutputStream output = client.getOutputStream();
		int pktSize = -1;        
		while ((pktSize = buffer.read(pkt)) > 0) { //escreve a medida que vai lendo o arquivo para nao causar falta de memoria
            output.write(pkt, 0, pktSize);
            System.out.println(pktSize);
        }
		
		output.flush();
		client.close();;
		System.out.println("prinf");
		
	}
	
	public void sendFileName(String path) throws UnknownHostException, IOException{//envia o nome do arquivo na porta 8100
		Socket socket = new Socket(this.address, 8100);
		DataOutputStream data = new DataOutputStream(socket.getOutputStream());
		data.writeUTF(path);
		data.flush();
		data.close();
	}
	
	@Override
	public void run() {
			try{
				sendFileName(this.filePath);
				this.send(this.filePath, this.socket);
				this.socket.close();
			}catch(IOException e){	
				e.printStackTrace();
			}
		
	}

}
