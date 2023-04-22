/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import data.ComplaintRepository;
import dto.ComplaintDTO;
import entities.Complaint;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.util.List;
import java.lang.reflect.Type;
import jakarta.transaction.Transactional;

/**
 *
 * @author Mateusz
 */
@ApplicationScoped
public class ComplaintService {
    @Inject
    private ComplaintRepository repository;
    
    @Inject
    private ModelMapper mapper;

    public ComplaintService() {
    }
    
    
    @Transactional
    public void create(ComplaintDTO dto) {
        repository.create(mapper.map(dto, Complaint.class));
    }
    
    @Transactional
    public void edit(ComplaintDTO dto) {
        repository.edit(mapper.map(dto, Complaint.class));
    }
    
    @Transactional
    public void remove(ComplaintDTO dto) {
        repository.remove(mapper.map(dto, Complaint.class));
    }
    
    @Transactional
    public ComplaintDTO find(Long id) {
        Complaint complaint = repository.find(id);
        return mapper.map(complaint, ComplaintDTO.class);
    }
    
    @Transactional
    public List<ComplaintDTO> findAll(String status) {
        List<Complaint> entityList = repository.findAll(status);
        Type listType = new TypeToken<List<ComplaintDTO>>() {}.getType();
        List<ComplaintDTO> dtoList = mapper.map(entityList, listType);
        return dtoList;
    }
}
