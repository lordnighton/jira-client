package com.ubs.qa.jira.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(ClientApplication.class);

    @Value("${jira.session.endpoint}")
    private String jiraSessionAPI;

    @Autowired
    private OkHttpClient okHttpClient;

	@Override
	public void run(String... strings) throws Exception {
        LOG.info("EXECUTING : command line runner");

        Request request = new Request.Builder()
                .url(jiraSessionAPI)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        LOG.info(response.body().string());
	}

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}