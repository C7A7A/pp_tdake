/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package req.backing;

import data.RequestRepository;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.html.HtmlDataTable;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import req.entities.Request;

/**
 *
 * @author Mateusz
 */
@Named(value = "requestsList")
@RequestScoped
public class RequestsList {
    
    @Inject
    private RequestRepository requestRepository;
    
    @Size(min = 3, max = 60, message = "{request.size}")
    private String newRequest;
    private HtmlDataTable requestsDataTable;

      /**
     * Creates a new instance of RequestsList
     */
    public RequestsList() {
    }
    
    /**
     * Get the value of newRequest
     *
     * @return the value of newRequest
     */
    public String getNewRequest() {
        return newRequest;
    }

    /**
     * Set the value of newRequest
     *
     * @param newRequest new value of newRequest
     */
    public void setNewRequest(String newRequest) {
        this.newRequest = newRequest;
    }
    
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    /**
     * @return the requestsDataTable
     */
    public HtmlDataTable getRequestsDataTable() {
        return requestsDataTable;
    }

    /**
     * @param requestsDataTable the requestsDataTable to set
     */
    public void setRequestsDataTable(HtmlDataTable requestsDataTable) {
        this.requestsDataTable = requestsDataTable;
    }
    
    @Transactional
    public String addRequest() {
        Request request = new Request();
        request.setRequestText(this.getNewRequest());
        request.setRequestDate(LocalDate.now());
        requestRepository.create(request);
        return null;
    }
    
    @Transactional
    public String deleteRequest() {
        Request req = (Request) this.getRequestsDataTable().getRowData();
        requestRepository.remove(req);
        return null;
    }
    
}
