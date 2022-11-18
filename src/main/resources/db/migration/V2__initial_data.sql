---------------------------------- Project-------------------------
INSERT INTO Project (ID, name, jira_id, read_role_id, write_role_id, admin_role_id, sp_application_id,sp_instance_id, sp_project_id,sp_read_role_id, sp_write_id,sp_admin_role_id)
 VALUES (default, 'CTRX Radar Family [CTRXFAMILY]', '68500', '11800', '10001', '10002','11','15','411600','337980323','337980322','337980329');

---------------------------------- Requester-------------------------
INSERT INTO Requester (ID, user_name, email, full_name, password,role)
VALUES (default, 'elshayeb', 'Elshayeb.external@infineon.com','Elshayeb Ayman (IFAT DCL ATV SC D RAD PJM)','password123','MANAGER');

INSERT INTO Requester (ID, user_name, email, full_name, password,role)
VALUES (default, 'Kampfer', 'Elshayeb.external@infineon.com','Stefan Kampfer','password123','MEMBER');

INSERT INTO Requester (ID, user_name, email, full_name, password,role)
VALUES (default, 'Scheiblh', 'Elshayeb.external@infineon.com','Klaus Dieter Scheibl hofer','password123','MEMBER');


INSERT INTO Requester (ID, user_name, email, full_name, password,role)
VALUES (default, 'marktlth', 'Elshayeb.external@infineon.com','Thomas Marktl','password123','MEMBER');

--------------------------------------System Access----------------------------------
INSERT INTO System_Access (ID, system_name, access_permission) VALUES (1, 'JIRA', 'READ');
INSERT INTO System_Access (ID, system_name, access_permission) VALUES (2, 'JIRA', 'WRITE');
INSERT INTO System_Access (ID, system_name, access_permission) VALUES (3, 'JIRA', 'ADMIN');
INSERT INTO System_Access (ID, system_name, access_permission) VALUES (4, 'SVN', 'READ');
INSERT INTO System_Access (ID, system_name, access_permission) VALUES (5, 'SVN', 'WRITE');
INSERT INTO System_Access (ID, system_name, access_permission) VALUES (6, 'SVN', 'ADMIN');

---------------------------------- Project Role-------------------------
INSERT INTO PROJECT_ROLE (ID, requester_id, project_id,role) VALUES (1, 1,1,'MANAGER');
------------------------------ Request-------------------------------------
INSERT INTO Request (ID, requester_Id, system_access_id, status, project_id, creation_date , last_modified_date, last_modifier_Id)
VALUES (default, 2, 1, 'DRAFT',1,'2022-10-20','2022-10-21', 2);

INSERT INTO Request (ID, requester_Id, system_access_id, status, project_id, creation_date , last_modified_date, last_modifier_Id)
VALUES (default, 3, 2, 'PENDING',1,'2022-10-20','2022-10-21', 3);

INSERT INTO Request (ID, requester_Id, system_access_id, status, project_id, creation_date , last_modified_date, last_modifier_Id)
VALUES (default, 4, 3, 'COMPLETED',1,'2022-10-20','2022-10-21', 1);

