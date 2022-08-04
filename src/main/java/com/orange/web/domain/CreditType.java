package com.orange.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "credit_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditType {

    @Id
    private Long id;
    private String description;

    public CreditType(String description) {
        this.description = description;
    }
}
