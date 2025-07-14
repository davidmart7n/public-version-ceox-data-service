package com.ceox.ceox_data_service.service;

import com.ceox.ceox_data_service.model.Task;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @SuppressWarnings("unchecked")
    public List<Task> getAllTasks() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference tasksRef = db.collection("tasks");

        ApiFuture<QuerySnapshot> future = tasksRef.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Task> tasks = new ArrayList<>();

        for (QueryDocumentSnapshot doc : documents) {
            Task task = new Task();
            task.setId(doc.getId());
            task.setName(doc.getString("name"));
            task.setDescription(doc.getString("description"));
            task.setDone(Boolean.TRUE.equals(doc.getBoolean("isDone")));

            // dueDate
            Timestamp timestamp = doc.getTimestamp("dueDate");
            if (timestamp != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                task.setDueDate(sdf.format(timestamp.toDate()));
            }

            // assignedUsers → fullName
            List<Map<String, Object>> assignedUsers = (List<Map<String, Object>>) doc.get("assignedUsers");
            if (assignedUsers != null) {
                List<String> fullNames = assignedUsers.stream()
                        .map(user -> (String) user.get("fullName"))
                        .collect(Collectors.toList());
                task.setAssignedUserNames(fullNames);
            }

            // project → name
            Map<String, Object> project = (Map<String, Object>) doc.get("project");
            if (project != null && project.get("name") != null) {
                task.setProjectName((String) project.get("name"));
            }

            tasks.add(task);
        }

        return tasks;
    }
}
