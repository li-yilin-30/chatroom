package chatroom;
//�ͻ���UI
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
	private JButton btn1;//���Ͱ�ť
	private JButton btn2;//����ͼƬҳ��
	private JButton btn3;//��Ӻ���
	public ArrayList<String>partner=new ArrayList<>();
	private JTextArea friends;
	private String messageString;
	private JLabel jlabel;
	private String path;
	private JTextArea message_list;//��Ϣ�б�
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
		
		btn1= new JButton("������Ϣ/�ļ���");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isclicked=true;
				//message_to_send.setText("");
				
			}
		});
		btn1.setBounds(810,630,150,50);
		
		jpanel.add(btn1);

		btn2= new JButton("��ʾͼƬ��");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				picture1=new picture(path);
				
			}
		});
		btn2.setBounds(810,500,150,50);
		
		jpanel.add(btn2);
		btn3= new JButton("��Ӻ��ѣ�");
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name =friends.getText();
				if(!name.equals(""))
				{	partner.add(name);
				
				JOptionPane.showMessageDialog(null,"�ɹ���Ӻ���"+name);
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
		//�����
		message_to_send=new JTextArea();
		message_to_send.setBounds(10, 630, 800, 50);
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
 	  Clientui a=	new Clientui("mini-chatroom_clientroom");
 	}  
}