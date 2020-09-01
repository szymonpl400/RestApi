CREATE TABLE "user"(
	id bigserial PRIMARY KEY UNIQUE NOT NULL,
	uuid UUID NOT NULL UNIQUE,
	login VARCHAR( 255 ) UNIQUE NOT NULL,
	firstname VARCHAR( 255 ) NOT NULL,
	lastname VARCHAR( 255 ) NOT NULL,
	account_type VARCHAR( 50 ) NOT NULL CHECK (account_type IN('ADMINISTRATOR', 'MANAGER', 'EMPLOYEE')),
	password VARCHAR( 255 ) NOT NULL,
	email VARCHAR( 254 ) NOT NULL,
	cost_per_hour NUMERIC(20,2) NOT NULL Check ( cost_per_hour > 0 )
);

CREATE TABLE project (
  	id bigserial PRIMARY KEY UNIQUE NOT NULL,
  	uuid UUID NOT NULL UNIQUE,
  	name VARCHAR( 255 ) UNIQUE NOT NULL,
  	description VARCHAR( 255 ),
    beginning DATE Check ( beginning < finishing ) NOT NULL,
    finishing DATE Check ( beginning < finishing ) NOT NULL,
	budget NUMERIC(20,2) NOT NULL Check ( budget > 0 ) NOT NULL
);

CREATE INDEX idx_project_beginning ON project(beginning);
CREATE INDEX idx_project_finishing ON project(finishing);

CREATE TABLE user_project (
    user_id bigint NOT NULL,
    project_id bigint NOT NULL,
    PRIMARY KEY (user_id,project_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id) on update cascade on delete cascade,
    FOREIGN KEY (project_id) REFERENCES project (id) on update cascade
);

CREATE TABLE timesheet (
    id bigserial PRIMARY KEY NOT NULL UNIQUE,
    uuid UUID NOT NULL UNIQUE,
    user_id bigint NOT NULL,
    project_id bigint NOT NULL,
    starting TIMESTAMP Check ( starting < ending ) NOT NULL,
    ending TIMESTAMP Check ( starting < ending ) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (id) on update cascade on delete cascade,
    FOREIGN KEY (project_id) REFERENCES project (id) on update cascade on delete cascade
);

CREATE INDEX idx_timesheet_starting ON timesheet(starting);
CREATE INDEX idx_timesheet_ending ON timesheet(ending);

CREATE VIEW userView AS
SELECT
        id,
        uuid,
        login

FROM "user";

CREATE VIEW projectView AS
SELECT
        row_number() over () as pvId,
        p.id,
        p.uuid,
        name,
        description,
        beginning,
        finishing,
        budget,

    CASE
        WHEN (p.budget < (SELECT  (SUM((extract(epoch from ending - starting) / 3600 ) * u.cost_per_hour)) as total_cost from timesheet t join "user" u on t.user_id=u.id where t.project_id = p.id))
        THEN TRUE
        ELSE FALSE
    END AS isExceeded
FROM project p;


CREATE VIEW project_members_view AS
    SELECT
                project_id,
                user_id
    FROM    user_project;


select * from projectView;

Drop View userView;