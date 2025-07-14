package com.ceox.ceox_data_service.model;

public class WeeklyReportResponse {
    private String html;
    private String markdown;

    public String getHtml() {
        return html;
    }

    public String getMarkdown() {
        return markdown;
    }

    // Constructor vacío para deserialización
    public WeeklyReportResponse() {}
}
