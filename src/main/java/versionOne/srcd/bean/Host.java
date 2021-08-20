package versionOne.srcd.bean;

public class Host {
    private int hostId;
    private String hostName;
    private String hostAddress;

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        if (hostAddress.split(":").length != 2) {
            System.err.println(hostAddress + " is not a valid url address.");
            System.exit(-102);
        }
        this.hostAddress = hostAddress;
    }
}
