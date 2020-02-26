-- Database: parser

-- DROP DATABASE parser;

CREATE DATABASE parser
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
  --  LC_COLLATE = 'English_India.1252'
  --  LC_CTYPE = 'English_India.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
    -- SCHEMA: parse

-- DROP SCHEMA parse ;

CREATE SCHEMA parse
    AUTHORIZATION postgres;
    
    -- Table: parse."CANDIDATE_PERSONAL"

-- DROP TABLE parse."CANDIDATE_PERSONAL";

CREATE TABLE parse."CANDIDATE_PERSONAL"
(
    "CANDIDATE_ID" bigint NOT NULL,
    "CANDIDATE_NAME" character varying COLLATE pg_catalog."default" NOT NULL,
    "PRIMARY_EMAIL" character varying COLLATE pg_catalog."default" NOT NULL,
    "SECONDARY_EMAIL" character varying COLLATE pg_catalog."default",
    "PRIMARY_PHONE" character varying COLLATE pg_catalog."default" NOT NULL,
    "SECONDARY_PHONE" character varying COLLATE pg_catalog."default",
    "TITLE" character varying COLLATE pg_catalog."default",
    "GENDER" character varying COLLATE pg_catalog."default",
    "CURRENT_LOCATION" character varying COLLATE pg_catalog."default",
    "DATE_OF_BIRTH" character varying COLLATE pg_catalog."default",
    "SOCIAL_MEDIA_LINK" character varying COLLATE pg_catalog."default",
    "NATIONALITY" character varying COLLATE pg_catalog."default",
    "VISA" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_BY_USER" character varying COLLATE pg_catalog."default",
    "WORK_EXPERIENCE" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    CONSTRAINT "CANDIDATE_PERSONAL_pkey" PRIMARY KEY ("CANDIDATE_ID")
)

TABLESPACE pg_default;

COMMENT ON TABLE parse."CANDIDATE_PERSONAL"
    IS 'Stores personal data of candidate';
    
    -- Table: parse."CANDIDATE_EDUCATION"

-- DROP TABLE parse."CANDIDATE_EDUCATION";

CREATE TABLE parse."CANDIDATE_EDUCATION"
(
    "CANDIDATE_ID" bigint,
    "EDUCATION" character varying COLLATE pg_catalog."default",
    "AWARDS" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_BY_USER" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    "EDUCATION_ID" smallint NOT NULL,
    CONSTRAINT "CANDIDATE_EDUCATION_pkey" PRIMARY KEY ("EDUCATION_ID"),
    CONSTRAINT "CANDIDATE_EDUCATION_FKEY" FOREIGN KEY ("CANDIDATE_ID")
        REFERENCES parse."CANDIDATE_PERSONAL" ("CANDIDATE_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

-- Table: parse."CANDIDATE_PROFILE"

-- DROP TABLE parse."CANDIDATE_PROFILE";

CREATE TABLE parse."CANDIDATE_PROFILE"
(
    "CANDIDATE_ID" bigint,
    "VERSION" smallint,
    "FILE_PATH" character varying COLLATE pg_catalog."default",
    "AVAILABILITY" character varying COLLATE pg_catalog."default",
    "REFERENCES" character varying COLLATE pg_catalog."default",
    "ADDITIONAL_NOTES" text COLLATE pg_catalog."default",
    "ASSIGNED_TO_EMPLOYEE_ID" bigint,
    "ASSIGNED_TO_EMPLOYEE_NAME" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_BY_USER" character varying COLLATE pg_catalog."default",
    "EMPLOYED_BY_REN" character varying COLLATE pg_catalog."default",
    "ASSIGNED_DATE" date,
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    "PROFILE_TEXT" text COLLATE pg_catalog."default",
    "SKILLS" character varying COLLATE pg_catalog."default",
    "CERTIFICATION" character varying COLLATE pg_catalog."default",
    "PROFILE_ID" smallint NOT NULL,
    CONSTRAINT "CANDIDATE_PROFILE_pkey" PRIMARY KEY ("PROFILE_ID"),
    CONSTRAINT "CANDIDATE_PROFILE_FKEY" FOREIGN KEY ("CANDIDATE_ID")
        REFERENCES parse."CANDIDATE_PERSONAL" ("CANDIDATE_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

-- Table: parse."CANDIDATE_WORK_HISTORY"

-- DROP TABLE parse."CANDIDATE_WORK_HISTORY";

CREATE TABLE parse."CANDIDATE_WORK_HISTORY"
(
    "CANDIDATE_ID" bigint,
    "ORGANIZATION" character varying COLLATE pg_catalog."default",
    "DESIGNATION" character varying COLLATE pg_catalog."default",
    "WORK_START_DATE" date,
    "WORK_END_DATE" date,
    "LAST_UPDATED_BY_USER" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    "WORK_ID" smallint NOT NULL,
    CONSTRAINT "CANDIDATE_WORK_HISTORY_pkey" PRIMARY KEY ("WORK_ID"),
    CONSTRAINT "CANDIDATE_WORK_HISTORY_FKEY" FOREIGN KEY ("CANDIDATE_ID")
        REFERENCES parse."CANDIDATE_PERSONAL" ("CANDIDATE_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

-- Table: parse."EMPLOYEE"

-- DROP TABLE parse."EMPLOYEE";

CREATE TABLE parse."EMPLOYEE"
(
    "EMPLOYEE_ID" smallint NOT NULL,
    "EMPLOYEE_NAME" character varying COLLATE pg_catalog."default",
    "EMPLOYEE_ROLE" character varying COLLATE pg_catalog."default",
    "EMAIL" character varying COLLATE pg_catalog."default",
    "CONTACT_NO" character varying COLLATE pg_catalog."default",
    "ADDRESS" character varying COLLATE pg_catalog."default",
    "JOINING_DATE" date,
    "LAST_UPDATED_BY_USER" character varying COLLATE pg_catalog."default",
    "WORK_END_DATE" date,
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    "PROFILE_PARSER_APP_PWD" character varying COLLATE pg_catalog."default",
    "PROFILE_PARSER_APP_LOGIN" character varying COLLATE pg_catalog."default",
    CONSTRAINT "EMPLOYEE_pkey" PRIMARY KEY ("EMPLOYEE_ID")
)

TABLESPACE pg_default;


-- SEQUENCE: parse.candidate_education_sequence

-- DROP SEQUENCE parse.candidate_education_sequence;

CREATE SEQUENCE parse.candidate_education_sequence
    INCREMENT 1
    START 32
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
-- SEQUENCE: parse.candidate_general_sequence

-- DROP SEQUENCE parse.candidate_general_sequence;

CREATE SEQUENCE parse.candidate_general_sequence
    INCREMENT 1
    START 29
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

    -- SEQUENCE: parse.candidate_personal_sequence

-- DROP SEQUENCE parse.candidate_personal_sequence;

CREATE SEQUENCE parse.candidate_personal_sequence
    INCREMENT 1
    START 60
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

    -- SEQUENCE: parse.candidate_profile_sequence

-- DROP SEQUENCE parse.candidate_profile_sequence;

CREATE SEQUENCE parse.candidate_profile_sequence
    INCREMENT 5
    START 1166
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

    -- SEQUENCE: parse.candidate_work_sequence

-- DROP SEQUENCE parse.candidate_work_sequence;

CREATE SEQUENCE parse.candidate_work_sequence
    INCREMENT 1
    START 32
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- SEQUENCE: parse.employee_id_sequence

-- DROP SEQUENCE parse.employee_id_sequence;

CREATE SEQUENCE parse.employee_id_sequence
    INCREMENT 5
    START 1000
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


    
    