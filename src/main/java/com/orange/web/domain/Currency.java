package com.orange.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("currencies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency  {

    @Id
    private  Long id;
    private String name;
    private  String code;

    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
