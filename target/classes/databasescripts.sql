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
    "VISA_TYPE" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_BY_USER" character varying COLLATE pg_catalog."default",
    "WORK_EXPERIENCE" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    "VISA_NO" character varying COLLATE pg_catalog."default",
    "VALID_UPTO" date,
    "FIRST_NAME" character varying COLLATE pg_catalog."default",
    "MIDDLE_NAME" character varying COLLATE pg_catalog."default",
    "LAST_NAME" character varying COLLATE pg_catalog."default",
    CONSTRAINT "CANDIDATE_PERSONAL_pkey" PRIMARY KEY ("CANDIDATE_ID")
)

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
    "FIRST_NAME" character varying COLLATE pg_catalog."default",
    "MIDDLE_NAME" character varying COLLATE pg_catalog."default",
    "LAST_NAME" character varying COLLATE pg_catalog."default",
    "VISA_TYPE" character varying COLLATE pg_catalog."default",
    "VISA_NO" character varying COLLATE pg_catalog."default",
    "VISA_VALID_DATE" date,
    CONSTRAINT "EMPLOYEE_pkey" PRIMARY KEY ("EMPLOYEE_ID")
)
-- Table: parse."CONTRACTOR_PERSONAL_DETAILS"

-- DROP TABLE parse."CONTRACTOR_PERSONAL_DETAILS";

CREATE TABLE parse."CONTRACTOR_PERSONAL_DETAILS"
(
    "CONTRACTOR_ID" bigint NOT NULL,
    "FIRST_NAME" character varying COLLATE pg_catalog."default",
    "MIDDLE_NAME" character varying COLLATE pg_catalog."default",
    "LAST_NAME" character varying COLLATE pg_catalog."default",
    "GENDER" character varying COLLATE pg_catalog."default",
    "PERSONAL_EMAIL" character varying COLLATE pg_catalog."default",
    "EMAIL2" character varying COLLATE pg_catalog."default",
    "MOBILE_PHONE" character varying COLLATE pg_catalog."default",
    "HOME_PHONE" character varying COLLATE pg_catalog."default",
    "PREVIOUS_NAME" character varying COLLATE pg_catalog."default",
    "ADDRESS" character varying COLLATE pg_catalog."default",
    "CITY" character varying COLLATE pg_catalog."default",
    "STATE" character varying COLLATE pg_catalog."default",
    "COUNTRY" character varying COLLATE pg_catalog."default",
    "OTHER_COUNTRY" character varying COLLATE pg_catalog."default",
    "VISA_CATEGORY" character varying COLLATE pg_catalog."default",
    "VISA_TYPE" character varying COLLATE pg_catalog."default",
    "EMERGENCY_CONTACT_NAME" character varying COLLATE pg_catalog."default",
    "EMERGENCY_CONTACT_NO" character varying COLLATE pg_catalog."default",
    "EMERGENCY_CONTACT_ADDRESS" character varying COLLATE pg_catalog."default",
    "EMERGENCY_CONTACT_EMAIL" character varying COLLATE pg_catalog."default",
    "EMERGENCY_CONTACT_RELATION" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_USER" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    "ZIP_CODE" bigint,
    "DATE_OF_BIRTH" character varying COLLATE pg_catalog."default",
    "VISA_VALID_DATE" character varying COLLATE pg_catalog."default",
    "CONTRACTOR_FULL_NAME" character varying COLLATE pg_catalog."default",
    "ABN_HOLDER" character varying COLLATE pg_catalog."default",
    "OTHER_STATE" character varying COLLATE pg_catalog."default",
    CONSTRAINT "CONTRACTOR_PERSONAL_DETAILS_pkey" PRIMARY KEY ("CONTRACTOR_ID")
)
-- Table: parse."CONTRACTOR_ABN_DETAILS"

-- DROP TABLE parse."CONTRACTOR_ABN_DETAILS";

