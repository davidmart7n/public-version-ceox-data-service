package com.ceox.ceox_data_service.service;

import com.ceox.ceox_data_service.model.Notification;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    public List<Notification> getAllNotifications() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference notificationsRef = db.collection("notifications");

        ApiFuture<QuerySnapshot> future = notificationsRef.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Notification> notifications = new ArrayList<>();

        for (QueryDocumentSnapshot doc : documents) {
            Notification notification = new Notification();
            notification.setTitle(doc.getString("title"));
            notification.setBody(doc.getString("body"));

            Timestamp timestamp = doc.getTimestamp("timestamp");
            if (timestamp != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                notification.setTimestamp(sdf.format(timestamp.toDate()));
            }

            notifications.add(notification);
        }

        return notifications;
    }
}
