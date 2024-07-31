package com.store.crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.crud.model.Products;

public interface ProductRepo  extends JpaRepository<Products,Integer>{
    
}
