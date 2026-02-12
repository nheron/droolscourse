package org.chtijbug.drools;

import java.util.ArrayList;
import java.util.List;

public class KieContainerResponse {

    public enum STATUS {
        ERROR,
        SUCCESS
    }

    private KieContainerUpdate kieContainerUpdate;

    private String messageError;

    private STATUS status;

    private List<String> errorMessages= new ArrayList<>();

    public KieContainerUpdate getKieContainerUpdate() {
        return kieContainerUpdate;
    }

    public void setKieContainerUpdate(KieContainerUpdate kieContainerUpdate) {
        this.kieContainerUpdate = kieContainerUpdate;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KieContainerResponse{");
        sb.append("kieContainerUpdate=").append(kieContainerUpdate);
        sb.append(", messageError='").append(messageError).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
