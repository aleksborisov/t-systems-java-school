SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mars_army_store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mars_army_store` DEFAULT CHARACTER SET utf8;
USE `mars_army_store`;

-- -----------------------------------------------------
-- Table `mars_army_store`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mars_army_store`.`customers`
(
    `customer_id`   BIGINT(10)   NOT NULL AUTO_INCREMENT,
    `first_name`    VARCHAR(50)  NOT NULL,
    `last_name`     VARCHAR(50)  NOT NULL,
    `date_of_birth` DATE         NOT NULL,
    `email`         VARCHAR(50)  NOT NULL,
    `password`      VARCHAR(255) NOT NULL,
    PRIMARY KEY (`customer_id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mars_army_store`.`payment_methods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mars_army_store`.`payment_methods`
(
    `payment_method_id` BIGINT(10)  NOT NULL,
    `method`            VARCHAR(20) NOT NULL,
    PRIMARY KEY (`payment_method_id`),
    UNIQUE INDEX `method_UNIQUE` (`method` ASC) VISIBLE
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mars_army_store`.`delivery_methods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mars_army_store`.`delivery_methods`
(
    `delivery_method_id` BIGINT(10)  NOT NULL,
    `method`             VARCHAR(20) NOT NULL,
    PRIMARY KEY (`delivery_method_id`),
    UNIQUE INDEX `method_UNIQUE` (`method` ASC) VISIBLE
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mars_army_store`.`payment_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mars_army_store`.`payment_statuses`
(
    `payment_status_id` BIGINT(10)  NOT NULL,
    `status`            VARCHAR(20) NOT NULL,
    PRIMARY KEY (`payment_status_id`),
    UNIQUE INDEX `status_UNIQUE` (`status` ASC) VISIBLE
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mars_army_store`.`order_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mars_army_store`.`order_statuses`
(
    `order_status_id` BIGINT(10)  NOT NULL,
    `status`          VARCHAR(20) NOT NULL,
    PRIMARY KEY (`order_status_id`),
    UNIQUE INDEX `status_UNIQUE` (`status` ASC) VISIBLE
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mars_army_store`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mars_army_store`.`orders`
(
    `order_id`           BIGINT(10)   NOT NULL AUTO_INCREMENT,
    `customer_id`        BIGINT(10)   NOT NULL,
    `payment_method_id`  BIGINT(10)   NOT NULL,
    `delivery_method_id` BIGINT(10)   NOT NULL,
    `payment_status_id`  BIGINT(10)   NOT NULL,
    `order_status_id`    BIGINT(10)   NOT NULL,
    `address`            VARCHAR(255) NOT NULL,
    `total`              INT          NOT NULL,
    `date_of_sale`       DATE         NOT NULL,
    PRIMARY KEY (`order_id`),
    INDEX `customer_in_order` (`customer_id` ASC) VISIBLE,
    INDEX `payment_method_in_order_idx` (`payment_method_id` ASC) VISIBLE,
    INDEX `delivery_method_in_order_idx` (`delivery_method_id` ASC) VISIBLE,
    INDEX `payment_status_in_order_idx` (`payment_status_id` ASC) VISIBLE,
    INDEX `order_status_in_order_idx` (`order_status_id` ASC) VISIBLE,
    CONSTRAINT `customer_in_order`
        FOREIGN KEY (`customer_id`)
            REFERENCES `mars_army_store`.`customers` (`customer_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `payment_method_in_order`
        FOREIGN KEY (`payment_method_id`)
            REFERENCES `mars_army_store`.`payment_methods` (`payment_method_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `delivery_method_in_order`
        FOREIGN KEY (`delivery_method_id`)
            REFERENCES `mars_army_store`.`delivery_methods` (`delivery_method_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `payment_status_in_order`
        FOREIGN KEY (`payment_status_id`)
            REFERENCES `mars_army_store`.`payment_statuses` (`payment_status_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `order_status_in_order`
        FOREIGN KEY (`order_status_id`)
            REFERENCES `mars_army_store`.`order_statuses` (`order_status_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mars_army_store`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mars_army_store`.`addresses`
(
    `address_id`  BIGINT(10)   NOT NULL AUTO_INCREMENT,
    `customer_id` BIGINT(10)   NOT NULL,
    `country`     VARCHAR(50)  NOT NULL,
    `city`        VARCHAR(255) NOT NULL,
    `zip_code`    VARCHAR(9)   NOT NULL,
    `street`      VARCHAR(255) NOT NULL,
    `building`    INT          NOT NULL,
    `apartment`   INT          NOT NULL,
    PRIMARY KEY (`address_id`),
    INDEX `customer_in_address` (`customer_id` ASC) VISIBLE,
    CONSTRAINT `customer_in_address`
        FOREIGN KEY (`customer_id`)
            REFERENCES `mars_army_store`.`customers` (`customer_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mars_army_store`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mars_army_store`.`categories`
(
    `category_id` BIGINT(10)  NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(50) NOT NULL,
    PRIMARY KEY (`category_id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mars_army_store`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mars_army_store`.`products`
(
    `product_upc` BIGINT(10)   NOT NULL,
    `category_id` BIGINT(10)   NOT NULL,
    `name`        VARCHAR(255) NOT NULL,
    `price`       INT          NOT NULL,
    `brand`       VARCHAR(20)  NOT NULL,
    `color`       VARCHAR(20)  NOT NULL,
    `weight`      INT          NOT NULL,
    `height`      INT          NOT NULL,
    `width`       INT          NOT NULL,
    `depth`       INT          NOT NULL,
    `in_stock`    INT          NOT NULL,
    `deleted`     TINYINT      NOT NULL,
    PRIMARY KEY (`product_upc`),
    INDEX `category_in_product` (`category_id` ASC) VISIBLE,
    CONSTRAINT `category_in_product`
        FOREIGN KEY (`category_id`)
            REFERENCES `mars_army_store`.`categories` (`category_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mars_army_store`.`products_in_orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mars_army_store`.`products_in_orders`
(
    `products_in_order_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
    `product_upc`          BIGINT(10) NOT NULL,
    `order_id`             BIGINT(10) NOT NULL,
    `number_of_products`   INT        NOT NULL,
    PRIMARY KEY (`products_in_order_id`),
    INDEX `product_in_order` (`product_upc` ASC) VISIBLE,
    INDEX `order_in_product` (`order_id` ASC) VISIBLE,
    CONSTRAINT `product_in_order`
        FOREIGN KEY (`product_upc`)
            REFERENCES `mars_army_store`.`products` (`product_upc`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `order_in_product`
        FOREIGN KEY (`order_id`)
            REFERENCES `mars_army_store`.`orders` (`order_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mars_army_store`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mars_army_store`.`roles`
(
    `role_id` BIGINT(10)  NOT NULL,
    `name`    VARCHAR(20) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mars_army_store`.`roles_of_customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mars_army_store`.`roles_of_customers`
(
    `roles_of_customer_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
    `customer_id`          BIGINT(10) NOT NULL,
    `role_id`              BIGINT(10) NOT NULL,
    PRIMARY KEY (`roles_of_customer_id`),
    INDEX `role_of_customer` (`role_id` ASC) INVISIBLE,
    INDEX `customer_of_role` (`customer_id` ASC) INVISIBLE,
    CONSTRAINT `role_of_customer`
        FOREIGN KEY (`role_id`)
            REFERENCES `mars_army_store`.`roles` (`role_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `customer_of_role`
        FOREIGN KEY (`customer_id`)
            REFERENCES `mars_army_store`.`customers` (`customer_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Fill table `mars_army_store`.`roles`
-- -----------------------------------------------------
INSERT INTO mars_army_store.roles (role_id, name)
VALUES (0, 'ROLE_ADMIN');
INSERT INTO mars_army_store.roles (role_id, name)
VALUES (1, 'ROLE_USER');

-- -----------------------------------------------------
-- Fill table `mars_army_store`.`payment_methods`
-- -----------------------------------------------------
INSERT INTO mars_army_store.payment_methods (payment_method_id, method)
VALUES (0, 'CASH');
INSERT INTO mars_army_store.payment_methods (payment_method_id, method)
VALUES (1, 'CARD');

-- -----------------------------------------------------
-- Fill table `mars_army_store`.`delivery_methods`
-- -----------------------------------------------------
INSERT INTO mars_army_store.delivery_methods (delivery_method_id, method)
VALUES (0, 'RUSSIAN_POST');
INSERT INTO mars_army_store.delivery_methods (delivery_method_id, method)
VALUES (1, 'DHL');

-- -----------------------------------------------------
-- Fill table `mars_army_store`.`payment_statuses`
-- -----------------------------------------------------
INSERT INTO mars_army_store.payment_statuses (payment_status_id, status)
VALUES (0, 'AWAITING_PAYMENT');
INSERT INTO mars_army_store.payment_statuses (payment_status_id, status)
VALUES (1, 'PAID');

-- -----------------------------------------------------
-- Fill table `mars_army_store`.`order_statuses`
-- -----------------------------------------------------
INSERT INTO mars_army_store.order_statuses (order_status_id, status)
VALUES (0, 'AWAITING_PAYMENT');
INSERT INTO mars_army_store.order_statuses (order_status_id, status)
VALUES (1, 'AWAITING_SHIPMENT');
INSERT INTO mars_army_store.order_statuses (order_status_id, status)
VALUES (2, 'SHIPPED');
INSERT INTO mars_army_store.order_statuses (order_status_id, status)
VALUES (3, 'DELIVERED');

-- -----------------------------------------------------
-- Fill table `mars_army_store`.`categories`
-- -----------------------------------------------------
INSERT INTO mars_army_store.categories (name)
VALUES ('Other Equipment');
INSERT INTO mars_army_store.categories (name)
VALUES ('Chest Rigs & Vests');
INSERT INTO mars_army_store.categories (name)
VALUES ('Body Armor & Plate Carriers');
INSERT INTO mars_army_store.categories (name)
VALUES ('Belts & Modular Panels');
INSERT INTO mars_army_store.categories (name)
VALUES ('Backpacks & Bags');
INSERT INTO mars_army_store.categories (name)
VALUES ('Pouches');
INSERT INTO mars_army_store.categories (name)
VALUES ('Holsters');
INSERT INTO mars_army_store.categories (name)
VALUES ('Slings & Straps');
INSERT INTO mars_army_store.categories (name)
VALUES ('Sitting Mats');
INSERT INTO mars_army_store.categories (name)
VALUES ('Wallets');

-- -----------------------------------------------------
-- Fill table `mars_army_store`.`products`
-- -----------------------------------------------------
INSERT INTO mars_army_store.products (product_upc, category_id, name, price, brand, color, weight, height, width, depth,
                                      in_stock, deleted)
VALUES (766871496808, 2, 'Modernized Vest 6SH117', 94, 'Techinkom', 'EMR Digital Flora', 930, 0, 0, 0, 5, false);

INSERT INTO mars_army_store.products (product_upc, category_id, name, price, brand, color, weight, height, width, depth,
                                      in_stock, deleted)
VALUES (766871499069, 3, 'Heavy Weight Training Plate', 49, 'Techinkom', 'Black', 2600, 310, 270, 15, 10, false);

INSERT INTO mars_army_store.products (product_upc, category_id, name, price, brand, color, weight, height, width, depth,
                                      in_stock, deleted)
VALUES (766871496891, 4, 'Battle Belt Size 1', 39, 'Techinkom', 'VSR-98 Flora', 315, 110, 920, 10, 2, false);

INSERT INTO mars_army_store.products (product_upc, category_id, name, price, brand, color, weight, height, width, depth,
                                      in_stock, deleted)
VALUES (766871498864, 5, 'Patrol Backpack 25L', 79, 'Techinkom', 'Olive', 975, 440, 480, 150, 3, false);

INSERT INTO mars_army_store.products (product_upc, category_id, name, price, brand, color, weight, height, width, depth,
                                      in_stock, deleted)
VALUES (766871497072, 6, 'Pouch for 2 30rds Rifle Mags', 14, 'Techinkom', 'EMR Digital Flora', 120, 210, 75, 65, 4,
        false);

INSERT INTO mars_army_store.products (product_upc, category_id, name, price, brand, color, weight, height, width, depth,
                                      in_stock, deleted)
VALUES (766871498130, 6, 'Medic Pouch', 19, 'Techinkom', 'VSR-98 Flora', 150, 180, 145, 90, 6, false);

INSERT INTO mars_army_store.products (product_upc, category_id, name, price, brand, color, weight, height, width, depth,
                                      in_stock, deleted)
VALUES (766871498659, 6, 'Dump Pouch Transformer', 22, 'Techinkom', 'Olive', 140, 240, 200, 85, 0, false);

INSERT INTO mars_army_store.products (product_upc, category_id, name, price, brand, color, weight, height, width, depth,
                                      in_stock, deleted)
VALUES (766871498376, 7, 'Right Tactical Holster', 32, 'Techinkom', 'EMR Digital Flora', 260, 340, 150, 35, 8, false);

INSERT INTO mars_army_store.products (product_upc, category_id, name, price, brand, color, weight, height, width, depth,
                                      in_stock, deleted)
VALUES (766871498086, 8, 'Two Point Gun Sling', 19, 'Techinkom', 'Olive', 120, 1500, 40, 10, 9, false);

INSERT INTO mars_army_store.products (product_upc, category_id, name, price, brand, color, weight, height, width, depth,
                                      in_stock, deleted)
VALUES (766871498499, 9, 'Sitting Mat', 18, 'Techinkom', 'VSR-98 Flora', 170, 290, 350, 20, 10, false);

INSERT INTO mars_army_store.products (product_upc, category_id, name, price, brand, color, weight, height, width, depth,
                                      in_stock, deleted)
VALUES (766871500345, 10, 'Wallet', 19, 'Techinkom', 'EMR Digital Flora', 50, 95, 130, 15, 7, false);

-- -----------------------------------------------------
-- Fill table `mars_army_store`.`customers`
-- -----------------------------------------------------
INSERT INTO mars_army_store.customers (first_name, last_name, date_of_birth, email, password)
VALUES ('John', 'Smith', '1970-01-01', 'smith@gmail.com', '$2a$10$mT.pnD6b1UYoGJqMpyaKoeY2pgZsRnTsQZZ1bKMrAv18FaUZFqsme');
INSERT INTO mars_army_store.customers (first_name, last_name, date_of_birth, email, password)
VALUES ('Antoni', 'Gaudi', '1852-06-25', 'gaudi@gmail.com', '$2a$10$JJEIgUaK7H7RoBiFFtk9V.kVGbLHl1mkv8oM42XUy0HiRKL.v2cca');
INSERT INTO mars_army_store.customers (first_name, last_name, date_of_birth, email, password)
VALUES ('Test', 'Testovich', '1970-01-01', 'test@gmail.com', '$2a$10$v.dF4pGCp/2M2yy9Y58W0eIp8KM.qi.hNUr/p6YPnOi8Pio3XFprK');

-- -----------------------------------------------------
-- Fill table `mars_army_store`.`addresses`
-- -----------------------------------------------------
INSERT INTO mars_army_store.addresses (customer_id, country, city, zip_code, street, building, apartment)
VALUES (1, 'USA', 'New York City', 101180114, 'West 34th Street', 20, 666);
INSERT INTO mars_army_store.addresses (customer_id, country, city, zip_code, street, building, apartment)
VALUES (2, 'Spain', 'Barcelona', 08013, 'Carrer Mallorca', 401, 3);
INSERT INTO mars_army_store.addresses (customer_id, country, city, zip_code, street, building, apartment)
VALUES (3, 'Russia', 'Moscow', 103132, 'Ilinka Street', 23, 1);
INSERT INTO mars_army_store.addresses (customer_id, country, city, zip_code, street, building, apartment)
VALUES (3, 'Italy', 'Roma', 00184, 'Piazza del Colosseo', 1, 1);
INSERT INTO mars_army_store.addresses (customer_id, country, city, zip_code, street, building, apartment)
VALUES (3, 'France', 'Nice', 06364, 'Rue de le Hotel de ville', 5, 7);

-- -----------------------------------------------------
-- Fill table `mars_army_store`.`roles_of_customers`
-- -----------------------------------------------------
INSERT INTO mars_army_store.roles_of_customers (customer_id, role_id)
VALUES (1, 0);
INSERT INTO mars_army_store.roles_of_customers (customer_id, role_id)
VALUES (2, 1);
INSERT INTO mars_army_store.roles_of_customers (customer_id, role_id)
VALUES (3, 1);

-- -----------------------------------------------------
-- Fill table `mars_army_store`.`orders`
-- -----------------------------------------------------
INSERT INTO mars_army_store.orders (customer_id, payment_method_id, delivery_method_id, payment_status_id,
                                    order_status_id, address, total, date_of_sale)
VALUES (3, 0, 0, 0, 0, '1 Ilinka Street 23, Moscow, Russia, 103132', 99, '2020-08-01');
INSERT INTO mars_army_store.orders (customer_id, payment_method_id, delivery_method_id, payment_status_id,
                                    order_status_id, address, total, date_of_sale)
VALUES (3, 1, 1, 1, 1, '1 Piazza del Colosseo 1, Roma, Italy, 00184', 741, '2020-08-03');
INSERT INTO mars_army_store.orders (customer_id, payment_method_id, delivery_method_id, payment_status_id,
                                    order_status_id, address, total, date_of_sale)
VALUES (3, 0, 1, 1, 2, '5 Rue de le Hotel de ville 7, Nice, France, 06364', 456, '2020-08-04');

-- -----------------------------------------------------
-- Fill table `mars_army_store`.`products_in_orders`
-- -----------------------------------------------------
INSERT INTO mars_army_store.products_in_orders (product_upc, order_id, number_of_products)
VALUES (766871496808, 1, 5);
INSERT INTO mars_army_store.products_in_orders (product_upc, order_id, number_of_products)
VALUES (766871499069, 1, 7);
INSERT INTO mars_army_store.products_in_orders (product_upc, order_id, number_of_products)
VALUES (766871496891, 1, 3);
INSERT INTO mars_army_store.products_in_orders (product_upc, order_id, number_of_products)
VALUES (766871498864, 2, 2);
INSERT INTO mars_army_store.products_in_orders (product_upc, order_id, number_of_products)
VALUES (766871497072, 2, 8);
INSERT INTO mars_army_store.products_in_orders (product_upc, order_id, number_of_products)
VALUES (766871498130, 2, 1);
INSERT INTO mars_army_store.products_in_orders (product_upc, order_id, number_of_products)
VALUES (766871498659, 3, 4);
INSERT INTO mars_army_store.products_in_orders (product_upc, order_id, number_of_products)
VALUES (766871498376, 3, 6);
INSERT INTO mars_army_store.products_in_orders (product_upc, order_id, number_of_products)
VALUES (766871498086, 3, 9);