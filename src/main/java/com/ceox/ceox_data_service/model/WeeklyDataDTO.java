package com.ceox.ceox_data_service.model;

import java.util.List;

public class WeeklyDataDTO {
    private List<Task> tasks;
    private List<Project> projects;
    private List<Client> clients;
    private List<Notification> notifications;

    // Constructor con todos los campos
    public WeeklyDataDTO(List<Task> tasks, List<Project> projects, List<Client> clients, List<Notification> notifications) {
        this.tasks = tasks;
        this.projects = projects;
        this.clients = clients;
        this.notifications = notifications;
    }

    // Getters y Setters
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}