CREATE TABLE "user" (
	"id" bigserial NOT NULL,
	"group_id" bigserial NOT NULL,
	"username" character varying(255) NOT NULL,
	"user_uri" character varying(500),
	"last_name" character varying(255),
	"first_name" character varying(255),
	"middle_name" character varying(255),
	"vks_number" character varying(255),
	"mobile_phone" character varying(255),
	"work_phone" character varying(255),
	"org_name" character varying(500),
	"position_name" character varying(500),
	CONSTRAINT user_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "role" (
	"id" bigserial NOT NULL,
	"name" character varying(500) NOT NULL,
	"title" character varying(500),
	CONSTRAINT role_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "link_user_role" (
	"id" bigserial NOT NULL,
	"user_id" bigserial NOT NULL,
	"role_id" bigserial NOT NULL,
	CONSTRAINT link_user_role_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "access_to_group" (
	"id" bigserial NOT NULL,
	"role_id" bigserial NOT NULL,
	"group_id" bigserial NOT NULL,
	"access_level_id" bigserial NOT NULL,
	CONSTRAINT access_to_group_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "status" (
	"id" bigserial NOT NULL,
	"name" character varying(255) NOT NULL,
	CONSTRAINT status_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "link_user_status" (
	"id" bigserial NOT NULL,
	"user_id" bigserial NOT NULL,
	"status_id" bigserial NOT NULL,
	"updated_at" timestamp with time zone NOT NULL,
	CONSTRAINT link_user_status_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "history" (
	"id" bigserial NOT NULL,
	"user_id" bigserial NOT NULL,
	"caller_id" bigserial NOT NULL,
	"start_on" timestamp with time zone NOT NULL,
	"end_on" timestamp with time zone NOT NULL,
	"phone_type" character varying(255) NOT NULL,
	"call_type" character varying(255) NOT NULL,
	CONSTRAINT history_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "group" (
	"id" bigserial NOT NULL,
	"name" character varying NOT NULL,
	CONSTRAINT group_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "access_level" (
	"id" bigserial NOT NULL,
	"name" character varying(255) NOT NULL,
	CONSTRAINT access_level_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "favorite" (
	"id" bigserial NOT NULL,
	"user_id" bigserial NOT NULL,
	"favorite_id" bigserial NOT NULL,
	CONSTRAINT favorite_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "user" ADD CONSTRAINT user_fk0 FOREIGN KEY (group_id) REFERENCES "group" (id);


ALTER TABLE "link_user_role" ADD CONSTRAINT link_user_role_fk0 FOREIGN KEY (user_id) REFERENCES "user" (id);
ALTER TABLE "link_user_role" ADD CONSTRAINT link_user_role_fk1 FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE "access_to_group" ADD CONSTRAINT access_to_group_fk0 FOREIGN KEY (role_id) REFERENCES role(id);
ALTER TABLE "access_to_group" ADD CONSTRAINT access_to_group_fk1 FOREIGN KEY (group_id) REFERENCES "group" (id);
ALTER TABLE "access_to_group" ADD CONSTRAINT access_to_group_fk2 FOREIGN KEY (access_level_id) REFERENCES access_level(id);


ALTER TABLE "link_user_status" ADD CONSTRAINT link_user_status_fk0 FOREIGN KEY (user_id) REFERENCES "user" (id);
ALTER TABLE "link_user_status" ADD CONSTRAINT link_user_status_fk1 FOREIGN KEY (status_id) REFERENCES status(id);

ALTER TABLE "history" ADD CONSTRAINT history_fk0 FOREIGN KEY (user_id) REFERENCES "user"(id);
ALTER TABLE "history" ADD CONSTRAINT history_fk1 FOREIGN KEY (caller_id) REFERENCES "user"(id);



ALTER TABLE "favorite" ADD CONSTRAINT favorite_fk0 FOREIGN KEY (user_id) REFERENCES "user" (id);
ALTER TABLE "favorite" ADD CONSTRAINT favorite_fk1 FOREIGN KEY (favorite_id) REFERENCES "user" (id);
