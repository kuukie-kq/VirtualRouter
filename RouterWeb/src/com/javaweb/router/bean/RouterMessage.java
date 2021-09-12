package com.javaweb.router.bean;

import version.one.bean.Router;

import java.util.List;

public class RouterMessage {
    private int pageCountNumber;
    private int countPageNumber;
    private int pageNumber;
    private int countNumber;
    private List<Router> routers;

    public RouterMessage() {

    }

    public RouterMessage(int pageCountNumber, int countPageNumber, int pageNumber, int countNumber, List<Router> routers) {
        this.pageCountNumber = pageCountNumber;
        this.countPageNumber = countPageNumber;
        this.pageNumber = pageNumber;
        this.countNumber = countNumber;
        this.routers = routers;
    }

    @Override
    public String toString() {
        return "RouterMessage{" +
                "pageCountNumber=" + pageCountNumber +
                ", countPageNumber=" + countPageNumber +
                ", pageNumber=" + pageNumber +
                ", countNumber=" + countNumber +
                ", routers=" + routers +
                '}';
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

    public List<Router> getRouters() {
        return routers;
    }

    public void setRouters(List<Router> routers) {
        this.routers = routers;
    }
}
