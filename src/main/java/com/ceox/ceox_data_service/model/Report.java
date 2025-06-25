package com.ceox.ceox_data_service.model;

import java.time.Instant;

public class Report {
    private String id;         // yyyy-MM-dd
    private String pdfUrl;     // enlace al PDF
    private String svgUrl;     // enlace al SVG
    private Instant timestamp;

    public Report() {}

    public Report(String id, String pdfUrl, String svgUrl, Instant timestamp) {
        this.id = id;
        this.pdfUrl = pdfUrl;
        this.svgUrl = svgUrl;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getSvgUrl() {
        return svgUrl;
    }

    public void setSvgUrl(String svgUrl) {
        this.svgUrl = svgUrl;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
