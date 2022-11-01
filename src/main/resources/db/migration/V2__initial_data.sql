---------------------------------- Project-------------------------
INSERT INTO Project (ID, name, jira_id, read_role_id, write_role_id, admin_role_id)
 VALUES (default, 'CTRX Radar Family [CTRXFAMILY]', '68500', '11800', '10001', '10002');

---------------------------------- Requester-------------------------
INSERT INTO Requester (ID, user_name, email, full_name, password)
VALUES (default, 'elshayeb', 'Elshayeb.external@infineon.com','Elshayeb Ayman (IFAT DCL ATV SC D RAD PJM)','password123');

INSERT INTO Requester (ID, user_name, email, full_name, password)
VALUES (default, 'lnzautomationuser', 'no.mail@nomail.nomail','LNZautomationuser None (IFL ATV SC D RAD PJM)','z.Cve%{6p.fjR%o');

--------------------------------------System Access----------------------------------
INSERT INTO System_Access (ID, system_name, access_permission) VALUES (1, 'JIRA', 'READ');
INSERT INTO System_Access (ID, system_name, access_permission) VALUES (2, 'JIRA', 'WRITE');
INSERT INTO System_Access (ID, system_name, access_permission) VALUES (3, 'JIRA', 'ADMIN');
INSERT INTO System_Access (ID, system_name, access_permission) VALUES (4, 'SVN', 'READ');
INSERT INTO System_Access (ID, system_name, access_permission) VALUES (5, 'SVN', 'WRITE');
INSERT INTO System_Access (ID, system_name, access_permission) VALUES (6, 'SVN', 'ADMIN');

---------------------------------- Project Role-------------------------
INSERT INTO PROJECT_ROLE (ID, requester_id, project_id,role) VALUES (1, 1,1,'MEMBER');
INSERT INTO PROJECT_ROLE (ID, requester_id, project_id,role) VALUES (2, 2,1,'MANAGER');

---------------------------------- Project ACCESS-------------------------
INSERT INTO Requester_Access (ID, requester_id, system_access_id) VALUES (1, 2,3);
INSERT INTO Requester_Access (ID, requester_id, system_access_id) VALUES (2, 2,6);

------------------------------ Request-------------------------------------
INSERT INTO Request (ID, requester_Id, system_access_id, status, project_id, creation_date , last_modified_date, last_modifier_Id)
VALUES (default, 2, 3, 'COMPLETED',1,'2022-10-20','2022-10-21', 2);

INSERT INTO Request (ID, requester_Id, system_access_id, status, project_id, creation_date , last_modified_date, last_modifier_Id)
VALUES (default, 2, 6, 'COMPLETED',1,'2022-10-20','2022-10-21', 2);

