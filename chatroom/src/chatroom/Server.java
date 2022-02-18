//�������ˣ�����ͻ���
//Ĭ�ϵ�һ�����������ҵ��û�Ϊ����Ա
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
	private Server_ui  serverui;//���������Ҵ���
	private Socket client;//����ͨ���׽��ּ������Ŀͻ���
	private final int port=6666;//�������˿�
	private InputStream inputStream;
	private OutputStream outputStream;
	private login a;//��¼����
	public File file;//�ļ�
	public  Vector<String> userslist;//�洢��¼���û��б�
	public  ArrayList<String> usersname;//����һ����¼uiÿ�ε�¼������ּ������
	private ArrayList<Socket> connected_socket=new ArrayList<>();
	private String MessageRecv="";//���������ܵ���Ϣ
	private String Message="";
	public static int fileno=0;//����Ϊ������������ļ����
	Vector<String>test=new Vector<>();
	
	public Server() 
	{ 
		
	try {  //picture.showa();
		   file =new File("D:\\Cache");
		   
			serverui=new Server_ui("mini-chatroom");
			a=	new login("��¼ע��ϵͳ");
			System.out.println("����ҳ��ɹ�");
			this.connect();
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		try {
			System.out.println("����������ʧ��");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        }
	}
	public void connect()  
	{
		System.out.println("�����������ɹ�����ӭ��mini��chatroom������");
		try {
			ServerSocket serverSocket = new ServerSocket(port);
		    while(true) {
			client=serverSocket.accept();//ͨ���׽��ּ�����������ӵĿͻ���
			System.out.println("��������ͦ");
			connected_socket.add(client);
			
			new threadreceiveandsend(client);
	   //������û����һ���ͻ��˾�����һ�����̣߳����߳�������Ϣ���ڽ���ת��
			}
		}catch (Exception e) {
			
		System.out.println("�������չ���");
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
	            
	            System.out.println("�������µ����� "+"ip��ַΪ��"+client.getInetAddress()+"�˿ں�:"+client.getPort());
	            Boolean isnormal=true;
	            while (isnormal) {
                	
	            	try {
	            	   	
                		DataInputStream in = new DataInputStream(client.getInputStream());//����Ϣ
 	                    MessageRecv = in.readUTF().trim();
                	    //�ж��Ƿ�Ϊ�ļ�·������Ϊ�ļ�·���򲻵�ת��·�����Ҹ��ơ����档
 	                   int index= MessageRecv.indexOf("$");
 	                   if((MessageRecv.charAt(index+2)=='E'||MessageRecv.charAt(index+2)=='F'||MessageRecv.charAt(index+2)=='D'||MessageRecv.charAt(index+2)=='C')&&MessageRecv.charAt(index+3)==':'&&MessageRecv.charAt(index+4)=='\\')
 	                   {++fileno;
 	                   String path =MessageRecv.substring(index+2);
 	                   int index2=MessageRecv.indexOf(".");
 	                   String suffix=MessageRecv.substring(index2);
 	        
 	                 /* if (!file.exists()) {
 	                	  System.out.println("����Ĳ������ļ�·��");
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
 	                         output.write(buf, 0, bytesRead);//�ļ�����
 	                     }
 	                   }
 	                   finally {
 	                	   input.close();
 	                	   output.close();
 	                   }
 	                   MessageRecv=pathnew;
 	                   }if(serverui.prichat.getText().equals(""))
	                    {System.out.println("�ӿͻ��˽��յ�����");
	                    serverui.showmessage(MessageRecv);
	                    serverui.showmessage("\n");//�ڷ���������ʾ
	                    for(int i=0;i<connected_socket.size();++i)
	                    {  DataOutputStream out1 = new DataOutputStream(connected_socket.get(i).getOutputStream());
	                    out1.writeUTF(MessageRecv);}//����ת��
	                    }
 	                   else {
 	                	   int userno=0;
 	                	   for(int i=0;i<onlineuser.onlineusers.size();++i)
 	                	   {if(serverui.prichat.getText().equals(onlineuser.onlineusers.get(i)))
 	                		   {userno=i;
 	                		   break;
 	                		   }//��ѡ��˽���򵥶�ת��
 	                	   }
 	                	  for(int i=0;i<connected_socket.size();++i)
 		                    {
 	                		  DataOutputStream out1 = new DataOutputStream(connected_socket.get(userno).getOutputStream());
 		                     out1.writeUTF(MessageRecv);}//д��Ϣ
 		                    }
 	                      serverui.prichat.setText("");
	                 } catch (IOException e) {
	        	     isnormal=false;
	        	     System.out.println("�̳߳���");
	        
	              }
	    
	            }
	    }
	}
		

	public String getdate()
	{
		SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");//�������ڸ�ʽ
		return date.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
	}
	public Vector<String> getuserslist()
	{   int i=0;
	   
		for(Socket s:connected_socket) {
			userslist.add(usersname.get(i)+s.getPort() );
			++i;
		}
		serverui.users.setListData(userslist);//��ʾ�����û�
		return userslist;
	}
	 public static void main(String[] args) {
			new Server();
		}
}
