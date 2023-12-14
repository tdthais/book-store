package com.ada.Bookshop.repository;

import com.ada.Bookshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM ORDERS WHERE USER_ID = :userId", nativeQuery = true)
    List<Order> findAllByUser(Integer userId);

    @Query(value = "SELECT order FROM Order order JOIN order.books book WHERE book.id = :bookId")
    List<Order> findAllByProduct(Integer bookId);

}
