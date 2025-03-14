package com.trade.store.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.trade.store.model.Trade;
import com.trade.store.repository.TradeRepository;

public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRejectTradeWithPastMaturityDate() {
        Trade trade = new Trade("T1", 1, "CP-1", "B1", LocalDate.now().minusDays(1), LocalDate.now(), false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> tradeService.processTrade(trade));
        assertEquals("Trade maturity date has passed!", exception.getMessage());
    }

    @Test
    void testRejectLowerVersionTrade() {
        Trade existingTrade = new Trade("T1", 2, "CP-1", "B1", LocalDate.now().plusDays(10), LocalDate.now(), false);
        when(tradeRepository.findById("T1")).thenReturn(Optional.of(existingTrade));

        Trade lowerVersionTrade = new Trade("T1", 1, "CP-1", "B1", LocalDate.now().plusDays(10), LocalDate.now(), false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> tradeService.processTrade(lowerVersionTrade));
        assertEquals("Rejected lower version trade!", exception.getMessage());
    }

    @Test
    void testReplaceSameVersionTrade() {
        Trade existingTrade = new Trade("T1", 1, "CP-1", "B1", LocalDate.now().plusDays(10), LocalDate.now(), false);
        when(tradeRepository.findById("T1")).thenReturn(Optional.of(existingTrade));

        Trade sameVersionTrade = new Trade("T1", 1, "CP-1", "B1", LocalDate.now().plusDays(10), LocalDate.now(), false);
        tradeService.processTrade(sameVersionTrade);

        verify(tradeRepository, times(1)).save(sameVersionTrade);
    }

    @Test
    void testSaveNewTrade() {
        Trade newTrade = new Trade("T2", 1, "CP-2", "B2", LocalDate.now().plusDays(10), LocalDate.now(), false);
        when(tradeRepository.findById("T2")).thenReturn(Optional.empty());

        tradeService.processTrade(newTrade);
        verify(tradeRepository, times(1)).save(newTrade);
    }
}
