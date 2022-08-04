package com.orange.web.report;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import com.orange.web.domain.CreditScore;
import com.orange.web.utils.ByteArrayInOutStream;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


@Service
public class CsvCreditScoreReportService {

    public Mono<ByteArrayInputStream> generateCsv(CreditScore creditScore){
        String[] columns = {"scoreByAge", "scoreBySex", "scoreByCivilState",
                "scoreByFamilyMembersNumber", "scoreByHomePlaceFrom", "scoreByEducationLevel",
                "scoreByContactInformation", "scoreBySalaryNet", "scoreByIncomeOtherSources",
                "scoreByActivitySector", "scoreByJobActivityFrom",
                "scoreByDelaysDay", "scoreByDebtAmountMDL"};

        return Mono.fromCallable(() -> {
            try {
                ByteArrayInOutStream stream = new ByteArrayInOutStream();
                OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
                CSVWriter writer = new CSVWriter(streamWriter);

                ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
                mappingStrategy.setType(CreditScore.class);
                mappingStrategy.setColumnMapping(columns);
                writer.writeNext(columns);

                StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .withMappingStrategy(mappingStrategy)
                        .withSeparator(',')
                        .build();

                beanToCsv.write(creditScore);
                streamWriter.flush();
                return stream.getInputStream();
            }
            catch (CsvException | IOException e) {
                throw new RuntimeException(e);
            }

        }).subscribeOn(Schedulers.boundedElastic());

    }
}
