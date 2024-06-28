-- Drop the database if it already exists
DROP DATABASE IF EXISTS TestingSystem;

-- Create database
CREATE DATABASE IF NOT EXISTS TestingSystem;
USE TestingSystem;

-- Create table user
DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` ( 
    id              SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username        CHAR(50) NOT NULL UNIQUE,
    email           CHAR(50) NOT NULL UNIQUE,
    password       VARCHAR(800) NOT NULL,
    firstName       NVARCHAR(50) NOT NULL,
    lastName        NVARCHAR(50) NOT NULL,
    phoneNumber		INT NOT NULL UNIQUE,
    role           ENUM('Admin', 'Saler', 'Customer') DEFAULT 'Customer',
    status          TINYINT DEFAULT 0, -- 0: Not Active, 1: Active
    avatarUrl       VARCHAR(500)
);

-- Create table Registration_User_Token
DROP TABLE IF EXISTS `Registration_User_Token`;
CREATE TABLE IF NOT EXISTS `Registration_User_Token` ( 
    id              INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    token           CHAR(36) NOT NULL UNIQUE,
    user_id         SMALLINT UNSIGNED NOT NULL,
    expiryDate      DATETIME NOT NULL
);

-- Create table Reset_Password_Token
DROP TABLE IF EXISTS `Reset_Password_Token`;
CREATE TABLE IF NOT EXISTS `Reset_Password_Token` ( 
    id              INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    token           CHAR(36) NOT NULL UNIQUE,
    user_id         SMALLINT UNSIGNED NOT NULL,
    expiryDate      DATETIME NOT NULL
);

-- Create table category
DROP TABLE IF EXISTS `Category`;
CREATE TABLE IF NOT EXISTS `Category` ( 
    id              SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name            NVARCHAR(50) NOT NULL UNIQUE,
    description     VARCHAR(500)
);

-- Create table product
DROP TABLE IF EXISTS `Product`;
CREATE TABLE IF NOT EXISTS `Product`(
    id              SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name            NVARCHAR(50) NOT NULL UNIQUE KEY,
    category_id     SMALLINT UNSIGNED NOT NULL,
    number_of_products INT NOT NULL CHECK(number_of_products >= 0),
    price           FLOAT NOT NULL,
    thumbnailUrl    VARCHAR(500) DEFAULT '',
    description     VARCHAR(500) DEFAULT '',
    created_at      DATETIME DEFAULT NOW(),
    updated_at      DATETIME DEFAULT NOW(),
    FOREIGN KEY (category_id) REFERENCES `Category`(id)
);

-- Create table order
DROP TABLE IF EXISTS `Order`;
CREATE TABLE IF NOT EXISTS `Order`(
    id              INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id         SMALLINT UNSIGNED NOT NULL,
    address         VARCHAR(500) NOT NULL,
    status          ENUM('Pending', 'Processing', 'Completed', 'Cancelled') DEFAULT 'Pending',
    total_amount    FLOAT NOT NULL,
    created_at      DATETIME DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES `User`(id)
);

-- Create table order details
DROP TABLE IF EXISTS `OrderDetail`;
CREATE TABLE IF NOT EXISTS `OrderDetail`(
    id              INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    order_id        INT UNSIGNED NOT NULL,
    product_id      SMALLINT UNSIGNED NOT NULL,
    quantity        INT NOT NULL CHECK(quantity > 0),
    price           FLOAT NOT NULL,
    total_price     FLOAT AS (quantity * price) STORED,
    FOREIGN KEY (order_id) REFERENCES `Order`(id),
    FOREIGN KEY (product_id) REFERENCES `Product`(id)
);

-- Create table cart
DROP TABLE IF EXISTS `Cart`;
CREATE TABLE IF NOT EXISTS `Cart`(
    id              INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id         SMALLINT UNSIGNED NOT NULL,
    created_at      DATETIME DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES `User`(id)
);

-- Create table cart item
DROP TABLE IF EXISTS `CartItem`;
CREATE TABLE IF NOT EXISTS `CartItem`(
    id              INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    cart_id         INT UNSIGNED NOT NULL,
    product_id      SMALLINT UNSIGNED NOT NULL,
    quantity        INT NOT NULL CHECK(quantity > 0),
    FOREIGN KEY (cart_id) REFERENCES `Cart`(id),
    FOREIGN KEY (product_id) REFERENCES `Product`(id)
);

-- Create table Transaction
DROP TABLE IF EXISTS `Transaction`;
CREATE TABLE IF NOT EXISTS `Transaction`(
    id              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id         SMALLINT UNSIGNED NOT NULL,
    order_id        INT UNSIGNED NOT NULL,
    amount          FLOAT NOT NULL,
    transaction_date DATETIME DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES `User`(id),
    FOREIGN KEY (order_id) REFERENCES `Order`(id)
);

-- Create table Payment
DROP TABLE IF EXISTS `Payment`;
CREATE TABLE IF NOT EXISTS `Payment`(
    id              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    transaction_id  BIGINT UNSIGNED NOT NULL,
    payment_method  ENUM('Credit Card', 'Debit Card', 'PayPal', 'Bank Transfer') NOT NULL,
    payment_status  ENUM('Pending', 'Completed', 'Failed') DEFAULT 'Pending',
    payment_date    DATETIME DEFAULT NOW(),
    FOREIGN KEY (transaction_id) REFERENCES `Transaction`(id)
);


