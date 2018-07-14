package me.int32.core.dao.repositories;

import me.int32.core.dao.po.CommodityPO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends CrudRepository<CommodityPO, Long> {

    List<CommodityPO> findAll();

    @Query(value = "from CommodityPO where name = :name and sku = :sku")
    List<CommodityPO> findByNameAndSku(@Param("name") String name, @Param("sku") String sku);

    List<CommodityPO> findAllById(List<Long> ids);
}
