package com.vossenix.asf_2.service;

import com.vossenix.asf_2.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class LookupService {

    private static final String GITHUB_USERS_URL = "https://api.github.com/users/%s";

    private final RestTemplate restTemplate;


    public LookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //Needs to be working async
    @Async
    public CompletableFuture<User> findUser(String user) throws InterruptedException {
        log.info("Looking up " + user);

        String url = String.format(GITHUB_USERS_URL, user);
        User results = restTemplate.getForObject(url, User.class);
        Thread.sleep(1000L);

        return CompletableFuture.completedFuture(results);

    }

}
