package ru.x5.example.model;

public class Action {

    private String catAction;
    private String supervisorAction;
    private Boolean result;

    public Action() {
    }

    public Action(String catAction) {
        super();
        this.catAction = catAction;
    }

    public String getFirstAction() {
        return catAction;
    }

    public String getSecondAction() {
        return supervisorAction;
    }

    public Boolean getResult() {
        return result;
    }

    public void setFirstAction(String catAction) {
        this.catAction = catAction;
    }

    public void setSecondAction(String supervisorAction) {
        this.supervisorAction = supervisorAction;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Action{" +
                "catAction='" + catAction + '\'' +
                ", supervisorAction='" + supervisorAction + '\'' +
                ", result=" + result +
                '}';
    }
}
