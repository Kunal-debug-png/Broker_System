package com.kunal_debug_png.test_relations.Repository;

import com.kunal_debug_png.test_relations.Entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.sound.sampled.Port;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {

    Portfolio findByUserId(Long userid);

    void deleteByUserId(Long userid);


    void deleteByPortfolioIdAndStocksId(Long portfolioId, Long stockId);
    long countByPortfolioId(Long userId);
}
