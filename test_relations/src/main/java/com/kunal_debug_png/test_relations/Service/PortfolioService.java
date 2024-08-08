package com.kunal_debug_png.test_relations.Service;

import com.kunal_debug_png.test_relations.Entity.Customer;
import com.kunal_debug_png.test_relations.Entity.Portfolio;
import com.kunal_debug_png.test_relations.Entity.Stock;
import com.kunal_debug_png.test_relations.Repository.CustomerRepository;
import com.kunal_debug_png.test_relations.Repository.PortfolioRepository;
import com.kunal_debug_png.test_relations.Repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Transactional
    public boolean buy(Long userId, Long stockId) {
        Optional<Customer> buyerOpt = customerRepository.findById(userId);
        Optional<Stock> stockToBuyOpt = stockRepository.findById(stockId);

        if (buyerOpt.isPresent() && stockToBuyOpt.isPresent()) {
            Customer buyer = buyerOpt.get();
            Stock stockToBuy = stockToBuyOpt.get();

            if (buyer.getBalance() >= stockToBuy.getPrice()) {
                // Deduct the stock price from the user's balance
                buyer.setBalance(buyer.getBalance() - stockToBuy.getPrice());
                customerRepository.save(buyer); // Save updated balance

                // Retrieve or create portfolio
                Portfolio portfolio = portfolioRepository.findByUserId(userId);

                if (portfolio != null) {
                    // Portfolio exists, update it
                    List<Stock> stocks = portfolio.getStocks();
                    if (stocks == null) {
                        stocks = new ArrayList<>();
                    }
                    stocks.add(stockToBuy);
                    portfolio.setStocks(stocks);
                    portfolioRepository.save(portfolio); // Save updated portfolio
                } else {
                    // Portfolio does not exist, create a new one
                    List<Stock> stocks = new ArrayList<>();
                    stocks.add(stockToBuy);
                    Portfolio newPortfolio = new Portfolio();
                    newPortfolio.setUserId(userId);
                    newPortfolio.setStocks(stocks);
                    portfolioRepository.save(newPortfolio); // Save new portfolio
                }

                return true; // Success
            } else {
                return false; // Insufficient balance
            }
        }

        return false; // Customer or stock not found
    }
    @Transactional
    public void sellStock(Long stockId, Long userId) {
        Optional<Customer> customerOptional = customerRepository.findById(userId);
        Optional<Stock> stockOptional = stockRepository.findById(stockId);

        if (customerOptional.isPresent() && stockOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Stock stock = stockOptional.get();


            customer.setBalance(customer.getBalance() + stock.getPrice());
            customerRepository.save(customer); // Don't forget to save the updated customer

            portfolioRepository.deleteByPortfolioIdAndStocksId(userId, stockId);

            long remainingStockCount = portfolioRepository.countByPortfolioId(userId);
            if (remainingStockCount == 0) {
                portfolioRepository.deleteByUserId(userId);
            }
        } else {

            throw new EntityNotFoundException("Customer or Stock not found");
        }
    }
}
