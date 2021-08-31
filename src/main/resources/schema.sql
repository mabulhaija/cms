------------------------------------------------------------------------------------------------------------------------
CREATE DATABASE pwc
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

------------------------------------------------------------------------------------------------------------------------
CREATE SCHEMA cms AUTHORIZATION postgres;
------------------------------------------------------------------------------------------------------------------------
CREATE TYPE cms.roles AS ENUM ('MANAGER', 'EMPLOYEE');

ALTER TYPE cms.roles OWNER TO postgres;

CREATE CAST (character varying AS cms.roles) WITH INOUT AS ASSIGNMENT;
------------------------------------------------------------------------------------------------------------------------
CREATE TYPE cms.status AS ENUM ('ACTIVE', 'INACTIVE');

ALTER TYPE cms.status OWNER TO postgres;

CREATE CAST (character varying AS cms.status) WITH INOUT AS ASSIGNMENT;
------------------------------------------------------------------------------------------------------------------------
CREATE TABLE cms.departments
(
   id serial,
   name text COLLATE pg_catalog."default" NOT NULL,
   description text COLLATE pg_catalog."default",
   added_by integer NOT NULL,
   added_date timestamp without time zone NOT NULL DEFAULT now(),
   PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE cms.departments  OWNER to postgres;

CREATE INDEX departments_name
    ON cms.departments USING btree
    (name COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
------------------------------------------------------------------------------------------------------------------------
CREATE TABLE cms.system_users
(
id serial,
user_name text not null UNIQUE,
password text not null,
department_id integer not null,
department_name text not null,
name text not null,
email text not null UNIQUE,
status cms.status NOT NULL DEFAULT 'ACTIVE'::cms.status,
role cms.roles NOT NULL DEFAULT 'EMPLOYEE'::cms.roles,
added_by integer NOT NULL,
added_date TIMESTAMP without time zone DEFAULT now(),

PRIMARY KEY (id),
CONSTRAINT fk_dept_id FOREIGN KEY(department_id)
	  REFERENCES cms.departments(id)
	  ON DELETE CASCADE
)
TABLESPACE pg_default;

ALTER TABLE cms.system_users OWNER to postgres;

CREATE INDEX systemusers_user_name
    ON cms.system_users USING btree
    (user_name COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE INDEX systemuser_dept_id
    ON cms.system_users USING btree
    (department_id ASC NULLS LAST)
    TABLESPACE pg_default;

 CREATE INDEX systemusers_name
    ON cms.system_users USING btree
    (name ASC NULLS LAST)
    TABLESPACE pg_default;
CREATE INDEX systemusers_name_dept_id
    ON cms.system_users USING btree
    (name COLLATE pg_catalog."default" ASC NULLS LAST, department_id ASC NULLS LAST)
    TABLESPACE pg_default;
------------------------------------------------------------------------------------------------------------------------
CREATE TABLE cms.projects
(
   id serial,
   name text COLLATE pg_catalog."default" NOT NULL,
   description text COLLATE pg_catalog."default",
   added_by integer NOT NULL,
   added_date timestamp without time zone NOT NULL DEFAULT now(),
   PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE cms.projects  OWNER to postgres;
------------------------------------------------------------------------------------------------------------------------

CREATE TABLE cms.project_users
(
   project_id integer not null,
   user_id integer not null,
   project_name text COLLATE pg_catalog."default" NOT NULL,
   user_email text COLLATE pg_catalog."default" NOT NULL,
   added_by integer NOT NULL,
   added_date timestamp without time zone NOT NULL DEFAULT now(),
   PRIMARY KEY (project_id,user_id),
   CONSTRAINT fk_usr_id FOREIGN KEY(user_id)
   	  REFERENCES cms.system_users(id)
   	  ON DELETE CASCADE,

   CONSTRAINT fk_prj_id FOREIGN KEY(project_id)
      REFERENCES cms.projects(id)
      ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE cms.project_users  OWNER to postgres;
------------------------------------------------------------------------------------------------------------------------