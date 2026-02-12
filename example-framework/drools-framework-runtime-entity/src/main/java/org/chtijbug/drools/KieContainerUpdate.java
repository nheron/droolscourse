package org.chtijbug.drools;

public class KieContainerUpdate {

    public enum STATUS {
        TODEPLOY,
        TODELETE
    }


    private String mainClass;

    private String groupID;

    private String artifactID;

    private String processID;

    private String projectVersion;

    private String containerID;

    private STATUS action;

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getArtifactID() {
        return artifactID;
    }

    public void setArtifactID(String artifactID) {
        this.artifactID = artifactID;
    }

    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    public String getContainerID() {
        return containerID;
    }

    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }

    public STATUS getAction() {
        return action;
    }

    public void setAction(STATUS action) {
        this.action = action;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KieContainerUpdate{");
        sb.append("mainClass='").append(mainClass).append('\'');
        sb.append(", groupID='").append(groupID).append('\'');
        sb.append(", artifactID='").append(artifactID).append('\'');
        sb.append(", processID='").append(processID).append('\'');
        sb.append(", projectVersion='").append(projectVersion).append('\'');
        sb.append(", containerID='").append(containerID).append('\'');
        sb.append(", action=").append(action);
        sb.append('}');
        return sb.toString();
    }
}
