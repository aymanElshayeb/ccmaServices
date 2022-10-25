
CREATE TABLE System_Access (
                        ID bigint auto_increment,
                        systemID varchar(255),
                        accessID varchar(255)
);

CREATE TABLE Request (
                               ID bigint auto_increment,
                               RequesterID varchar(255),
                               SystemAccessID varchar(255),
                               statusID varchar(255),
                               projectID varchar(255),
                               subProjectID varchar(255)
);