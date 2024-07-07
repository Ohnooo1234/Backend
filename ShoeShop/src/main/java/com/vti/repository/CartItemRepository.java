package com.vti.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>, JpaSpecificationExecutor<CartItem> {

    public void deleteByIdIn(List<Integer> ids);
    
    public List<CartItem> findByProduct_Id(Integer id);
}
