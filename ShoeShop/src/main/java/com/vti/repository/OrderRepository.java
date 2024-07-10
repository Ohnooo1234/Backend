package com.vti.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

import com.vti.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    public void deleteByIdIn(List<Integer> ids);
    public List<Order> findByUser_Id(Integer id);
}


