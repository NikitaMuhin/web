package com.orange.web.controllers;

import com.orange.web.domain.Credit;
import com.orange.web.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/web")
public class CreditHistoryController {

    @Autowired
    private CreditRepository creditRepository;

    @GetMapping("/credit-history")
    public String creditHistoryPersonPage(@RequestParam(value = "personId", required = false) Long personId, Model model) {
        Flux<Credit> creditList = creditRepository.findAll();
        model.addAttribute("listCredits", creditList);
        return "credit_history";
    }

}
