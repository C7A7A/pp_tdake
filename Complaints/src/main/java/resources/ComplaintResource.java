/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package resources;

import dto.ComplaintDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PUT;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import services.ComplaintService;

/**
 * REST Web Service
 *
 * @author Mateusz
 */
@Path("complaints")
@RequestScoped
public class ComplaintResource {
    @Inject
    private ComplaintService service;

    public ComplaintResource() {

    }

    @GET
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<ComplaintDTO> getAllComplaints(@QueryParam("status") String status) {
        return service.findAll(status);
    }
    
    @GET
    @Path("{id}")
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public ComplaintDTO getComplaint(@PathParam("id") Long id) {
        return service.find(id);
    }
    
    @POST
    @Consumes(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public void postComplaint(ComplaintDTO complaint) {
        service.create(complaint);
    }
    
    @PUT
    @Path("{id}")
    @Consumes(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putComplaint(@PathParam("id") Long id, ComplaintDTO complaint) {
        service.edit(complaint);
    }
    
    @DELETE
    @Path("{id}")
    public void deleteComplaint(@PathParam("id") Long id) {
        service.remove(service.find(id));
    }
    
    @GET
    @Path("{id}/status")
    @Produces(jakarta.ws.rs.core.MediaType.TEXT_PLAIN)
    public String getStatus(@PathParam("id") Long id) {
        return service.find(id).getStatus();
    }
}
