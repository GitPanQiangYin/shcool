package com.zzuli.controller;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

import java.awt.Color;  
import java.awt.Font;  
import java.awt.Graphics;  
import java.awt.image.BufferedImage;   
import java.util.Random;  
  
import javax.imageio.ImageIO;    
  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.ResponseBody;  

@Controller
@RequestMapping("/verification")
public class verification extends HttpServlet{

	private static final long serialVersionUID = 1L; 
	//验证码验证
	 @RequestMapping("/verificationServlet")  
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	        response.setCharacterEncoding("utf-8");  
	        response.setContentType("text/html;charset=utf-8");  
	          System.out.println("11111");
	        HttpSession session =request.getSession();  
	        String verificationCode = (String)session.getAttribute("verificationCode");  
	        String checkcode = request.getParameter("op");  
	        PrintWriter out = response.getWriter();  
	        if(checkcode.equals(verificationCode)){  
	            out.println(1);  
	        }else{  
	            out.println(0);  
	        }  
	        out.flush();  
	        out.close();  
	    }  
	 
	 	//生成验证码
	 	@ResponseBody  
	    @RequestMapping("/imageServlet")  
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	        request.setCharacterEncoding("utf-8");  
	        System.out.println("1111122222");
	        BufferedImage bfi = new BufferedImage(80,25,BufferedImage.TYPE_INT_RGB);  
	        Graphics g =  bfi.getGraphics();  
	        g.fillRect(0, 0, 80, 25);  
	  
	        //验证码字符范围  
	        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();  
	        Random r = new Random();   
	        int index;    
	        StringBuffer sb = new StringBuffer(); //保存字符串  
	        for(int i=0; i<4; i++){  
	            index = r.nextInt(ch.length);  
	            g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));  
	            Font font = new Font("宋体", 30, 20);  
	            g.setFont(font);  
	            g.drawString(ch[index]+"", (i*20)+2, 23);  
	            sb.append(ch[index]);  
	        }  
	          
	        // 添加噪点  
	        int area = (int) (0.02 * 80 * 25);  
	        for(int i=0; i<area; ++i){  
	            int x = (int)(Math.random() * 80);  
	            int y = (int)(Math.random() * 25);  
	            bfi.setRGB(x, y, (int)(Math.random() * 255));  
	        }  
	          
	      //设置验证码中的干扰线  
	        for (int i = 0; i < 6; i++) {    
	              //随机获取干扰线的起点和终点  
	              int xstart = (int)(Math.random() * 80);  
	              int ystart = (int)(Math.random() * 25);  
	              int xend = (int)(Math.random() * 80);  
	              int yend = (int)(Math.random() * 25);  
	              g.setColor(interLine(1, 255));  
	              g.drawLine(xstart, ystart, xend, yend);  
	            }  
	        HttpSession session = request.getSession();  //保存到session  
	        session.setAttribute("verificationCode", sb.toString());  
	        ImageIO.write(bfi, "JPG", response.getOutputStream());  //写到输出流  
	    }  
	  
	      
	      
	    /** 
	       * 获取随机的颜色值，r,g,b的取值在Low到High之间 
	       * @param L 左区间 
	       * @param R 右区间 
	       * @return 返回随机颜色值 
	       */  
	      private static Color interLine(int Low, int High){  
	        if(Low > 255)  
	            Low = 255;  
	        if(High > 255)  
	            High = 255;  
	        if(Low < 0)  
	            Low = 0;  
	        if(High < 0)  
	            High = 0;  
	        int interval = High - Low;   
	        int r = Low + (int)(Math.random() * interval);  
	        int g = Low + (int)(Math.random() * interval);  
	        int b = Low + (int)(Math.random() * interval);  
	        return new Color(r, g, b);  
	      }  
	
}
