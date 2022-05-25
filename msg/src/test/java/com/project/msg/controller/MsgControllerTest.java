package com.project.msg.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.*;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MsgControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void controller() throws FileNotFoundException {
        String body = this.restTemplate.getForObject("/gcon?keyword=customer&primaryKeyField=Long-id&field=Long-id-String-name",String.class);
        ResponseEntity responseEntity = this.restTemplate.getForEntity("/gcon?keyword=customer&primaryKeyField=Long-id&field=Long-id-String-name",String.class);

        System.out.println("body = " + body);
        System.out.println("responseEntity = " + responseEntity);


//파일 io











        Assertions.assertThat(body).contains("package");
    }

}
