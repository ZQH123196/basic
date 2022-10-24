package com.eor.jpa.repository;

import com.eor.jpa.entity.CoffeeOrder;
import com.eor.jpa.repository.base.BaseRepository;

import java.util.List;

public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);
    List<CoffeeOrder> findByItems_Name(String name);
}
