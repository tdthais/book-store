package com.ada.bookStore.controller.dto;

import com.ada.bookStore.model.User;
import com.ada.bookStore.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class OrderResponse {
    private Integer id;
    private BigDecimal totalPrice;
    private User user;
    private List<Product> books;
}
