package com.ada.Bookshop.controller.dto;

import com.ada.Bookshop.model.User;
import com.ada.Bookshop.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrderResponse {
    private Integer id;
    private Double totalPrice;
    private User user;
    private List<Product> books;
}
