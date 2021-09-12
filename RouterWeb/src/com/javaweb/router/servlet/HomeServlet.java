package com.javaweb.router.servlet;

import com.javaweb.router.bean.HeadMessage;
import com.javaweb.router.service.HomeService;
import com.javaweb.router.util.RouterWebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

import java.io.IOException;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HeadMessage headMessage = RouterWebUtils.homeService.getMessage();
        headMessage.setHostRelationshipList(RouterWebUtils.hostRelationshipDao.lookupHostShipGetHostShipsByLimit(0));

        JSONArray jsonArray = JSONArray.fromObject(headMessage);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(jsonArray);
        System.out.println(jsonArray);
    }
}
