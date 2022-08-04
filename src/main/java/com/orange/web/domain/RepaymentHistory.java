package com.orange.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "repayment_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentHistory {

    @Id
    private Long id;
    private Integer creditId;
    private BigDecimal invested;
    private BigDecimal indebtedness;
    private Date paymentDate;

    public RepaymentHistory(Integer creditId, BigDecimal invested, BigDecimal indebtedness, Date paymentDate) {
        this.creditId = creditId;
        this.invested = invested;
        this.indebtedness = indebtedness;
        this.paymentDate = paymentDate;
    }
}
