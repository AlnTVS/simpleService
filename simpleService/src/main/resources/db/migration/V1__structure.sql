CREATE TABLE IF NOT EXISTS organization (
    id bigserial   NOT NULL,
    name varchar(127)   NOT NULL,
    full_name varchar(255)   NOT NULL,
    inn varchar(12)   NOT NULL,
    kpp varchar(9)   NOT NULL,
    phone varchar(15)   NULL,
    is_active boolean   NOT NULL,
    CONSTRAINT UX_organization_id PRIMARY KEY (
        id
     ),
    CONSTRAINT uc_organization_name UNIQUE (
        name
    ),
    CONSTRAINT uc_organization_full_name UNIQUE (
        full_name
    ),
    CONSTRAINT uc_organization_inn UNIQUE (
        inn
    ),
    CONSTRAINT uc_organization_kpp UNIQUE (
        kpp
    )
);
COMMENT ON TABLE organization IS 'Организация';

CREATE TABLE IF NOT EXISTS address (
    id bigserial   NOT NULL,
    address varchar(511)   NOT NULL,
    CONSTRAINT UX_address_id PRIMARY KEY (
        id
     ),
    CONSTRAINT uc_address_address UNIQUE (
        address
    )
);

CREATE TABLE IF NOT EXISTS organization_address (
    organization_id int   NOT NULL,
    address_id int   NOT NULL
);
COMMENT ON TABLE organization_address IS 'Join для связи организации и адреса';

CREATE TABLE IF NOT EXISTS office (
    id bigserial   NOT NULL,
    organization_id int   NOT NULL,
    name varchar(255)   NULL,
    phone varchar(15)   NULL,
    is_active boolean   NOT NULL,
    CONSTRAINT UX_office_id PRIMARY KEY (
        id
     ),
    CONSTRAINT uc_office_name UNIQUE (
        name
    )
);
COMMENT ON TABLE office IS 'Офис';

CREATE TABLE IF NOT EXISTS office_address (
    office_id int   NOT NULL,
    address_id int   NOT NULL
);
COMMENT ON TABLE office_address IS 'Join для связи офиса и адреса';

CREATE TABLE IF NOT EXISTS user (
    id bigserial   NOT NULL,
    office_id int   NOT NULL,
    first_name varchar(63)   NOT NULL,
    second_name varchar(63)   NULL,
    middle_name varchar(63)   NULL,
    last_name varchar(63)   NULL,
    citizenship_id int   NULL,
    phone varchar(15)   NULL,
    is_active boolean   NOT NULL,
    CONSTRAINT UX_user_id PRIMARY KEY (
        id
     )
);
COMMENT ON TABLE user IS 'Пользователь';

CREATE TABLE IF NOT EXISTS user_position (
    user_id int NOT NULL,
    position_id int NOT NULL
);
COMMENT ON TABLE user_position IS 'Join для связи пользователей и должностей';

CREATE TABLE IF NOT EXISTS position (
    id bigserial   NOT NULL,
    position_name varchar(255)   NOT NULL,
    CONSTRAINT UX_position_id PRIMARY KEY (
        id
     ),
    CONSTRAINT uc_position_position_name UNIQUE (
        position_name
    )
);
COMMENT ON TABLE position IS 'Позиция';

CREATE TABLE IF NOT EXISTS document (
    id bigserial   NOT NULL,
    number varchar(63)   NOT NULL,
    date date   NOT NULL,
    type_document_id int   NOT NULL,
    CONSTRAINT UX_document_id PRIMARY KEY (
        id
     ),
    CONSTRAINT uc_document_number UNIQUE (
        number
    )
);
COMMENT ON TABLE document IS 'Документ';

CREATE TABLE IF NOT EXISTS type_document (
    id bigserial   NOT NULL,
    code varchar(3)   NOT NULL,
    type varchar(127)   NOT NULL,
    CONSTRAINT UX_type_document_id PRIMARY KEY (
        id
     ),
    CONSTRAINT uc_type_document_code UNIQUE (
        code
    ),
    CONSTRAINT uc_type_document_type UNIQUE (
        type
    )
);
COMMENT ON TABLE type_document IS 'Тип документа';

CREATE TABLE IF NOT EXISTS citizenship (
    id bigserial   NOT NULL,
    code varchar(3)   NOT NULL,
    name varchar(127)   NOT NULL,
    CONSTRAINT UX_citizenship_id PRIMARY KEY (
        id
     ),
    CONSTRAINT uc_citizenship_code UNIQUE (
        code
    )
);
COMMENT ON TABLE citizenship IS 'Гражданство';

CREATE INDEX IX_organization_address_organization_id ON organization_address (organization_id);
ALTER TABLE organization_address ADD FOREIGN KEY(organization_id) REFERENCES organization (id);

CREATE INDEX IX_organization_address_address_id ON organization_address (address_id);
ALTER TABLE organization_address ADD FOREIGN KEY(address_id) REFERENCES address (id);

CREATE INDEX IX_office_organization_id ON office (organization_id);
ALTER TABLE office ADD FOREIGN KEY(organization_id) REFERENCES organization (id);

CREATE INDEX IX_office_address_office_id ON office_address (office_id);
ALTER TABLE office_address ADD FOREIGN KEY(office_id) REFERENCES office (id);

CREATE INDEX IX_office_address_address_id ON office_address (address_id);
ALTER TABLE office_address ADD FOREIGN KEY(address_id) REFERENCES address (id);

CREATE INDEX IX_user_office_id ON user (office_id);
ALTER TABLE user ADD FOREIGN KEY(office_id) REFERENCES office (id);

--CREATE INDEX IX_user_position_id ON user (position_id);
--ALTER TABLE user ADD FOREIGN KEY(position_id) REFERENCES position (id);

CREATE INDEX IX_user_position_user_id ON user_position (user_id);
ALTER TABLE user_position ADD FOREIGN KEY(user_id) REFERENCES user (id);

CREATE INDEX IX_user_position_position_id ON user_position (position_id);
ALTER TABLE user_position ADD FOREIGN KEY(position_id) REFERENCES position (id);

-- index не трубуется т.к. user.id уже является PK.
ALTER TABLE user ADD FOREIGN KEY(id) REFERENCES document (id);

CREATE INDEX IX_user_citizenship_id ON user (citizenship_id);
ALTER TABLE user ADD FOREIGN KEY(citizenship_id) REFERENCES citizenship (id);

CREATE INDEX IX_document_type_document_id ON document (type_document_id);
ALTER TABLE document ADD FOREIGN KEY(type_document_id) REFERENCES type_document (id);