package com.trade.store.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "trade_audit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeAudit {
    private String tradeId;
    private Integer version;
    private String action; // "CREATED", "UPDATED", "REJECTED"
    private LocalDateTime timestamp = LocalDateTime.now();
}

