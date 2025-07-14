package com.ceox.ceox_data_service.model;

import java.util.List;

public class Task {
    private String id;
    private String name;
    private String description;
    private String dueDate;
    private boolean isDone;
    private List<String> assignedUserNames;
    private String projectName;

    public List<String> getAssignedUserNames() {
        return assignedUserNames;
    }

    public void setAssignedUserNames(List<String> assignedUserNames) {
        this.assignedUserNames = assignedUserNames;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
