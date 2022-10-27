
CREATE TABLE Project (
                           ID bigint auto_increment PRIMARY KEY,
                           name varchar(255),
                           jira_id varchar(255)
);

CREATE TABLE Requester (
                           ID bigint auto_increment PRIMARY KEY,
                           user_name varchar(255),
                           email varchar(255),
                           full_name varchar(255),
                           password varchar(255)
);


CREATE TABLE System_Access (
                        ID bigint auto_increment PRIMARY KEY,
                        system_name varchar(255),
                        access_permission varchar(255)
);


CREATE TABLE Project_Role (
                           ID bigint auto_increment  PRIMARY KEY,
                           role varchar(255),
                           requester_id bigint,
                           project_id bigint,
                           FOREIGN KEY (requester_id ) REFERENCES REQUESTER(ID),
                           FOREIGN KEY (project_id ) REFERENCES PROJECT(ID)
);

CREATE TABLE Requester_Access (
                              ID bigint auto_increment PRIMARY KEY,
                              requester_id bigint,
                              system_access_id bigint,
                              FOREIGN KEY (requester_id ) REFERENCES REQUESTER(ID),
                              FOREIGN KEY (system_access_id ) REFERENCES System_Access(ID)
);


CREATE TABLE Request (
    ID bigint auto_increment PRIMARY KEY ,
    requester_id bigint,
    system_access_id bigint,
    status varchar(255),
    project_id bigint,
    creation_date date,
    last_modified_date date,
    last_modifier_Id bigint,
    FOREIGN KEY (requester_id ) REFERENCES REQUESTER(ID),
    FOREIGN KEY (last_modifier_Id ) REFERENCES REQUESTER(ID),
    FOREIGN KEY (system_access_id ) REFERENCES System_Access(ID),
    FOREIGN KEY (project_id ) REFERENCES Project(ID)
);