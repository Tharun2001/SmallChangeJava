drop table sc_bankaccount;
drop table sc_account;

CREATE TABLE sc_account (
    username varchar(255),
    acct_num varchar(255) NOT NULL PRIMARY KEY,
    acct_type varchar(255) NOT NULL
);

CREATE TABLE sc_bankaccount (
    acct_num varchar(255),
    bank_acctnum varchar(255) NOT NULL,
    bank_name varchar(255) NOT NULL,
    balance decimal(20,2),
    PRIMARY KEY(bank_acctnum),
    FOREIGN KEY(acct_num) REFERENCES sc_account(acct_num)
); 


INSERT INTO sc_account(USERNAME, ACCT_NUM, ACCT_TYPE) VALUES ('aryan@smallchange.com', 'ABC123', 'Brokerage');
INSERT INTO sc_bankaccount(acct_num, bank_acctnum, bank_name, balance) values('ABC123', '608502111', 'Brokerage', 1050000.0);

COMMIT;

