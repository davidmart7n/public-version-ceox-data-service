package com.ceox.ceox_data_service.service;

import com.ceox.ceox_data_service.model.Project;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    public List<Project> getAllProjects() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference projectsRef = db.collection("projects");

        ApiFuture<QuerySnapshot> future = projectsRef.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Project> projects = new ArrayList<>();

        for (QueryDocumentSnapshot doc : documents) {
            Project project = new Project();
            project.setId(doc.getId());
            project.setClientId(doc.getString("clientId"));
            project.setName(doc.getString("name"));
            project.setDescription(doc.getString("description"));
            project.setProgress(doc.getDouble("progress")); // ← debe ser Double en el modelo
            projects.add(project);
        }

        return projects;
    }
}
