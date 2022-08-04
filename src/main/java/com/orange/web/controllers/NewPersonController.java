package com.orange.web.controllers;

import com.orange.web.domain.PersonalData;
import com.orange.web.repositories.PersonalDataRepository;
import com.orange.web.server.PersonalDataServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/web")
public class NewPersonController {

    @Autowired
    private PersonalDataServer personalDataServer;

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @GetMapping("/new")
    public String newPersonPage(Model model) {
        model.addAttribute("personalData", new PersonalData());
        return "new_person";
    }

    @PostMapping("/new")
    public Mono<String> savePersonalData(@ModelAttribute("personalData") Mono<PersonalData> personalData) {
        return  personalDataRepository.save(personalData.block()).then(Mono.just("new_person"));
    }
}
