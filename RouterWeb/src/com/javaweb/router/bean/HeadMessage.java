package com.javaweb.router.bean;

import version.one.bean.Host;

import java.util.List;

public class HeadMessage {
    private int hostNumber;
    private int routerNumber;
    private int pageCountNumber;
    private int countPageNumber;
    private int pageNumber;
    private int countNumber;
    private List<HostRelationship> hostRelationshipList;

    @Override
    public String toString() {
        return "HeadMessage{" +
                "hostNumber=" + hostNumber +
                ", routerNumber=" + routerNumber +
                ", pageCountNumber=" + pageCountNumber +
                ", countPageNumber=" + countPageNumber +
                ", pageNumber=" + pageNumber +
                ", countNumber=" + countNumber +
                ", hostRelationshipList=" + hostRelationshipList +
                '}';
    }

    public HeadMessage(int hostNumber, int routerNumber, int pageNumber, int countNumber, List<HostRelationship> hostRelationshipList) {
        this.hostNumber = hostNumber;
        this.routerNumber = routerNumber;
        this.pageNumber = pageNumber;
        this.countNumber = countNumber;
        this.hostRelationshipList = hostRelationshipList;
    }

    public HeadMessage(int hostNumber, int routerNumber, int pageCountNumber, int countPageNumber, int pageNumber, int countNumber, List<HostRelationship> hostRelationshipList) {
        this.hostNumber = hostNumber;
        this.routerNumber = routerNumber;
        this.pageCountNumber = pageCountNumber;
        this.countPageNumber = countPageNumber;
        this.pageNumber = pageNumber;
        this.countNumber = countNumber;
        this.hostRelationshipList = hostRelationshipList;
    }

    public int getHostNumber() {
        return hostNumber;
    }

    public void setHostNumber(int hostNumber) {
        this.hostNumber = hostNumber;
    }

    public int getRouterNumber() {
        return routerNumber;
    }

    public void setRouterNumber(int routerNumber) {
        this.routerNumber = routerNumber;
    }

    public int getPageCountNumber() {
        return pageCountNumber;
    }

    public void setPageCountNumber(int pageCountNumber) {
        this.pageCountNumber = pageCountNumber;
    }

    public int getCountPageNumber() {
        return countPageNumber;
    }

    public void setCountPageNumber(int countPageNumber) {
        this.countPageNumber = countPageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(int countNumber) {
        this.countNumber = countNumber;
    }

    public List<HostRelationship> getHostRelationshipList() {
        return hostRelationshipList;
    }

    public void setHostRelationshipList(List<HostRelationship> hostRelationshipList) {
        this.hostRelationshipList = hostRelationshipList;
    }
}
