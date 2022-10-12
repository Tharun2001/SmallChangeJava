drop table sc_users;
drop table sc_preferences;

CREATE TABLE sc_users (
    first_name varchar(255),
    last_name varchar(255),
    date_of_birth DATE,
    email varchar(255),
    phone_number varchar(255),
    username varchar(255),
    user_password varchar(255),
    primary key(username)
);

CREATE TABLE sc_preferences (
    username varchar(255),
    purpose varchar(255),
    risk_tolerance INT,
    income_category varchar(255),
    length_of_investment varchar(255),
    FOREIGN KEY (username) REFERENCES sc_users(username)
);

INSERT INTO sc_users (
    first_name, last_name, date_of_birth, email, phone_number, username, user_password) 
    VALUES ('Aryan', 'Sinha', TO_DATE('01/03/2000', 'DD/MM/YYYY'),  'aryan@smallchange.com', '+91-9999999999', 'Aryan', 'aryan'); 

INSERT INTO sc_users (
    first_name, last_name, date_of_birth, email, phone_number, username, user_password) 
    VALUES ('Tharun', 'Bhargav Annamalai', TO_DATE('01/04/2000', 'DD/MM/YYYY'),  'tharun@smallchange.com', '+91-9999999998', 'Tharun', 'tharun'); 

INSERT INTO sc_users (
    first_name, last_name, date_of_birth, email, phone_number, username, user_password) 
    VALUES ('Akilesh', 'N S', TO_DATE('01/05/2000', 'DD/MM/YYYY'),  'akilesh@smallchange.com', '+91-9999999997', 'Akilesh', 'akilesh'); 
    
INSERT INTO sc_users (
    first_name, last_name, date_of_birth, email, phone_number, username, user_password) 
    VALUES ('Akshay', 'V Nayak', TO_DATE('01/06/2000', 'DD/MM/YYYY'),  'Akshay@smallchange.com', '+91-9999999996', 'Akshay', 'akshay');
    
INSERT INTO sc_users (
    first_name, last_name, date_of_birth, email, phone_number, username, user_password) 
    VALUES ('Sandhya', 'Ramesh', TO_DATE('01/07/2000', 'DD/MM/YYYY'),  'Sandhya@smallchange.com', '+91-9999999995', 'Sandhya', 'sandhya');


INSERT INTO sc_preferences (username, purpose, risk_tolerance, income_category, length_of_investment) VALUES
('Aryan', 'Trading', 3, 'Middle', '3 Years');

INSERT INTO sc_preferences (username, purpose, risk_tolerance, income_category, length_of_investment) VALUES
('Tharun', 'College Tuition', 3, 'Upper', '1 Year');

INSERT INTO sc_preferences (username, purpose, risk_tolerance, income_category, length_of_investment) VALUES
('Akhilesh', 'Trading', 3, 'Middle', '5 Years');

INSERT INTO sc_preferences (username, purpose, risk_tolerance, income_category, length_of_investment) VALUES
('Akshay', 'Trading', 3, 'Upper', '2 Years');

INSERT INTO sc_preferences (username, purpose, risk_tolerance, income_category, length_of_investment) VALUES
('Sandhya', 'Debt', 3, 'Middle', '7 Years');