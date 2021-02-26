CREATE DATABASE IF NOT EXISTS balanced;
USE balanced;

-- Since movie contains foreign key to directors, the movie table must be dropped before directors
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS transaction_settings;

DROP TABLE IF EXISTS categories_subcategories;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS subcategories;

DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS methods;
DROP TABLE IF EXISTS types;

CREATE TABLE accounts (
	account_id INT AUTO_INCREMENT PRIMARY KEY,
	account_name VARCHAR(50) NOT NULL UNIQUE,
    initial_balance INT NOT NULL,
    initial_balance_date DATE NOT NULL,
    is_liquid BOOLEAN NOT NULL,
    account_number VARCHAR(50),
    routing_number VARCHAR(50),
	description TEXT
);

CREATE TABLE methods (
	method_id INT AUTO_INCREMENT PRIMARY KEY,
	method_name VARCHAR(50) NOT NULL UNIQUE,
	description TEXT
);

CREATE TABLE types (
	type_id INT AUTO_INCREMENT PRIMARY KEY,
	type_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE categories (
	category_id INT AUTO_INCREMENT PRIMARY KEY,
	category_name VARCHAR(50) NOT NULL 
);

CREATE TABLE subcategories (
	subcategory_id INT AUTO_INCREMENT PRIMARY KEY,
	subcategory_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE categories_subcategories (
	id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT NOT NULL,
    subcategory_id INT NOT NULL
);

CREATE TABLE transaction_settings (
	transaction_setting_id INT AUTO_INCREMENT PRIMARY KEY,
	setting_name VARCHAR(50) NOT NULL UNIQUE,
    type_id INT,
    method_id INT,
    category_id INT,
    subcategory_id INT,
    from_account_id INT,
    to_account_id INT,
    description TEXT NOT NULL,
    is_recurring BOOLEAN NOT NULL DEFAULT false,
    is_default BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (type_id) REFERENCES types (type_id),
    FOREIGN KEY (method_id) REFERENCES methods (method_id),
    FOREIGN KEY (category_id) REFERENCES categories (category_id),
    FOREIGN KEY (subcategory_id) REFERENCES subcategories (subcategory_id),
    FOREIGN KEY (from_account_id) REFERENCES accounts (account_id),
    FOREIGN KEY (to_account_id) REFERENCES accounts (account_id)
);

CREATE TABLE transactions (
	transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    type_id INT NOT NULL,
    `date` DATE NOT NULL,
    amount INT NOT NULL,
    method_id INT NOT NULL,
    category_id INT,
    subcategory_id INT,
    from_account_id INT,
    to_account_id INT,
    description TEXT,
    tip INT NOT NULL DEFAULT 0,
    is_recurring BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (type_id) REFERENCES types (type_id),
    FOREIGN KEY (method_id) REFERENCES methods (method_id),
    FOREIGN KEY (category_id) REFERENCES categories (category_id),
    FOREIGN KEY (subcategory_id) REFERENCES subcategories (subcategory_id),
    FOREIGN KEY (from_account_id) REFERENCES accounts (account_id),
    FOREIGN KEY (to_account_id) REFERENCES accounts (account_id)
);

select * from transactions;