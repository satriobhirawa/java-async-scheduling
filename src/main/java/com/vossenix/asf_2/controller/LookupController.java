package com.vossenix.asf_2.controller;

import com.vossenix.asf_2.model.User;
import com.vossenix.asf_2.service.LookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class LookupController {

    private int userIndex = 0;

    @Autowired
    private LookupService lookupService;

    private static final List<String> usersList = new ArrayList<String>();

    static{
        usersList.add("pytorch");
        usersList.add("tensorflow");
        usersList.add("scikit-learn");
        usersList.add("spring-boot");
        usersList.add("spring-mvc");
        usersList.add("spring-security");
    }

    @Scheduled(fixedRate = 2000)
    public void scheduledTask() throws Exception{


        CompletableFuture<User> info = lookupService.findUser(usersList.get(userIndex));

        userIndex = (userIndex + 1) % usersList.size();
        log.info("--> " + info.get());
    }
}
