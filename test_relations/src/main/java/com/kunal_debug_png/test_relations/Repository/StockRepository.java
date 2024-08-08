package com.kunal_debug_png.test_relations.Repository;

import com.kunal_debug_png.test_relations.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {


}
