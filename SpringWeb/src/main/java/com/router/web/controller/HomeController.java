package com.router.web.controller;

import com.router.web.service.HostService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    private HostService hostService;

    @RequestMapping(value = "/hostMessage", method = RequestMethod.POST)
    public String hostMessage() {
        JSONArray jsonArray = JSONArray.fromObject(hostService.getMessage());
        return jsonArray.toString();
    }
}
