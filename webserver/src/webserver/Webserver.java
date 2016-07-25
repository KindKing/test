package webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {
	
	public void serverStart(int port){
		try {
			//监听80接口
			System.out.println("web服务器启动成功");
			ServerSocket serverSocket = new ServerSocket(port);
			while(true){
				//如果监听到请求socket
				Socket socket = serverSocket.accept();
				//开启一个新的线程，去处理这个socket获得的信息
				new Processor(socket).start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		int port = 80;//默认端口为80
		if(args.length==1){
			port = Integer.parseInt(args[0]);
		}
		new Webserver().serverStart(port);
	}
}
