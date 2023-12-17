package com.ada.bookStore.controller.dto;

import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private Integer bookSubjectId;
}
