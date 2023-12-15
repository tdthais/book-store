package com.ada.bookStore.service;

import com.ada.bookStore.controller.dto.TypeProductRequest;
import com.ada.bookStore.controller.dto.TypeProductResponse;
import com.ada.bookStore.model.TypeProduct;
import com.ada.bookStore.repository.TypeProductRepository;
import com.ada.bookStore.utils.TypeProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProductService {

    @Autowired
    TypeProductRepository bookSubjectRepository;

    public List<TypeProductResponse> getAllTypeProducts(){
        return TypeProductConvert.toResponseList(bookSubjectRepository.findAll());
    }

    public TypeProductResponse saveTypeProduct(TypeProductRequest bookSubjectRequest){
        TypeProduct bookSubject = bookSubjectRepository.save(
                TypeProductConvert.toEntity(bookSubjectRequest)
        );
        return TypeProductConvert.toResponse(bookSubject);
    }

    public void deleteTypeProduct(Integer id){
        bookSubjectRepository.deleteById(id);
    }
}
