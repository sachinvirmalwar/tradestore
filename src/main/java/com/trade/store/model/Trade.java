package com.trade.store.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String tradeId;

    @NotNull
    private Integer version;

    @NotNull
    private String counterPartyId;

    @NotNull
    private String bookId;

    @NotNull
    @FutureOrPresent(message = "Maturity date must be today or in the future")
    private LocalDate maturityDate;

    private LocalDate createdDate = LocalDate.now();

    private Boolean expired = false;
}


// {"tradeId":"1","version":"1","counterPartyId":"party1","bookId":"b1","maturityDate":"03/15/2025"}
