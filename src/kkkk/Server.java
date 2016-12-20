package kkkk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	 public static void main(String[] args){
	        PrintWriter pw = null;
	        BufferedReader br = null;
	        Socket socket = null;
	         
	        try{
	            //[1]서버준비
	            ServerSocket server =new ServerSocket(8080);
	            //server 객체는 서비스 해주고 클라이언트의 응답을 기다리는 기능
	             
	            //[2]소켓구하기
	            System.out.println("서버가 요청을 기다립니다.");
	            socket = server.accept();                    //여기서 클라이언트를 기다림.
	            //Socket을 구했다는 것은 통신대상이 명확해졌다는 것.
	             
	            //[3]클라이언트 IP구하고
	            InetAddress addr = socket.getInetAddress();    
	            //소켓 안에 있는 클라이언트의 아이피를 얻어올수 있다.
	            String ip = addr.getHostAddress();
	            System.out.println(ip + "의 클라이언트가 접속했습니다.");
	             
	            //[4]in, out Stream구하기    
	            InputStream is = socket.getInputStream();//Stream    ↓
	            InputStreamReader isr = new InputStreamReader(is);//char          ↓
	            br =new BufferedReader(isr);//String       ↓
	            //br.readLine()을 쓰자.
	             
	            OutputStream os = socket.getOutputStream();//Stream    ↑
	            OutputStreamWriter osw = new OutputStreamWriter(os);//char         ↑
	            pw = new PrintWriter(osw);//String       ↑
	            //pw.println()을 쓰자.
	             
	            //서버에서는 클라이언트가 보낸 문자열을 읽어서 그대로 보내자.
	            String msg = null;
	             
	            //main쓰레드가 클라이언트 하나(한개의 소켓)에 대해서만 반복(while)하고있다.
	            while((msg = br.readLine()) != null){      //main쓰레드.
	                if(msg.equals("quit")) break;            //"quit"입력시 종료.    
	                 
	                System.out.println(msg);
	                pw.println(msg);
	                pw.flush();
	            }
	             
	             
	             
	        }catch(IOException e){
	            System.out.println("서버가 준비되지 않았습니다." + e.getMessage());
	        }finally{
	            try{
	                if(pw != null)pw.close();
	                if(br != null)br.close();
	                if(socket != null)socket.close();
	            }catch(Exception e){}
	        }
	    }
	}

