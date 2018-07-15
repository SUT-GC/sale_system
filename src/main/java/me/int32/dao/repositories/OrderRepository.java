package me.int32.dao.repositories;

import me.int32.dao.po.OrderPO;
import me.int32.service.bo.OrderBO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderPO, Long> {

    @Query("from OrderPO where dataStatus = 1 order by createdAt desc")
    List<OrderPO> findAll();

    OrderPO save(OrderPO orderPO);
}
