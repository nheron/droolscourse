package es;

public class Question {
    private String screenId;
    private String questionId;
    private String questionText;
    private String variableName;
    private String stringVariableValue;

    public Question(String screenId, String questionId, String questionText, String variableName, String stringVariableValue) {
        this.screenId = screenId;
        this.questionId = questionId;
        this.questionText = questionText;
        this.variableName = variableName;
        this.stringVariableValue = stringVariableValue;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getStringVariableValue() {
        return stringVariableValue;
    }

    public void setStringVariableValue(String stringVariableValue) {
        this.stringVariableValue = stringVariableValue;
    }

    @Override
    public String toString() {
        return "Question{" +
                "screenId='" + screenId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", questionText='" + questionText + '\'' +
                ", variableName='" + variableName + '\'' +
                '}';
    }
}
