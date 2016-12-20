package network1;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try {
			String serverIp = "192.168.1.92";
			System.out.println("서버에 연결중입니다. 서버IP:"+serverIp);
			
			Socket socket = new Socket(serverIp, 8080);
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			
			System.out.println("서버로부터 받은 메세지:" + dis.readUTF());
			System.out.println("연결을 종료합니다.");
			dis.close();
			socket.close();
			System.out.println("연결이 종료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
