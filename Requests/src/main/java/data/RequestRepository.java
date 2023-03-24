/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import req.entities.Request;

/**
 *
 * @author Mateusz
 */
@ApplicationScoped
public class RequestRepository {
    
    @Inject
    private EntityManager em;
    
    public void create(Request entity) {
        em.persist(entity);
    }
    public void edit(Request entity) {
        em.merge(entity);
    }
    public void remove(Request entity) {
        em.remove(em.merge(entity));
    }
    public Request find(Object id) {
        return em.find(Request.class, id);
    }
    public List<Request> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Request.class));
        return em.createQuery(cq).getResultList();
    }
}
