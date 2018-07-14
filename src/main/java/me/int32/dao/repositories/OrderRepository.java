package me.int32.dao.repositories;

import me.int32.dao.po.OrderPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderPO, Long> {

    List<OrderPO> findAll();
}
