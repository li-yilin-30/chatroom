//服务器端，管理客户端
//默认第一个进入聊天室的用户为管理员
package chatroom;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.io.DataInputStream;
import chatroom.onlineuser;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.*;
public class Server {
	private Server_ui  serverui;//创建聊天室窗口
	private Socket client;//代表通过套接字监听到的客户端
	private final int port=6666;//服务器端口
	private InputStream inputStream;
	private OutputStream outputStream;
	private login a;//登录界面
	public File file;//文件
	public  Vector<String> userslist;//存储登录的用户列表
	public  ArrayList<String> usersname;//另设一个登录ui每次登录便把名字加入进来
	private ArrayList<Socket> connected_socket=new ArrayList<>();
	private String MessageRecv="";//服务器接受的信息
	private String Message="";
	public static int fileno=0;//便于为服务器缓存的文件编号
	Vector<String>test=new Vector<>();
	
	public Server() 
	{ 
		
	try {  //picture.showa();
		   file =new File("D:\\Cache");
		   
			serverui=new Server_ui("mini-chatroom");
			a=	new login("登录注册系统");
			System.out.println("布置页面成功");
			this.connect();
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		try {
			System.out.println("服务器启动失败");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        }
	}
	public void connect()  
	{
		System.out.println("服务器启动成功，欢迎在mini—chatroom中聊天");
		try {
			ServerSocket serverSocket = new ServerSocket(port);
		    while(true) {
			client=serverSocket.accept();//通过套接字监听到完成连接的客户端
			System.out.println("服务器坚挺");
			connected_socket.add(client);
			
			new threadreceiveandsend(client);
	   //服务器没连接一个客户端就启动一个新线程，该线程先收消息，在进行转发
			}
		}catch (Exception e) {
			
		System.out.println("服务器罢工了");
		}
	}

	class threadreceiveandsend implements Runnable {
	    private final Socket client;
	    private BufferedWriter getWriter(Socket socket) throws IOException{
	        OutputStream socketOut=socket.getOutputStream();
	        return new BufferedWriter(new OutputStreamWriter(socketOut));
	    }
	    private BufferedReader getReader(Socket socket) throws IOException{
	        InputStream socketIn=socket.getInputStream();
	        return new BufferedReader(new InputStreamReader(socketIn));
	    }
	    public threadreceiveandsend(Socket client)
	    {
	    	this.client=client;
	    	new Thread(this).start();
	    }
	    @Override
	    public void run(){
	            
	            System.out.println("建立了新的连接 "+"ip地址为："+client.getInetAddress()+"端口号:"+client.getPort());
	            Boolean isnormal=true;
	            while (isnormal) {
                	
	            	try {
	            	   	
                		DataInputStream in = new DataInputStream(client.getInputStream());//读信息
 	                    MessageRecv = in.readUTF().trim();
                	    //判断是否为文件路径，若为文件路径则不但转发路径并且复制、缓存。
 	                   int index= MessageRecv.indexOf("$");
 	                   if((MessageRecv.charAt(index+2)=='E'||MessageRecv.charAt(index+2)=='F'||MessageRecv.charAt(index+2)=='D'||MessageRecv.charAt(index+2)=='C')&&MessageRecv.charAt(index+3)==':'&&MessageRecv.charAt(index+4)=='\\')
 	                   {++fileno;
 	                   String path =MessageRecv.substring(index+2);
 	                   int index2=MessageRecv.indexOf(".");
 	                   String suffix=MessageRecv.substring(index2);
 	        
 	                 /* if (!file.exists()) {
 	                	  System.out.println("传输的并不是文件路径");
 	                   }*/
 	                   String pathnew="D:\\Cache\\"+String.valueOf(fileno)+suffix;
 	                   File desFile = new File("D:\\Cache\\"+String.valueOf(fileno)+suffix);
 	                   File sourceFile =new File(path);
 	                   InputStream input =null;
 	                   OutputStream output =null;
 	                   try {
 	                	  input = new FileInputStream(sourceFile);
 	                     output = new FileOutputStream(desFile);        
 	                     byte[] buf = new byte[1024];        
 	                     int bytesRead;        
 	                     while ((bytesRead = input.read(buf)) > 0) {
 	                         output.write(buf, 0, bytesRead);//文件传输
 	                     }
 	                   }
 	                   finally {
 	                	   input.close();
 	                	   output.close();
 	                   }
 	                   MessageRecv=pathnew;
 	                   }if(serverui.prichat.getText().equals(""))
	                    {System.out.println("从客户端接收到数据");
	                    serverui.showmessage(MessageRecv);
	                    serverui.showmessage("\n");//在服务器中显示
	                    for(int i=0;i<connected_socket.size();++i)
	                    {  DataOutputStream out1 = new DataOutputStream(connected_socket.get(i).getOutputStream());
	                    out1.writeUTF(MessageRecv);}//依次转发
	                    }
 	                   else {
 	                	   int userno=0;
 	                	   for(int i=0;i<onlineuser.onlineusers.size();++i)
 	                	   {if(serverui.prichat.getText().equals(onlineuser.onlineusers.get(i)))
 	                		   {userno=i;
 	                		   break;
 	                		   }//若选择私聊则单独转发
 	                	   }
 	                	  for(int i=0;i<connected_socket.size();++i)
 		                    {
 	                		  DataOutputStream out1 = new DataOutputStream(connected_socket.get(userno).getOutputStream());
 		                     out1.writeUTF(MessageRecv);}//写信息
 		                    }
 	                      serverui.prichat.setText("");
	                 } catch (IOException e) {
	        	     isnormal=false;
	        	     System.out.println("线程出错");
	        
	              }
	    
	            }
	    }
	}
		

	public String getdate()
	{
		SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");//设置日期格式
		return date.format(new Date());// new Date()为获取当前系统时间
	}
	public Vector<String> getuserslist()
	{   int i=0;
	   
		for(Socket s:connected_socket) {
			userslist.add(usersname.get(i)+s.getPort() );
			++i;
		}
		serverui.users.setListData(userslist);//显示在线用户
		return userslist;
	}
	 public static void main(String[] args) {
			new Server();
		}
}
