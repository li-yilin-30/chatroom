package chatroom;
//客户端UI
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.text.SimpleDateFormat;
public class Clientui {
	private JPanel jpanel;
	public picture picture1;
	public OutputStream outputStream;
	private JFrame jframe;
	private JButton btn1;//发送按钮
	private JButton btn2;//弹出图片页面
	private JButton btn3;//添加好友
	public ArrayList<String>partner=new ArrayList<>();
	private JTextArea friends;
	private String messageString;
	private JLabel jlabel;
	private String path;
	private JTextArea message_list;//消息列表
	public OutputStream output;
	public JTextArea message_to_send;
	public static boolean isclicked=false;
	public void setpath(String x)
	{
	  path=x;
	}
	public Clientui(String name) {
		jframe=new JFrame(name);
		
		jframe.setLayout(new FlowLayout());
		jpanel=new JPanel();
		jpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		jframe.setContentPane(jpanel);
		jpanel.setLayout(null);
		jframe.setBounds(100,100,1000,800);
		
	/*	String path="D:\\ttt\\dd.jpg";
		Icon icon=new ImageIcon(path);
		jlabel=new JLabel(icon, JLabel.CENTER);*/
		message_list=new JTextArea(10,50);
		message_to_send=new JTextArea(10,20);
		
		btn1= new JButton("发送消息/文件：");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isclicked=true;
				//message_to_send.setText("");
				
			}
		});
		btn1.setBounds(810,630,150,50);
		
		jpanel.add(btn1);

		btn2= new JButton("显示图片：");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				picture1=new picture(path);
				
			}
		});
		btn2.setBounds(810,500,150,50);
		
		jpanel.add(btn2);
		btn3= new JButton("添加好友：");
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name =friends.getText();
				if(!name.equals(""))
				{	partner.add(name);
				
				JOptionPane.showMessageDialog(null,"成功添加好友"+name);
			}
				friends.setText("");
			}
		});
		btn3.setBounds(810,400,150,50);
		
		jpanel.add(btn3);
		message_list = new JTextArea();
		message_list.setBounds(10, 10, 800, 600);
		jpanel.add(message_list);
		friends=new JTextArea();
		friends.setBounds(810, 350, 150, 20);
		jpanel.add(friends);
		//输入框
		message_to_send=new JTextArea();
		message_to_send.setBounds(10, 630, 800, 50);
		jpanel.add(message_to_send);
	
		jframe.setVisible(true);	
				
	}
	public boolean isclicked() {
		return isclicked;
	}
    //发送按钮，判断是否点击
	public void setclicked(boolean isclicked) {
		this.isclicked = isclicked;
	}
	public void showmessage(String message)
	{
		message_list.append(message);
		
	}
	public  void main(String[] args) throws IOException {   	
 	  Clientui a=	new Clientui("mini-chatroom_clientroom");
 	}  
}