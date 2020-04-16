package cn.edu.hzvtc.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;



public class VerifyCodeUtil {
	private int width=100;//图片宽度
	private int height=40;//图片高度
	
	public void setWidth(int width){
		this.width=width;
	}
	
	public void setHeight(int height){
		this.height=height;
	}
	
	private BufferedImage image=null;//图像
	private String rnds;//验证码
	
	/*
	 * 取得图片的验证码
	 */
	public String getRnds(){
		return rnds;
	}
	
	/*
	 * 取得验证码图片
	 */
	public BufferedImage getImage(){
		return this.image;
	}
	
	private VerifyCodeUtil(){
			try {
				image=createImage();
			} catch (IOException e) {
				System.out.println("验证码图片产生出现错误:"+e.toString());
			}

	}
	
	/*
	 * 取得ValidationCodeUtil实例
	 */
	public static VerifyCodeUtil Instance(){
		return new VerifyCodeUtil();
	}
	
	public BufferedImage createImage() throws IOException{
		BufferedImage image=new BufferedImage(width,height,
			BufferedImage.TYPE_INT_BGR);
		Graphics g=image.getGraphics();
		
		createRandom();
		
		drawBackground(g);
		
		drawRnds(g,rnds);
		
		g.dispose();
		
		return image;
	}
	
	private void drawRnds(Graphics g,String rnds){
		g.setColor(new Color(0xFFFFFF));
		g.setFont(new Font(null,Font.ITALIC|Font.BOLD,18));
		
		g.drawString(rnds.charAt(0)+"",1, width/4-1);
		g.drawString(rnds.charAt(1)+"",width/4+1, width/4+1);
		g.drawString(rnds.charAt(2)+"",width/2+1, width/4-1);
		g.drawString(rnds.charAt(3)+"",width*3/4+1, width/4+1);
	}
	
	private void drawBackground(Graphics g){
		g.setColor(new Color(0x1DB899));
		g.fillRect(0, 0, width, height);
		//噪点
		for(int i=0;i<120;i++){
			int x=(int)(Math.random()*width);
			int y=(int)(Math.random()*height);
			int red=(int)(Math.random()*256);
			int green=(int)(Math.random()*256);
			int blue=(int)(Math.random()*256);
			g.setColor(new Color(red,green,blue));
			g.drawOval(x,y,1,1);
		}
	}
	
	private void createRandom() {
		String str="1234567890qwertyuiopasdfghjklzxcvbnm";
		char[] rndChars=new char[4];
		Random random=new Random();
		for(int i=0;i<4;i++){
			rndChars[i]=str.charAt(random.nextInt(str.length()));
		}
		rnds = new String(rndChars);
	}
}
