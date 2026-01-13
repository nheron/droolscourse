package es;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String id;
    private Map<String,ScreenStep> screenSteps = new HashMap<>();

    public Request(String id) {
        this.id = id;
    }
    public ScreenStep addScreenStep(String screenId,String title) {
        ScreenStep screenStep = new ScreenStep(this.id,screenId,title);
        this.screenSteps.put (screenStep.getScreenId(),screenStep);
        return screenStep;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                '}';
    }
}
