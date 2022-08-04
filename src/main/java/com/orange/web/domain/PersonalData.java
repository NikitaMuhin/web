package com.orange.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Table(name = "personal_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalData {
    @Id
    private Long id;
    private String firstName;
    private String lastName;

    private Integer age;
    private String idnp;
    private String nationality;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate availabilityPassport;
    private String sex;
    private String civilState;
    private Integer childrenNumber;
    private Integer familyMembersNumber;
    private String homePlace;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate homePlaceFrom;
    private String educationLevel;
    private  String currentMobileNumber;
    private String email;
    private Integer salaryMdl;
    private Integer otherProfits;
    private String activitySector;
    private String jobDescription;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate jobActivityFrom;

    public PersonalData(String firstName, String lastName, Integer age, String idnp, String nationality,
                        LocalDate availabilityPassport, String sex, String civilState, Integer childrenNumber,
                        Integer familyMembersNumber, String homePlace, LocalDate homePlaceFrom, String educationLevel, String currentMobileNumber, String email,
                        Integer salaryMdl, Integer otherProfits, String activitySector, String jobDescription, LocalDate jobActivityFrom) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.idnp = idnp;
        this.nationality = nationality;
        this.availabilityPassport = availabilityPassport;
        this.sex = sex;
        this.civilState = civilState;
        this.childrenNumber = childrenNumber;
        this.familyMembersNumber = familyMembersNumber;
        this.homePlace = homePlace;
        this.homePlaceFrom = homePlaceFrom;
        this.educationLevel = educationLevel;
        this.currentMobileNumber = currentMobileNumber;
        this.email = email;
        this.salaryMdl = salaryMdl;
        this.otherProfits = otherProfits;
        this.activitySector = activitySector;
        this.jobDescription = jobDescription;
        this.jobActivityFrom = jobActivityFrom;
    }
}
