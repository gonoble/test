package chatchat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("192.168.1.92", 8080);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
			String msgin = "", msgout="";
			while(!msgin.equals("end")){
				msgout = br.readLine();
				dos.writeUTF(msgout);
				 msgin = dis.readUTF();
				System.out.println(msgin);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
