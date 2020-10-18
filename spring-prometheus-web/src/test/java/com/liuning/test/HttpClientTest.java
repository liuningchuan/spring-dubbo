package com.liuning.test;

import com.liuning.service.user.UserService;
import com.liuning.web.StartApplication;
import com.liuning.web.http.HttpClientAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StartApplication.class})
public class HttpClientTest {

    @Autowired
    HttpClientAdapter httpClientAdapter;

    @Autowired
    UserService userService;

    @Test
    public void test() {
        userService.insertUser();
    }

    @Test
    public void test2() {
        userService.insertUsers();
    }

    @Test
    public void doGet() {
        httpClientAdapter.get("http://127.0.0.1:8080/hello");
    }
}
