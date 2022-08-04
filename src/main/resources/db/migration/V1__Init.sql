DROP TABLE IF EXISTS personal_data;
CREATE TABLE personal_data
(
    id                    SERIAL PRIMARY KEY,
    first_name            VARCHAR(255),
    last_name             VARCHAR(255),
    age                   INT,
    idnp                  VARCHAR(13),
    nationality           VARCHAR(255),
    availability_passport DATE,
    sex                   VARCHAR(255),
    civil_state           VARCHAR(255),
    children_number       SMALLINT,
    family_members_number SMALLINT,
    home_place            VARCHAR(255),
    home_place_from       DATE,
    education_level       VARCHAR(255),
    current_mobile_number VARCHAR(255),
    email                 VARCHAR(255),
    salary_mdl            INT,
    other_profits         INT,
    activity_sector       VARCHAR(255),
    job_description       VARCHAR(255),
    job_activity_from     DATE
);
--
DROP TABLE IF EXISTS credit_types;
CREATE TABLE credit_types
(
    id          SERIAL PRIMARY KEY,
    description VARCHAR(255)
);
--
DROP TABLE IF EXISTS currencies;
CREATE TABLE currencies
(
    id   SERIAL PRIMARY KEY,
    name varchar(255),
    code varchar(3)
);
--
DROP TABLE IF EXISTS credits;
CREATE TABLE credits
(
    id                     SERIAL PRIMARY KEY,
    user_id                INT NOT NULL,
    type_id                INT NOT NULL,
    currency_id            INT NOT NULL,
    percentage             decimal,
    initial_value          NUMERIC,
    total_value            NUMERIC,
    expected_payment_date  DATE,
    expected_payment_value NUMERIC,
    credit_from            DATE,
    credit_to              DATE,
    FOREIGN KEY (user_id) REFERENCES personal_data (id),
    FOREIGN KEY (type_id) REFERENCES credit_types (id),
    FOREIGN KEY (currency_id) REFERENCES currencies (id)
);
--
DROP TABLE IF EXISTS repayment_history;
CREATE TABLE repayment_history
(
    id           SERIAL PRIMARY KEY,
    credit_id    INT NOT NULL,
    invested     NUMERIC,
    indebtedness NUMERIC,
    payment_date DATE,
    FOREIGN KEY (credit_id) REFERENCES credits (id)
);
--
-- DROP TABLE IF EXISTS usr;
-- CREATE TABLE usr
-- (
--     id       BIGSERIAL PRIMARY KEY,
--     username VARCHAR(64),
--     password VARCHAR(64),
--     role     VARCHAR(64)
-- );
