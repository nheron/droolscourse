package decisiontable;

import java.util.HashMap;
import java.util.Map;

public class ScreenStep {
    private static int stepNumber;

    public int getCurrentStepNumber() {
        return stepNumber;
    }
    public int getNextStepNumber() {
        return stepNumber++;
    }
    private String requestId;
    private String screenId;
    private String title;
    private String orderNumber;
    private Map<String, Question> questions= new HashMap<String, Question>();
    public ScreenStep(String requestId, String screenId, String title) {
        this.requestId = requestId;
        this.screenId = screenId;
        this.title = title;
        this.orderNumber=String.valueOf(getNextStepNumber());
    }
    public Question addQuestion(String questionId, String questionText, String variableName){
        Question question = new Question(this.screenId,questionId,questionText,variableName,null);
        questions.put(questionId,question);
        return question;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "ScreenStep{" +
                "requestId='" + requestId + '\'' +
                ", screenId='" + screenId + '\'' +
                ", title='" + title + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                '}';
    }
}
