package com.ada.Bookshop.controller;

import com.ada.Bookshop.controller.dto.TypeProductRequest;
import com.ada.Bookshop.controller.dto.TypeProductResponse;
import com.ada.Bookshop.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/book-subject")
public class TypeProductController {

    @Autowired
    TypeProductService bookSubjectService;

    @GetMapping
    public ResponseEntity<List<TypeProductResponse>>getAllTypeProduct(){
        return ResponseEntity.ok(bookSubjectService.getAllTypeProducts());
    }

    @PostMapping
    public ResponseEntity<TypeProductResponse> saveTypeProduct(@RequestBody TypeProductRequest bookSubjectRequest){
        TypeProductResponse bookSubjectResponse = bookSubjectService.saveTypeProduct(bookSubjectRequest);
        return ResponseEntity.created(
                URI.create("/book-subject/"+ bookSubjectResponse.getId())
        ).body(bookSubjectResponse);
    }

    @DeleteMapping("/{id}")
    public void deleteTypeProduct(@PathVariable Integer id){
        bookSubjectService.deleteTypeProduct(id);
    }
}
