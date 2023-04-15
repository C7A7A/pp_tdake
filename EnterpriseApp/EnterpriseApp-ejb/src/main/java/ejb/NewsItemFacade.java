/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author Mateusz
 */
@Stateless
public class NewsItemFacade implements NewsItemFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")\
    
    @PersistenceContext
    private EntityManager em;
    public NewsItemFacade() {
        
    }
    
    public List<NewsItem> getAllNewsItems() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<NewsItem> cq = cb.createQuery(NewsItem.class);
        Root<NewsItem> rootEntry = cq.from(NewsItem.class);
        CriteriaQuery<NewsItem> ct = cq.select(rootEntry);
        TypedQuery<NewsItem> allNewsItemsQuery = em.createQuery(ct);
        return allNewsItemsQuery.getResultList();
    }
}
