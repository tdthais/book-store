package com.ada.bookStore.repository;

import com.ada.bookStore.model.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeProductRepository extends JpaRepository<TypeProduct, Integer> {
}
