package com.vti.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>, JpaSpecificationExecutor<Cart> {

    public void deleteByIdIn(List<Integer> ids);
    
    public List<Cart> findByUser_Id(Integer id);
}
