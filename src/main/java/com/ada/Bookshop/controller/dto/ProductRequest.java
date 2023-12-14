package com.ada.Bookshop.controller.dto;

import lombok.Getter;


@Getter
public class ProductRequest {
    private String name;
    private Double price;
    private Integer bookSubjectId;
}
