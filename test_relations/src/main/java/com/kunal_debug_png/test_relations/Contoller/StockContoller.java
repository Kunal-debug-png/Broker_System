package com.kunal_debug_png.test_relations.Contoller;

import com.kunal_debug_png.test_relations.Entity.Stock;
import com.kunal_debug_png.test_relations.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockContoller {

    @Autowired
    StockService stockService;
    @GetMapping("/stocks")
    private List<Stock> getAllStocks(){
        return stockService.getAllStocks();
    }

    @PostMapping("/buy/{stockid}/{userid}")
    private String buyStock(@PathVariable Long stockid, @PathVariable Long userid){
        if(stockService.buyStock(stockid, userid)) return "Success";
        else return "Could not buy";
    }
    @PostMapping("/sell/{stockid}/{userid}")
    public String sellStock(@PathVariable Long stockid,@PathVariable Long userid){
        if(stockService.sellStock(stockid,userid)) return "Success";
        else return "Cannot sell";
    }

    @PostMapping("/stocks")
    private String addStock(@RequestBody Stock stock)
    {
        return stockService.addStock(stock);
    }
}
