package me.int32.core.dao.repositories;

import me.int32.core.dao.po.OrderPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderPO, Long> {

    List<OrderPO> findAll();
}
