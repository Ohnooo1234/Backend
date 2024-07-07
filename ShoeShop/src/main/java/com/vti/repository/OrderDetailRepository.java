package com.vti.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>, JpaSpecificationExecutor<OrderDetail> {

    public void deleteByIdIn(List<Integer> ids);
    
    public List<OrderDetail> findByProduct_Id(Integer id);
}

