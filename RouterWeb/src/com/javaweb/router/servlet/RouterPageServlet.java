package com.javaweb.router.servlet;

import com.javaweb.router.bean.HeadMessage;
import com.javaweb.router.bean.RouterMessage;
import com.javaweb.router.util.RouterWebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

import java.io.IOException;

public class RouterPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        RouterMessage routerMessage = RouterWebUtils.routerService.getMessageByLimit(page-1);

        JSONArray jsonArray = JSONArray.fromObject(routerMessage);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(jsonArray);
        System.out.println(jsonArray);
    }
}
