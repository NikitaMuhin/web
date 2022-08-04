package com.orange.web.server;

import com.orange.web.domain.CreditScore;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class CreditScoreServe {

    private Map<Long, Mono<CreditScore>> monoMap = new HashMap();

    public void save(Long id, Mono<CreditScore> creditScoreMono) {
        monoMap.put(id, creditScoreMono);
    }

    public Mono<CreditScore> findById(Long id) {
        return monoMap.get(id);
    }

}
