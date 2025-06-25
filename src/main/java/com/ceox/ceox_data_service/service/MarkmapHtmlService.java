package com.ceox.ceox_data_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.nio.file.Path;

import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class MarkmapHtmlService {

    public byte[] convertMarkdownToHtml(String markdown) throws IOException, InterruptedException {
        String tempDir = System.getProperty("java.io.tmpdir");
        String inputFile = tempDir + "/input-" + UUID.randomUUID() + ".md";
        String outputFile = tempDir + "/output-" + UUID.randomUUID() + ".html";

        Files.writeString(Path.of(inputFile), markdown);

        // Ejecutar el script JS usando Node
        ProcessBuilder pb = new ProcessBuilder("node", "render-markmap.js", inputFile, outputFile);
        pb.directory(new File("scripts")); // Directorio del script
        pb.redirectErrorStream(true);
        Process process = pb.start();

        if (process.waitFor() != 0) {
            throw new RuntimeException("Error generando el HTML con Markmap");
        }

        // Leer HTML generado
        return Files.readAllBytes(Path.of(outputFile));

    }
}
