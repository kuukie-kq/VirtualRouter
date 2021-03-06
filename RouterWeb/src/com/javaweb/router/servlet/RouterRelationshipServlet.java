package com.javaweb.router.servlet;

import com.javaweb.router.bean.HeadMessage;
import com.javaweb.router.bean.RouterShip;
import com.javaweb.router.service.HomeService;
import com.javaweb.router.service.RouterRelationshipService;
import com.javaweb.router.util.RouterWebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

import java.io.IOException;

public class RouterRelationshipServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RouterShip routerShip = RouterWebUtils.routerRelationshipService.autoFoundRouterShip();

        JSONArray jsonArray = JSONArray.fromObject(routerShip);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(jsonArray);
        System.out.println(jsonArray);
    }
}
