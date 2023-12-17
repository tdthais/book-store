package com.ada.bookStore.utils;

import com.ada.bookStore.controller.dto.OrderRequest;
import com.ada.bookStore.controller.dto.OrderResponse;
import com.ada.bookStore.model.User;
import com.ada.bookStore.model.Order;
import com.ada.bookStore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderConvert {

    public static Order toEntity(User user, List<Product> books ){
        Order order = new Order();
        List<BigDecimal> prices = new ArrayList<>();
        for (Product book : books) {
            prices.add(book.getPrice());
        }
        BigDecimal totalPrice = Calculator.calculateTotalPrice(prices);
        order.setTotalPrice(totalPrice);
        order.setUser(user);
        order.setBooks(books);
        return order;
    }

    public static OrderResponse toResponse(Order order){
        OrderResponse ordersResponse = new OrderResponse();
        ordersResponse.setId(order.getId());
        ordersResponse.setUser(order.getUser());
        ordersResponse.setBooks(order.getBooks());
        ordersResponse.setTotalPrice(order.getTotalPrice());
        return ordersResponse;
    }

    public static List<OrderResponse> toResponseList(List<Order> orders){
        List<OrderResponse> ordersResponse = new ArrayList<>();
        for(Order order: orders){
            ordersResponse.add(toResponse(order));
        }

        return ordersResponse;
    }

    public static Page<OrderResponse> toResponsePage(Page<Order> orders){
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            OrderResponse orderResponse = OrderConvert.toResponse(order);
            orderResponses.add(orderResponse);
        }
        return new PageImpl<>(orderResponses);
    }
}
