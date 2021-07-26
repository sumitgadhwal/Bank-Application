DROP TABLE IF EXISTS Users;

CREATE TABLE Users
(
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL,
    mobilenumber BIGINT NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS Accounts;

CREATE TABLE Accounts
(
    account_id INT NOT NULL AUTO_INCREMENT,
    accountnumber BIGINT NOT NULL,
    balance BIGINT NOT NULL,
    userid INT NOT NULL,
    PRIMARY KEY(account_id)
);


