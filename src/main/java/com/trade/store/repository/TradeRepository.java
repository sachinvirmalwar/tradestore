package com.trade.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trade.store.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, String> {
	Optional<Trade> findByTradeId(String tradeId);
}
