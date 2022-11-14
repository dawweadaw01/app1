package com.app1;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ParamServlet extends HttpServlet {

    String c1;   //全局变量
    String c2;   //局部变量

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //读取全局初始化参数和局部初始化参数

        c1=getServletContext().getInitParameter("cdu");
        c2=config.getInitParameter("major");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer=resp.getWriter();
        writer.println("cdu:" + c1);
        writer.println("<br/>");
        writer.println("major:" + c2);
        writer.close();
    }
}
