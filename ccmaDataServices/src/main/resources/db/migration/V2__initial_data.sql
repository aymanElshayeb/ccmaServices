INSERT INTO System_Access (ID, systemID, accessID) VALUES (1, 'JIRA', 'READ ACCESS');
INSERT INTO System_Access (ID, systemID, accessID) VALUES (2, 'JIRA', 'WRITE ACCESS');
INSERT INTO System_Access (ID, systemID, accessID) VALUES (3, 'JIRA', 'ADMIN ACCESS');
INSERT INTO System_Access (ID, systemID, accessID) VALUES (4, 'SVN', 'READ ACCESS');
INSERT INTO System_Access (ID, systemID, accessID) VALUES (5, 'SVN', 'WRITE ACCESS');
INSERT INTO System_Access (ID, systemID, accessID) VALUES (6, 'SVN', 'ADMIN ACCESS');

INSERT INTO Request (ID, requesterID, systemAccessID, StatusID, projectID, subProjectID)
VALUES (1, 'elshayeb', 1, 'DRAFT','CTRX Rader Family',null);
INSERT INTO Request (ID, requesterID, systemAccessID, StatusID, projectID, subProjectID)
VALUES (2, 'LNZautomationuser', 3, 'DRAFT','CTRX Rader Family',null);