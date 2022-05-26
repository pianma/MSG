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
        String body = this.restTemplate.getForObject("/gcon?keyword=customer&primaryKeyField=Long-id&field=Long-id-String-name", String.class);
        ResponseEntity responseEntity = this.restTemplate.getForEntity("/gcon?keyword=customer&primaryKeyField=Long-id&field=Long-id-String-name", String.class);

        Assertions.assertThat(body).contains("package");

        System.out.println("responseEntity = " + responseEntity);

        //파일 경로 생성 ( - 로 구분되게..? => 패키지경로 '-' 을 File.seperator로 변환)

        String path = "src"+File.separator
                        +"main"+File.separator
                        + "java"+File.separator
                        + "com"+File.separator          //패키지 경로 ~
                        + "project"+File.separator
                        + "target"+File.separator
                        + "controller"+ File.separator; //역할 경로

        File javaFile = new File(path);

        if (!javaFile.exists()) {

            javaFile.mkdirs();
            System.out.println("생성 경로= "+ javaFile.getPath());

        }else {
            System.out.println("이미 경로 존재");
        }


        try(
            BufferedWriter writer = new BufferedWriter(new FileWriter(path + "CustomerController.java")); //경로 + 파일명
                ) {

            writer.write(body);
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
