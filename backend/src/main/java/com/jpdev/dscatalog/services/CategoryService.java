package com.jpdev.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.jpdev.dscatalog.dto.CategoryDTO;
import com.jpdev.dscatalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpdev.dscatalog.entities.Category;
import com.jpdev.dscatalog.repositories.CategoryRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> list = repository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return new CategoryDTO(entity);
    }

   @Transactional
    public CategoryDTO insert(CategoryDTO dto){
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }


}