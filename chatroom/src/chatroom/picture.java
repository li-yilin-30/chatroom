package chatroom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//用于在客户端显示图片信息

public class picture {
	
	    private	JFrame frame;
		private Icon icon;
		private String path;
		private JLabel label=null;
		public picture(String pt)
		{
			frame=new JFrame("picture_show");
			 path=pt;
			icon=new ImageIcon(path);
			
			label=new JLabel(icon, JLabel.CENTER);
			label.setForeground(Color.white);
			label.setBackground(Color.YELLOW);
			frame.add(label);
			frame.setSize(200,150);
			frame.setBackground(Color.white);
			frame.setLocation(200,130);
			frame.setVisible(true);
		}
	public  void main(String[] args) throws IOException  {   	
 		
 	}  
}
	