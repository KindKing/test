package webserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class Processor extends Thread{
	
	private Socket socket;
	private InputStream in;
	private PrintStream out;
	//我规定请求只能访问这个文件下的文件
	//如果是在正规的容器下运行
	//这个值应该是项目部署的磁盘物理路径
	private static String WEB_ROOT = "C:\\web\\tuxiaobai\\html";
	public Processor(Socket socket){
		this.socket = socket;
		try {
			in = socket.getInputStream();
			out = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		//run方法运行
		String filename = parse(in);
		sendFile(filename);
		
	}
	
	//解析浏览器发送的内容，获得要访问的文件名称
	//因为浏览器发送的内容是按一定格式传上来的，所以要先解析
	public String parse(InputStream in){
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String filename = "";
		try {
			//根据输入流获得第一行字符串，这是http协议的内容
			//第一行字符串包含了三部分，请求类型，要访问的文件，http版本
			//三部分通过空格隔开
			//不要问为什么，会这样，这是约定，是契约
			String httpMesRequest = br.readLine();
			System.out.println("得到的http第一行内容"+httpMesRequest);
			String content[] = httpMesRequest.split(" ");
			//判断一下是否分文三部分，如果不是三部分，则是错误请求
			if(content.length!=3){
				sendErrorMessage(400, "the msg of http is error");
				return null;
			}
			filename = content[1];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filename;
	}
	
	//返回错误信息方法
	public void sendErrorMessage(int errorCode,String errorMes){
		//如果错误，
		out.println("HTTP/1.0 "+errorCode+" "+errorMes);
		out.println("content-type:text/html");
		out.println();
		out.println("<html>");
		out.println("<body>");
		out.println("<div>");
		out.println(errorCode);
		out.println("<p>");
		out.println("</p>");
		out.println(errorMes);
		out.println("<p>");
		out.println("</p>");
		out.println("</div>");
		out.println("<body>");
		out.println("<html>");
		out.flush();
		out.close();
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//返回浏览器访问的文件
	public void sendFile(String filename){
		//获得硬盘下的这个文件
		String thefile = Processor.WEB_ROOT+"/"+filename;
		File file = new File(thefile);
		//判断硬盘下有没有这个文件
		if(!file.exists()){
			sendErrorMessage(404, "the file is not exist");
			return;
		}
		try {
			//如果有，那么获得这个文件的流
			InputStream in = new FileInputStream(thefile);
			byte content[] =  new byte[(int)file.length()];
			//输入流读取这个文件
			in.read(content);
			out.println("HTTP/1.0 200 sendfile");//HTTP协议内容，返回内容第一行分三部分，版本号，状态码，返回信息说明
			out.println("content-length:"+content.length);//HTTP协议内容，告诉浏览器返回的文件大小
			out.println();//空一行
			out.write(content);//输出流输出这个文件
			out.flush();//释放
			out.close();//关闭资源
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
