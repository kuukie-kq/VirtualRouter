package com.javaweb.router.bean;

import version.one.bean.Host;
import version.one.bean.Router;

import java.util.List;

public class HostMessage {
    private int pageCountNumber;
    private int countPageNumber;
    private int pageNumber;
    private int countNumber;
    private List<Host> hosts;
    private List<HostRelationship> relationships;

    public HostMessage() {

    }

    public HostMessage(int pageCountNumber, int countPageNumber, int pageNumber, int countNumber, List<Host> hosts, List<HostRelationship> relationships) {
        this.pageCountNumber = pageCountNumber;
        this.countPageNumber = countPageNumber;
        this.pageNumber = pageNumber;
        this.countNumber = countNumber;
        this.hosts = hosts;
        this.relationships = relationships;
    }

    @Override
    public String toString() {
        return "HostMessage{" +
                "pageCountNumber=" + pageCountNumber +
                ", countPageNumber=" + countPageNumber +
                ", pageNumber=" + pageNumber +
                ", countNumber=" + countNumber +
                ", hosts=" + hosts +
                ", relationships=" + relationships +
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

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    public List<HostRelationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<HostRelationship> relationships) {
        this.relationships = relationships;
    }
}
