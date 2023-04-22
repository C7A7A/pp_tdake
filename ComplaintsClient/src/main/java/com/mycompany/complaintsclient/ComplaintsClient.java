/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.complaintsclient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Mateusz
 */
public class ComplaintsClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        String status = client.target("http://localhost:8080/Complaints/" + "api/complaints/551/status")
            .request(MediaType.TEXT_PLAIN)
            .get(String.class);
        System.out.println("Status: " + status);
        
        String complaints = client.target("http://localhost:8080/Complaints/" + "api/complaints")
            .request(MediaType.APPLICATION_JSON)
            .get(String.class);
        System.out.println("complaints: " + complaints);
        
        String complaint = client.target("http://localhost:8080/Complaints/" + "api/complaints/555")
            .request(MediaType.APPLICATION_JSON)
            .get(String.class);
        System.out.println("complaint 555: " + complaint);
        
        String updatedComplaint = "{\"author\":\"Johny Bravo\",\"complaintDate\":\"2021-04-24\",\"complaintText\":\"Fix air conditioning in room 242\",\"id\":555,\"status\":\"closed\"}";
        Response response = client.target("http://localhost:8080/Complaints/api/complaints/555")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(updatedComplaint));
        System.out.println("Response: " + response);
        
        String complaintsOpened = client.target("http://localhost:8080/Complaints/" + "api/complaints?status=open")
            .request(MediaType.APPLICATION_JSON)
            .get(String.class);
        System.out.println("opened complaints: " + complaintsOpened);
        
        client.close();
    }
}
