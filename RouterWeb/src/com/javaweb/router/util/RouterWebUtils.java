package com.javaweb.router.util;

import com.javaweb.router.dao.HostRelationshipDao;
import com.javaweb.router.dao.RouterRelationshipDao;
import com.javaweb.router.server.RequestAndResponse;
import com.javaweb.router.server.StartNorthward;
import com.javaweb.router.server.StartSouthbound;
import com.javaweb.router.server.Stdout;
import com.javaweb.router.service.HomeService;
import com.javaweb.router.service.HostService;
import com.javaweb.router.service.RouterRelationshipService;
import com.javaweb.router.service.RouterService;

public class RouterWebUtils {
    public static HomeService homeService = new HomeService();
    public static RouterRelationshipService routerRelationshipService = new RouterRelationshipService();
    public static HostService hostService = new HostService();
    public static RouterService routerService = new RouterService();

    public static HostRelationshipDao hostRelationshipDao = new HostRelationshipDao();
    public static RouterRelationshipDao routerRelationshipDao = new RouterRelationshipDao();

    public static StartSouthbound southbound = new StartSouthbound();
    public static StartNorthward northward = new StartNorthward();
    public static Stdout stdout = new Stdout();
    public static RequestAndResponse rar = new RequestAndResponse();
}
