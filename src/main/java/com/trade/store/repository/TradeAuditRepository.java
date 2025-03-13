package com.trade.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trade.store.model.TradeAudit;

public interface TradeAuditRepository extends JpaRepository<TradeAudit, Long> {
    List<TradeAudit> findByTradeId(String tradeId);
}
