package com.ubs.qa.jira.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JiraService {

    private static Logger LOG = LoggerFactory.getLogger(JiraService.class);

    @Value("${jira.session.endpoint}")
    private String jiraSessionAPI;

    @Value("${user.name}")
    private String userName;

    @Value("${user.password}")
    private String userPassword;

    @Autowired
    private OkHttpClient okHttpClient;

    public String createSession() throws IOException {
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                new Credentials(userName, userPassword).toJson());

        Request request = new Request.Builder()
                .url(jiraSessionAPI)
                .post(body)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        LOG.info("Request = " + request.body().toString());
        LOG.info("Response = " + response.body().string());

        return response.body().toString();
    }

    private class Credentials {
        private String userName;
        private String userPassword;

        public Credentials(String userName, String userPassword) {
            this.userName = userName;
            this.userPassword = userPassword;
        }

        public String toJson() throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }
    }

}
