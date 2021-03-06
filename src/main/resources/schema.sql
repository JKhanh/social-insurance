CREATE TABLE IF NOT EXISTS enterprise(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(100) NOT NULL,
    PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS employee(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    birthday DATE NOT NULL,
    join_date DATE NOT NULL,
    position VARCHAR(100) NULL,
    enterprise_id INT NULL,
    address VARCHAR(200) NOT NULL,
    street VARCHAR(200) NOT NULL,
    salary LONG NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_employee_enterprise_idx (enterprise_id ASC) VISIBLE,
    CONSTRAINT fk_employee_enterprise
                                   FOREIGN KEY (enterprise_id)
                                   REFERENCES enterprise (id)
                                   ON DELETE SET NULL
                                   ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS insurance(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(10) NOT NULL,
    employee_id INT NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_insurance_employee_idx (employee_id ASC ) VISIBLE,
    CONSTRAINT fk_insurance_employee
                                    FOREIGN KEY (employee_id)
                                    REFERENCES employee (id)
                                    ON DELETE NO ACTION
                                    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `district` (
                            `id` char(6) COLLATE utf8_unicode_ci NOT NULL,
                            `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
                            `type` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
                            `province_id` char(6) COLLATE utf8_unicode_ci NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `province` (
                            `id` char(6) COLLATE utf8_unicode_ci NOT NULL,
                            `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
                            `type` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `ward` (
                        `id` char(6) COLLATE utf8_unicode_ci NOT NULL,
                        `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
                        `type` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
                        `district_id` char(6) COLLATE utf8_unicode_ci NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS annual_adjust(
    id INT NOT NULL AUTO_INCREMENT,
    adjustment FLOAT NOT NULL ,
    year INT NOT NULL,
    PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS min_salary(
     id INT NOT NULL AUTO_INCREMENT,
     area VARCHAR(100) NOT NULL ,
     type INT NOT NULL ,
     salary FLOAT NOT NULL ,
     PRIMARY KEY (id)
);

DELETE FROM min_salary;
ALTER TABLE min_salary AUTO_INCREMENT =1;

INSERT INTO min_salary (area, type, salary) VALUES ('V??ng 1', 1, 4420000);
INSERT INTO min_salary (area, type, salary) VALUES ('V??ng 2', 1, 3920000);
INSERT INTO min_salary (area, type, salary) VALUES ('V??ng 3', 1, 3430000);
INSERT INTO min_salary (area, type, salary) VALUES ('V??ng 4', 1, 3070000);
INSERT INTO min_salary (area, type, salary) VALUES ('V??ng 1', 2, 4729000);
INSERT INTO min_salary (area, type, salary) VALUES ('V??ng 2', 2, 4194000);
INSERT INTO min_salary (area, type, salary) VALUES ('V??ng 3', 2, 3670000);
INSERT INTO min_salary (area, type, salary) VALUES ('V??ng 4', 2, 3284000);