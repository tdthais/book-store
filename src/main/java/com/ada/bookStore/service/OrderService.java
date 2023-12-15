package com.ada.bookStore.service;

import com.ada.bookStore.controller.dto.OrderRequest;
import com.ada.bookStore.controller.dto.OrderResponse;
import com.ada.bookStore.model.User;
import com.ada.bookStore.model.Order;
import com.ada.bookStore.model.Product;
import com.ada.bookStore.repository.OrderRepository;
import com.ada.bookStore.repository.ProductRepository;
import com.ada.bookStore.repository.UserRepository;
import com.ada.bookStore.utils.OrderConvert;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository bookRepository;

    @Autowired
    EntityManager entityManager;

    public OrderResponse saveOrder(OrderRequest orderRequest){
        User user = userRepository.findById(orderRequest.getUserId()).get();

        List<Product> books = new ArrayList<>();

        List<Integer> booksId = orderRequest.getBooksIds();

        for(Integer id : booksId){
            Product book = bookRepository.findById(id).get();
            books.add(book);
        }

        Order order = OrderConvert.toEntity(user, books);

        return OrderConvert.toResponse(orderRepository.save(order));
    }

    public List<OrderResponse> getAllOrders(Integer userId, Integer bookId){
        if (userId != null){
            return getAllByUser(userId);
        } else if (bookId != null){
            return getAllByProduct(bookId);
        } else {
            return OrderConvert.toResponseList(orderRepository.findAll());
        }
    }


    public List<OrderResponse> getAllByUser(Integer userId){
        return OrderConvert.toResponseList(orderRepository.findAllByUser(userId));
    }

    public List<OrderResponse> getAllByProduct(Integer bookId){
        return OrderConvert.toResponseList(orderRepository.findAllByProduct(bookId));
    }

    public OrderResponse updateOrder(Integer id, OrderRequest orderRequest){
        User user = userRepository.findById(orderRequest.getUserId()).get();
        List<Product> books = new ArrayList<>();
        List<Integer> booksId = orderRequest.getBooksIds();

        for(Integer bookId : booksId){
            Product book = bookRepository.findById(bookId).get();
            books.add(book);
        }

        Order order = OrderConvert.toEntity(user, books);
        order.setId(id);
        return OrderConvert.toResponse(orderRepository.save(order));
    }




}
