package chatroom;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.text.SimpleDateFormat;
//维护一个客户ui发送消息接受消息在其中完成，服务器ui实现所以消息显示和用户列表
public class Client {
	public String username;
	private Socket socket;
	private Clientui clientui;
	
	private final String ipaddr="localhost";
	private final int port=6666;
	public Client(String username) throws UnknownHostException, IOException
	{   clientui = new Clientui(username);
		this.username=username;
		this.connect();
		//new Server_ui("client");
	}
	public void connect() throws UnknownHostException, IOException
	{
		System.out.println("欢迎来到mini—chatroom");
		socket=new Socket(ipaddr,port);
		new thread_send(socket);
		//每创建一个客户端对象，就分别添加一个发收线程，使得发收消息并不会相互冲突。
		new thread_receive(socket);
	}
	public String getdate()
	{
		SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");//设置日期格式
		return date.format(new Date());// new Date()为获取当前系统时间
	}
	class thread_send implements  Runnable {
		private Socket sk;
		public thread_send(Socket sk)
		{
			this.sk=sk;
			new Thread(this).start();
		}
		@Override
		public void run() {
			while(true)
			{	try {
				while(sk!=null)
				{
			
			
				DataOutputStream ops=new DataOutputStream(sk.getOutputStream());
			
			 
			  	if(clientui.isclicked()&&clientui.message_to_send.getText()!="")
				{String msgtext=clientui.message_to_send.getText();
				
				 System.out.println(msgtext);      //捕获非空信息，传递给服务器
				if(!msgtext.equals(""))
                 	ops.writeUTF("\n"+getdate()+"@"+username+"$:"+msgtext);
                 	msgtext="";
                clientui.message_to_send.setText("");
				   clientui.setclicked(false);
				  
				System.out.println("成功把消息上传给服务器");
				
				 if (sk == null) {
                     ops.close();
                     break;
                 }
			
                 	}
			}
				
			}
			catch (Exception e) {
				System.out.println("阿这，mini_chatroom似乎崩溃了，o(╥﹏╥)o");
			}
		}
		}
	}
	class thread_receive implements  Runnable{
		private Socket sk1;
	    public thread_receive(Socket sk1) 
		{
			this.sk1=sk1;
			new Thread(this).start();
		}
		@Override
		public void run() {
			try {
				
				while(sk1!=null)
				{
	                    DataInputStream in = new DataInputStream(sk1.getInputStream());//读取服务器数据
	                    //接收数据
	                    String recvMsg = in.readUTF().trim();
	                    String tmp=recvMsg.substring(recvMsg.length() -4);
	                    if(tmp.equals(".jpg")||tmp.equals(".png"))
	                    {  //读取的信息若为图片，则把路径保存，读取图片并显示
	                    	clientui.setpath(recvMsg);
	                    }
	                    if(recvMsg.charAt(0)!='D'&&recvMsg.charAt(0)!='C')
	                    {  int index1=recvMsg.indexOf("@");
	                    int index2=recvMsg.indexOf("$");
	                    int leng=index2-index1;
	                    System.out.println(index1);
	                    System.out.println(index2);
	                    System.out.println(leng);
	                   String uname=recvMsg.substring(index1+1,index2);
	                   System.out.println(uname);
	                    //提取用户名
	                  if(clientui.partner.contains(uname)==true)
	                	clientui.showmessage("*好友信息* ");
	                    }//若是好友发来的信息则特殊标识
	                    System.out.println("客户端接收到数据"+recvMsg);
                         clientui.showmessage(recvMsg);
                         clientui.showmessage("\n");
                         
	                   System.out.println(recvMsg);
	                   
				}
			}
			catch (Exception e) {
				System.out.println("阿这，mini_chatroom似乎崩溃了，o(╥﹏╥)o");
			}
		}
	}
	public static void main(String[] args) throws IOException {   	
 		new Client("小红").connect();
 	}  
}
