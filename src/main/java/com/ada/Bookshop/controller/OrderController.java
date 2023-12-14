package com.ada.Bookshop.controller;

import com.ada.Bookshop.controller.dto.OrderRequest;
import com.ada.Bookshop.controller.dto.OrderResponse;
import com.ada.Bookshop.controller.dto.UserRequest;
import com.ada.Bookshop.controller.dto.UserResponse;
import com.ada.Bookshop.model.Order;
import com.ada.Bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> saveOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.saveOrder(orderRequest);
        return ResponseEntity.created(URI.create("/order/"+orderResponse.getId())).body(orderResponse);
    }


    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrder(
            @RequestParam(name = "userId", required = false) Integer userId,
            @RequestParam(name = "bookId", required = false) Integer bookId){
        return ResponseEntity.ok(orderService.getAllOrders(userId, bookId));
    }



    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(
            @PathVariable Integer id,
            @RequestBody OrderRequest orderRequest
    ){
        return  ResponseEntity.ok(orderService.updateOrder(id, orderRequest));
    }


}
