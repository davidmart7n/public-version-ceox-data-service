package com.ceox.ceox_data_service.controller;

import com.ceox.ceox_data_service.model.Task;
import com.ceox.ceox_data_service.model.Project;
import com.ceox.ceox_data_service.model.Client;
import com.ceox.ceox_data_service.model.Notification;
import com.ceox.ceox_data_service.model.WeeklyDataDTO;
import com.ceox.ceox_data_service.model.WeeklyReportResponse;
import com.ceox.ceox_data_service.service.TaskService;
import com.ceox.ceox_data_service.service.ProjectService;
import com.ceox.ceox_data_service.service.ReportService;
import com.ceox.ceox_data_service.service.ClientService;
import com.ceox.ceox_data_service.service.NotificationService;
import com.ceox.ceox_data_service.service.GeminiReportService;
import com.ceox.ceox_data_service.service.HtmlToPdfService;
import com.ceox.ceox_data_service.service.MarkmapHtmlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DataController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final ClientService clientService;
    private final NotificationService notificationService;
    private final GeminiReportService geminiReportService;
    private final ReportService reportService;
    private final HtmlToPdfService htmlToPdfService;
    private final MarkmapHtmlService markmapHtmlService;

    @Autowired
    public DataController(
            TaskService taskService,
            ProjectService projectService,
            ClientService clientService,
            NotificationService notificationService,
            GeminiReportService geminiReportService,
            ReportService reportService,
            HtmlToPdfService htmlToPdfService,
            MarkmapHtmlService markmapHtmlService // 👈 Añádelo aquí
    ) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.clientService = clientService;
        this.notificationService = notificationService;
        this.geminiReportService = geminiReportService;
        this.reportService = reportService;
        this.htmlToPdfService = htmlToPdfService;
        this.markmapHtmlService = markmapHtmlService; // 👈 Y aquí también
    }

    @GetMapping("/api/data/tasks")
    public List<Task> getAllTasks() throws Exception {
        return taskService.getAllTasks();
    }

    @GetMapping("/api/data/projects")
    public List<Project> getAllProjects() throws Exception {
        return projectService.getAllProjects();
    }

    @GetMapping("/api/data/clients")
    public List<Client> getAllClients() throws Exception {
        return clientService.getAllClients();
    }

    @GetMapping("/api/data/notifications")
    public List<Notification> getAllNotifications() throws Exception {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/api/data/weekly-report")
    public Map<String, Object> generateWeeklyReport() throws Exception {
        WeeklyDataDTO data = new WeeklyDataDTO(
                taskService.getAllTasks(),
                projectService.getAllProjects(),
                clientService.getAllClients(),
                notificationService.getAllNotifications());

        // 1. Obtener HTML + Markdown desde Gemini (via intelligent-service)
        WeeklyReportResponse report = geminiReportService.generateFullReport(data);

        // 2. Convertir HTML a PDF
        byte[] pdfBytes = htmlToPdfService.convertHtmlToPdf(report.getHtml());
        String pdfUrl = reportService.uploadPdfToStorage(pdfBytes);

        // 3. Convertir Markdown a HTML
        byte[] htmlBytes = markmapHtmlService.convertMarkdownToHtml(report.getMarkdown());
        String htmlUrl = reportService.uploadHtmlToStorage(htmlBytes);

        // 4. Guardar en Firestore
        reportService.saveReportMetadata(pdfUrl, htmlUrl);

        // 5. Devolver respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Reporte generado y subido correctamente");
        response.put("date", LocalDate.now().toString());
        response.put("pdfUrl", pdfUrl);
        response.put("htmlUrl", htmlUrl);

        return response;
    }
}