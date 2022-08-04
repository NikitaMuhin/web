package com.orange.web.controllers;

import com.orange.web.domain.CreditScore;
import com.orange.web.domain.PersonalData;
import com.orange.web.report.CsvCreditScoreReportService;
import com.orange.web.report.CsvPersonalDataReportService;
import com.orange.web.server.CreditScoreServe;
import com.orange.web.server.PersonalDataServer;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequestMapping("/web")
public class ReportController {

    @Autowired
    private CreditScoreServe creditScoreServer;

    @Autowired
    private PersonalDataServer personalDataServer;
    @Autowired
    private CsvCreditScoreReportService csvWriterService;
    @Autowired
    private CsvPersonalDataReportService csvPersonalDataReportService;


    @GetMapping("/credit-score-report")
    @ResponseBody
    public ResponseEntity<Mono<Resource>> creditScoreReport(@RequestParam(value = "creditScore", required = false) Long id) {
        String fileName = String.format("CreditScoreReport.csv", RandomStringUtils.randomAlphabetic(10));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .body(creditScoreServer.findById(id).flatMap(this::creditScoreReportResponse));
    }

    private Mono<Resource> creditScoreReportResponse(CreditScore creditScore) {
        return csvWriterService.generateCsv(creditScore)
                .flatMap(x -> {
                    Resource resource = new InputStreamResource(x);
                    return Mono.just(resource);
                });
    }


    @GetMapping("/personal-data-report")
    @ResponseBody
    public ResponseEntity<Mono<Resource>> personalDataReport() {
        String fileName = String.format("PersonalDataReport.csv", RandomStringUtils.randomAlphabetic(10));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .body(personalDataReportResponse(personalDataServer.findAll()));
    }

    private Mono<Resource> personalDataReportResponse(Flux<PersonalData> personalDataList) {
        return csvPersonalDataReportService.generateCsv(personalDataList)
                .flatMap(x -> {
                    Resource resource = new InputStreamResource(x);
                    return Mono.just(resource);
                });
    }

}
