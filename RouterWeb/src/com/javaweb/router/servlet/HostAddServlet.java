package com.javaweb.router.servlet;

import com.javaweb.router.util.RouterWebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

import java.io.IOException;

public class HostAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int hostId = Integer.parseInt(request.getParameter("hostId"));
        String hostName = request.getParameter("hostName");
        String hostAddress = request.getParameter("hostAddress");
        int hostShip = Integer.parseInt(request.getParameter("hostShip"));
        boolean isSuccess = RouterWebUtils.hostService.setNewHostAndShip(hostId,hostName,hostAddress,hostShip);

        JSONArray jsonArray = JSONArray.fromObject(isSuccess);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(jsonArray);
        System.out.println(jsonArray);
    }
}
