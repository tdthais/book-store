package com.ada.Bookshop.utils;

import com.ada.Bookshop.controller.dto.TypeProductRequest;
import com.ada.Bookshop.controller.dto.TypeProductResponse;
import com.ada.Bookshop.model.TypeProduct;

import java.util.ArrayList;
import java.util.List;

public class TypeProductConvert {

    public static TypeProduct toEntity(TypeProductRequest bookSubjectRequest){
        TypeProduct bookSubject = new TypeProduct();
        bookSubject.setName(bookSubjectRequest.getName());
        return bookSubject;
    }

    public static TypeProductResponse toResponse(TypeProduct bookSubject){
        TypeProductResponse bookSubjectResponse =  new TypeProductResponse();
        bookSubjectResponse.setId(bookSubject.getId());
        bookSubjectResponse.setName(bookSubject.getName());
        return bookSubjectResponse;
    }

    public static List<TypeProductResponse> toResponseList(List<TypeProduct> bookSubjects){
        List<TypeProductResponse> bookSubjectResponses = new ArrayList<>();
        for(TypeProduct bookSubject : bookSubjects){
            bookSubjectResponses.add(toResponse(bookSubject));
        }

        return bookSubjectResponses;
    }
}
