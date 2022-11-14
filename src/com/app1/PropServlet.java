package com.app1;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet("/prop")
public class PropServlet extends HttpServlet {

    Properties properties = new Properties();

    @Override
    public void init() throws ServletException {
        InputStream inputStream = getServletContext().getResourceAsStream("WEB-INF/classes/my.properties");

        try{
            properties.load(inputStream);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int width = 1000;
        int height = 500;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        g.setColor(Color.white);
        g.fillRect(0,0,width,height);

        g.setColor(Color.red);
        g.setFont(new Font("宋体",Font.BOLD,100));
        String myInfo = properties.getProperty("my.name") + properties.getProperty("my.id");
        myInfo = new String(myInfo.getBytes("iso-8859-1"),"utf-8");
        g.drawString(myInfo,0,160);

        ImageIO.write(image,"png",response.getOutputStream());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
