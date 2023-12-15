package com.ada.bookStore.controller;

import com.ada.bookStore.controller.dto.OrderRequest;
import com.ada.bookStore.controller.dto.OrderResponse;
import com.ada.bookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
