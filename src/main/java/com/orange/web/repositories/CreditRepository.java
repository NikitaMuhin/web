package com.orange.web.repositories;


import com.orange.web.domain.Credit;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CreditRepository extends R2dbcRepository<Credit, Long> {
}
