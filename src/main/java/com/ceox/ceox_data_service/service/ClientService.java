package com.ceox.ceox_data_service.service;

import com.ceox.ceox_data_service.model.Client;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    public List<Client> getAllClients() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference clientsRef = db.collection("clients");

        ApiFuture<QuerySnapshot> future = clientsRef.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Client> clients = new ArrayList<>();

        for (QueryDocumentSnapshot doc : documents) {
            Client client = new Client();
            client.setId(doc.getId());
            client.setName(doc.getString("name"));
            client.setDescription(doc.getString("description"));
            client.setServices(doc.getString("services")); // ← CORREGIDO: ya no es una lista
            clients.add(client);
        }

        return clients;
    }
}
