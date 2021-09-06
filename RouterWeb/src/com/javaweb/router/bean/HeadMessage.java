package com.javaweb.router.bean;

import version.one.bean.Host;

import java.util.List;

public class HeadMessage {
    private int hostNumber;
    private int routerNumber;
    private int pageNumber;
    private int countNumber;
    private List<HostRelationship> hostRelationshipList;

    public HeadMessage(int hostNumber, int routerNumber, int pageNumber, int countNumber, List<HostRelationship> hostRelationshipList) {
        this.hostNumber = hostNumber;
        this.routerNumber = routerNumber;
        this.pageNumber = pageNumber;
        this.countNumber = countNumber;
        this.hostRelationshipList = hostRelationshipList;
    }
}
