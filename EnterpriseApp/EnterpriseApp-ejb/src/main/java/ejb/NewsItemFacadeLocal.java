/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author Mateusz
 */
@Local
public interface NewsItemFacadeLocal {
    public List<NewsItem> getAllNewsItems();
}
