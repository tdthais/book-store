package com.ada.Bookshop.controller;


import com.ada.Bookshop.controller.dto.ProductRequest;
import com.ada.Bookshop.controller.dto.ProductResponse;
import com.ada.Bookshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/book")
public class ProductController {

    @Autowired
    ProductService bookService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct(
            @RequestParam(name = "bookSubject", required = false) Integer bookSubject
    ){
        return ResponseEntity.ok(bookService.getAllProduct(bookSubject));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest bookRequest){
        ProductResponse bookResponse =  bookService.saveProduct(bookRequest);

        return ResponseEntity.created(URI.create("/book/"+ bookResponse.getId())).body(bookResponse);
    }
}
