package com.orange.web.repositories;


import com.orange.web.domain.CreditType;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CreditTypeRepository extends R2dbcRepository<CreditType, Long> {

}
