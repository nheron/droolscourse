package org.chtijbug.drools;

import java.time.LocalDateTime;

/**
 * Created by nheron on 07/07/2016.
 */
public class ChtijbugObjectRequest {


    private String transactionID;
    private LocalDateTime transactionStartTimeStamp;
    private LocalDateTime transactionEndTimeStamp;

    private String processID;
    private String containerID;

    private String artifactID;
    private String groupID;
    private String version;

    private Object objectRequest;

    private SessionContext sessionLogging;

    private boolean disableLogging;

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public LocalDateTime getTransactionStartTimeStamp() {
        return transactionStartTimeStamp;
    }

    public void setTransactionStartTimeStamp(LocalDateTime transactionStartTimeStamp) {
        this.transactionStartTimeStamp = transactionStartTimeStamp;
    }

    public LocalDateTime getTransactionEndTimeStamp() {
        return transactionEndTimeStamp;
    }

    public void setTransactionEndTimeStamp(LocalDateTime transactionEndTimeStamp) {
        this.transactionEndTimeStamp = transactionEndTimeStamp;
    }

    public Object getObjectRequest() {
        return objectRequest;
    }

    public void setObjectRequest(Object objectRequest) {
        this.objectRequest = objectRequest;
    }

    public SessionContext getSessionLogging() {
        return sessionLogging;
    }

    public void setSessionLogging(SessionContext sessionLogging) {
        this.sessionLogging = sessionLogging;
    }

    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public String getContainerID() {
        return containerID;
    }

    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }

    public String getArtifactID() {
        return artifactID;
    }

    public void setArtifactID(String artifactID) {
        this.artifactID = artifactID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isDisableLogging() {
        return disableLogging;
    }

    public void setDisableLogging(boolean disableLogging) {
        this.disableLogging = disableLogging;
    }
}
