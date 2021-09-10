package com.javaweb.router.bean;

public class Relationship {
    private String fromNode;
    private String toNode;

    public Relationship() {

    }

    public Relationship(String fromNode, String toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
    }

    @Override
    public String toString() {
        return "Relationship{" +
                "fromNode='" + fromNode + '\'' +
                ", toNode='" + toNode + '\'' +
                '}';
    }

    public String getFromNode() {
        return fromNode;
    }

    public void setFromNode(String fromNode) {
        this.fromNode = fromNode;
    }

    public String getToNode() {
        return toNode;
    }

    public void setToNode(String toNode) {
        this.toNode = toNode;
    }
}
