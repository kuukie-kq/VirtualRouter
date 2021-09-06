package com.javaweb.router.util;

import com.javaweb.router.dao.HostRelationshipDao;
import com.javaweb.router.dao.RouterRelationshipDao;
import com.javaweb.router.service.HomeService;

public class RouterWebUtils {
    public static HomeService homeService = new HomeService();
    public static HostRelationshipDao hostRelationshipDao = new HostRelationshipDao();
    public static RouterRelationshipDao routerRelationshipDao = new RouterRelationshipDao();
}
