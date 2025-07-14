package com.ceox.ceox_data_service.model;

import java.util.List;

public class Client {
    private String id;
    private String name;
    private String description;
    private String services;

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

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }
}
