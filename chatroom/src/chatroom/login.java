

package chatroom;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import chatroom.nameandpassword;
import chatroom.onlineuser;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class login {
	private JPanel jpanel;
	private JFrame jframe;
	private JButton login1;
	private JButton make;
	public JLabel username;
	public JLabel password;
	private JTextArea inputname;
	private JTextArea inputpassword;
	public Connection connect;
	public Statement state;
	public Connection connect1;
	public Client cli;
	public login(String name) {
		
	jframe=new JFrame(name);
    jframe.setLayout(new FlowLayout());
	jpanel=new JPanel();
	jpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
	jframe.setContentPane(jpanel);
	jpanel.setLayout(null);
	jframe.setBounds(100,100,1000,800);
	username =new JLabel("�û�����");
	username.setBounds(50,200,50,50);
	password =new JLabel("���룺");
	password.setBounds(50,250,50,50);
	login1= new JButton("��¼");
	/*
	nameandpassword.map.put("*admin1","123456");//�����ҹ���Ա�˺��ڲ��Ѿ�ע�ᡣ
	nameandpassword.map.put("*admin2","123456");//*���������ҵĹ���Ա
	nameandpassword.map.put("*admin3","123456");
	nameandpassword.map.put("*admin4","123456");
	nameandpassword.map.put("*admin5","123456");//���������Ա
	*/
	login1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String tmp1=inputname.getText();
			String tmp2=inputpassword.getText();
			try {
				
				 Class.forName("com.mysql.jdbc.Driver"); 
				  connect1 = DriverManager.getConnection( "jdbc:mysql://localhost:3306/chatroom","root","��������Ϊ�������");
					   String sql1="select * from nameandpassword";
					 
					   PreparedStatement    pst1 = connect1.prepareStatement(sql1);
					
					   ResultSet resultSet =pst1.executeQuery();
					   System.out.println("ִ�гɹ�");
					   while (resultSet.next()) {
				            
				            String usernamee = resultSet.getString("name");
				            String passwordd = resultSet.getString("password");
				            if(tmp1.equals(usernamee))
				            {if(tmp2.equals(passwordd))
				            	{onlineuser.onlineusers.add(tmp1);
				            	//�����ݿ��ѯ���Ƚϣ�������ȷ���¼�ɹ������ͻ���
				   				JOptionPane.showMessageDialog(null,"��¼�ɹ�");
				   				try {
				   					new Client(tmp1);
				   					System.out.println("�ͻ���"+tmp1+"�����ɹ�");
				   				} catch (IOException e2) {
				   					// TODO Auto-generated catch block
				   					System.out.println("�ͻ�������ʧ��");
				   				}
				            	}
				            else {
				            	JOptionPane.showMessageDialog(null,"�û������������");
				            }
				            }
				        }
					  
					   
					  
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("sorry,��¼�������");
				}// catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
				//	e1.printStackTrace();
			//	} 
 catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		

		}
	});
	login1.setBounds(250,330,100,50);
	make= new JButton("ע��");
	make.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		String tmp1=inputname.getText();
		String tmp2=inputpassword.getText();
		char []tmp11=tmp1.toCharArray();
		char []tmp22=tmp2.toCharArray();
		System.out.println(tmp11);
		System.out.println(tmp22);
		
		try {
		
		  Class.forName("com.mysql.jdbc.Driver"); 
		  connect = DriverManager.getConnection( "jdbc:mysql://localhost:3306/chatroom","root","�����滻Ϊ�������");
			   String sql=("INSERT INTO nameandpassword(name,password) VALUES(?,?);");
			  // String sql1=("select name from nameandpassword where name=?");
			   PreparedStatement pst = connect.prepareStatement(sql);
		
			   pst.setString(1,tmp1);
			   pst.setString(2,tmp2);
			  
			   pst.executeUpdate();
			   System.out.println("���ݿⲿ��ɹ�");
			//���ı����ע����Ϣд�뵽���ݿ�
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("δ�ܳɹ���ӵ����ݿ�,������ͬ�û������û�");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		nameandpassword.map.put(tmp1,tmp2);
		JOptionPane.showMessageDialog(null,"ע��ɹ�");
		}
	});
	make.setBounds(100,330,100,50);
	inputname=new JTextArea();
	inputpassword=new JTextArea();
	inputname.setBounds(100,200,300,50);
	inputpassword.setBounds(100,270,300,50);
	jpanel.add(inputname);
	jpanel.add(inputpassword);
	jpanel.add(make);
	jpanel.add(login1);
	jpanel.add(username);
	jpanel.add(password);
	jframe.setVisible(true);	
	}

  
public  void main(String[] args) throws IOException {   	
	 login a=	new login("��¼ע��ϵͳ");
	} 
}
