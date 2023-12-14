package com.ada.Bookshop.service;

import com.ada.Bookshop.controller.dto.OrderRequest;
import com.ada.Bookshop.controller.dto.OrderResponse;
import com.ada.Bookshop.controller.dto.UserRequest;
import com.ada.Bookshop.controller.dto.UserResponse;
import com.ada.Bookshop.model.User;
import com.ada.Bookshop.model.Order;
import com.ada.Bookshop.model.Product;
import com.ada.Bookshop.repository.OrderRepository;
import com.ada.Bookshop.repository.ProductRepository;
import com.ada.Bookshop.repository.UserRepository;
import com.ada.Bookshop.utils.OrderConvert;
import com.ada.Bookshop.utils.UserConvert;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        Order order = OrderConvert.toEntity(orderRequest, user, books);

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

        Order order = OrderConvert.toEntity(orderRequest, user, books);
        order.setId(id);
        return OrderConvert.toResponse(orderRepository.save(order));
    }




}
