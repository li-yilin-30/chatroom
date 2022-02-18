package chatroom;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.text.SimpleDateFormat;
//ά��һ���ͻ�ui������Ϣ������Ϣ��������ɣ�������uiʵ��������Ϣ��ʾ���û��б�
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
		System.out.println("��ӭ����mini��chatroom");
		socket=new Socket(ipaddr,port);
		new thread_send(socket);
		//ÿ����һ���ͻ��˶��󣬾ͷֱ����һ�������̣߳�ʹ�÷�����Ϣ�������໥��ͻ��
		new thread_receive(socket);
	}
	public String getdate()
	{
		SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");//�������ڸ�ʽ
		return date.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
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
				
				 System.out.println(msgtext);      //����ǿ���Ϣ�����ݸ�������
				if(!msgtext.equals(""))
                 	ops.writeUTF("\n"+getdate()+"@"+username+"$:"+msgtext);
                 	msgtext="";
                clientui.message_to_send.setText("");
				   clientui.setclicked(false);
				  
				System.out.println("�ɹ�����Ϣ�ϴ���������");
				
				 if (sk == null) {
                     ops.close();
                     break;
                 }
			
                 	}
			}
				
			}
			catch (Exception e) {
				System.out.println("���⣬mini_chatroom�ƺ������ˣ�o(�i�n�i)o");
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
	                    DataInputStream in = new DataInputStream(sk1.getInputStream());//��ȡ����������
	                    //��������
	                    String recvMsg = in.readUTF().trim();
	                    String tmp=recvMsg.substring(recvMsg.length() -4);
	                    if(tmp.equals(".jpg")||tmp.equals(".png"))
	                    {  //��ȡ����Ϣ��ΪͼƬ�����·�����棬��ȡͼƬ����ʾ
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
	                    //��ȡ�û���
	                  if(clientui.partner.contains(uname)==true)
	                	clientui.showmessage("*������Ϣ* ");
	                    }//���Ǻ��ѷ�������Ϣ�������ʶ
	                    System.out.println("�ͻ��˽��յ�����"+recvMsg);
                         clientui.showmessage(recvMsg);
                         clientui.showmessage("\n");
                         
	                   System.out.println(recvMsg);
	                   
				}
			}
			catch (Exception e) {
				System.out.println("���⣬mini_chatroom�ƺ������ˣ�o(�i�n�i)o");
			}
		}
	}
	public static void main(String[] args) throws IOException {   	
 		new Client("С��").connect();
 	}  
}
