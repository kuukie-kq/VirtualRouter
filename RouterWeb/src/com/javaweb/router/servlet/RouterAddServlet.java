package com.javaweb.router.servlet;

import com.javaweb.router.util.RouterWebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import version.one.bean.Router;
import version.one.util.RouterUtils;

import java.io.IOException;

public class RouterAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int routerId = Integer.parseInt(request.getParameter("routerId"));
        String routerName = request.getParameter("routerName");
        String routerAddress = request.getParameter("routerAddress");
        boolean isSuccess = RouterWebUtils.routerService.setNewRouter(routerId,routerName,routerAddress);

        JSONArray jsonArray = JSONArray.fromObject(isSuccess);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(jsonArray);
        System.out.println(jsonArray);
    }
}
