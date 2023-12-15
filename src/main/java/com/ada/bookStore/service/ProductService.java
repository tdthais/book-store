package com.ada.bookStore.service;


import com.ada.bookStore.controller.dto.ProductRequest;
import com.ada.bookStore.controller.dto.ProductResponse;
import com.ada.bookStore.model.Product;
import com.ada.bookStore.model.TypeProduct;
import com.ada.bookStore.repository.ProductRepository;
import com.ada.bookStore.repository.TypeProductRepository;
import com.ada.bookStore.utils.ProductConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository bookRepository;

    @Autowired
    TypeProductRepository bookSubjectRepository;

    public ProductResponse saveProduct(ProductRequest bookRequest){
        TypeProduct bookSubject = bookSubjectRepository.findById(bookRequest.getBookSubjectId()).get();
        Product book = ProductConvert.toEntity(bookRequest, bookSubject);
        return  ProductConvert.toResponse(bookRepository.save(book));
    }

    public List<ProductResponse> getAllProduct(Integer bookSubject){
        if(bookSubject != null){
            return getAllByTypeProduct(bookSubject);
        }
        return ProductConvert.toResponseList(bookRepository.findAll());
    }

    public List<ProductResponse> getAllByTypeProduct(Integer bookSubject){
        return ProductConvert.toResponseList(bookRepository.findProductByType(bookSubject));
    }
}
