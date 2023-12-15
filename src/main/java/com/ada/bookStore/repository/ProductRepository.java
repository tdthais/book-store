package com.ada.bookStore.repository;

import com.ada.bookStore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.bookSubject.id = :type")
    List<Product> findProductByType(@Param("type") Integer bookSubject);
}
