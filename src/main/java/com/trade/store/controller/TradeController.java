package com.trade.store.controller;

import com.trade.store.model.Trade;
import com.trade.store.service.TradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trades")
public class TradeController {
	@Autowired
	private TradeService ts; 
    private final KafkaTemplate<String, Trade> kafkaTemplate;

    public TradeController(KafkaTemplate<String, Trade> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public ResponseEntity<String> sendTrade(@RequestBody Trade trade) {
    	ts.processTrade(trade); 
        kafkaTemplate.send("trade-store-topic", trade);
        return ResponseEntity.ok("Trade sent for processing.");
    }
}
	