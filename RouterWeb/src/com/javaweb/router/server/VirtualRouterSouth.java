package com.javaweb.router.server;

import com.javaweb.router.bean.RouterShip;
import com.javaweb.router.util.RouterWebUtils;
import net.sf.json.JSONArray;
import version.one.bean.Router;
import version.one.northward.router.NettyVirtualRouter;
import version.one.southbound.router.NettyRouter;
import version.one.util.RouterUtils;

public class VirtualRouterSouth {
    public static void main(String[] args) throws InterruptedException {
        RouterShip routerShip = RouterWebUtils.routerRelationshipService.autoFoundRouterShip();
        JSONArray jsonArray = JSONArray.fromObject(routerShip);
        System.out.println(jsonArray);
    }

}
