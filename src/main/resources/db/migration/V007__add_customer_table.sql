CREATE TABLE customer_data (
    customer_data_id UUID CONSTRAINT pk_cat_customer_data PRIMARY KEY,
    account_id UUID NOT NULL UNIQUE,
    weight FLOAT,
    height FLOAT,
    day_of_birth DATE,
    phone_number VARCHAR(20),
    CONSTRAINT customer_data_fk01 FOREIGN KEY (account_id) REFERENCES account(account_id)
);