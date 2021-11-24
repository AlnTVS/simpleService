CREATE TABLE IF NOT EXISTS "tb_organization" (
    "fl_id" bigserial   NOT NULL,
    "fl_name" varchar(127)   NOT NULL,
    "fl_full_name" varchar(255)   NOT NULL,
    "fl_inn" varchar(12)   NOT NULL,
    "fl_kpp" varchar(9)   NOT NULL,
    "fl_phone" varchar(15)   NULL,
    "fl_is_active" boolean   NOT NULL,
    CONSTRAINT "pk_tb_organization" PRIMARY KEY (
        "fl_id"
     ),
    CONSTRAINT "uc_tb_organization_fl_name" UNIQUE (
        "fl_name"
    ),
    CONSTRAINT "uc_tb_organization_fl_full_name" UNIQUE (
        "fl_full_name"
    ),
    CONSTRAINT "uc_tb_organization_fl_inn" UNIQUE (
        "fl_inn"
    ),
    CONSTRAINT "uc_tb_organization_fl_kpp" UNIQUE (
        "fl_kpp"
    )
);

CREATE TABLE IF NOT EXISTS "tb_address" (
    "fl_id" bigserial   NOT NULL,
    "fl_address" varchar(511)   NOT NULL,
    CONSTRAINT "pk_tb_address" PRIMARY KEY (
        "fl_id"
     ),
    CONSTRAINT "uc_tb_address_fl_address" UNIQUE (
        "fl_address"
    )
);

CREATE TABLE IF NOT EXISTS "tb_organization_address" (
    "fl_organization_id" int   NOT NULL,
    "fl_address_id" int   NOT NULL
);

CREATE TABLE IF NOT EXISTS "tb_office" (
    "fl_id" bigserial   NOT NULL,
    "fl_organization_id" int   NOT NULL,
    "fl_name" varchar(255)   NULL,
    "fl_phone" varchar(15)   NULL,
    "fl_is_active" boolean   NOT NULL,
    CONSTRAINT "pk_tb_office" PRIMARY KEY (
        "fl_id"
     ),
    CONSTRAINT "uc_tb_office_fl_name" UNIQUE (
        "fl_name"
    )
);

CREATE TABLE IF NOT EXISTS "tb_office_address" (
    "fl_office_id" int   NOT NULL,
    "fl_address_id" int   NOT NULL
);

CREATE TABLE IF NOT EXISTS "tb_user" (
    "fl_id" bigserial   NOT NULL,
    "fl_office_id" int   NOT NULL,
    "fl_first_name" varchar(63)   NOT NULL,
    "fl_second_name" varchar(63)   NULL,
    "fl_middle_name" varchar(63)   NULL,
    "fl_last_name" varchar(63)   NULL,
    "fl_position_id" int   NOT NULL,
    "fl_document_id" int   NULL,
    "fl_citizenship_id" int   NULL,
    "fl_phone" varchar(15)   NULL,
    "fl_is_active" boolean   NOT NULL,
    CONSTRAINT "pk_tb_user" PRIMARY KEY (
        "fl_id"
     )
);

CREATE TABLE IF NOT EXISTS "tb_position" (
    "fl_id" bigserial   NOT NULL,
    "fl_position_name" varchar(255)   NOT NULL,
    CONSTRAINT "pk_tb_position" PRIMARY KEY (
        "fl_id"
     ),
    CONSTRAINT "uc_tb_position_fl_position_name" UNIQUE (
        "fl_position_name"
    )
);

CREATE TABLE IF NOT EXISTS "tb_document" (
    "fl_id" bigserial   NOT NULL,
    "fl_number" varchar(63)   NOT NULL,
    "fl_date" date   NOT NULL,
    "fl_type_document_id" int   NOT NULL,
    CONSTRAINT "pk_tb_document" PRIMARY KEY (
        "fl_id"
     ),
    CONSTRAINT "uc_tb_document_fl_number" UNIQUE (
        "fl_number"
    )
);

CREATE TABLE IF NOT EXISTS "tb_type_document" (
    "fl_id" bigserial   NOT NULL,
    "fl_code" varchar(3)   NOT NULL,
    "fl_type" varchar(127)   NOT NULL,
    CONSTRAINT "pk_tb_type_document" PRIMARY KEY (
        "fl_id"
     ),
    CONSTRAINT "uc_tb_type_document_fl_code" UNIQUE (
        "fl_code"
    ),
    CONSTRAINT "uc_tb_type_document_fl_type" UNIQUE (
        "fl_type"
    )
);

CREATE TABLE IF NOT EXISTS "tb_citizenship" (
    "fl_id" bigserial   NOT NULL,
    "fl_code" varchar(3)   NOT NULL,
    "fl_name" varchar(127)   NOT NULL,
    CONSTRAINT "pk_tb_citizenship" PRIMARY KEY (
        "fl_id"
     ),
    CONSTRAINT "uc_tb_citizenship_fl_code" UNIQUE (
        "fl_code"
    )
);

ALTER TABLE "tb_organization_address" ADD CONSTRAINT "IX_tb_organization_address_fl_organization_id" FOREIGN KEY("fl_organization_id")
REFERENCES "tb_organization" ("fl_id");

ALTER TABLE "tb_organization_address" ADD CONSTRAINT "IX_tb_organization_address_fl_address_id" FOREIGN KEY("fl_address_id")
REFERENCES "tb_address" ("fl_id");

ALTER TABLE "tb_office" ADD CONSTRAINT "IX_tb_office_fl_organization_id" FOREIGN KEY("fl_organization_id")
REFERENCES "tb_organization" ("fl_id");

ALTER TABLE "tb_office_address" ADD CONSTRAINT "IX_tb_office_address_fl_office_id" FOREIGN KEY("fl_office_id")
REFERENCES "tb_office" ("fl_id");

ALTER TABLE "tb_office_address" ADD CONSTRAINT "IX_tb_office_address_fl_address_id" FOREIGN KEY("fl_address_id")
REFERENCES "tb_address" ("fl_id");

ALTER TABLE "tb_user" ADD CONSTRAINT "IX_tb_user_fl_office_id" FOREIGN KEY("fl_office_id")
REFERENCES "tb_office" ("fl_id");

ALTER TABLE "tb_user" ADD CONSTRAINT "IX_tb_user_fl_position_id" FOREIGN KEY("fl_position_id")
REFERENCES "tb_position" ("fl_id");

ALTER TABLE "tb_user" ADD CONSTRAINT "IX_tb_user_fl_document_id" FOREIGN KEY("fl_document_id")
REFERENCES "tb_document" ("fl_id");

ALTER TABLE "tb_user" ADD CONSTRAINT "IX_tb_user_fl_citizenship_id" FOREIGN KEY("fl_citizenship_id")
REFERENCES "tb_citizenship" ("fl_id");

ALTER TABLE "tb_document" ADD CONSTRAINT "IX_tb_document_fl_type_document_id" FOREIGN KEY("fl_type_document_id")
REFERENCES "tb_type_document" ("fl_id");

