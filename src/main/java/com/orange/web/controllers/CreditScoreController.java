package com.orange.web.controllers;


import com.orange.web.domain.CreditScore;
import com.orange.web.domain.PersonalData;
import com.orange.web.kafka.KafkaProducer;
import com.orange.web.repositories.CreditTypeRepository;
import com.orange.web.repositories.PersonalDataRepository;
import com.orange.web.server.CreditScoreServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("/web")
public class CreditScoreController {

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @Autowired
    private CreditTypeRepository creditTypeRepository;

    @Autowired
    private CreditScoreServe creditScoreServer;

    @GetMapping("/credit-scoring")
    public String  creditScoringPersonPage(@RequestParam(value = "personId", required = false) Long personId, Model model) throws Exception {
        Mono<CreditScore> creditScoreMono = sendMessage(personId);
        model.addAttribute("personalData", personalDataRepository.findById(personId));
        model.addAttribute("creditScore", creditScoreMono);
        creditScoreMono.map(c -> {
            model.addAttribute("creditScoreByPersonalDate", c.getCreditScoreByPersonalDate());
            model.addAttribute("creditScoreBySourcesIncome", c.getCreditScoreBySourcesIncome());
            model.addAttribute("creditScoreBySectorActivity", c.getCreditScoreBySectorActivity());
            model.addAttribute("creditScoreByLoanRepaymentHistory", c.getCreditScoreByLoanRepaymentHistory());
            model.addAttribute("generalCreditScore", c.getGeneralCreditScore());
            return c;
        }).subscribe();

        return "credit_scoring";
    }

    private Mono<CreditScore> sendMessage(Long personId) throws InterruptedException {
        Mono<PersonalData> personalDataMono = personalDataRepository.findById(personId);
        KafkaProducer producer = new KafkaProducer("localhost:9092");
        CountDownLatch latch = new CountDownLatch(1);
        personalDataMono.doOnNext(p-> {
            try {
                producer.sendMessage("my-topic", p, latch);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).subscribe();
        latch.await(1000, TimeUnit.SECONDS);

        return creditScoreServer.findById(personId);
    }

}
