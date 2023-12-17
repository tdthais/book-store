package com.ada.bookStore.utils;

import com.ada.bookStore.controller.dto.ProductRequest;
import com.ada.bookStore.controller.dto.ProductResponse;
import com.ada.bookStore.model.Product;
import com.ada.bookStore.model.TypeProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductConvert {

    public static Product toEntity(ProductRequest bookRequest, TypeProduct bookSubject){
        Product book = new Product();
        book.setName(bookRequest.getName());
        book.setPrice(bookRequest.getPrice());
        book.setBookSubject(bookSubject);
        return book;
    }

    public static ProductResponse toResponse(Product book){
        ProductResponse bookResponse = new ProductResponse();
        bookResponse.setId(book.getId());
        bookResponse.setName(book.getName());
        bookResponse.setPrice(book.getPrice());
        bookResponse.setBookSubject(TypeProductConvert.toResponse(book.getBookSubject()));
        return bookResponse;
    }

    public static List<ProductResponse> toResponseList(List<Product> books){
        List<ProductResponse> bookResponses = new ArrayList<>();
        for(Product book : books){
            bookResponses.add(toResponse(book));
        }

        return bookResponses;
    }
}
