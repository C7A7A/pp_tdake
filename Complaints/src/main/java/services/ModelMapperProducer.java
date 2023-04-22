/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Mateusz
 */
public class ModelMapperProducer {
    
    @Produces
    @ApplicationScoped
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }
}
