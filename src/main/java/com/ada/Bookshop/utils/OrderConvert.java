package com.ada.Bookshop.utils;

import com.ada.Bookshop.controller.dto.OrderRequest;
import com.ada.Bookshop.controller.dto.OrderResponse;
import com.ada.Bookshop.model.User;
import com.ada.Bookshop.model.Order;
import com.ada.Bookshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class OrderConvert {

    public static Order toEntity(OrderRequest orderRequest, User user, List<Product> books){
        Order order = new Order();
        order.setTotalPrice(orderRequest.getTotalPrice());
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