CREATE TABLE parse."CONTRACTOR_ABN_DETAILS"
(
    "ID" bigint NOT NULL,
    "CONTRACTOR_ID" bigint,
    "ABN_NUMBER" character varying COLLATE pg_catalog."default",
    "ACN_NUMBER" character varying COLLATE pg_catalog."default",
    "COMPANY_NAME" character varying COLLATE pg_catalog."default",
    "COMPANY_ADDRESS" character varying COLLATE pg_catalog."default",
    "COMPANY_CITY" character varying COLLATE pg_catalog."default",
    "COMPANY_STATE" character varying COLLATE pg_catalog."default",
    "COMPANY_ZIP_CODE" bigint,
    "ABN_GROUP" character varying COLLATE pg_catalog."default",
    "GST_REGISTERED" character varying COLLATE pg_catalog."default",
    "GST_CERTIFICATE_PATH" character varying COLLATE pg_catalog."default",
    "PI_PL_FLAG" character varying COLLATE pg_catalog."default",
    "PI_PL_CERT1_PATH" character varying COLLATE pg_catalog."default",
    "PI_PL_CERT2_PATH" character varying COLLATE pg_catalog."default",
    "PI_PL_CERT3_PATH" character varying COLLATE pg_catalog."default",
    "WORK_COVER_FLAG" character varying COLLATE pg_catalog."default",
    "WORK_COVER_CERT_PATH" character varying COLLATE pg_catalog."default",
    "ADDITIONAL_INFO" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_USER" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    "ACTIVE_RECORD" character varying COLLATE pg_catalog."default",
    CONSTRAINT "CONTRACTOR_ABN_FKEY" FOREIGN KEY ("CONTRACTOR_ID")
        REFERENCES parse."CONTRACTOR_PERSONAL_DETAILS" ("CONTRACTOR_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
-- Table: parse."CONTRACTOR_BANK_DETAILS"

-- DROP TABLE parse."CONTRACTOR_BANK_DETAILS";

CREATE TABLE parse."CONTRACTOR_BANK_DETAILS"
(
    "ID" bigint NOT NULL,
    "CONTRACTOR_ID" bigint,
    "ACCOUNT_NAME" character varying COLLATE pg_catalog."default" NOT NULL,
    "BSB" character varying COLLATE pg_catalog."default" NOT NULL,
    "ACCOUNT_NUMBER" character varying COLLATE pg_catalog."default" NOT NULL,
    "ADDITIONAL_INFO" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_USER" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    "ACTIVE_RECORD" character varying COLLATE pg_catalog."default",
    CONSTRAINT "CONTRACTOR_BANK_DETAILS_pkey" PRIMARY KEY ("ID"),
    CONSTRAINT "CONTRACTOR_BANK_FKEY" FOREIGN KEY ("CONTRACTOR_ID")
        REFERENCES parse."CONTRACTOR_PERSONAL_DETAILS" ("CONTRACTOR_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
-- Table: parse."CONTRACTOR_EMPLOYMENT_DETAILS"

-- DROP TABLE parse."CONTRACTOR_EMPLOYMENT_DETAILS";

CREATE TABLE parse."CONTRACTOR_EMPLOYMENT_DETAILS"
(
    "ID" bigint NOT NULL,
    "CONTRACTOR_ID" bigint,
    "CLIENT_NAME" character varying COLLATE pg_catalog."default",
    "END_CLIENT_NAME" character varying COLLATE pg_catalog."default",
    "CONTRACT_NUMBER" character varying COLLATE pg_catalog."default",
    "WORK_LOCATION_ADDRESS" character varying COLLATE pg_catalog."default",
    "WORK_LOCATION_CITY" character varying COLLATE pg_catalog."default",
    "WORK_LOCATION_STATE" character varying COLLATE pg_catalog."default",
    "WORK_LOCATION_ZIP_CODE" bigint,
    "WORK_LOCATION_COUNTRY" character varying COLLATE pg_catalog."default",
    "EMPLOYMENT_TYPE" character varying COLLATE pg_catalog."default",
    "JOB_ROLE" character varying COLLATE pg_catalog."default",
    "JOB_START_DATE" character varying COLLATE pg_catalog."default",
    "JOB_END_DATE" character varying COLLATE pg_catalog."default",
    "LAST_WORKING_DATE" character varying COLLATE pg_catalog."default",
    "FINISHED_CLIENT" character varying COLLATE pg_catalog."default",
    "ADDITIONAL_INFO" character varying COLLATE pg_catalog."default",
    "ACTIVE_RECORD" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_USER" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    "RECRUITER_ID" bigint,
    "RECRUITER_NAME" character varying COLLATE pg_catalog."default",
    "WL_OTHER_STATE" character varying COLLATE pg_catalog."default",
    "WL_OTHER_COUNTRY" character varying COLLATE pg_catalog."default",
    CONSTRAINT "CONTRACTOR_EMPLOYMENT_DETAILS_pkey" PRIMARY KEY ("ID"),
    CONSTRAINT "CONTRACTOR_EMPLOYMENT_FKEY" FOREIGN KEY ("CONTRACTOR_ID")
        REFERENCES parse."CONTRACTOR_PERSONAL_DETAILS" ("CONTRACTOR_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
-- Table: parse."CONTRACTOR_RATE_DETAILS"

-- DROP TABLE parse."CONTRACTOR_RATE_DETAILS";

CREATE TABLE parse."CONTRACTOR_RATE_DETAILS"
(
    "ID" bigint NOT NULL,
    "CONTRACTOR_ID" bigint,
    "RATE_PER_DAY" double precision,
    "RATE_START_DATE" character varying COLLATE pg_catalog."default",
    "RATE_END_DATE" character varying COLLATE pg_catalog."default",
    "INCLUDE_SUPER_FLAG" character varying COLLATE pg_catalog."default",
    "BILL_RATE_PER_DAY" double precision,
    "PAYROLL_TAX_PAYMENT_FLAG" character varying COLLATE pg_catalog."default",
    "INSURANCE_PAYMENT_FLAG" character varying COLLATE pg_catalog."default",
    "INSURANCE_PERCENTAGE" double precision,
    "OTHER_DEDUCTION_PERCENTAGE" double precision,
    "OTHER_DEDUCTION_AMOUNT" double precision,
    "NET_MARGIN" double precision,
    "ACTIVE_RECORD" character varying COLLATE pg_catalog."default",
    "ADDITIONAL_INFO" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_USER" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    "INSURANCE_TYPE" character varying COLLATE pg_catalog."default",
    "REFERRAL_COMM_TYPE" character varying COLLATE pg_catalog."default",
    "REFERRAL_COMM_VALUE" double precision,
    "GROSS_MARGIN" double precision,
    "PAYROLL_TAX_PERCENTAGE" double precision,
    CONSTRAINT "CONTRACTOR_RATE_DETAILS_pkey" PRIMARY KEY ("ID"),
    CONSTRAINT "CONTRACTOR_RATE_FKEY" FOREIGN KEY ("CONTRACTOR_ID")
        REFERENCES parse."CONTRACTOR_PERSONAL_DETAILS" ("CONTRACTOR_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

-- Table: parse."CONTRACTOR_SUPER_ANNUATION_DETAILS"

-- DROP TABLE parse."CONTRACTOR_SUPER_ANNUATION_DETAILS";

CREATE TABLE parse."CONTRACTOR_SUPER_ANNUATION_DETAILS"
(
    "CONTRACTOR_ID" bigint,
    "SUPER_ANNUATION_FUND_NAME" character varying COLLATE pg_catalog."default",
    "SUPER_ANNUATION_MEMBER_ID" character varying COLLATE pg_catalog."default",
    "ADDITIONAL_SUPER_ANNUATION_CONTRIBUTION_FLAG" character varying COLLATE pg_catalog."default",
    "ADDITIONAL_SUPER_ANNUATION_DETAILS" character varying COLLATE pg_catalog."default",
    "ADDITIONAL_INFO" character varying COLLATE pg_catalog."default",
    "ACTIVE_RECORD" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_USER" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    "ID" bigint NOT NULL,
    CONSTRAINT "CONTRACTOR_SUPER_ANNUATION_DETAILS_pkey" PRIMARY KEY ("ID"),
    CONSTRAINT "CONTRACTOR_SUPER_ANNUATION_FKEY" FOREIGN KEY ("CONTRACTOR_ID")
        REFERENCES parse."CONTRACTOR_PERSONAL_DETAILS" ("CONTRACTOR_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

-- Table: parse."CONTRACTOR_TFN_DETAILS"

-- DROP TABLE parse."CONTRACTOR_TFN_DETAILS";

)
CREATE TABLE parse."CONTRACTOR_TFN_DETAILS"
(
    "ID" bigint NOT NULL,
    "CONTRACTOR_ID" bigint,
    "NEW_APPLICATION_FLAG" character varying COLLATE pg_catalog."default",
    "UNDER_AGE_EXEMPTION_FLAG" character varying COLLATE pg_catalog."default",
    "PENSION_HOLDER_FLAG" character varying COLLATE pg_catalog."default",
    "EMPLOYMENT_TYPE" character varying COLLATE pg_catalog."default",
    "TAX_PAYER_TYPE" character varying COLLATE pg_catalog."default",
    "TAX_FREE_THRESHOLD_FLAG" character varying COLLATE pg_catalog."default",
    "LOAN_FLAG" character varying COLLATE pg_catalog."default",
    "FINANCIAL_SHIPMENT_DEBT_FLAG" character varying COLLATE pg_catalog."default",
    "ADDITIONAL_INFO" character varying COLLATE pg_catalog."default",
    "ACTIVE_RECORD" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_USER" character varying COLLATE pg_catalog."default",
    "LAST_UPDATED_DATE_TIME" timestamp without time zone,
    "TFN_NUMBER" character varying COLLATE pg_catalog."default",
    CONSTRAINT "CONTRACTOR_TFN_DETAILS_pkey" PRIMARY KEY ("ID"),
    CONSTRAINT "CONTRACTOR_TFN_FKEY" FOREIGN KEY ("CONTRACTOR_ID")
        REFERENCES parse."CONTRACTOR_PERSONAL_DETAILS" ("CONTRACTOR_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE parse."APP_CONSTANTS"
(
    "ID" smallint NOT NULL,
    "CONSTANT_NAME" character varying COLLATE pg_catalog."default" NOT NULL,
    "CONSTANT_VALUE" double precision NOT NULL,
    CONSTRAINT "APP_CONSTANTS_pkey" PRIMARY KEY ("ID")
)

-- DROP SEQUENCE parse.app_constants_sequence;

CREATE SEQUENCE parse.app_constants_sequence
    INCREMENT 5
    START 86
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- DROP SEQUENCE parse.candidate_education_sequence;

CREATE SEQUENCE parse.candidate_education_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    
    -- DROP SEQUENCE parse.candidate_general_sequence;

CREATE SEQUENCE parse.candidate_general_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    
    -- DROP SEQUENCE parse.candidate_personal_sequence;

CREATE SEQUENCE parse.candidate_personal_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    
    -- DROP SEQUENCE parse.candidate_profile_sequence;

CREATE SEQUENCE parse.candidate_profile_sequence
    INCREMENT 5
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    -- DROP SEQUENCE parse.candidate_work_sequence;

CREATE SEQUENCE parse.candidate_work_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    -- DROP SEQUENCE parse.contractor_abn_sequence;

CREATE SEQUENCE parse.contractor_abn_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    
    -- DROP SEQUENCE parse.contractor_bank_sequence;

CREATE SEQUENCE parse.contractor_bank_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    -- DROP SEQUENCE parse.contractor_employment_sequence;

CREATE SEQUENCE parse.contractor_employment_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    -- DROP SEQUENCE parse.contractor_personal_sequence;

CREATE SEQUENCE parse.contractor_personal_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    -- DROP SEQUENCE parse.contractor_rate_sequence;

CREATE SEQUENCE parse.contractor_rate_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    -- DROP SEQUENCE parse.contractor_super_annuation_sequence;

CREATE SEQUENCE parse.contractor_super_annuation_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    -- DROP SEQUENCE parse.contractor_tfn_sequence;

CREATE SEQUENCE parse.contractor_tfn_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    -- DROP SEQUENCE parse.employee_id_sequence;

CREATE SEQUENCE parse.employee_id_sequence
    INCREMENT 5
    START 1010
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    
    