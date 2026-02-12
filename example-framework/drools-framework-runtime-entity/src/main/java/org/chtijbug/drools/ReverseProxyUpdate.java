package org.chtijbug.drools;

import java.util.ArrayList;
import java.util.List;

public class ReverseProxyUpdate implements java.io.Serializable{
    private String path;

    private String tokenUUID;

    private String containerID;

    List<String> serverNames = new ArrayList<>();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTokenUUID() {
        return tokenUUID;
    }

    public void setTokenUUID(String tokenUUID) {
        this.tokenUUID = tokenUUID;
    }

    public List<String> getServerNames() {
        return serverNames;
    }

    public void setServerNames(List<String> serverNames) {
        this.serverNames = serverNames;
    }

    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }

    public String getContainerID() {
        return containerID;
    }
}
