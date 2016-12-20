package chatchat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {

		try{
			
			ServerSocket serverSocket = new ServerSocket(8080);
			Socket socket = serverSocket.accept();
		
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String msgin = "", msgout="";
		
		while(!msgin.equals("end")){
			msgin = dis.readUTF();
			System.out.println(msgin);
			msgout = br.readLine();
			dos.writeUTF(msgout);
			dos.flush();
		}
		socket.close();
		}catch(Exception e){
			
		}
	}
}