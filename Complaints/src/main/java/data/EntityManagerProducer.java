/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Mateusz
 */
public class EntityManagerProducer {
    @PersistenceContext
    private EntityManager em;
    
    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return em;
    }
}
