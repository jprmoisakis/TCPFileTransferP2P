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

import javax.swing.JProgressBar;

public class Client implements Runnable{
	private int port;
	private String address;
	private Socket socket;
	private String filePath;
	private JProgressBar progressBar;
	private int auxValue;
	private int totalSize;
	private ClientRTT clientRTT;
	
	public Client(int port, String address, String filePath, JProgressBar progressBar,ClientRTT clientRTT) throws UnknownHostException, IOException{
		this.port = port;
		this.address = address;
		this.socket = new Socket(address, port);
		this.filePath = filePath;
		this.progressBar = progressBar;
		this.clientRTT = clientRTT;
	}
	
	public static void main(String []args) throws UnknownHostException, IOException{
		//Client client = new Client(8000,"127.0.0.1");
		//client.send("Projeto de Hardware.rar", client.socket);
	}
	
	public void send(String path, Socket client, File file) throws IOException {
		
		byte[] pkt = new byte [1024]; //define o tamanho maximo do pkt
		FileInputStream toSend = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(toSend);//buffer do arquivo
		OutputStream output = client.getOutputStream();
		this.totalSize=(int) file.getTotalSpace();
		int pktSize = -1;        
		while ((pktSize = buffer.read(pkt)) > 0) { //escreve a medida que vai lendo o arquivo para nao causar falta de memoria
            this.manageProgressBar(pktSize, this.totalSize);
			output.write(pkt, 0, pktSize);
			// System.out.println(pktSize);
        }
		
		output.flush();
		client.close();;
		System.out.println("enviou");
		
	}
	
	public void sendFileName(String path, File file) throws UnknownHostException, IOException{//envia o nome e tamanho do arquivo na porta 8100
		this.totalSize = (int) file.getTotalSpace();
		Socket socket = new Socket(this.address, 8100);
		DataOutputStream data = new DataOutputStream(socket.getOutputStream());
		data.writeUTF(path);
		data.writeInt(this.totalSize);
		data.flush();
		data.close();
		socket.close();
	}
	
	public void manageProgressBar(int value,int total){//gerencia o progresso da progressbar
		this.progressBar.setMaximum(total);
		this.auxValue +=value;
		this.progressBar.setValue(this.auxValue);
		
		//System.out.println("progressbar value "+this.progressBar.getValue());
	}
	
	@Override
	public void run() {
			try{
				File file = new File(this.filePath);
				sendFileName(this.filePath,file);//chama o metodo que envia o nome do arquivo (o arquivo deve estar na pasta do projeto no eclipse
				this.send(this.filePath, this.socket,file);//chama o metodo de enviar
				this.socket.close();
				this.clientRTT.getSocket().close();//fecha o socket do rtt
			}catch(IOException e){	
				e.printStackTrace();
			}
		
	}

}
