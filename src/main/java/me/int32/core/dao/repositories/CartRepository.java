package me.int32.core.dao.repositories;

import me.int32.core.dao.po.CartPO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<CartPO, Long> {
    @Query("from CartPO where orderId in :orderIds")
    List<CartPO> findByOrderIds(@Param("orderIds") List<Long> orderIds);

}
