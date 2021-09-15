package com.javaweb.router.servlet;

import com.javaweb.router.bean.HostMessage;
import com.javaweb.router.util.RouterWebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

import java.io.IOException;

public class SendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String head = request.getParameter("head");
        String body = request.getParameter("body");
        if(head.split("->").length == 2) {
            RouterWebUtils.rar.send(head,body);
        } else {

        }

        JSONArray jsonArray = JSONArray.fromObject("ok");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(jsonArray);
        System.out.println(jsonArray);
    }
}
