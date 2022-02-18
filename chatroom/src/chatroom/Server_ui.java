package chatroom;
//������ui
import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import jdk.internal.net.http.common.MinimalFuture;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Server_ui {
	private JPanel jpanel;
	public OutputStream outputStream;
	private JFrame jframe;
	private JButton btn1;//���Ͱ�ť
	private String messageString;
	private JButton update;
	private JButton flush;
	private JButton searchfile;
	public JList users;
	public JLabel jLable;
	public JLabel jLable2;
	public JTextArea message_list;//��Ϣ�б�
	public JTextArea prichat;//˽����Ϣ
	public OutputStream output;
	static JTextArea message_to_send;
	private static boolean isclicked=false;
	public Server_ui(String name) {
		jframe=new JFrame(name);
		
		jframe.setLayout(new FlowLayout());
		jpanel=new JPanel();
		jpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		jframe.setContentPane(jpanel);
		jpanel.setLayout(null);
		jframe.setBounds(100,100,1000,800);
		users=new JList<String>();
		users.setBounds(830, 100, 100, 300);
		jpanel.add(users);
		message_list=new JTextArea(10,50);
		message_to_send=new JTextArea(10,20);
		prichat=new JTextArea(10,20);
		btn1= new JButton("Ⱥ��������Ϣ��");
		update =new JButton("�����û���Ϣ ");
		flush = new JButton("ˢ��");
		searchfile=new JButton("�鿴��ʷ�ļ�");
		flush.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				message_list.setText("");
				
			}
			
		});
		
		flush.setBounds(810,470,150,50);
		jpanel.add(flush);
		searchfile.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"�ļ�·��Ϊ:D://Cache");
				
			}
			
		});
		searchfile.setBounds(810,20,150,50);
		jpanel.add(searchfile);
		update.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				users.setListData(onlineuser.onlineusers);
				
			}
			
		});
		update.setBounds(810,550,150,50);
		jpanel.add(update);
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isclicked=true;
				String x=message_to_send.getText();
            	showmessage(x);
				message_to_send.setText("");
				
			}
		});
		btn1.setBounds(810,630,150,50);
		
		jpanel.add(btn1);
		jLable =new JLabel("�����û�");
		jLable.setBounds(855,70,100,30);
		jpanel.add(jLable);
		jLable2 =new JLabel("˽��ģʽ");
		jLable2.setBounds(855,405,100,30);
		jpanel.add(jLable2);
		message_list = new JTextArea();
		message_list.setBounds(10, 10,800, 600);
		jpanel.add(message_list);
		prichat = new JTextArea();
		prichat.setBounds(830, 435,100,30);
		jpanel.add(prichat);
		//�����
		message_to_send=new JTextArea();
		message_to_send.setBounds(10,630, 800, 50);
		jpanel.add(message_to_send);
	
		jframe.setVisible(true);	
				
	}
	public boolean isclicked() {
		return isclicked;
	}
    //���Ͱ�ť���ж��Ƿ���
	public void setclicked(boolean isclicked) {
		this.isclicked = isclicked;
	}
	public void showmessage(String message)
	{
		message_list.append(message);
		
	}
	public  void main(String[] args) throws IOException {   	
 	  Server_ui a=	new Server_ui("mini-chatroom");
 	}  
}
