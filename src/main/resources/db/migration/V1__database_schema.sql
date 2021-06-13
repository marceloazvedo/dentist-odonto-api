CREATE TABLE address (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    cep VARCHAR(8) NOT NULL,
    street VARCHAR(100) NOT NULL,
    number VARCHAR(10) NOT NULL,
    city VARCHAR(255) NOT NULL,
    complement VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE contact (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    value VARCHAR(255) NOT NULL,
    type VARCHAR(40) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE dentist (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    cro VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    rg VARCHAR(20) DEFAULT NULL,
    genre VARCHAR(20) NOT NULL,
    birthDate datetime NOT NULL,
    updated_at datetime DEFAULT NULL,
    created_at datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE dentist_addresses (
    dentist_id bigint(20) NOT NULL,
    addresses_id bigint(20) NOT NULL,
    CONSTRAINT fk_dentist FOREIGN KEY (dentist_id) REFERENCES dentist(id),
    CONSTRAINT fk_addresses_id FOREIGN KEY (addresses_id) REFERENCES address(id)
);

CREATE TABLE dentist_contacts (
    dentist_id bigint(20) NOT NULL,
    contacts_id bigint(20) NOT NULL,
    CONSTRAINT fk_dentist FOREIGN KEY (dentist_id) REFERENCES dentist(id),
    CONSTRAINT fk_contacts FOREIGN KEY (contacts_id) REFERENCES contact(id)
);
