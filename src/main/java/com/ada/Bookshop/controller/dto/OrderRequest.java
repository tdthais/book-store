package com.ada.Bookshop.controller.dto;

import lombok.Getter;
import java.util.List;


@Getter
public class OrderRequest {
    private Double totalPrice;
    private Integer userId;
    private List<Integer> booksIds;
}
