package kkkk;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	 public static void main(String[] args){
	        Socket socket = null;
	        GetThread gt = null;
	        PostThread pt = null;
	         
	        try{
	            //[1]소켓구하기(클 : 자기ip, port : 임의 포트, 프로토콜(Socket-tcp)
	            socket = new Socket("192.168.1.92", 8080);
	            gt = new GetThread(socket);
	            pt = new PostThread(socket);
	         
	            gt.start();        //start()메소드가 하는일 :　1.run큐, sleep큐 준비.
	            pt.start();        //　　　　　　　　　　　　2.run()메소드를 call함.                    
	             
	        }catch(Exception e){
	            System.out.println("소켓을 구하지 못했습니다." + e.getMessage());
	        }
	    }
	}
	 
	//네트워크로 받고 모니터로 출력.
	class GetThread extends Thread{
	    //필드로 올리면 힙으로 올라가기때문에 this로 접근 가능
	    private BufferedReader br = null;
	    private Socket socket=null;
	    private PrintWriter monitor = null;
	     
	    //생성자 함수.
	    public GetThread(Socket socket){
	        try{
	            //[1]네트워크로 받기.
	            InputStream is = socket.getInputStream();
	            this.br = new BufferedReader(new InputStreamReader(is));    
	             
	            //[2]모니터로 출력.
	            OutputStreamWriter osw = new OutputStreamWriter(System.out);
	            this.monitor = new PrintWriter(osw);
	        }catch(Exception e){}      
	    }
	     
	    public void run(){
	        try{
	            String msg = null;
	            while((msg = this.br.readLine()) !=null){
	                this.monitor.println(msg);
	                this.monitor.flush();
	            }
	        }catch(Exception e){}
	    }
	}
	 
	//키보드로 받고 네트워크로 출력.
	class PostThread extends Thread{
	    private PrintWriter  pw= null;
	    private BufferedReader keyboard = null;
	     
	    public PostThread(Socket socket){
	        try{
	            OutputStream os = socket.getOutputStream();
	            this.pw = new PrintWriter(new OutputStreamWriter(os));
	             
	            InputStreamReader isr = new InputStreamReader(System.in);
	            this.keyboard  = new BufferedReader(isr);
	        }catch(Exception e){
	            System.out.println("예외" + e.getMessage());
	        }
	    }
	    public void run(){
	        try{
	            //동시작업 쓰레드 코드
	            String msg=null;
	            while((msg = keyboard.readLine()) != null){
	                if(msg.equals("quit")) break;
	                this.pw.println(msg);
	                this.pw.flush();
	            }
	        }catch(Exception e){
	             
	        }finally{
	            try{
	                pw.close();
	                keyboard.close();
	            }catch(Exception e){}    
	        }
	    }
}
