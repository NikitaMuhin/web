package com.orange.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "credits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit {

    @Id
    private Long id;
    private Long userId;
    private Long typeId;
    private  Long currencyId;
    private Float percentage;
    private BigDecimal initialValue;
    private BigDecimal totalValue;
    private LocalDate expectedPaymentDate;
    private BigDecimal expectedPaymentValue;
    private LocalDate creditFrom;
    private  LocalDate creditTo;

    public Credit(Long userId, Long typeId, Long currencyId, Float percentage,
                  BigDecimal initialValue, BigDecimal totalValue,
                  LocalDate expectedPaymentDate, BigDecimal expectedPaymentValue, LocalDate creditFrom, LocalDate creditTo) {
        this.userId = userId;
        this.typeId = typeId;
        this.currencyId = currencyId;
        this.percentage = percentage;
        this.initialValue = initialValue;
        this.totalValue = totalValue;
        this.expectedPaymentDate = expectedPaymentDate;
        this.expectedPaymentValue = expectedPaymentValue;
        this.creditFrom = creditFrom;
        this.creditTo = creditTo;
    }

}
