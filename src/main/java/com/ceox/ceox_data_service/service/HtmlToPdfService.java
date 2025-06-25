package com.ceox.ceox_data_service.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class HtmlToPdfService {

    public byte[] convertHtmlToPdf(String htmlContent) {
        System.out.println(">>> HTML (raw) start: " + htmlContent.substring(0, Math.min(100, htmlContent.length())));

        System.out.println("Código ASCII de los primeros caracteres:");
        for (int i = 0; i < Math.min(10, htmlContent.length()); i++) {
            char c = htmlContent.charAt(i);
            System.out.println((int) c + " → '" + c + "'");
        }

        String cleanHtml = htmlContent
                .replaceAll("(?s)^```html\\s*", "") // elimina bloque inicial ```html
                .replaceAll("(?s)```\\s*$", "") // elimina cierre ``` al final si existe
                .replaceAll("(?s)^.*?<html>", "<html>") // borra cualquier contenido antes de <html>
                .trim();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(cleanHtml, null);
            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace(); // muestra el error real en consola
            throw new RuntimeException("Error al generar PDF", e);
        }

    }
}
