package com.infenion.ccmaintegeration;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.ProjectRolesRestClient;
import com.atlassian.jira.rest.client.api.UserRestClient;
import com.atlassian.jira.rest.client.api.domain.ProjectRole;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class CcmaIntegerationApplication implements CommandLineRunner {
    @Value("${jira.url}")
    private String jiraURL;

    @Value("${jira.project}")
    private String jiraProject;

    @Value("${jira.superUserName}")
    private String userName ;
    @Value("${jira.password}")
    private String password;
    private static Logger LOG = LoggerFactory
            .getLogger(CcmaIntegerationApplication.class);
    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(CcmaIntegerationApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }
    @Override
    public void run(String... args) {
        displayProjectRolesAndActors();

        displayUserInfo("elshayeb");
    }

    private void displayUserInfo(String userName) {
        UserRestClient userRestClient = getUserRestClient();
        Promise<User> userPromise = userRestClient.getUser(userName);
        
    }

    private  void displayProjectRolesAndActors() {

        ProjectRolesRestClient projectRolesRestClient = getProjectRolesRestClient();


        try {
            URI projectUri = URI.create(jiraProject);
            Promise<Iterable<ProjectRole>> roleList = projectRolesRestClient.getRoles(projectUri);

            roleList.get().forEach((role ->  {
                System.out.println("\n role: " + role.getName());
                role.getActors().forEach(roleActor ->  System.out.println( " roleActor: " + roleActor.getName() + " DisplayName: "
                        + roleActor.getDisplayName() + "User : " + roleActor.getId()
                        + "User Email: "));
            }));


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private ProjectRolesRestClient getProjectRolesRestClient() {
        JiraRestClient restClient = getJiraRestClient();
        ProjectRolesRestClient projectRolesRestClient = restClient.getProjectRolesRestClient();
        return projectRolesRestClient;
    }
    private UserRestClient getUserRestClient() {
        JiraRestClient restClient = getJiraRestClient();
        UserRestClient userClient = restClient.getUserClient();
        return userClient;
    }

    private JiraRestClient getJiraRestClient() {
        URI jiraUri = URI.create(jiraURL);
        JiraRestClient restClient = new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(jiraUri, this.userName, this.password);
        return restClient;
    }
}
