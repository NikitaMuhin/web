package com.orange.web.server;

import com.orange.web.domain.PersonalData;
import com.orange.web.repositories.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PersonalDataServer {
    @Autowired
    private PersonalDataRepository personalDataRepository;

    public Flux<PersonalData> findAll() {
        return personalDataRepository.findAll();
    }
}
