package com.kunal_debug_png.test_relations.Service;

import com.kunal_debug_png.test_relations.Entity.Customer;
import com.kunal_debug_png.test_relations.Entity.Stock;
import com.kunal_debug_png.test_relations.Repository.CustomerRepository;
import com.kunal_debug_png.test_relations.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    CustomerRepository customerRepository;

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }

    public boolean buyStock(Long stockid, Long userid) {
        boolean brought=false;
        Optional<Stock> isAvailable=stockRepository.findById(stockid);
        if(isAvailable.isPresent()){
            return brought=portfolioService.buy(userid,stockid);
        }
        return false;
    }

    public String addStock(Stock stock) {
        stockRepository.save(stock);
        return "Success";
    }

    public boolean sellStock(Long stockid, Long userid) {
        Optional<Customer> customer=customerRepository.findById(userid);
        if(customer.isPresent())portfolioService.sellStock(stockid,userid);
        else return false;
        return true;
    }
}
