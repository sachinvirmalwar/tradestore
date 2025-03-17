package com.trade.store.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trade.store.model.Trade;
import com.trade.store.repository.TradeAuditRepository;
import com.trade.store.repository.TradeRepository;

@ExtendWith(MockitoExtension.class)
class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private TradeAuditRepository tradeAuditRepository;  // Ensure this is mocked

    @InjectMocks
    private TradeService tradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Ensures mocks are initialized
    }

    @Test
    void shouldSaveValidTrade() {
        Trade trade = new Trade("T1", 1, "CP-4", "B1", LocalDate.now().plusDays(10), LocalDate.now(), false);

        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);

        Trade savedTrade = tradeService.processTrade(trade);

        assertNotNull(savedTrade);

        verify(tradeRepository, times(1)).save(trade);
        verify(tradeAuditRepository, times(1)).save(any());  // Ensure audit is also saved
    }
}
