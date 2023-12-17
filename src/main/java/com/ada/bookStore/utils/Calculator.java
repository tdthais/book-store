package com.ada.bookStore.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Calculator {


    // order request:     private Integer userId;   private List<Integer> booksIds;

    // order response:    private Integer id;  private BigDecimal totalPrice;
                         // private User user;  private List<Product> books;
    
    
    
    
    
    
    public static BigDecimal calculateTotalPrice(List<BigDecimal> prices) {
        BigDecimal totalPrice = BigDecimal.valueOf(0);

        for (BigDecimal price : prices) {
            totalPrice = totalPrice.add(price);
        }
        return totalPrice;
    }







}
