package com.ceox.ceox_data_service.service;

import com.ceox.ceox_data_service.model.Report;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ReportService {

    public String uploadPdfToStorage(byte[] pdfBytes) throws IOException {
        String date = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        String path = "reports/" + date + "/report.pdf";

        Blob blob = StorageClient.getInstance().bucket()
                .create(path, pdfBytes, "application/pdf");

        return blob.getMediaLink();
    }

    public void saveReportMetadata(String pdfUrl, String svgUrl) {
        Firestore db = FirestoreClient.getFirestore();
        String id = LocalDate.now().toString();
        Report report = new Report(id, pdfUrl, svgUrl, Instant.now());
        db.collection("reports").document(id).set(report);
    }

    public String uploadHtmlToStorage(byte[] htmlBytes) throws IOException {
        String date = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        String path = "reports/" + date + "/map.html";

        Blob blob = StorageClient.getInstance().bucket()
                .create(path, htmlBytes, "text/html");

        return blob.getMediaLink();
    }

}
