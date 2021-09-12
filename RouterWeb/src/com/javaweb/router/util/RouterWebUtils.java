package com.javaweb.router.util;

import com.javaweb.router.dao.HostRelationshipDao;
import com.javaweb.router.dao.RouterRelationshipDao;
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
}
