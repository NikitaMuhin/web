package com.orange.web.repositories;


import com.orange.web.domain.Currency;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CurrencyRepository extends R2dbcRepository<Currency, Long> {

}
