package com.orange.web.controllers;

import com.orange.web.repositories.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class AllPersonController {

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @GetMapping("/all-person")
    public String allPersonPage(Model model) {
        model.addAttribute("listPerson", personalDataRepository.findAll());
        return "all_person";
    }
}
