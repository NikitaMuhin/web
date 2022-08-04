package com.orange.web.repositories;


import com.orange.web.domain.PersonalData;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PersonalDataRepository extends R2dbcRepository<PersonalData, Long> {

}
