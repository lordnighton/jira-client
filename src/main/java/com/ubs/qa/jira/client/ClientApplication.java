package com.ubs.qa.jira.client;

import com.ubs.qa.jira.client.service.JiraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(ClientApplication.class);

    @Autowired
    private JiraService jiraService;

	@Override
	public void run(String... strings) throws Exception {
        LOG.info("EXECUTING : command line runner");

        String sessionID = jiraService.createSession();
        jiraService.createJiraIssue(sessionID);
	}

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}