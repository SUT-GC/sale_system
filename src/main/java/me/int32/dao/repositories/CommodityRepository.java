package me.int32.dao.repositories;

import me.int32.dao.po.CommodityPO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends CrudRepository<CommodityPO, Long> {

    @Query(value = "from CommodityPO where dataStatus = 1")
    List<CommodityPO> findAll();

    @Query(value = "from CommodityPO where name = :name and sku = :sku and dataStatus = 1")
    List<CommodityPO> findByNameAndSku(@Param("name") String name, @Param("sku") String sku);

    @Query(value = "from CommodityPO where id in :ids and dataStatus = 1")
    List<CommodityPO> findAllByIds(@Param("ids") List<Long> ids);

    CommodityPO save(CommodityPO commodityPO);
}
