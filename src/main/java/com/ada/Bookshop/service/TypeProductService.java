package com.ada.Bookshop.service;

import com.ada.Bookshop.controller.dto.TypeProductRequest;
import com.ada.Bookshop.controller.dto.TypeProductResponse;
import com.ada.Bookshop.model.TypeProduct;
import com.ada.Bookshop.repository.TypeProductRepository;
import com.ada.Bookshop.utils.TypeProductConvert;
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
