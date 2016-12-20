package network1;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(8080);
			System.out.println(getTime()+ "서버가 준비되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(true){
			try {
				System.out.println(getTime() + "연결요청을 기다립니다.");
				Socket socket = serverSocket.accept();
				System.out.println(getTime() + socket.getInetAddress() + "로부터 연결요청이 들어왔습니다.");
				
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				dos.writeUTF("[공지] 서버로부터 실험메시지");
				System.out.println(getTime()+"데이터를 전송했습니다.");
				
				dos.close();
				socket.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static String getTime(){
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}

}
