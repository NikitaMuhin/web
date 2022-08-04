INSERT INTO currencies(name, code) VALUES ('euro', 'EUR' );
INSERT INTO currencies(name, code) VALUES ('dollar', 'USD' );

INSERT INTO personal_data(first_name, last_name, age, nationality, availability_passport, sex, civil_state, children_number
, family_members_number, home_place, home_place_from, education_level, current_mobile_number, salary_mdl, activity_sector
, job_description, job_activity_from, email, idnp, other_profits) VALUES ('Marko', 'Polo', 22, 'Moldovan', to_date('01/01/2030', 'DD/MM/YYYY'), 'M'
, 'Necasatorit', 2, 5, 'Moldova', to_date('01/01/2001', 'DD/MM/YYYY'), 'Superioare', '+373696969', '10000', 'IT'
, 'Programist', to_date('01/01/2020', 'DD/MM/YYYY'), 'marko.polo@gmail.com', '2012010200112', '2500');

INSERT INTO personal_data(first_name, last_name, age, nationality, availability_passport, sex, civil_state, children_number
, family_members_number, home_place, home_place_from, education_level, current_mobile_number, salary_mdl, activity_sector
, job_description, job_activity_from, email, idnp, other_profits) VALUES ('Marko', 'Polo', 27, 'Moldovan', to_date('01/01/2030', 'DD/MM/YYYY'), 'M'
, 'Necasatorit', 2, 5, 'Moldova', to_date('01/01/2001', 'DD/MM/YYYY'), 'General', '+373696969', '10000', 'IT'
, 'Programist', to_date('01/01/2020', 'DD/MM/YYYY'), 'marko.polo@gmail.com', '2012010200114', '1000');

INSERT INTO personal_data(first_name, last_name, age, nationality, availability_passport, sex, civil_state, children_number
, family_members_number, home_place, home_place_from, education_level, current_mobile_number, salary_mdl, activity_sector
, job_description, job_activity_from, email, idnp, other_profits) VALUES ('Marko', 'Polo', 50, 'Moldovan', to_date('01/01/2030', 'DD/MM/YYYY'), 'M'
, 'Necasatorit', 2, 5, 'Moldova', to_date('01/01/2001', 'DD/MM/YYYY'), 'General', '+373696969', '10000', 'IT'
, 'Programist', to_date('01/01/2020', 'DD/MM/YYYY'), 'marko.polo@gmail.com', '2012010200117', '2000');


INSERT INTO personal_data(first_name, last_name, age, nationality, availability_passport, sex, civil_state, children_number
                         , family_members_number, home_place, home_place_from, education_level, current_mobile_number, salary_mdl, activity_sector
                         , job_description, job_activity_from, email, idnp, other_profits) VALUES ('Marko', 'Polo', 50, 'Moldovan', to_date('01/01/2030', 'DD/MM/YYYY'), 'M'
                                                                                   , 'Necasatorit', 2, 5, 'Moldova', to_date('01/01/2001', 'DD/MM/YYYY'), 'General', '+373696969', '10000', 'IT'
                                                                                   , 'Programist', to_date('01/01/2020', 'DD/MM/YYYY'), 'marko.polo@gmail.com', '2012010200118', '0');

INSERT INTO credit_types(description) values ('Ipoteca');
INSERT INTO credit_types(description) values ('Credit-auto');

INSERT INTO credits(type_id, user_id ,currency_id, initial_value, total_value, expected_payment_date, expected_payment_value , credit_from, credit_to)
VALUES (1,1, 1, 10000, 20000, to_date('01/01/2021', 'DD/MM/YYYY'), 1250 ,to_date('01/01/2021', 'DD/MM/YYYY'),to_date('01/01/2022', 'DD/MM/YYYY'));
